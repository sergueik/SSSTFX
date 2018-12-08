package com.github.sergueik.ssstfx;

import com.google.gson.annotations.SerializedName;

/**
 * Copyright 2018 Serguei Kouzmine
 */

/**
 * Enumeration of supported Selenium IDE TNG operations one may find in ".tests[].commands[].command"
 * when de-serializing of Side recording
 * @author: Serguei Kouzmine (kouzmine_serguei@yahoo.com)
 */

public enum Operation {
//@formatter:off
	open("open"),
	click("click"),
	clickAt("clickAt"),
	check("check"),
	uncheck("uncheck"),
	doubleClick("doubleClick"),
	doubleClickAt("doubleClickAt"),
	dragAndDropToObject("dragAndDropToObject"),
	type("type"),
	sendKeys("sendKeys"),
	echo("echo"),
	run("run"),
	runScript("runScript"),
	executeScript("executeScript"),
	executeAsyncScript("executeAsyncScript"),
	pause("pause"),
	verifyChecked("verifyChecked"),
	verifyNotChecked("verifyNotChecked"),
	verifyEditable("verifyEditable"),
	verifyNotEditable("verifyNotEditable"),
	verifyElementPresent("verifyElementPresent"),
	verifyElementNotPresent("verifyElementNotPresent"),
	verifySelectedValue("verifySelectedValue"),
	verifyNotSelectedValue("verifyNotSelectedValue"),
	verifyValue("verifyValue"),
	verifyText("verifyText"),
	verifyTitle("verifyTitle"),
	verifyNotText("verifyNotText"),
	verifySelectedLabel("verifySelectedLabel"),
	assertChecked("assertChecked"),
	assertNotChecked("assertNotChecked"),
	assertEditable("assertEditable"),
	assertNotEditable("assertNotEditable"),
	assertElementPresent("assertElementPresent"),
	assertElementNotPresent("assertElementNotPresent"),
	assertSelectedValue("assertSelectedValue"),
	assertNotSelectedValue("assertNotSelectedValue"),
	assertValue("assertValue"),
	assertText("assertText"),
	assertTitle("assertTitle"),
	assertSelectedLabel("assertSelectedLabel"),
	store("store"),
	storeText("storeText"),
	storeValue("storeValue"),
	storeTitle("storeTitle"),
	storeXpathCount("storeXpathCount"),
	storeAttribute("storeAttribute"),
	select("select"),
	addSelection("addSelection"),
	removeSelection("removeSelection"),
	selectFrame("selectFrame"),
	selectWindow("selectWindow"),
	close("close"),
	mouseDown("mouseDown"),
	mouseDownAt("mouseDownAt"),
	mouseUp("mouseUp"),
	mouseUpAt("mouseUpAt"),
	mouseMove("mouseMove"),
	mouseMoveAt("mouseMoveAt"),
	mouseOver("mouseOver"),
	mouseOut("mouseOut"),
	assertAlert("assertAlert"),
	assertNotText("assertNotText"),
	assertPrompt("assertPrompt"),
	assertConfirmation("assertConfirmation"),
	webdriverAnswerOnVisiblePrompt("webdriverAnswerOnVisiblePrompt"),
	webdriverChooseOkOnVisibleConfirmation("webdriverChooseOkOnVisibleConfirmation"),
	webdriverChooseCancelOnVisibleConfirmation("webdriverChooseCancelOnVisibleConfirmation"),
	webdriverChooseCancelOnVisiblePrompt("webdriverChooseCancelOnVisiblePrompt"),
	editContent("editContent"),
	submit("submit"),
	answerOnNextPrompt("answerOnNextPrompt"),
	chooseCancelOnNextConfirmation("chooseCancelOnNextConfirmation"),
	chooseCancelOnNextPrompt("chooseCancelOnNextPrompt"),
	chooseOkOnNextConfirmation("chooseOkOnNextConfirmation"),
	setSpeed("setSpeed"),
	setWindowSize("setWindowSize"),

	@SerializedName("do")
  _do("do"),
	@SerializedName("else")
	_else("else"),
	@SerializedName("elseIf")
	_elseIf("elseIf"),
	@SerializedName("end")
	_end("end"),
	@SerializedName("if")
	_if("if"),
	repeatIf("repeatIf"),
	times("times"),
	@SerializedName("while")
	_while("while"),
	@SerializedName("assert")
	_assert("assert"),
	verify("verify"),
	waitForElementPresent("waitForElementPresent"),
	waitForElementNotPresent("waitForElementNotPresent"),
	waitForElementVisible("waitForElementVisible"),
	waitForElementNotVisible("waitForElementNotVisible"),
	waitForElementEditable("waitForElementEditable"),
	waitForElementNotEditable("waitForElementNotEditable");
//@formatter:on

	private String operaion;

	// Constructor
	Operation(String value) {
		operaion = value;
	}

	String getOperation() {
		return operaion;
	}
}
