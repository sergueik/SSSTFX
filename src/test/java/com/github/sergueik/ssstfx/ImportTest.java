package com.github.sergueik.ssstfx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import com.google.gson.Gson;

/**
 * Selenium IDE TNG Side JSON resource de-serialization unit tests
 * @author: Serguei Kouzmine (kouzmine_serguei@yahoo.com)
 */

public class ImportTest {

	private static String payload = "{\"id\":\"837d3acd-285e-478a-8d46-817df0a5b4d9\",\"name\":\"Google<br>\",\"url\":\"https://www.google.com \t\",\"tests\":[{\"id\":\"ae13d6ad-c3f2-4fb8-aaeb-14af40f2b3b9\",\"name\":\"Google\",\"commands\":[{\"id\":\"160c2276-d9b3-4523-bdf3-b914111ca407\",\"comment\":\"\",\"command\":\"open\",\"target\":\"/images\",\"value\":\"\"},{\"id\":\"856ac533-41f0-4091-813d-6f865cf72985\",\"comment\":\"\",\"command\":\"open\",\"target\":\"/\",\"value\":\"\"},{\"id\":\"9c8e6eaf-3b39-435f-93f8-6716f159721d\",\"comment\":\"\",\"command\":\"windowMaximize\",\"target\":\"\",\"value\":\"\"},{\"id\":\"4a0595ff-61c3-41fc-94fa-b338e5c65faf\",\"comment\":\"\",\"command\":\"highlight\",\"target\":\"id=lst-ib\",\"value\":\"\"},{\"id\":\"602f45cf-a2f4-4491-b51c-a1036cebe879\",\"comment\":\"\",\"command\":\"pause\",\"target\":\"\",\"value\":\"200\"},{\"id\":\"22d356ea-f33a-49b7-ab07-53ecba35cf65\",\"comment\":\"\",\"command\":\"click\",\"target\":\"//input[@type='text']\",\"value\":\"\"},{\"id\":\"37f57010-4677-4bbf-8a1d-e0f19faadbb2\",\"comment\":\"\",\"command\":\"store\",\"target\":\"selenium ide\",\"value\":\"text\"},{\"id\":\"47b767f5-a7d4-4332-bc4f-5e1e986cf732\",\"comment\":\"\",\"command\":\"type\",\"target\":\"id=lst-ib\",\"value\":\"\"},{\"id\":\"f2b9ac0a-7cce-4c0b-bb29-277eeea8bf12\",\"comment\":\"\",\"command\":\"verifyElementPresent\",\"target\":\"//input[@value='Google Search']\",\"value\":\"\"},{\"id\":\"5bfa6d57-3039-4a5a-82c1-e56dabb3cae5\",\"comment\":\"\",\"command\":\"verifyText\",\"target\":\"id=SIvCob\",\"value\":\"Google offered in:\"},{\"id\":\"673164e5-5802-4763-9dbe-f6afef86bef8\",\"comment\":\"\",\"command\":\"sendKeys\",\"target\":\"id=lst-ib\",\"value\":\"\"},{\"id\":\"82eb4147-efe2-4965-b71a-3132e3f7651b\",\"comment\":\"\",\"command\":\"refresh\",\"target\":\"\",\"value\":\"\"},{\"id\":\"b269914c-4a15-40c3-b467-8c36c01d331b\",\"comment\":\"\",\"command\":\"clickAt\",\"target\":\"id=logo\",\"value\":\"\"},{\"id\":\"f129939b-8f3b-4220-92a7-c4ecb4e9aff8\",\"comment\":\"\",\"command\":\"verifyTitle\",\"target\":\"Google\",\"value\":\"\"},{\"id\":\"1a503afd-367b-4f28-a8b1-01d489da5d81\",\"comment\":\"\",\"command\":\"storeTitle\",\"target\":\"\",\"value\":\"var1\"},{\"id\":\"768c0d16-b6bf-48e1-a424-b3248ebd088f\",\"comment\":\"\",\"command\":\"echo\",\"target\":\"\",\"value\":\"\"}]}],\"suites\":[{\"id\":\"05e89807-cb33-4ca6-8ca4-10e1cdf127c3\",\"name\":\"Default Suite\",\"tests\":[\"ae13d6ad-c3f2-4fb8-aaeb-14af40f2b3b9\"]}],\"urls\":[\"https://www.google.co.in\",\"https://www.google.co.in\"]}";

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
	private static Object[] expected = new Object[] { "id", "name", "url", "urls",
			"tests", "suites" };

	@Test
	public void dataKeysTest() {

		sideRecording = new Gson().fromJson(payload, SideRecording.class);
		for (Object obj : expected) {
			elementData.put(obj.toString(), (String) null);
		}
		Set<Object> objectMememberSet = new HashSet<Object>();
		// reflection ?
		if (sideRecording.getUrl() != null) {
			objectMememberSet.add("url");
		}
		if (sideRecording.getId() != null) {
			objectMememberSet.add("id");
		}
		if (sideRecording.getName() != null) {
			objectMememberSet.add("name");
		}
		if (sideRecording.getSuites() != null) {
			objectMememberSet.add("suites");
		}
		if (sideRecording.getTests() != null) {
			objectMememberSet.add("tests");
		}
		if (sideRecording.getUrls() != null) {
			objectMememberSet.add("urls");
		}
		assertTrue(new HashSet<Object>(elementData.keySet())
				.containsAll(objectMememberSet));
		// this will be important during serialization
	}

	@Test
	// load and print
	public void deserializeShallowTest() {
		sideRecording = new Gson().fromJson(payload, SideRecording.class);
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

	@Test
	// load, inspect the data, update the object and print
	public void deserializeDeepTest() {
		sideRecording = new Gson().fromJson(payload, SideRecording.class);
		assertThat(sideRecording, notNullValue());
		tests = sideRecording.getTests();
		sideTest = tests.get(0);
		assertThat(sideTest.getName(), notNullValue());
		assertThat(sideTest.getId(), notNullValue());
		assertThat(sideTest, notNullValue());
		commands = sideTest.getCommands();
		sideCommand = commands.get(0);
		assertThat(sideCommand, notNullValue());
		// fill strongly-typed operation member
		commands.stream().forEach(o -> {
			o.setOperation(o.getCommand());
		});

		// fill strongly-typed selector member
		commands.stream().forEach(o -> {
			o.setSelector(o.getTarget());
		});

		commands.stream().forEach(
				o -> System.err.println("Deserialized side command: " + o.toString()));

		System.err.println(
				"Deserialized side test recording: " + sideRecording.toString());
	}

	private String id() {
		return UUID.randomUUID().toString();
	}

}
