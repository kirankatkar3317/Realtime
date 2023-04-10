package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\java\\Cucumber\\Login1.feature", glue = "Cucumber.StepDefinition", monochrome = true, plugin = {
		"html:target/Cucumber.html" }, dryRun = false)

public class TestRunner extends AbstractTestNGCucumberTests{

	
}
