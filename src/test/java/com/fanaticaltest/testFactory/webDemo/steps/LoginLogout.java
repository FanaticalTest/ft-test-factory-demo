package com.fanaticaltest.testFactory.webDemo.steps;


import com.fanaticaltest.testFactory.webDemo.steps.serenity.CustomerSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class LoginLogout {

    @Steps
    CustomerSteps customer;

    @When("^the customer with \"([^\"]*)\" and password \"([^\"]*)\" logs in on the LoginPage$")
    public void the_customer_with_and_password_logs_in_on_the_LoginPage(String username, String password) throws Exception {
        customer.goes_to_login_page();
        customer.enters_his_credential(username, password);
    }

    @Then("^the customer gets a error message \"([^\"]*)\"$")
    public void the_customer_gets_a_error_message(String message) throws Exception {
        customer.gets_login_error(message);
    }

    @Then("^the customer is redirected to the home page$")
    public void the_customer_is_redirected_to_the_home_page() throws Exception {
        customer.is_on_home_page();
    }


}
