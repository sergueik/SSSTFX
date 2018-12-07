package com.github.sergueik.ssstfx;
/**
 * Copyright 2018 Serguei Kouzmine
 */

import static java.lang.String.format;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.sergueik.ssstfx.Operation;

/**
 * Selenium IDE TNG Side Command JSON mapped class
 * @author: Serguei Kouzmine (kouzmine_serguei@yahoo.com)
 */

final class SideCommand {

	private String name;
	private String id;
	private String command;
	private String comment;
	private String target;
	private String value;
	// extracted fields
	private Selector selector;
	private String selectorValue;
	private Operation operation;
	// "operationArgument" would be redundant with value

	public String getName() {
		return name;
	}

	public void setName(String data) {
		this.name = data;
	}

	public String getId() {
		return id;
	}

	public Selector getSelector() {
		return selector;
	}

	public void setSelector(Selector data) {
		this.selector = data;
	}

	public void setSelector(String data) {
		System.err
				.println(String.format("Executing setSelector from: \"%s\"", data));
		if (!data.isEmpty()) {
			System.err.println("Processing: " + data);
			if (data.matches("^(\\w+)=(.+)$")) {
				String sel = data.replaceFirst("=(.+)$", "");
				System.err.println("setSelector extracted sel: " + data);
				for (Selector obj : Selector.values()) {
					if (obj.getSelector().equals(sel)) {
						this.selector = obj;
						this.selectorValue = data.replaceFirst(String.format("^%s=", sel),
								"");
					}
				}
			}
		}
	}

	public String getSelectorValue() {
		return selectorValue;
	}

	public void setSelectorValue(String data) {
		this.selectorValue = data;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation data) {
		this.operation = data;
	}

	public void setOperation(String data) {
		for (Operation op : Operation.values()) {
			if (op.getOperation().equals(data)) {
				this.operation = op;
			}
		}
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
				.append(format("\"selector\": \"%s\"\n", selector))
				.append(format("\"selectorValue\": \"%s\"\n", selectorValue))
				.append(format("\"operation\": \"%s\"\n", operation))
				.append(format("\"value\": \"%s\"\n", value)).toString();
	}
}
