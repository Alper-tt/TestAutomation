package com.trendyol.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/com/trendyol/features/follow",
    glue = {"com.trendyol.stepdefs"},
    plugin = {"pretty", "html:target/takip-report.html"}
)
public class FollowRunner {
}
