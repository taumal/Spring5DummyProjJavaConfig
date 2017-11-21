package com.dummies.model;

import java.util.Date;

/**
 * @author taumal
 * @since 11/20/17 5:36 PM
 */
public class Home {
    public Home() {
    }

    private String greetings;
    private Date today;

    public String getGreetings() {
        return greetings;
    }

    public void setGreetings(String greetings) {
        this.greetings = greetings;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }
}
