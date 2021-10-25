package com.github.sergueik.ssstfx;

/**
 * Copyright 2018 Serguei Kouzmine
 */

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.notNullValue;

import com.github.sergueik.ssstfx.SideCommand;
import com.github.sergueik.ssstfx.SideRecording;
import com.github.sergueik.ssstfx.SideSuite;
import com.github.sergueik.ssstfx.SideTest;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Selenium IDE TNG Side recording serialization unit tests
 * @author: Serguei Kouzmine (kouzmine_serguei@yahoo.com)
 */

// Practicing compound object serialization
// https://howtodoinjava.com/apache-commons/google-gson-tutorial-convert-java-object-to-from-json/
// https://futurestud.io/tutorials/gson-mapping-of-arrays-and-lists-of-objects
// http://zetcode.com/java/gson/
// https://www.programcreek.com/java-api-examples/index.php?api=com.google.gson.JsonSerializer

public class ExportTest {

	private static Boolean updated = false;
	private static String result = null;

	private SideRecording sideRecordingObj = new SideRecording();
	private SideSuite sideSuite = new SideSuite();
	private SideTest sideTest = new SideTest();
	private SideCommand sideCommand = new SideCommand();
	private List<SideSuite> suites = new ArrayList<>();
	private List<SideCommand> commands = new ArrayList<>();
	private List<SideTest> tests = new ArrayList<>();
	private List<String> testNames = new ArrayList<>();
	private String testName = "test 1";
	private static Map<String, String> elementData = new HashMap<>();
	private static Object[] expected = new Object[] { "id", "name", "url", "urls",
			"tests", "suites" };

	@BeforeClass
	public static void beforeSuiteMethod() throws Exception {

	}

	@Before
	public void createSampleData() {

		sideCommand.setId(id());
		sideCommand.setName("name of command 1");
		sideCommand.setValue("value of command 1");
		// NOTE: not a real SIDE recording (seems to need change processing of
		// these), just to exercise
		// other code
		sideCommand.setTarget("xpath=//input[@value='Google Search']");
		sideCommand.setSelector(sideCommand.getTarget());
		commands.add(sideCommand);

		sideTest.setId(id());
		sideTest.setName("name of test 1");
		sideTest.setCommands(commands);
		tests.add(sideTest);

		testNames.add(testName);
		sideSuite.setId(id());
		sideSuite.setTests(testNames);
		sideSuite.setName("name of suite 1");
		suites.add(sideSuite);

		sideRecordingObj.setId(id());
		sideRecordingObj.setName("name of recording 1");
		sideRecordingObj.setUrl("url of recording 1");
		sideRecordingObj.setUrls(new ArrayList<String>());
		sideRecordingObj.setSuites(suites);
		sideRecordingObj.setTests(tests);
		System.err
				.println("Created side test recording: " + sideRecordingObj.toString());

	}

	@Ignore
	// https://sites.google.com/site/gson/gson-user-guide#TOC-Collections-Examples
	// https://github.com/google/gson/blob/master/UserGuide.md
	@Test
	public void scratchSideRecordingSerialzationTest() {

		Gson gson = new GsonBuilder()
				// .setPrettyPrinting().serializeNulls()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.registerTypeAdapter(SideRecording.class, new SideRecordingSerializer())
				.registerTypeAdapter(SideCommand.class, new SideCommandSerializer())
				.registerTypeAdapter(SideTest.class, new SideTestSerializer()).create();
		System.err.println(
				"Reloading Configuration Object: " + gson.toJson(sideRecordingObj));
		System.err
				.println("Created side test recording: " + sideRecordingObj.toString());
	}

	@Test
	public void sideRecordingDetailedSerialzationTest() {

		com.google.gson.Gson gson = new GsonBuilder()
				// .setPrettyPrinting().serializeNulls()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.registerTypeAdapter(SideRecording.class, new SideRecordingSerializer())
				.registerTypeAdapter(SideCommand.class,
						new SideCommandDetailedSerializer())
				.registerTypeAdapter(SideTest.class, new SideTestSerializer()).create();
		// NOTE: serialization
		// "target":"//input[@value\u003d\u0027Google Search\u0027]"
		// not
		// "target":"//input[@value='Google Search']"
		System.err.println("Detailed serialization of Recording Object: "
				+ gson.toJson(sideRecordingObj));
		System.err
				.println("Created side test recording: " + sideRecordingObj.toString());
	}

	private String id() {
		return UUID.randomUUID().toString();
	}
}
