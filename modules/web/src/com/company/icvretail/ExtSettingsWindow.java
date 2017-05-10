/*
 * Copyright@ by IconViet
 */
package com.company.icvretail;

import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.security.app.UserSettingService;
import com.haulmont.cuba.web.app.ui.core.settings.SettingsWindow;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExtSettingsWindow extends SettingsWindow {
    @Inject
    private UserSettingService userSettingService;

    @Inject
    protected OptionsGroup menuTypeOption;

    @Inject
    protected Button okBtn;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Side-Bar Menu", 1);
        map.put("Top-down Menu", 2);
        menuTypeOption.setOptionsMap(map);
        menuTypeOption.setOrientation(OptionsGroup.Orientation.HORIZONTAL);

        String menuOption = userSettingService.loadSetting("menuType");
        if (menuOption != null) {
            if (menuOption.equals("1")) {
                menuTypeOption.setValue(1);
            } else {
                menuTypeOption.setValue(2);
            }
        }
    }

      public void saveMenuTypeOption() {
        userSettingService.saveSetting("menuType", menuTypeOption.getValue().toString());
        //close(COMMIT_ACTION_ID);
    }

 

    public void onSaveMenuOption(Component source) {
        saveMenuTypeOption();
    }
}