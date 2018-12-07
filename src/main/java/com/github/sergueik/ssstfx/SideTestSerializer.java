package com.github.sergueik.ssstfx;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

// https://stackoverflow.com/questions/11038553/serialize-java-object-with-gson
public class SideTestSerializer implements JsonSerializer<SideTest> {
	@Override
	public JsonElement serialize(final SideTest sideTest, final Type type,
			final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		String id = sideTest.getId();
		if (id != null && !id.isEmpty()) {
			result.add("id", new JsonPrimitive(id));
		}
		result.add("name", new JsonPrimitive(sideTest.getName()));
		List<SideCommand> sideCommands = sideTest.getCommands();

		// unused.
		// JsonSerializer<SideCommand> sideCommandSerializer = new
		// SideCommandSerializer();
		// Type sideCommandListType = new TypeToken<ArrayList<SideCommand>>()
		// {}.getType();

		if (sideCommands != null && !sideCommands.isEmpty()) {
			result.add("commands", context.serialize(sideCommands));
		}
		return result;
	}
}
