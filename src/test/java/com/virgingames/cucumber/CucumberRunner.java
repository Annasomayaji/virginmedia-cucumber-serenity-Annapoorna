package com.virgingames.cucumber;

import com.virgingames.testbase.TestBase;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class )
@CucumberOptions(features = "src/test/resources/features/",
glue = "com/virgingames/steps"
)
public class CucumberRunner extends TestBase {


}
