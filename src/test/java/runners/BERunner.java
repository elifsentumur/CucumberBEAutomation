package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "html:target/cucumber-reports.html",
                "json:target/cucumber-report"},
        features = "src/test/resources/features",
        glue = "stepdef"
)
public class BERunner {
}