package com.company.icvretail.web.documents.purchase;

import com.company.icvretail.entity.documents.purchasedoc.PurchaseProductLines;
import com.haulmont.cuba.core.app.UniqueNumbersService;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.icvretail.entity.documents.purchasedoc.Purchases;
import com.company.icvretail.entity.catalogs.Products;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.icvretail.entity.documents.purchasedoc.Purchases;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.DataGrid;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.components.actions.RemoveAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class PurchasesEdit extends AbstractEditor<Purchases> {
    @Named("productLinesTable.create")
    private CreateAction productLinesTableCreate;
    @Named("productLinesTable.edit")
    private EditAction productLinesTableEdit;
    @Named("productLinesTable.remove")
    private RemoveAction productLinesTableRemove;

    @Inject
    private Metadata metadata;
    @Inject
    private UniqueNumbersService unService;
    @Inject
    private CollectionDatasource<PurchaseProductLines, UUID> productLinesDs;
    @Inject
    private DataGrid<PurchaseProductLines> productLinesTable;

    @Override
    public void init(Map<String, Object> params) {
        productLinesTableCreate.setOpenType(WindowManager.OpenType.DIALOG);
        //productLinesTableEdit.setOpenType(WindowManager.OpenType.DIALOG);

        productLinesDs.addCollectionChangeListener(e -> calculateAmount());
        productLinesDs.addItemPropertyChangeListener(e -> calculateAmount());
    }

    @Override
    protected void postInit() {
        // sort and init next number on screen opening
        initNextEntryNum();

        // sort and init next number after each addition or deletion
        productLinesTableCreate.setAfterCommitHandler(entity -> {
            initNextEntryNum();
        });
        productLinesTableRemove.setAfterRemoveHandler(removedItems -> {
            fixEntryNumbers();
            initNextEntryNum();
        });
    }

    @Override
    protected void initNewItem(Purchases item) {
        item.setNumber(unService.getNextNumber("PurchasesNumber"));
        item.setDate(new Date());
    }

    private void calculateAmount() {
        BigDecimal total = BigDecimal.ZERO;
        for (PurchaseProductLines line : productLinesDs.getItems()) {
            BigDecimal amount = line.getPrice().multiply(line.getQuantity())
                    .setScale(2, BigDecimal.ROUND_HALF_UP); // always set scale to the same as defined in the database to avoid modification of the datasource after commit
            BigDecimal amountDiscount=BigDecimal.ZERO;
            if (line.getDiscount()!=null) {
                amountDiscount = amount.multiply(line.getDiscount()).divide(BigDecimal.valueOf(100));
            }
            line.setAmountDiscount(amountDiscount);
            line.setAmount(amount.subtract(amountDiscount));

            total = total.add(line.getAmount());
        }
        getItem().setTotalAmount(total);

    }
    public void onCreateLine(Component source) {
        openLookup(Products.class,
                items -> {
                    if (!items.isEmpty()) {
                        PurchaseProductLines productLines = metadata.create(PurchaseProductLines.class);
                        productLines.setPurchases(getItem());
                        Products product = (Products) items.iterator().next();
                        productLines.setProduct(product);
                        productLines.setQuantity(BigDecimal.ONE);
                        productLines.setPrice(product.getPrice());

                        Integer lastNum = productLinesDs.getItems().stream()
                                .map(PurchaseProductLines::getNumLine)
                                .max(Integer::compareTo)
                                .orElse(0);
                        productLines.setNumLine(lastNum+1);

                        productLinesDs.addItem(productLines);
                        productLinesTable.setSelected(productLines);
                        productLinesTable.requestFocus();
                    }
                },
                WindowManager.OpenType.DIALOG.height(400));


    }


    public void onCopyLine(Component source) {
        PurchaseProductLines curLine = productLinesTable.getSingleSelected();
        PurchaseProductLines newLiner = metadata.create(PurchaseProductLines.class);
        newLiner.setPurchases(getItem());
        if (curLine != null){
            newLiner.setProduct(curLine.getProduct());
            newLiner.setPrice(curLine.getPrice());
            newLiner.setQuantity(curLine.getQuantity());

            Integer lastNum = productLinesDs.getItems().stream()
                    .map(PurchaseProductLines::getNumLine)
                    .max(Integer::compareTo)
                    .orElse(0);
            newLiner.setNumLine(lastNum+1);
        }
        else{

        }

        productLinesDs.addItem(newLiner);
        productLinesTable.setSelected(newLiner);
        productLinesTable.requestFocus();
    }

    /* Process MoveUp/MoveDown */
    private void initNextEntryNum() {
        Integer lastNum = productLinesDs.getItems().stream()
                .map(PurchaseProductLines::getNumLine)
                .max(Integer::compareTo)
                .orElse(0);
        productLinesTableCreate.setInitialValues(ParamsMap.of("numLine", lastNum + 1));
    }
    private void fixEntryNumbers() {
        int num = 1;
        for (PurchaseProductLines line : productLinesDs.getItems()) {
            line.setNumLine(num++);
        }
    }
    public void onMoveUp(Component source) {
        PurchaseProductLines selectedLine = productLinesTable.getSingleSelected();
        if (selectedLine == null)
            return;
        int i = getItem().getProductLines().indexOf(selectedLine);
        if (i == 0)
            return;
        // create a temporary copy of the collection
        ArrayList<PurchaseProductLines> lines = new ArrayList<>(getItem().getProductLines());
        // modify entryNum attributes
        Integer num = selectedLine.getNumLine();
        selectedLine.setNumLine(num - 1);
        lines.get(i - 1).setNumLine(num);
        // sort copy according to the new order
        lines.sort(Comparator.comparingInt(PurchaseProductLines::getNumLine));
        // refill the datasource
        for (PurchaseProductLines line : lines) {
            productLinesDs.excludeItem(line);
        }
        for (PurchaseProductLines line : lines) {
            productLinesDs.includeItem(line);
        }
        // select the same item
        productLinesTable.setSelected(selectedLine);
    }

    public void onMoveDown(Component source) {
        PurchaseProductLines selectedLine = productLinesTable.getSingleSelected();
        if (selectedLine == null)
            return;
        int i = getItem().getProductLines().indexOf(selectedLine);
        if (i == getItem().getProductLines().size() - 1)
            return;
        // create a temporary copy of the collection
        ArrayList<PurchaseProductLines> lines = new ArrayList<>(getItem().getProductLines());
        // modify entryNum attributes
        Integer num = selectedLine.getNumLine();
        selectedLine.setNumLine(num + 1);
        lines.get(i + 1).setNumLine(num);
        // sort copy according to the new order
        lines.sort(Comparator.comparingInt(PurchaseProductLines::getNumLine));
        // refill the datasource
        for (PurchaseProductLines line : lines) {
            productLinesDs.excludeItem(line);
        }
        for (PurchaseProductLines line : lines) {
            productLinesDs.includeItem(line);
        }
        // select the same item
        productLinesTable.setSelected(selectedLine);

    }
}