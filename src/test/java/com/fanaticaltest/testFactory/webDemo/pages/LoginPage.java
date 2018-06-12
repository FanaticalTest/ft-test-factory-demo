package com.fanaticaltest.testFactory.webDemo.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://demo-website.fanaticaltest.com/login.html")
public class LoginPage extends PageObject {

    @FindBy(xpath="//input[@id='username-field']")
    WebElementFacade userField;

    @FindBy(xpath="//input[@id='password-field']")
    WebElementFacade passwordField;

    @FindBy(xpath="//input[@id='login-submit']")
    WebElementFacade loginButton;

    @FindBy(xpath="//div[@id='validatorLogin']")
    WebElementFacade errorMessage;

    public void login(String username, String password) {
        userField.clear();
        userField.type(username);
        passwordField.clear();
        passwordField.type(password);
        loginButton.click();
    }

    public void loginError(String message) {
        assert (errorMessage.getText().equals(message));
    }
}
