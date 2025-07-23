package com.trendyol.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/trendyol/features/favorite",
        glue = {"com.trendyol.stepdefs"},
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class FavoriteRunner {
}
