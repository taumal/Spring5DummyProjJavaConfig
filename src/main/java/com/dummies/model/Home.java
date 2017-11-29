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
    private String today;

    public String getGreetings() {
        return greetings;
    }

    public void setGreetings(String greetings) {
        this.greetings = greetings;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }
}
