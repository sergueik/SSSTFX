package com.github.sergueik.ssstfx;

public enum Selector {

//@formatter:off
  css("css"), 
  id("id"), 
  tagName("tag"), 
  text("text"),
  xpath("xpath");
//@formatter:on
	private String kind; //

	// Constructor
	Selector(String value) {
		kind = value;
	}

	String getSelector() {
		return kind;
	}
}
