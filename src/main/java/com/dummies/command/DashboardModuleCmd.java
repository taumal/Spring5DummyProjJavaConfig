package com.dummies.command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author taumal
 * @since 12/17/17 10:19 AM
 */
public class DashboardModuleCmd implements Serializable {

    private String displayName;
    List<DashboardLinkCmd> links;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String moduleDisplayName) {
        this.displayName = moduleDisplayName;
    }

    public List<DashboardLinkCmd> getLinks() {
        return links;
    }

    public void setLinks(List<DashboardLinkCmd> links) {
        this.links = links;
    }

    public void addLinks(DashboardLinkCmd link) {
        if (getLinks() == null) {
            this.setLinks(new ArrayList<DashboardLinkCmd>());
        }
        this.getLinks().add(link);
    }
}
