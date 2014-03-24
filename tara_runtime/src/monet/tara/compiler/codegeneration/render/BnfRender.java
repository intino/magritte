package monet.tara.compiler.codegeneration.render;

import monet.tara.compiler.codegeneration.render.grammarcomponents.Constituents;
import monet.tara.compiler.codegeneration.render.grammarcomponents.types.ExplicitAttributes;
import monet.tara.compiler.codegeneration.render.grammarcomponents.types.ImplicitAttributes;
import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.ast.ASTNode;
import org.monet.templation.CanvasLogger;
import org.monet.templation.Render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BnfRender extends Render {
	private static final java.util.logging.Logger LOG = java.util.logging.Logger.getLogger(DefaultRender.class.getName());
	private String tplName;
	private String projectName;
	private AST ast;
	private ArrayList<ASTNode> rootList = new ArrayList<>();

	public BnfRender(String projectName, String tplName, String path, AST ast) {
		super(new Logger(), path);
		this.tplName = tplName;
		this.projectName = projectName;
		this.ast = ast;
	}

	@Override
	protected void init() {
		loadCanvas(tplName, true);
		addMark("projectNameFile", RenderUtils.toProperCase(projectName));
		addMark("projectName", projectName.substring(0, 1).toLowerCase() + projectName.substring(1));
		for (ASTNode node : ast.getAstRootNodes()) goOver(node);
		generateListOfRootConcepts();
	}

	private String getTransformedAbsolutePath(ASTNode node) {
		return node.getAbsolutePath().toLowerCase().replaceAll("\\.", "_");
	}

	private void goOver(ASTNode node) {
		if (!node.isAbstract() && !node.isPolymorphic()) {
			generateMainRulesForConcepts(node);
			if (isExtendedRoot(node)) rootList.add(node);
		}
		for (ASTNode child : node.getChildren())
			if (!child.getIdentifier().equals("")) goOver(child);
	}

	private void generateListOfRootConcepts() {
		StringBuilder concepts = new StringBuilder();
		for (ASTNode node : rootList) {
			Map<String, Object> localMap = new HashMap<>();
			localMap.put("identifier", getTransformedAbsolutePath(node));
			localMap.put("pipe", (rootList.indexOf(node) != 0 && concepts.length() != 0) ? "|" : "");
			concepts.append(block("concept", (HashMap<String, Object>) localMap));
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
		concepts.append(block("ruleConcept", (HashMap<String, Object>) localMap));
		addMark("rules", concepts.toString());
	}

	private boolean containsRoot(ASTNode.AnnotationType[] annotations) {
		for (ASTNode.AnnotationType annotation : annotations)
			if (annotation.equals(ASTNode.AnnotationType.Root)) return true;
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

	private boolean hasExtendedCode(ASTNode node) {
		if (node != null) {
			if (node.hasCode()) return true;
			if (node.getParent() != null && node.isMorph()) return hasExtendedCode(node.getParent());
			if (ast.searchAncestry(node) != null) return hasExtendedCode(ast.searchAncestry(node));
		}
		return false;
	}

	private void putConstituentsToHashMap(Map<String, Object> localMap, String constituent) {
		localMap.put("identifierConstituent", (!constituent.equals("")) ? " | " + localMap.get("identifier") + "Constituents" : "");
		localMap.put("constituentRule", (!constituent.equals("")) ? "private " + localMap.get("identifier") + "Constituents ::= " + constituent : "");
	}

	private void putAttributesComponentsToHashMap(Map<String, Object> localMap, String attributesImplicit, String attributesExplicit) {
		localMap.put("assignAttributeHeader", (!attributesImplicit.equals("")) ? "(ASSIGN " + localMap.get("identifier") + "AttributesImplicit)?" : "");
		localMap.put("typeValues", attributesImplicit);
		localMap.put("typeKey-Value", attributesExplicit);
		String attributeImplicitForm = localMap.get("identifier") + "AttributesImplicit ::= " + localMap.get("typeValues") + "\n";
		String attributeExplicit = localMap.get("identifier") + "AttributesExplicit ::= " + localMap.get("typeKey-Value");
		localMap.put("attributeRule", (!localMap.get("assignAttributeHeader").equals("")) ? attributeExplicit + "\n" + attributeImplicitForm : "");
		localMap.put("identifierExplicitAttribute", (!localMap.get("assignAttributeHeader").equals("")) ? "| " + localMap.get("identifier") + "AttributesExplicit" : "");
	}

	private void putMainComponentsToHashMap(ASTNode advance, Map<String, Object> localMap) {
		localMap.put("identifier", getTransformedAbsolutePath(advance));
		localMap.put("code", hasExtendedCode(advance) ? "CODE" : "");
		localMap.put("projectName", projectName);
		localMap.put("lexicoIdentifier", ast.getKeys(advance.getIdentifier()).get(0));
	}

	private static class Logger implements CanvasLogger {
		@Override
		public void debug(String message, Object... args) {
			LOG.info(message);
		}
	}
}