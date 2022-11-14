package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)            //annotation comes from JUnit

@CucumberOptions(
//              ->syntax for features option differs for Failed rerun here,note it's different than the main CucumberRunner syntax:
//                            also you provide not the path of features package anymore, but the path to the Failed.txt file,
//                            this failed.txt is auto-generated by Cucumber thanks to "rerun" setting under cucumberOptions we configured in the main Cucumber Runner.
        features = "@target/failed.txt",

        glue = "stepDefinitions",

        stepNotifications = true
)
public class FailedScenariosRunner {
}