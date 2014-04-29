package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.codegeneration.render.grammarcomponents.AttributeSimplifier;
import monet.tara.compiler.codegeneration.render.grammarcomponents.Constituents;
import monet.tara.compiler.codegeneration.render.grammarcomponents.attribute.ExplicitAttributes;
import monet.tara.compiler.codegeneration.render.grammarcomponents.attribute.ImplicitAttributes;
import monet.tara.lang.ASTNode;
import monet.tara.lang.ASTWrapper;
import org.monet.templation.Canvas;
import org.monet.templation.CanvasLogger;
import org.monet.templation.Render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BnfRender extends Render {
	private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(BnfRender.class.getName());
	private String tplName;
	private String projectName;
	private ASTWrapper ast;
	private List<ASTNode> rootList = new ArrayList<>();
	private List<String> synthesizeList = new ArrayList<>();

	public BnfRender(String projectName, String tplName, ASTWrapper ast) {
		super(new Logger(), Canvas.FROM_RESOURCES_PREFIX);
		this.tplName = tplName;
		this.projectName = projectName;
		this.ast = ast;
	}

	@Override
	protected void init() {
		loadCanvas(tplName, true);
		addMark("projectNameFile", RenderUtils.toProperCase(projectName));
		addMark("projectName", projectName.toLowerCase());
		for (ASTNode node : ast.getAST()) goOver(node);
		generateListOfRootConcepts();
		generateSynthesizeList();
	}

	private void generateSynthesizeList() {
		StringBuilder concepts = new StringBuilder();
		for (String concept : synthesizeList) {
			Map<String, Object> localMap = new HashMap<>();
			localMap.put("identifier", concept);
			localMap.put("pipe", (synthesizeList.indexOf(concept) != 0 && concepts.length() != 0) ? "|" : "");
			concepts.append(block("concept", localMap));
		}
		addMark("conceptKeyList", concepts.toString());
	}

	private String getTransformedAbsolutePath(ASTNode node) {
		return node.getAbsolutePath().toLowerCase().replaceAll("\\.", "_");
	}

	private void goOver(ASTNode node) {
		if (!node.isAbstract() && !node.isPolymorphic()) {
			generateMainRulesForConcepts(node);
			if (!synthesizeList.contains(ast.getKeys(node.getIdentifier()).get(0)))
				synthesizeList.add(ast.getKeys(node.getIdentifier()).get(0));
			if (isExtendedRoot(node)) rootList.add(node);
		}
		for (ASTNode child : node.getChildren())
			if (!"".equals(child.getIdentifier())) goOver(child);
	}

	private void generateListOfRootConcepts() {
		StringBuilder concepts = new StringBuilder();
		for (ASTNode node : rootList) {
			Map<String, Object> localMap = new HashMap<>();
			localMap.put("identifier", getTransformedAbsolutePath(node));
			localMap.put("pipe", (rootList.indexOf(node) != 0 && concepts.length() != 0) ? "|" : "");
			concepts.append(block("concept", localMap));
		}
		addMark("concepts", concepts.toString());
	}

	private void generateMainRulesForConcepts(ASTNode node) {
		StringBuilder concepts = new StringBuilder();
		Map<String, Object> localMap = new HashMap<>();
		putMainComponentsToHashMap(node, localMap);
		putAttributesComponentsToHashMap(localMap,
			(new ImplicitAttributes(node, ast)).getAttributesString(),
			(new ExplicitAttributes(node, ast)).getAttributesString());
		putConstituentsToHashMap(localMap, (new Constituents(ast, node)).getConstituentString());
		concepts.append(block("ruleConcept", localMap));
		addMark("rules", concepts.toString());
	}

	private boolean containsRoot(ASTNode.AnnotationType[] annotations) {
		for (ASTNode.AnnotationType annotation : annotations)
			if (ASTNode.AnnotationType.ROOT.equals(annotation)) return true;
		return false;
	}

	private boolean containsGeneric(ASTNode.AnnotationType[] annotations) {
		for (ASTNode.AnnotationType annotation : annotations)
			if (ASTNode.AnnotationType.GENERIC.equals(annotation)) return true;
		return false;
	}

	private boolean isExtendedRoot(ASTNode node) {
		if (node != null) {
			if (containsRoot(node.getAnnotations())) return true;
			if (node.getParent() != null && node.isMorph()) return isExtendedRoot(node.getParent());
			if (ast.searchAncestry(node) != null) return isExtendedRoot(ast.searchAncestry(node));
		}
		return false;
	}

	private void putConstituentsToHashMap(Map<String, Object> localMap, String constituent) {
		localMap.put("identifierConstituent", (!"".equals(constituent)) ? " | " + localMap.get("identifier") + "Constituents" : "");
		localMap.put("constituentRule", (!"".equals(constituent)) ? "private " + localMap.get("identifier") + "Constituents ::= " + constituent : "");
	}

	private void putAttributesComponentsToHashMap(Map<String, Object> localMap, String attributesImplicit, String attributesExplicit) {
		localMap.put("assignAttributeHeader", (!"".equals(attributesImplicit)) ? "(LEFT_PARENTHESIS (" + localMap.get("identifier") + "AttributesImplicit |"
			+ localMap.get("identifier") + "AttributesExplicit)" + " RIGHT_PARENTHESIS )?" : ""); //TODO TOCADO
		localMap.put("typeValues", AttributeSimplifier.reduce(attributesImplicit));  //TODO MODIFICADO
		localMap.put("typeKey-Value", attributesExplicit);
		String attributeImplicitForm = localMap.get("identifier") + "AttributesImplicit ::= (" + localMap.get("typeValues") + ")\n";
		String attributeExplicit = localMap.get("identifier") + "AttributesExplicit ::= (" + localMap.get("typeKey-Value") + ")+";
		localMap.put("attributeRule", (!"".equals(localMap.get("assignAttributeHeader"))) ? attributeExplicit + "\n" + attributeImplicitForm : "");
		localMap.put("identifierExplicitAttribute", (!"".equals(localMap.get("assignAttributeHeader"))) ? "| " + localMap.get("identifier") + "AttributesExplicit" : "");
	}

	private void putMainComponentsToHashMap(ASTNode advance, Map<String, Object> localMap) {
		localMap.put("identifier", getTransformedAbsolutePath(advance));
		localMap.put("projectName", projectName.toLowerCase());
		localMap.put("lexicoIdentifier", ast.getKeys(advance.getIdentifier()).get(0));
		localMap.put("genericOption",
			(containsGeneric(advance.getAnnotations())) ? "| genericList" : "");
	}

	private static class Logger implements CanvasLogger {
		@Override
		public void debug(String message, Object... args) {
			LOG.severe(String.format(message, args));
		}
	}
}