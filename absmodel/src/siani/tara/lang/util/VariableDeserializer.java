package siani.tara.lang.util;

import com.google.gson.*;
import siani.tara.lang.*;

import java.lang.reflect.Type;

import static siani.tara.lang.Primitives.getConverter;

class VariableDeserializer implements JsonDeserializer<Variable> {

	public Variable deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		if (json == null) return null;
		String name = json.getAsJsonObject().get(SerializingTags.NAME).getAsString();
		Variable variable;
		variable = createVariable(json, name);
		getAnnotations(json, variable);
		getValues(json, variable);
		getDefaultValues(json, variable);
		return variable;
	}

	private Variable createVariable(JsonElement json, String name) {
		JsonElement e;
		Variable variable;
		JsonArray array;
		if ((e = json.getAsJsonObject().get(SerializingTags.TYPE)) != null && e.isJsonPrimitive() && e.getAsString() != null) {
			variable = new Reference(e.getAsString(), name, json.getAsJsonObject().get(SerializingTags.IS_LIST).getAsBoolean());
			processReference(json, (Reference) variable);
		} else if ((e = json.getAsJsonObject().get(SerializingTags.PRIMITIVE_TYPE)) != null && e.isJsonPrimitive() && e.getAsString() != null) {
			variable = new Attribute(e.getAsString(), name, json.getAsJsonObject().get(SerializingTags.IS_LIST).getAsBoolean());
			processAttribute(json, (Attribute) variable);
		} else if (json.getAsJsonObject().get(SerializingTags.WORD_TYPES) != null && json.getAsJsonObject().get(SerializingTags.WORD_TYPES).isJsonArray()) {
			array = json.getAsJsonObject().get(SerializingTags.WORD_TYPES).getAsJsonArray();
			variable = new Word(name);
			for (JsonElement jsonElement : array) ((Word) variable).add(jsonElement.getAsString());
		} else
			variable = new Resource(json.getAsJsonObject().get(SerializingTags.FILE_TYPE).getAsString(), name);
		return variable;
	}

	private void getAnnotations(JsonElement json, Variable variable) {
		JsonArray array;
		if (json.getAsJsonObject().get(SerializingTags.ANNOTATIONS) != null &&
			(array = json.getAsJsonObject().get(SerializingTags.ANNOTATIONS).getAsJsonArray()) != null && array.isJsonArray())
			for (JsonElement jsonElement : array)
				variable.add(Annotation.valueOf(jsonElement.getAsString().toUpperCase()));
	}

	private void getValues(JsonElement json, Variable variable) {
		JsonArray array;
		if (json.getAsJsonObject().get(SerializingTags.VALUES) != null &&
			(array = json.getAsJsonObject().get(SerializingTags.VALUES).getAsJsonArray()) != null && array.isJsonArray())
			for (JsonElement jsonElement : array)
				variable.addValue(getConverter(variable.getType()).convert(jsonElement.getAsString()));
	}

	private void getDefaultValues(JsonElement json, Variable variable) {
		JsonArray array;
		if ((json.getAsJsonObject().get(SerializingTags.DEFAULT_VALUES)) != null && json.getAsJsonObject().get(SerializingTags.DEFAULT_VALUES).isJsonArray()) {
			array = json.getAsJsonObject().get(SerializingTags.DEFAULT_VALUES).getAsJsonArray();
			for (JsonElement jsonElement : array)
				variable.setDefaultValues(getConverter(variable.getType()).convert(jsonElement.getAsString()));
		}
	}

	private void processAttribute(JsonElement json, Attribute attribute) {
		JsonElement measureValue = json.getAsJsonObject().get("measureValue");
		if (measureValue != null && measureValue.isJsonPrimitive())
			attribute.setMeasureValue(measureValue.getAsString());
		JsonElement measureType = json.getAsJsonObject().get("measureType");
		if (measureType != null && measureType.isJsonPrimitive())
			attribute.setMeasureType(measureType.getAsString());
		JsonElement count = json.getAsJsonObject().get("count");
		if (count != null && count.isJsonPrimitive())
			attribute.setCount(count.getAsInt());
	}

	private void processReference(JsonElement json, Reference reference) {
		JsonElement empty = json.getAsJsonObject().get("empty");
		if (empty != null && empty.isJsonPrimitive())
			reference.setEmpty(empty.getAsBoolean());
		if ((json.getAsJsonObject().get(SerializingTags.INSTANCE_TYPES)) != null && json.getAsJsonObject().get(SerializingTags.INSTANCE_TYPES).isJsonArray()) {
			JsonArray array = json.getAsJsonObject().get(SerializingTags.INSTANCE_TYPES).getAsJsonArray();
			for (JsonElement jsonElement : array)
				reference.addInheritedType(jsonElement.getAsString());
		}
	}
}
