package com.github.sergueik.ssstfx;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

// https://stackoverflow.com/questions/11038553/serialize-java-object-with-gson
public class SideRecordingSerializer implements JsonSerializer<SideRecording> {
	@Override
	public JsonElement serialize(final SideRecording sideRecording,
			final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		String id = sideRecording.getId();
		if (id != null && !id.isEmpty()) {
			result.add("id", new JsonPrimitive(id));
		}
		result.add("name", new JsonPrimitive(sideRecording.getName()));
		result.add("url", new JsonPrimitive(sideRecording.getUrl()));

		List<SideSuite> sideSuites = sideRecording.getSuites();
		if (sideSuites != null && !sideSuites.isEmpty()) {
			result.add("sideSuites", context.serialize(sideSuites));
		}

		List<SideTest> sideTests = sideRecording.getTests();
		if (sideTests != null && !sideTests.isEmpty()) {
			result.add("tests", context.serialize(sideTests));
		}

		List<String> urls = sideRecording.getUrls();
		if (urls != null && !urls.isEmpty()) {
			result.add("urls", context.serialize(urls));
		}
		/*
		if (urls != null && !urls.isEmpty()) {
			result.add("templateDirectory", new JsonPrimitive(urls));
		}
		String templatePath = sideRecording.getTemplatePath();
		if (templatePath != null && !templatePath.isEmpty()) {
			result.add("templatePath", new JsonPrimitive(templatePath));
		}
		Template template = sideRecording.getTemplate();
		if (template != null) {
		    result.add("template", new JsonPrimitive(template.getId()));
		}
		*/
		return result;
	}
}
