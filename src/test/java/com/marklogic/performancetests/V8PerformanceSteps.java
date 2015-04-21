package com.marklogic.performancetests;

import java.net.URI;
import java.net.URISyntaxException;

import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.marklogic.xcc.ContentSource;
import com.marklogic.xcc.ContentSourceFactory;
import com.marklogic.xcc.Request;
import com.marklogic.xcc.ResultSequence;
import com.marklogic.xcc.Session;
import com.marklogic.xcc.exceptions.RequestException;
import com.marklogic.xcc.exceptions.XccConfigException;

public class V8PerformanceSteps extends Steps {

	String response;
	ContentSource cs;

	private Logger LOG = LoggerFactory
			.getLogger("com.marklogic.performancetests.V8PerformanceSteps");

	@BeforeStories
	public void beforeStories() {
		System.out
				.println("This is the beforestories stuff - I want to see this in the Jenkins Output!");
		LOG.debug("BEFORE: Running the initial setup to create the XCC Content Source");
		// Create an XCC ConnectionSource - this can be refactored out into a
		// helper class
		try {
			URI uri = new URI("xcc://q:q@localhost:8000/Modules");
			cs = ContentSourceFactory.newContentSource(uri);
			System.out
					.println("Content source REALLY should have been created");
		} catch (XccConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Given("MarkLogic has an XQuery module that returns a simple string")
	public void givenMLModule() {
		LOG.debug("In the Given  - inserting the Test XQuery Module");
		Session s = cs.newSession();
		Request r = s
				.newAdhocQuery("xdmp:document-insert(\"/test/performance.xqy\", text { '\"hello world\"' })");
		try {
			s.submitRequest(r);
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

	@Given("MarkLogic has a JavaScript module that returns a simple string")
	public void givenMLJSModule() {
		LOG.debug("In the Given  - inserting the Test JavaScript Module");
		Session s = cs.newSession();
		Request r = s
				.newAdhocQuery("xdmp:document-insert(\"/test/performance.sjs\", text { '\"hello world\";' })");
		try {
			s.submitRequest(r);
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

	@When("I invoke the XQY module $x times")
	public void runMulti(int x) {
		LOG.debug("In the When part of the test - Value passed in is: " + x);
		String str = "fn:avg(for $i in (1 to "
				+ x
				+ ") return prof:invoke(\"/test/performance.xqy\")[1]/prof:metadata/prof:overall-elapsed)";
		Session s = cs.newSession();
		Request r = s.newAdhocQuery(str);
		ResultSequence rs;

		try {
			rs = s.submitRequest(r);
			LOG.info("Average reported by XQuery Profiler: " + rs.asString());
			response = rs.asString();
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			s.close();
		}

	}

	@When("I invoke the SJS module $x times")
	public void runMultiJs(int x) {
		LOG.debug("In the When part of the test - Value passed in is: " + x);
		String str = "fn:avg(for $i in (1 to "
				+ x
				+ ") return prof:invoke(\"/test/performance.sjs\")[1]/prof:metadata/prof:overall-elapsed)";
		Session s = cs.newSession();
		Request r = s.newAdhocQuery(str);
		ResultSequence rs;

		try {
			rs = s.submitRequest(r);
			LOG.info("Average reported by JS Profiler: " + rs.asString());
			response = rs.asString();
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			s.close();
		}

	}

	@Then("response $res should be under 0.0025")
	public void isItFastEnough() {
		LOG.info("In the Then part of the test " + response);
	}

}
