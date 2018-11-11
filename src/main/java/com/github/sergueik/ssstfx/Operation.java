package com.github.sergueik.ssstfx;

public enum Operation {
//@formatter:off
	click("click"), 
  clickAt("clickAt"), 
  echo("echo"),
  highlight("highlight"), 
  open("open"), 
  pause("pause"), 
  refresh("refresh"), 
  sendKeys("sendKeys"), 
  store("store"), 
  storeTitle("storeTitle"), 
  type("type"), 
  verifyElementPresent("verifyElementPresent"), 
  verifyText("verifyText"), 
  verifyTitle("verifyTitle"), 
  windowMaximize("windowMaximize");
//@formatter:on
	private String operaion; //

	// Constructor
	Operation(String value) {
		operaion = value;
	}

	String getOperation() {
		return operaion;
	}
}