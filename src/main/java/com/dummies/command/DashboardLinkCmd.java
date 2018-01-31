package com.dummies.command;

import java.io.Serializable;

/**
 * @author taumal
 * @since 12/17/17 10:21 AM
 */
public class DashboardLinkCmd implements Serializable {

    private String displayName;
    private String url;

    public DashboardLinkCmd() {
    }

    public DashboardLinkCmd(String displayName, String url) {
        this.displayName = displayName;
        this.url = url;
    }

    public DashboardLinkCmd(String displayName, String legacyJsFunction, boolean legacy) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
