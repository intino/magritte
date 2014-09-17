package siani.tara.lang.util;

import com.google.gson.*;
import siani.tara.lang.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ModelLoader {
	protected static final String JSON = ".json";
	private static final Logger LOG = Logger.getLogger(ModelLoader.class.getName());

	public static Model load(String modelsDirectory, String model) {
		try {
			File file = new File(modelsDirectory, model + JSON);
			if (!file.exists()) throw new Exception("Model file not found");
			InputStream heritageInputStream = new FileInputStream(file);
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapter(Node.class, new NodeAdapter());
			gb.registerTypeAdapter(ModelObject.class, new NodeObjectAdapter());
			gb.registerTypeAdapter(Variable.class, new VariableDeserializer());
			Model aModel = gb.create().fromJson(new InputStreamReader(heritageInputStream), Model.class);
			restoreTreeLinks(aModel, aModel.getTreeModel());
			return aModel;
		} catch (Exception e) {
			LOG.severe("Error loading model " + model + ": ");
			e.printStackTrace();
			return null;
		}
	}

	private static void restoreTreeLinks(Model aModel, NodeTree tree) {
		Map<String, Node> nodeTable = new HashMap();
		for (Node node : tree) restoreDestinyLinks(node, aModel);
		for (Node node : tree) {
			nodeTable.put(node.getQualifiedName(), node);
			processInnerNodes(aModel, nodeTable, node);
			processCases(aModel, nodeTable, node);
		}
		aModel.setNodeTable(nodeTable);
		restoreHierarchyLinks(aModel);
	}

	private static void restoreDestinyLinks(Node node, Model model) {
		for (Node inner : node.getInnerNodes())
			if (inner instanceof LinkNode) {
				LinkNode linkNode = (LinkNode) inner;
				((LinkNode) inner)
					.setDestiny((DeclaredNode) model.searchNode(linkNode.getDestinyQN().replace(linkNode.getDestinyBox() + ".", "")));
			} else restoreDestinyLinks(inner, model);
	}

	private static void restoreHierarchyLinks(Model aModel) {
		for (Node node : aModel.getNodeTable().values())
			if (node instanceof DeclaredNode) {
				DeclaredNode declaredNode = (DeclaredNode) node;
				String parent = declaredNode.getObject().getParentName();
				if ((parent) != null) {
					Node parentNode = aModel.get(parent);
					parentNode.getObject().addChild(node.getObject());
					node.getObject().setParentObject(parentNode.getObject());
				}
			}
	}

	private static void processInnerNodes(Model aModel, Map<String, Node> nodeTable, Node node) {
		for (Node inner : node.getInnerNodes()) {
			if (inner instanceof LinkNode) {
				LinkNode linkNode = (LinkNode) inner;
				String replace = linkNode.getDestinyQN().replace(linkNode.getDestinyBox() + ".", "");
				DeclaredNode destiny = (DeclaredNode) aModel.searchNode(replace);
				linkNode.setDestiny(destiny);
			} else {
				processCases(aModel, nodeTable, inner);
				processInnerNodes(aModel, nodeTable, inner);
			}
			nodeTable.put(inner.getQualifiedName(), inner);
		}
	}

	private static void processCases(Model aModel, Map<String, Node> nodeTable, Node node) {
		for (DeclaredNode aCase : node.getSubConcepts()) {
			nodeTable.put(aCase.getQualifiedName(), aCase);
			processInnerNodes(aModel, nodeTable, aCase);
		}
	}

	protected static class VariableDeserializer implements JsonDeserializer<Variable> {

		public Variable deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			if (json == null) return null;
			String name = json.getAsJsonObject().get("name").getAsString();

			JsonElement e = json.getAsJsonObject().get("node");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null) {
				Reference reference = new Reference(e.getAsString(), name, json.getAsJsonObject().get("isList").getAsBoolean(), json.getAsJsonObject().get("isTerminal").getAsBoolean());
				JsonElement empty = json.getAsJsonObject().get("empty");
				if (empty != null && empty.isJsonPrimitive()) reference.setEmpty(empty.getAsBoolean());
				JsonElement isProperty = json.getAsJsonObject().get("isProperty");
				if (isProperty != null && isProperty.isJsonPrimitive())
					reference.setProperty(isProperty.getAsBoolean());
				return reference;
			}

			e = json.getAsJsonObject().get("primitiveType");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null) {
				NodeAttribute attr = new NodeAttribute(e.getAsString(), name, json.getAsJsonObject().get("isList").getAsBoolean(), json.getAsJsonObject().get("isTerminal").getAsBoolean());
				if (json.getAsJsonObject().get("value") != null)
					attr.setValue(json.getAsJsonObject().get("value").getAsString());
				JsonElement isProperty = json.getAsJsonObject().get("isProperty");
				if (isProperty != null && isProperty.isJsonPrimitive()) attr.setProperty(isProperty.getAsBoolean());
				JsonElement measure = json.getAsJsonObject().get("measure");
				if (measure != null && measure.isJsonPrimitive()) attr.measure = measure.getAsString();
				return attr;
			}

			e = json.getAsJsonObject().get("resourceType");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null) {
				Resource resource = new Resource(e.getAsString(), name, json.getAsJsonObject().get("isTerminal").getAsBoolean());
				JsonElement isProperty = json.getAsJsonObject().get("isProperty");
				if (isProperty != null && isProperty.isJsonPrimitive()) resource.setProperty(isProperty.getAsBoolean());
				return resource;
			}

			JsonArray array = json.getAsJsonObject().get("wordTypes").getAsJsonArray();
			if (array != null && array.isJsonArray()) {
				NodeWord word = new NodeWord(name, json.getAsJsonObject().get("isTerminal").getAsBoolean());
				for (JsonElement jsonElement : array) word.add(jsonElement.getAsString());
				JsonElement isProperty = json.getAsJsonObject().get("isProperty");
				if (isProperty != null && isProperty.isJsonPrimitive()) word.setProperty(isProperty.getAsBoolean());
				JsonElement defaultWord = json.getAsJsonObject().get("defaultWord");
				if (defaultWord != null && defaultWord.isJsonPrimitive()) word.setDefaultWord(defaultWord.getAsShort());
				if (json.getAsJsonObject().get("value") != null)
					word.setValue(json.getAsJsonObject().get("value").getAsString());
				return word;
			}
			return null;
		}
	}

	protected static class NodeAdapter implements JsonDeserializer<Node> {
		@Override
		public Node deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
			JsonObject jsonObject = json.getAsJsonObject();
			String type = jsonObject.get("NodeType").getAsString();
			JsonElement element = jsonObject.get("properties");
			try {
				return context.deserialize(element, Class.forName("siani.tara.lang." + type));
			} catch (ClassNotFoundException cnfe) {
				throw new JsonParseException("Unknown element type: " + type, cnfe);
			}
		}
	}

	protected static class NodeObjectAdapter implements JsonDeserializer<NodeObject> {
		@Override
		public NodeObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
			JsonObject jsonObject = json.getAsJsonObject();
			String type = jsonObject.get("ObjectType").getAsString();
			JsonElement element = jsonObject.get("properties");
			try {
				return context.deserialize(element, Class.forName("siani.tara.lang." + type));
			} catch (ClassNotFoundException cnfe) {
				throw new JsonParseException("Unknown element type: " + type, cnfe);
			}
		}
	}
}
