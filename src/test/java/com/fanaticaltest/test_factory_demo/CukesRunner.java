// To execute the script Run As CukesRunner.java
package com.fanaticaltest.test_factory_demo;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.*;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/resources"}
)
public class CukesRunner {
}