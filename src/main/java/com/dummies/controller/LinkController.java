package com.dummies.controller;

import com.dummies.command.DashboardLinkCmd;
import com.dummies.command.DashboardModuleCmd;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author taumal
 * @since 12/17/17 10:32 AM
 */
public class LinkController {

    public static final Logger log = Logger.getLogger(LinkController.class);

    public static List<DashboardModuleCmd> getLinks() {
        List<DashboardModuleCmd> modules = new ArrayList<DashboardModuleCmd>();

        DashboardModuleCmd module = new DashboardModuleCmd();
        module.setDisplayName("User");

        module.addLinks(new DashboardLinkCmd("New", "/user/entry"));
        module.addLinks(new DashboardLinkCmd("List", "/user/list"));

        modules.add(module);

        return modules;
    }

    public static Map<String, DashboardModuleCmd> getLinkMap() {
        Map<String, DashboardModuleCmd> map = new LinkedHashMap<String, DashboardModuleCmd>();

        for (DashboardModuleCmd dmc : getLinks()) {
            map.put(dmc.getDisplayName(), dmc);
        }
        return map;
    }
}
