package com.github.sergueik.ssstfx;
/**
 * Copyright 2018 Serguei Kouzmine
 */

/**
 * Enumeration covering element locator strategies in ".tests[].commands[].target" 
 * when de-serializing of Side recording
 * @author: Serguei Kouzmine (kouzmine_serguei@yahoo.com)
 */

// https://stackoverflow.com/questions/16740078/serialize-and-deserialize-enum-with-gson
public enum Selector {

// @formatter:off
  css("css"), 
  id("id"), 
  tagName("tag"), 
  text("text"),
  xpath("xpath");
// @formatter:on
	private String kind; //

	// Constructor
	Selector(String value) {
		kind = value;
	}

	String getSelector() {
		return kind;
	}
}
