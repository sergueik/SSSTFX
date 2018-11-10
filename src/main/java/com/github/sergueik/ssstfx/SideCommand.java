package com.github.sergueik.ssstfx;
/**
 * Copyright 2014 - 2018 Serguei Kouzmine
 */

import static java.lang.String.format;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Selenium TNG Side product JSON deserializer class for Selenium WebDriver Elementor Tool (SWET)
 * @author: Serguei Kouzmine (kouzmine_serguei@yahoo.com)
 */

final class SideCommand {

	private String name;
	private String id;
	private String command;
	private String comment;
	private String target;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String data) {
		this.name = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String data) {
		this.id = data;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String data) {
		this.command = data;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String data) {
		this.comment = data;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String data) {
		this.value = data;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(format("\"id\": \"%s\"\n", id))
				.append(format("\"name\": \"%s\"\n", name))
				.append(format("\"command\": \"%s\"\n", command))
				.append(format("\"comment\": \"%s\"\n", comment))
				.append(format("\"target\": \"%s\"\n", target))
				.append(format("\"value\": %s\n", value)).toString();
	}
}
