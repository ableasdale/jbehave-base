package com.xmlmachines.sample;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleSteps extends Steps {

	private Logger LOG = LoggerFactory
			.getLogger("com.xmlmachines.sample.SampleSteps");

	int x;

	@BeforeStories
	public void beforeStories() {
		LOG.debug("Before Stories");
	}

	@AfterStories
	public void afterStories() {
		LOG.debug("After Stories");
	}

	@Given("a variable x with value $value")
	public void givenXValue(@Named("value") int value) {
		x = value;
	}

	@When("I multiply x by $value")
	public void whenImultiplyXBy(@Named("value") int value) {
		x = x * value;
	}

	@Then("x should equal $value")
	public void thenXshouldBe(@Named("value") int value) {
		if (value != x)
			throw new RuntimeException("x is " + x + ", but should be " + value);
	}

}
