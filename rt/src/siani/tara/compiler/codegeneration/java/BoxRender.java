package siani.tara.compiler.codegeneration.java;

import org.monet.templation.Canvas;
import org.monet.templation.CanvasLogger;
import org.monet.templation.Render;
import siani.tara.lang.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static siani.tara.lang.Annotations.Annotation.*;

public class BoxRender extends Render {
	private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(BoxRender.class.getName());
	private final Set<Node> nodes;
	private final String boxName;

	public BoxRender(Set<Node> nodes, String boxName) {
		super(new Logger(), Canvas.FROM_RESOURCES_PREFIX);
		this.nodes = nodes;
		this.boxName = boxName;
	}


	@Override
	protected void init() {
		loadCanvas("/templates/box", true);
		addMark("imports", getImportsAsMark(nodes));
		addMark("identifiers", getIdentifiersAsMark(nodes));
		addMark("implicatedParentBoxes", getImplicatedParentBoxesAsMark(nodes));
		addMark("nodeLoads", getNodeLoadsAsMark(nodes));
		addMark("BoxName", boxName);
	}

	private String getNodeLoadsAsMark(Set<Node> nodes) {
		StringBuilder nodeLoadsBuilder = new StringBuilder();
		for (Node node : nodes)
			if (node.isPrime())
				nodeLoadsBuilder.append("\t\t").append(processNodeAsMark(node));
		return nodeLoadsBuilder.toString();
	}

	private String processNodeAsMark(Node node) {
		StringBuilder nodeLoadBuilder = new StringBuilder();
		Map<String, Object> map = new HashMap<>();
		map.put("nameUpper", node.getName().toUpperCase());
		map.put("nameProper", toProperCase(node.getName()));
		map.put("as", node.getObject().getType().toUpperCase());
		map.put("annotations", getAnnotationsAsBlock(node.getObject().getAnnotations()));
		map.put("indef", processInnerNodes(node));
		map.put("has", processLinkNodes(node));
		map.put("set", processVariableSets(node));
		return nodeLoadBuilder.append(block("nodeLoads", map)).toString();
	}

	private String processVariableSets(Node node) {
		StringBuilder variableBuilder = new StringBuilder();
		for (Variable variable : node.getObject().getVariables()) {
			if (variable.getValue() == null) continue;
			Map<String, Object> map = new HashMap<>();
			map.put("nodeName", node.getName());
			map.put("var", variable.getName());
			map.put("value", variable.getValue());
			variableBuilder.append(block("set", map));
		}
		return variableBuilder.toString();
	}


	private String processInnerNodes(Node node) {
		StringBuilder nodeLoadBuilder = new StringBuilder();
		for (Node inner : node.getInnerNodes()) {
			Map<String, Object> map = new HashMap<>();
			if (inner instanceof DeclaredNode)
				map.put("indef", processNodeAsMark(inner));
			nodeLoadBuilder.append(block("indef", map));
		}
		return nodeLoadBuilder.toString();
	}

	private String processLinkNodes(Node node) {
		StringBuilder nodeLoadBuilder = new StringBuilder();
		for (Node inner : node.getInnerNodes()) {
			Map<String, Object> map = new HashMap<>();
			if (inner instanceof LinkNode)
				map.put("has", processLinkNode((LinkNode) node));
			nodeLoadBuilder.append(block("has", map));
		}
		return nodeLoadBuilder.toString();
	}

	private String processLinkNode(LinkNode node) {
		StringBuilder nodeLoadBuilder = new StringBuilder();
		Map<String, Object> map = new HashMap<>();
		map.put("nameUpper", node.getName().toUpperCase());
		return nodeLoadBuilder.append(block("has", map)).toString();
	}

	private Object getAnnotationsAsBlock(Annotations.Annotation[] annotations) {
		StringBuilder annotationsBuilder = new StringBuilder();
		for (Annotations.Annotation annotation : annotations) {
			Map<String, Object> map = new HashMap<>();
			map.put("annotation", annotation.getName());
			annotationsBuilder.append(block("annotations", map).trim());
		}
		return annotationsBuilder.toString();
	}

	private String getImportsAsMark(Set<Node> nodes) {
		StringBuilder importsBuilder = new StringBuilder();
		Set<String> imports = collectQnNodes(nodes);
		for (String anImport : imports) {
			Map<String, Object> map = new HashMap<>();
			map.put("qn", anImport);
			importsBuilder.append(block("imports", map));
		}
		return importsBuilder.toString();
	}

	private String getIdentifiersAsMark(Set<Node> nodes) {
		StringBuilder idBuilder = new StringBuilder();
		Set<String> identifiers = collectIdentifiers(nodes);
		for (String id : identifiers) {
			Map<String, Object> map = new HashMap<>();
			map.put("identifierUpper", id.toUpperCase());
			map.put("identifier", id);
			idBuilder.append("\t").append(block("identifiers", map));
		}
		return idBuilder.toString();
	}

	private String getImplicatedParentBoxesAsMark(Set<Node> nodes) {
		StringBuilder boxesBuilder = new StringBuilder();
		Set<String> boxes = collectImports(nodes);
		for (String aBox : boxes) {
			Map<String, Object> map = new HashMap<>();
			map.put("box", aBox);
			boxesBuilder.append(block("implicatedParentBoxes", map));
		}
		return boxesBuilder.toString();
	}

	private Set<String> collectQnNodes(Set<Node> nodes) {
		Set<String> set = new HashSet<>();
		for (Node node : nodes) {
			if (node.isPrime() && !node.getObject().is(INTENTION))
				set.add(node.getQualifiedName());
		}
		return set;
	}

	private Set<String> collectIdentifiers(Set<Node> nodes) {
		Set<String> identifiers = new HashSet<>();
		for (Node node : nodes)
			if (!node.getName().isEmpty())
				identifiers.add(node.getName());
		return identifiers;
	}

	private Set<String> collectImports(Set<Node> nodes) {
		Set<String> imports = new HashSet<>();
		for (Node node : nodes) imports.addAll(node.getImports());
		return imports;
	}

	private String toProperCase(String word) {
		return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
	}


	private static class Logger implements CanvasLogger {
		@Override
		public void debug(String message, Object... args) {
			LOG.severe(String.format(message, args));
		}
	}
}
