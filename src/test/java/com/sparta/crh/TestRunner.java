package com.sparta.crh;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.sparta.crh.stepdefs"},
        plugin={"pretty", "html:target/testReport.html"},
        publish=true
)

public class TestRunner {
}