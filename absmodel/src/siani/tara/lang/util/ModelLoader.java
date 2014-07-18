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

	public static Model load(String modelsDirectory, String parent) {
		try {
			File file = new File(modelsDirectory, parent + JSON);
			if (!file.exists()) return null;
			InputStream heritageInputStream = new FileInputStream(file);
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapter(Node.class, new NodeAdapter());
			gb.registerTypeAdapter(ModelObject.class, new NodeObjectAdapter());
			gb.registerTypeAdapter(Variable.class, new VariableDeserializer());
			Model aModel = gb.create().fromJson(new InputStreamReader(heritageInputStream), Model.class);
			restoreTreeLinks(aModel, aModel.getTree());
			return aModel;
		} catch (Exception e) {
			LOG.severe("Error loading model " + parent + ": ");
			e.printStackTrace();
			return null;
		}
	}

	private static void restoreTreeLinks(Model aModel, NodeTree tree) {
		Map<String, Node> nodeTable = new HashMap();
		for (Node node : tree) {
			nodeTable.put(node.getQualifiedName(), node);
			processInnerNodes(aModel, nodeTable, node);
			processCases(aModel, nodeTable, node);
		}
		aModel.setNodeTable(nodeTable);
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
		for (DeclaredNode aCase : node.getCases()) {
			nodeTable.put(aCase.getQualifiedName(), aCase);
			processInnerNodes(aModel, nodeTable, aCase);
		}
	}

	protected static class VariableDeserializer implements JsonDeserializer<Variable> {

		public Variable deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			if (json == null) return null;
			String name = json.getAsJsonObject().get("name").getAsString();

			JsonElement e = json.getAsJsonObject().get("node");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null) return new Reference(
				e.getAsString(), name, json.getAsJsonObject().get("isList").getAsBoolean(), json.getAsJsonObject().get("isTerminal").getAsBoolean());

			e = json.getAsJsonObject().get("primitiveType");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null)
				return new NodeAttribute(e.getAsString(), name, json.getAsJsonObject().get("isList").getAsBoolean(), json.getAsJsonObject().get("isTerminal").getAsBoolean());

			e = json.getAsJsonObject().get("resourceType");
			if (e != null && e.isJsonPrimitive() && e.getAsString() != null)
				return new Resource(e.getAsString(), name, json.getAsJsonObject().get("isTerminal").getAsBoolean());
			JsonArray array = json.getAsJsonObject().get("wordTypes").getAsJsonArray();
			if (array != null && array.isJsonArray()) {
				NodeWord word = new NodeWord(name, json.getAsJsonObject().get("isTerminal").getAsBoolean());
				for (JsonElement jsonElement : array) word.add(jsonElement.getAsString());
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
