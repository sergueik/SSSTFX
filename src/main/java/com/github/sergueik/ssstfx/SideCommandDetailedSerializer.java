package com.github.sergueik.ssstfx;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

// https://stackoverflow.com/questions/11038553/serialize-java-object-with-gson

// serializes everything including extracted fields
// NOTE: since it is just a lambda there seems no way of extending one with another
public class SideCommandDetailedSerializer
		implements JsonSerializer<SideCommand> {
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
		// NOTE: Selector is not the String
		Selector selector = sideCommand.getSelector();
		if (selector != null) {
			// gson provides default serialization/deserialization for Enum
			result.add("selector", context.serialize(selector));
		}
		String selectorValue = sideCommand.getSelectorValue();
		if (selectorValue != null) {
			result.add("selectorValue", new JsonPrimitive(selectorValue));
		}
		Operation operation = sideCommand.getOperation();
		if (operation != null) {
			result.add("operation", context.serialize(operation));
		}

		return result;
	}
}
