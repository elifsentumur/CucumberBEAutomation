package hooks;

import com.mongodb.MapReduceCommand;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import utils.LoggerUtil;

public class Hooks {
    @Before
    public void beforeScenario() {
        LoggerUtil.info("Before Scenario: Setup");

    }

    @After
    public void afterScenario(Scenario scenario) {
        LoggerUtil.info("After Scenario: Teardown");
    }

}

