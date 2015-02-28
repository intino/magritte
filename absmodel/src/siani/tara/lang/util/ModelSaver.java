package siani.tara.lang.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import siani.tara.lang.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static siani.tara.lang.util.SerializingTags.*;

public class ModelSaver {

	private static final Logger LOG = Logger.getLogger(ModelSaver.class.getName());


	protected static final String JSON_EXTENSION = ".json";

	public static boolean save(Model model, String modelsDirectory) {
		try {
			File file = new File(modelsDirectory, model.getName() + JSON_EXTENSION);
			file.getParentFile().mkdirs();
			FileWriter writer = new FileWriter(file);
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat(DateFormat.FULL, DateFormat.FULL).setPrettyPrinting();
			gsonBuilder.registerTypeAdapter(Variable.class, new VariableSerializer());
			gsonBuilder.registerTypeAdapter(Node.class, new NodeAdapter());
			gsonBuilder.registerTypeAdapter(ModelObject.class, new ObjectAdapter());
			Type collectionType = new TypeToken<List<Variable>>() {
			}.getType();
			gsonBuilder.registerTypeAdapter(collectionType, new VariableListSerializer());
			Gson gson = gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
			writer.write(gson.toJson(model));
			writer.close();
			return true;
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			return false;
		}
	}

	public static class VariableSerializer implements JsonSerializer<Variable> {

		@Override
		public JsonElement serialize(Variable variable, Type type, JsonSerializationContext jsonSerializationContext) {
			final JsonObject object = new JsonObject();
			object.addProperty("name", variable.getName());
			if (variable instanceof Word) {
				Word word = (Word) variable;
				final JsonArray list = new JsonArray();
				for (String wordType : word.getWordTypes()) list.add(new JsonPrimitive(wordType));
				object.add("wordTypes", list);
			} else if (variable instanceof Attribute) {
				Attribute attribute = (Attribute) variable;
				object.addProperty(PRIMITIVE_TYPE, attribute.getPrimitiveType());
				object.addProperty(IS_LIST, attribute.isList());
				if (attribute.getMeasureValue() != null)
					object.addProperty("measureValue", attribute.getMeasureValue());
				if (attribute.getMeasureValue() != null) object.addProperty("measureType", attribute.getMeasureType());
				if (attribute.getCount() != null) object.addProperty("count", attribute.getCount());
			} else if (variable instanceof Reference) {
				Reference reference = (Reference) variable;
				object.addProperty(SerializingTags.TYPE, reference.getType());
				object.addProperty(IS_LIST, reference.isList());
				object.addProperty(EMPTY, reference.isEmpty());
				if (!reference.getInheritedTypes().isEmpty()) {
					JsonArray list = new JsonArray();
					for (String refType : reference.getInheritedTypes())
						list.add(new JsonPrimitive(refType));
					object.add(INSTANCE_TYPES, list);
				}
			} else if (variable instanceof Resource) {
				Resource resource = (Resource) variable;
				object.addProperty(FILE_TYPE, resource.getType());
			}
			JsonArray list = new JsonArray();
			for (Annotation value : variable.getAnnotations())
				list.add(new JsonPrimitive(value.getName()));
			object.add(ANNOTATIONS, list);
			list = new JsonArray();
			if (variable.getValues() != null) {
				for (Object value : variable.getValues())
					list.add(new JsonPrimitive(variable instanceof Word || variable instanceof Reference ? (String) value :
						Primitives.getConverter(variable.getType()).convert(value)[0]));
				object.add(VALUES, list);
			}
			list = new JsonArray();
			if (variable.getDefaultValues() != null) {
				for (Object value : variable.getDefaultValues())
					list.add(new JsonPrimitive(variable instanceof Word || variable instanceof Reference ? (String) value :
						Primitives.getConverter(variable.getType()).convert(value)[0]));
				object.add("defaultValues", list);
			}
			return object;
		}
	}

	private static class NodeAdapter implements JsonSerializer<Node> {
		@Override
		public JsonElement serialize(Node node, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject object = new JsonObject();
			object.add("NodeType", new JsonPrimitive(node.getClass().getSimpleName()));
			object.add("properties", context.serialize(node, node.getClass()));
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

	private static class VariableListSerializer implements JsonSerializer<Collection<Variable>> {
		@Override
		public JsonElement serialize(Collection<Variable> variables, Type type, JsonSerializationContext context) {
			JsonArray array = new JsonArray();
			for (Variable variable : variables) {
				if (!variable.isInherited() || variable.isTerminal())
					array.add(context.serialize(variable));
			}
			return array;
		}
	}
}