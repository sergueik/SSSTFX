package com.github.sergueik.ssstfx;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

// https://stackoverflow.com/questions/11038553/serialize-java-object-with-gson
public class SideSuiteSerializer implements JsonSerializer<SideSuite> {
	public JsonElement serialize(final SideSuite sideSuite, final Type type,
			final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		String id = sideSuite.getId();
		if (id != null && !id.isEmpty()) {
			result.add("id", new JsonPrimitive(id));
		}
		result.add("name", new JsonPrimitive(sideSuite.getName()));

		// NOTE: the tests of SideSuite are List<String> - not List<SideTest>
		List<String> tests = sideSuite.getTests();
		if (tests != null && !tests.isEmpty()) {
			result.add("tests", context.serialize(tests));
		}
		return result;
	}
}
