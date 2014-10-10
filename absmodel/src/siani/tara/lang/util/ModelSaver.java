package siani.tara.lang.util;

import com.google.gson.*;
import siani.tara.lang.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class ModelSaver {

	protected static final String JSON_EXTENSION = ".json";

	public static boolean save(Model model, String modelsDirectory) {
		try {
			File file = new File(modelsDirectory, model.getModelName() + JSON_EXTENSION);
			file.getParentFile().mkdirs();
			FileWriter writer = new FileWriter(file);
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();
			gsonBuilder.registerTypeAdapter(Variable.class, new VariableSerializer());
			gsonBuilder.registerTypeAdapter(Node.class, new NodeAdapter());
			gsonBuilder.registerTypeAdapter(ModelObject.class, new ObjectAdapter());
			Gson gson = gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
			writer.write(gson.toJson(model));
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	public static class VariableSerializer implements JsonSerializer<Variable> {
		@Override
		public JsonElement serialize(Variable variable, Type type, JsonSerializationContext jsonSerializationContext) {
			final JsonObject object = new JsonObject();
			object.addProperty("name", variable.name);
			if (variable instanceof Word) {
				Word word = (Word) variable;
				final JsonArray list = new JsonArray();
				for (String wordType : word.wordTypes) list.add(new JsonPrimitive(wordType));
				object.add("wordTypes", list);
				object.addProperty("defaultWord", word.getDefaultWord());
				object.addProperty("value", word.getValue());
			} else if (variable instanceof Attribute) {
				Attribute attribute = (Attribute) variable;
				object.addProperty("primitiveType", attribute.primitiveType);
				object.addProperty("value", attribute.getValue());
				object.addProperty("isList", attribute.isList);
				if (attribute.measure != null) object.addProperty("measure", attribute.measure);
			} else if (variable instanceof Reference) {
				Reference reference = (Reference) variable;
				object.addProperty("node", reference.type);
				object.addProperty("isList", reference.isList);
				object.addProperty("empty", reference.empty);

			} else if (variable instanceof Resource) {
				Resource resource = (Resource) variable;
				object.addProperty("resourceType", resource.node);
			}
			object.addProperty("isTerminal", variable.isTerminal);
			object.addProperty("isProperty", variable.isProperty);
			object.addProperty("isUniversal", variable.isUniversal);
			return object; // or throw an IllegalArgumentException

		}
	}

	private static class NodeAdapter implements JsonSerializer<Node> {
		@Override
		public JsonElement serialize(Node src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject object = new JsonObject();
			object.add("NodeType", new JsonPrimitive(src.getClass().getSimpleName()));
			object.add("properties", context.serialize(src, src.getClass()));
			return object;
		}
	}

	private static class ObjectAdapter implements JsonSerializer<ModelObject> {
		@Override
		public JsonElement serialize(ModelObject src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject object = new JsonObject();
			object.add("ObjectType", new JsonPrimitive(src.getClass().getSimpleName()));
			object.add("properties", context.serialize(src, src.getClass()));
			return object;
		}
	}

}
