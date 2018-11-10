package com.github.sergueik.ssstfx;

/**
 * Copyright 2014 - 2018 Serguei Kouzmine
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

/*
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
*/

import java.util.UUID;

import static org.hamcrest.CoreMatchers.notNullValue;

import com.github.sergueik.ssstfx.SideCommand;
import com.github.sergueik.ssstfx.SideRecording;
import com.github.sergueik.ssstfx.SideSuite;
import com.github.sergueik.ssstfx.SideTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// https://www.baeldung.com/gson-deserialization-guide
@SuppressWarnings("deprecation")
public class SideUtilityTest {

	private static Boolean updated = false;
	private static String result = null;

	private SideRecording sideRecording = new SideRecording();
	private SideSuite sideSuite = new SideSuite();
	private SideTest sideTest = new SideTest();
	private SideCommand sideCommand = new SideCommand();
	private List<SideSuite> suites = new ArrayList<>();
	private List<SideCommand> commands = new ArrayList<>();
	private List<SideTest> tests = new ArrayList<>();
	private List<String> testNames = new ArrayList<>();
	private String testName = "test 1";
	private static Map<String, String> elementData = new HashMap<>();
	private static Object[] expected = new Object[] { "id",
			"name", "url", "urls", "tests", "suites" };


	@BeforeClass
	public static void beforeSuiteMethod() throws Exception {

	}

	@Before
	public void loadBaseData() {
	}

	@Ignore
	@Test
	public void dataKeysTest() {
		List<Object> subkeys = Arrays.asList(expected);
		// subkeys.remove("Url");
		Set<Object> dataSet = new HashSet<Object>(subkeys);
		assertTrue(new HashSet<Object>(elementData.keySet()).containsAll(dataSet));
	}

	@Test
	public void scratchSideRecordingTest() {

		sideCommand.setId(id());
		sideCommand.setName("name of command 1");
		sideCommand.setValue("value of command 1");
		sideCommand.setTarget("target of command 1");
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

		sideRecording.setId(id());
		sideRecording.setName("name of recording 1");
		sideRecording.setUrl("url of recording 1");
		sideRecording.setUrls(new ArrayList<String>());
		sideRecording.setSuites(suites);
		sideRecording.setTests(tests);
		System.err
				.println("Created side test recording: " + sideRecording.toString());
	}

	private String id() {
		return UUID.randomUUID().toString();
	}

	@Test
	// https://www.baeldung.com/gson-deserialization-guide
	public void deserializeTypeTest() {
		String sideExamplepPayload = "{\"id\":\"837d3acd-285e-478a-8d46-817df0a5b4d9\",\"name\":\"Google<br>\",\"url\":\"https://www.google.com \t\",\"tests\":[{\"id\":\"ae13d6ad-c3f2-4fb8-aaeb-14af40f2b3b9\",\"name\":\"Google\",\"commands\":[{\"id\":\"160c2276-d9b3-4523-bdf3-b914111ca407\",\"comment\":\"\",\"command\":\"open\",\"target\":\"/images\",\"value\":\"\"},{\"id\":\"856ac533-41f0-4091-813d-6f865cf72985\",\"comment\":\"\",\"command\":\"open\",\"target\":\"/\",\"value\":\"\"}]}],\"suites\":[{\"id\":\"05e89807-cb33-4ca6-8ca4-10e1cdf127c3\",\"name\":\"Default Suite\",\"tests\":[\"ae13d6ad-c3f2-4fb8-aaeb-14af40f2b3b9\"]}],\"urls\":[\"https://www.google.co.in\",\"https://www.google.co.in\"]}";
		// load and print
		SideRecording sideRecording = new Gson().fromJson(sideExamplepPayload,
				SideRecording.class);
		assertThat(sideRecording, notNullValue());
		List<String> urls = sideRecording.getUrls();
		assertThat(urls.get(0), notNullValue());
		System.err
				.println("Deserialized url from side recording: " + urls.toString());
		suites = sideRecording.getSuites();
		sideSuite = suites.get(0);
		assertThat(sideSuite, notNullValue());
		assertThat(sideSuite.getName(), notNullValue());
		System.err.println("Deserialized suite name: " + sideSuite.getName());
		assertThat(sideSuite.getId(), notNullValue());
		System.err.println("Deserialized suite id: " + sideSuite.getId());
		testNames = sideSuite.getTests(); // can not name tests 'tests'
		try {
			assertThat(testNames.get(0), notNullValue());
			System.err.println("Deserialized suite: " + suites.get(0).toString());
		} catch (NullPointerException e) {
			System.err.println("Failed to Deserialized test names from suite.");
		}

		tests = sideRecording.getTests();
		sideTest = tests.get(0);
		assertThat(sideTest.getName(), notNullValue());
		assertThat(sideTest.getId(), notNullValue());
		assertThat(sideTest, notNullValue());
		commands = sideTest.getCommands();
		sideCommand = commands.get(0);
		assertThat(sideCommand, notNullValue());

		System.err.println("Deserialized side command: " + sideCommand.toString());

		System.err.println(
				"Deserialized side test recording: " + sideRecording.toString());
	}
}
