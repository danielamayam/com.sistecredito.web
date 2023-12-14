package com.sistecredito.web.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = "src/test/resources/features/",
        tags = "@smokTest",
        glue = {"com.sistecredito.web.stepdefinitions","com.sistecredito.web.conf"},
        plugin = {"pretty", "json:target/cucumber-reports/cucumber.json"},
        publish = true
)
public class Runner {

}
