package com.xmlmachines.old;

import com.xmlmachines.sample.SampleSteps;
import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.*;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(JUnitReportingRunner.class)
public class SimpleJBehaveRunner extends JUnitStories {

    private Configuration configuration;

    public SimpleJBehaveRunner() {
        super();
        configuration = new Configuration() {
        };

        // configuration.doDryRun(false); "no dry run" is implicit by using
        // default StoryControls

        /// configuration.useDefaultStoryReporter(new ConsoleOutput());
        // deprecated -- rather use StoryReportBuilder
           /*
        configuration.useFailureStrategy(new RethrowingFailure());
        configuration.useKeywords(new LocalizedKeywords(Locale.ENGLISH));
        configuration.usePathCalculator(new AbsolutePathCalculator());
        configuration.useParameterControls(new ParameterControls());
        configuration.useParameterConverters(new ParameterConverters());
        configuration.useParanamer(new NullParanamer());
        configuration.usePendingStepStrategy(new PassingUponPendingStep());
        configuration.useStepCollector(new MarkUnmatchedStepsAsPending());
        configuration.useStepdocReporter(new PrintStreamStepdocReporter());
        configuration.useStepFinder(new StepFinder());
        configuration.useStepMonitor(new SilentStepMonitor());
        configuration
                .useStepPatternParser(new RegexPrefixCapturingPatternParser());
        configuration.useStoryControls(new StoryControls());
        configuration.useStoryLoader(new LoadFromClasspath());
        configuration.useStoryParser(new RegexStoryParser(configuration
                .keywords()));
        configuration.useStoryPathResolver(new UnderscoredCamelCaseResolver());
        configuration.useStoryReporterBuilder(new StoryReporterBuilder());
        configuration.useViewGenerator(new FreemarkerViewGenerator());
             */
        JUnitReportingRunner.recommandedControls(configuredEmbedder());
        /*
        EmbedderControls embedderControls = configuredEmbedder()
                .embedderControls();
        embedderControls.doBatch(false);
        embedderControls.doGenerateViewAfterStories(true);
        embedderControls.doIgnoreFailureInStories(false);
        embedderControls.doIgnoreFailureInView(false);
        embedderControls.doSkip(false);
        embedderControls.doVerboseFailures(false);
        embedderControls.doVerboseFiltering(false);
        embedderControls.useStoryTimeoutInSecs(300);
        embedderControls.useThreads(1); */
    }

    @Override
    public Configuration configuration() {
        return configuration;
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new SampleSteps());
    }

    @Override
    protected List<String> storyPaths() {
        return Arrays.asList("Simple.story");
    }
}