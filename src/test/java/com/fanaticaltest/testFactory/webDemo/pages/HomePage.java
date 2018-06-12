package com.fanaticaltest.testFactory.webDemo.pages;

import net.serenitybdd.core.pages.PageObject;

public class HomePage extends PageObject {
    public void checkPageTitle(String pageTitle) {
        assert (getTitle().equals(pageTitle));
    }
}
