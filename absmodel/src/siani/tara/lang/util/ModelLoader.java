package siani.tara.lang.util;

import com.google.gson.*;
import siani.tara.lang.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static siani.tara.lang.Primitives.getConverter;

public class ModelLoader {
	protected static final String JSON = ".json";
	private static final Logger LOG = Logger.getLogger(ModelLoader.class.getName());

	public static Model load(String modelsDirectory, String model) {
		try {
			File file = new File(modelsDirectory.toLowerCase(), model.toLowerCase() + JSON);
			if (!file.exists()) {
				LOG.log(Level.SEVERE, "Model file not found: " + model + " in " + modelsDirectory);
				return null;
			}
			return load(file);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "Error loading model " + model + ": " + e.getMessage(), e);
			return null;
		}
	}

	public static Model load(File file) {
		if (file == null || !file.exists()) return null;
		try {
			InputStream heritageInputStream = new FileInputStream(file);
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapter(Node.class, new NodeAdapter());
			gb.registerTypeAdapter(ModelObject.class, new NodeObjectAdapter());
			gb.registerTypeAdapter(Variable.class, new VariableDeserializer());
			Model aModel = gb.create().fromJson(new InputStreamReader(heritageInputStream), Model.class);
			restoreTreeLinks(aModel, aModel.getTreeModel());
			return aModel;
		} catch (FileNotFoundException e) {
			LOG.log(Level.SEVERE, "Error loading model " + file.getName() + ": " + e.getMessage(), e);
			return null;
		}
	}

	private static void restoreTreeLinks(Model model, NodeTree tree) {
		List<Node> nodeTable = new ArrayList<>();
		for (Node node : tree)
			restoreDestinyLinks(node, model);
		for (Node node : tree) {
			nodeTable.add(node);
			processInnerNodes(model, nodeTable, node);
			processSubs(model, nodeTable, node);
		}
		model.register(nodeTable.toArray(new Node[nodeTable.size()]));
		restoreHierarchyLinks(model);
		restoreFacetDestinies(model);
		restoreContainerReferences(model.getTreeModel());
	}

	private static void restoreContainerReferences(NodeTree tree) {
		for (Node node : tree) {
			if (node.is(LinkNode.class)) continue;
			for (Node inner : node.getInnerNodes()) {
				if (inner.is(LinkNode.class) && ((LinkNode) inner).isReference())
					inner.setContainer((DeclaredNode) node);
				setContainerToSubs((DeclaredNode) node, inner);
				restoreContainerReferences(node.getInnerNodes());
			}
		}
	}

	private static void setContainerToSubs(DeclaredNode node, Node inner) {
		if (node.isSub()) return;
		for (DeclaredNode sub : inner.getDeepSubNodes())
			if (sub.getContainer() == null)
				sub.setContainer(node);
	}

	private static void restoreFacetDestinies(Model model) {
		for (Node node : model.getNodeTable()) {
			if (node.is(LinkNode.class) || node.getObject().getAllowedFacets().isEmpty()) continue;
			for (List<FacetTarget> facetTargets : node.getObject().getAllowedFacets().values())
				for (FacetTarget target : facetTargets) target.setDestiny(model.get(target.getDestinyQN()).getObject());
		}
	}

	private static void restoreDestinyLinks(Node node, Model model) {
		for (Node inner : node.getInnerNodes())
			restoreInnerLinks(inner, model);
	}

	private static void restoreInnerLinks(Node inner, Model model) {
		if (inner instanceof LinkNode) {
			LinkNode linkNode = (LinkNode) inner;
			Node destiny = model.searchNode(linkNode.getDestinyQN());
			if (destiny == null)
				throw new RuntimeException("Destiny of LinkNode " + linkNode.getQualifiedName() + ", not found");
			linkNode.setDestiny((DeclaredNode) destiny);
		} else {
			restoreInnersOfTargets((DeclaredNode) inner, model);
			restoreDestinyLinks(inner, model);
		}
	}

	private static void restoreInnersOfTargets(DeclaredNode inner, Model model) {
		for (List<FacetTarget> target : inner.getObject().getAllowedFacets().values())
			for (FacetTarget facetTarget : target)
				for (Node node : facetTarget.getInner())
					restoreInnerLinks(node, model);
	}

	private static void restoreHierarchyLinks(Model aModel) {
		for (Node node : aModel.getNodeTable())
			if (node instanceof DeclaredNode) restoreParent(aModel, node);
	}

	private static void restoreParent(Model aModel, Node node) {
		DeclaredNode declaredNode = (DeclaredNode) node;
		String parent = declaredNode.getObject().getParentName();
		if (parent != null) {
			Node parentNode = aModel.get(parent);
			if (parentNode == null && declaredNode.is(Annotation.TERMINAL)) return;
			if (parentNode == null)
				throw new RuntimeException("Error loading language definition. Parent of node " + parent + "not found");
			parentNode.getObject().addChild(node.getObject());
			node.getObject().setParentObject(parentNode.getObject());
		}
	}

	private static void processInnerNodes(Model aModel, List<Node> nodeTable, Node node) {
		for (Node inner : node.getInnerNodes()) {
			if (inner instanceof LinkNode) {
				LinkNode linkNode = (LinkNode) inner;
				DeclaredNode destiny = (DeclaredNode) aModel.searchNode(linkNode.getDestinyQN());
				linkNode.setDestiny(destiny);
			} else {
				processSubs(aModel, nodeTable, inner);
				processInnerNodes(aModel, nodeTable, inner);
			}
			nodeTable.add(inner);
		}
	}

	private static void processSubs(Model aModel, List<Node> nodeTable, Node node) {
		for (DeclaredNode sub : node.getSubNodes()) {
			nodeTable.add(sub);
			processInnerNodes(aModel, nodeTable, sub);
		}
	}

	protected static class VariableDeserializer implements JsonDeserializer<Variable> {

		public Variable deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			if (json == null) return null;
			String name = json.getAsJsonObject().get("name").getAsString();
			Variable variable;
			JsonElement e;
			JsonArray array;
			if ((e = json.getAsJsonObject().get("type")) != null && e.isJsonPrimitive() && e.getAsString() != null) {
				variable = new Reference(e.getAsString(), name, json.getAsJsonObject().get("isList").getAsBoolean());
				processReference(json, (Reference) variable);
			} else if ((e = json.getAsJsonObject().get("primitiveType")) != null && e.isJsonPrimitive() && e.getAsString() != null) {
				variable = new Attribute(e.getAsString(), name, json.getAsJsonObject().get("isList").getAsBoolean());
				processAttribute(json, (Attribute) variable);
			} else if (json.getAsJsonObject().get("wordTypes") != null && json.getAsJsonObject().get("wordTypes").isJsonArray()) {
				array = json.getAsJsonObject().get("wordTypes").getAsJsonArray();
				variable = new Word(name);
				for (JsonElement jsonElement : array) ((Word) variable).add(jsonElement.getAsString());
			} else
				variable = new Resource(json.getAsJsonObject().get("fileType").getAsString(), name);
			if (json.getAsJsonObject().get("annotations") != null &&
				(array = json.getAsJsonObject().get("annotations").getAsJsonArray()) != null && array.isJsonArray())
				for (JsonElement jsonElement : array)
					variable.add(Annotation.valueOf(jsonElement.getAsString().toUpperCase()));
			if (json.getAsJsonObject().get("values") != null &&
				(array = json.getAsJsonObject().get("values").getAsJsonArray()) != null && array.isJsonArray())
				for (JsonElement jsonElement : array)
					variable.addValue(getConverter(variable.getType()).convert(jsonElement.getAsString()));
			if ((json.getAsJsonObject().get("defaultValues")) != null && json.getAsJsonObject().get("defaultValues").isJsonArray()) {
				array = json.getAsJsonObject().get("defaultValues").getAsJsonArray();
				for (JsonElement jsonElement : array)
					variable.setDefaultValues(getConverter(variable.getType()).convert(jsonElement.getAsString()));
			}
			return variable;
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
			if ((json.getAsJsonObject().get("instanceTypes")) != null && json.getAsJsonObject().get("instanceTypes").isJsonArray()) {
				JsonArray array = json.getAsJsonObject().get("instanceTypes").getAsJsonArray();
				for (JsonElement jsonElement : array)
					reference.addInheritedType(jsonElement.getAsString());
			}
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
