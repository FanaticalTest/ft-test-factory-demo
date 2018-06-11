package com.fanaticaltest.testFactory.webDemo.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("http://www.yopmail.com/en/")
public class YopMail extends PageObject {

    @FindBy(xpath="//input[@id='login']")
    WebElementFacade loginField;

    @FindBy(xpath="//input[@title='Check inbox @yopmail.com']")
    WebElementFacade checkInboxButton;

    @FindBy(xpath="//div[@class='bname b al_l']")
    WebElementFacade inboxLabel;

    public void select(String usernameAddress) {
        loginField.clear();
        loginField.type(usernameAddress);
        checkInboxButton.click();
    }

    public void checkInBoxLabel(String labelContent) {
        assert (inboxLabel.getText().equals(labelContent));
    }
}
