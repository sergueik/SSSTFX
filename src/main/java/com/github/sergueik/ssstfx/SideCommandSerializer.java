package com.github.sergueik.ssstfx;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

// https://stackoverflow.com/questions/11038553/serialize-java-object-with-gson

public class SideCommandSerializer implements JsonSerializer<SideCommand> {
	@Override
	public JsonElement serialize(final SideCommand sideCommand, final Type type,
			final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		String id = sideCommand.getId();
		if (id != null && !id.isEmpty()) {
			result.add("id", new JsonPrimitive(id));
		}
		result.add("name", new JsonPrimitive(sideCommand.getName()));
		String command = sideCommand.getCommand();
		if (command != null) {
			result.add("command", new JsonPrimitive(command));
		}
		String comment = sideCommand.getComment();
		if (comment != null) {
			result.add("comment", new JsonPrimitive(comment));
		}
		String target = sideCommand.getTarget();
		if (target != null) {
			result.add("target", new JsonPrimitive(target));
		}
		String value = sideCommand.getValue();
		if (value != null) {
			result.add("value", new JsonPrimitive(value));
		}
		return result;
	}
}
