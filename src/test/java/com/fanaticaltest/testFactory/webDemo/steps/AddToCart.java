package com.fanaticaltest.testFactory.webDemo.steps;

import com.fanaticaltest.testFactory.webDemo.steps.serenity.CustomerSteps;
import cucumber.api.PendingException;
import net.thucydides.core.annotations.Steps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;

public class AddToCart {

    @Steps
    CustomerSteps customer;

    @Given("^the customer is on the shopping page$")
    public void the_customer_is_on_the_shopping_page() throws Exception {
        customer.goes_to_shopping_cart();
    }


    @When("^the customer clicks on the buy button of the product id \"([^\"]*)\"$")
    public void the_customer_clicks_on_the_buy_button_of_the_product_id(String productId) throws Exception {
        customer.adds_a_product(productId);
    }

    @Then("^the customer sees the product \"([^\"]*)\" id the shopping cart$")
    public void the_customer_sees_the_product_id_the_shopping_cart(String productId) throws Exception {
        customer.sees_product_in_shopping_cart(productId);
    }

    @When("^the customer selects the quantity \"([^\"]*)\"$")
    public void the_customer_selects_the_quantity(String quantity) throws Exception {
        customer.selects_quantity(quantity);
    }

    @Then("^the customer sees the correct Total price without VAT$")
    public void the_customer_sees_the_correct_Total_price_without_VAT() throws Exception {
        customer.checks_total_without_vat();
    }

    @Then("^the customer sees the correct VAT value$")
    public void the_customer_sees_the_correct_VAT_value() throws Exception {
        customer.checks_vat_value();
    }

    @Then("^the customer sees the correct Total price$")
    public void the_customer_sees_the_correct_Total_price() throws Exception {
        customer.checks_total_value();
    }

    @When("^the customer clicks the button \"([^\"]*)\"$")
    public void the_customer_clicks_the_button(String buttonValue) throws Exception {
        customer.clicks_on_done(buttonValue);
    }

}
