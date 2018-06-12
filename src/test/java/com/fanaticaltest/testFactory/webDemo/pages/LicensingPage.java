package com.fanaticaltest.testFactory.webDemo.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://demo-website.fanaticaltest.com/licence.html")
public class LicensingPage extends PageObject {

    @FindBy(xpath="//input[@name='submit']")
    WebElementFacade submit;

    @FindBy(xpath="//div[@id='validatorChkBox']")
    WebElementFacade errorMessage;

    @FindBy(xpath="//input[@name='MyCheckbox']")
    WebElementFacade agreementCheckBox;

    public void notAgreed() {
        submit.click();
    }

    public void agreed() {
        agreementCheckBox.click();
        submit.click();
    }

    public void getError(String message) {
        assert (errorMessage.getText().equals(message));
    }
}
