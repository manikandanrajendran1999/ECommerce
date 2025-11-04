package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
		features = "src/test/resources/Features", 
		glue = "com.stepStepDefinition",
		tags = "@tag",
		dryRun = true,
		monochrome = true,
		plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"}
		)
public class TestRunner extends AbstractTestNGCucumberTests{

}
