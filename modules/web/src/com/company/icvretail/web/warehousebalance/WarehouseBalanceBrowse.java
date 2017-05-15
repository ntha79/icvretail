/*
 * Copyright@ by IconViet
 */
package com.company.icvretail.web.warehousebalance;

import com.company.icvretail.entity.registers.WarehouseBalance;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.components.actions.RemoveAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.DataSupplier;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class WarehouseBalanceBrowse extends AbstractLookup {

    /**
     * The {@link CollectionDatasource} instance that loads a list of {@link WarehouseBalance} records
     * to be displayed in {@link WarehouseBalanceBrowse#warehouseBalancesTable} on the left
     */
    @Inject
    private CollectionDatasource<WarehouseBalance, UUID> warehouseBalancesDs;

    /**
     * The {@link Datasource} instance that contains an instance of the selected entity
     * in {@link WarehouseBalanceBrowse#warehouseBalancesDs}
     * <p/> Containing instance is loaded in {@link CollectionDatasource#addItemChangeListener}
     * with the view, specified in the XML screen descriptor.
     * The listener is set in the {@link WarehouseBalanceBrowse#init(Map)} method
     */
    @Inject
    private Datasource<WarehouseBalance> warehouseBalanceDs;

    /**
     * The {@link Table} instance, containing a list of {@link WarehouseBalance} records,
     * loaded via {@link WarehouseBalanceBrowse#warehouseBalancesDs}
     */
    @Inject
    private Table<WarehouseBalance> warehouseBalancesTable;

    /**
     * The {@link BoxLayout} instance that contains components on the left side
     * of {@link SplitPanel}
     */
    @Inject
    private BoxLayout lookupBox;

    /**
     * The {@link BoxLayout} instance that contains buttons to invoke Save or Cancel actions in edit mode
     */
    @Inject
    private BoxLayout actionsPane;

    /**
     * The {@link FieldGroup} instance that is linked to {@link WarehouseBalanceBrowse#warehouseBalanceDs}
     * and shows fields of the selected {@link WarehouseBalance} record
     */
    @Inject
    private FieldGroup fieldGroup;

    /**
     * The {@link RemoveAction} instance, related to {@link WarehouseBalanceBrowse#warehouseBalancesTable}
     */
    @Named("warehouseBalancesTable.remove")
    private RemoveAction warehouseBalancesTableRemove;

    @Inject
    private DataSupplier dataSupplier;

    /**
     * {@link Boolean} value, indicating if a new instance of {@link WarehouseBalance} is being created
     */
    private boolean creating;

    @Override
    public void init(Map<String, Object> params) {

        /*
         * Adding {@link com.haulmont.cuba.gui.data.Datasource.ItemChangeListener} to {@link warehouseBalancesDs}
         * The listener reloads the selected record with the specified view and sets it to {@link warehouseBalanceDs}
         */
        warehouseBalancesDs.addItemChangeListener(e -> {
            if (e.getItem() != null) {
                WarehouseBalance reloadedItem = dataSupplier.reload(e.getDs().getItem(), warehouseBalanceDs.getView());
                warehouseBalanceDs.setItem(reloadedItem);
            }
        });

        /*
         * Adding {@link CreateAction} to {@link warehouseBalancesTable}
         * The listener removes selection in {@link warehouseBalancesTable}, sets a newly created item to {@link warehouseBalanceDs}
         * and enables controls for record editing
         */
        warehouseBalancesTable.addAction(new CreateAction(warehouseBalancesTable) {
            @Override
            protected void internalOpenEditor(CollectionDatasource datasource, Entity newItem, Datasource parentDs, Map<String, Object> params) {
                warehouseBalancesTable.setSelected(Collections.emptyList());
                warehouseBalanceDs.setItem((WarehouseBalance) newItem);
                refreshOptionsForLookupFields();
                enableEditControls(true);
            }
        });

        /*
         * Adding {@link EditAction} to {@link warehouseBalancesTable}
         * The listener enables controls for record editing
         */
        warehouseBalancesTable.addAction(new EditAction(warehouseBalancesTable) {
            @Override
            protected void internalOpenEditor(CollectionDatasource datasource, Entity existingItem, Datasource parentDs, Map<String, Object> params) {
                if (warehouseBalancesTable.getSelected().size() == 1) {
                    refreshOptionsForLookupFields();
                    enableEditControls(false);
                }
            }
        });

        /*
         * Setting {@link RemoveAction#afterRemoveHandler} for {@link warehouseBalancesTableRemove}
         * to reset record, contained in {@link warehouseBalanceDs}
         */
        warehouseBalancesTableRemove.setAfterRemoveHandler(removedItems -> warehouseBalanceDs.setItem(null));

        disableEditControls();
    }

    private void refreshOptionsForLookupFields() {
        for (Component component : fieldGroup.getOwnComponents()) {
            if (component instanceof LookupField) {
                CollectionDatasource optionsDatasource = ((LookupField) component).getOptionsDatasource();
                if (optionsDatasource != null) {
                    optionsDatasource.refresh();
                }
            }
        }
    }

    /**
     * Method that is invoked by clicking Ok button after editing an existing or creating a new record
     */
    public void save() {
        if (!validate(Collections.singletonList(fieldGroup))) {
            return;
        }
        getDsContext().commit();

        WarehouseBalance editedItem = warehouseBalanceDs.getItem();
        if (creating) {
            warehouseBalancesDs.includeItem(editedItem);
        } else {
            warehouseBalancesDs.updateItem(editedItem);
        }
        warehouseBalancesTable.setSelected(editedItem);

        disableEditControls();
    }

    /**
     * Method that is invoked by clicking Cancel button, discards changes and disables controls for record editing
     */
    public void cancel() {
        WarehouseBalance selectedItem = warehouseBalancesDs.getItem();
        if (selectedItem != null) {
            WarehouseBalance reloadedItem = dataSupplier.reload(selectedItem, warehouseBalanceDs.getView());
            warehouseBalancesDs.setItem(reloadedItem);
        } else {
            warehouseBalanceDs.setItem(null);
        }

        disableEditControls();
    }

    /**
     * Enabling controls for record editing
     * @param creating indicates if a new instance of {@link WarehouseBalance} is being created
     */
    private void enableEditControls(boolean creating) {
        this.creating = creating;
        initEditComponents(true);
        fieldGroup.requestFocus();
    }

    /**
     * Disabling editing controls
     */
    private void disableEditControls() {
        initEditComponents(false);
        warehouseBalancesTable.requestFocus();
    }

    /**
     * Initiating edit controls, depending on if they should be enabled/disabled
     * @param enabled if true - enables editing controls and disables controls on the left side of the splitter
     *                if false - visa versa
     */
    private void initEditComponents(boolean enabled) {
        fieldGroup.setEditable(enabled);
        actionsPane.setVisible(enabled);
        lookupBox.setEnabled(!enabled);
    }
}