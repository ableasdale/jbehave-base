package com.xmlmachines.sample;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class SampleScenario extends JUnitStory {

	private Logger LOG = LoggerFactory
			.getLogger("com.xmlmachines.sample.SampleScenario");

	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration().useStoryLoader(
				new LoadFromClasspath(this.getClass()))
				.useStoryReporterBuilder(
						new StoryReporterBuilder().withDefaultFormats()
								.withFormats(Format.CONSOLE, Format.TXT,
										Format.HTML, Format.XML));
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		LOG.debug("Adding (Injecting) Sample Steps");
		return new InstanceStepsFactory(configuration(), new SampleSteps());
	}

}
