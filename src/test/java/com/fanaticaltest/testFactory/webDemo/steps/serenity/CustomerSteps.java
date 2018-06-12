package com.fanaticaltest.testFactory.webDemo.steps.serenity;

import com.fanaticaltest.testFactory.webDemo.pages.*;
import net.thucydides.core.steps.ScenarioSteps;


public class CustomerSteps extends ScenarioSteps {

    LoginPage loginPage;
    YopMail yopMail;
    HomePage homePage;
    LicensingPage licensingPage;
    ShoppingCartPage shoppingCartPage;

    public void enters_his_credential(String username, String password) {
        loginPage.login(username, password);
    }

    public void goes_to_login_page() {
        loginPage.open();
    }

    public void gets_login_error(String message) {
        loginPage.loginError(message);
    }

    public void goes_to_yopmail(String usernameAddress) {
        yopMail.open();
        yopMail.select(usernameAddress);
    }

    public void checks_his_mail_box_label(String labelContent) {
        yopMail.checkInBoxLabel(labelContent);
    }

    public void is_on_home_page() {
        homePage.checkPageTitle("Demo Frontend Website - Home");
    }

    public void goes_to_terms_and_conditions() {
        licensingPage.open();
    }

    public void does_not_agree_terms_and_conditions() {
        licensingPage.notAgreed();
    }

    public void sees_error_terms_and_conditions() {
        licensingPage.getError("You need to agree the Terms and Conditions");
    }

    public void agrees_terms_and_conditions() {
        licensingPage.agreed();
    }

    public void goes_to_shopping_cart() {
        shoppingCartPage.open();
    }

    public void adds_a_product(String productId) {
        shoppingCartPage.addToCart(productId);
    }

    public void sees_product_in_shopping_cart(String prodictId) {
        shoppingCartPage.checkProductInShoppingCart(prodictId);
    }

    public void selects_quantity(String quantity) {
        shoppingCartPage.selectQuantity(quantity);
    }

    public void checks_total_without_vat() {
        shoppingCartPage.checkTotalWithoutVat();
    }

    public void checks_vat_value() {
        shoppingCartPage.checkVatValue();
    }

    public void checks_total_value() {
        shoppingCartPage.checkTotalValue();
    }

    public void clicks_on_done(String buttonValue) {
        shoppingCartPage.clickDone(buttonValue);
    }
}
