package tara.intellij.lang.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import tara.intellij.lang.file.TaraFileType;
import tara.intellij.lang.psi.*;
import tara.lang.model.Node;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;

import java.util.Collection;
import java.util.Map;

public class TaraElementFactoryImpl extends TaraElementFactory {

	private static final String DUMMY_CONCEPT = "Dummy Ficha";
	private static final String CONCEPT_DUMMY = "Concept Dummy";
	private final Project project;

	public TaraElementFactoryImpl(Project project) {
		this.project = project;
	}

	public TaraNode createNode(String name, String type) {
		final TaraModelImpl file = createDummyFile(
			type + " " + name + "\n"
		);
		return (TaraNode) file.components().iterator().next();
	}

	public TaraNode createNode(String name) {
		final TaraModelImpl file = createDummyFile(
			"Concept" + " " + name + "\n"
		);
		return (TaraNode) file.components().iterator().next();
	}

	public TaraNode createNodeWithType(String type) {
		final TaraModelImpl file = createDummyFile(
			type + " " + "Dummy" + "\n"
		);
		return (TaraNode) file.components().iterator().next();
	}


	public TaraModelImpl createDummyFile(String text) {
		return (TaraModelImpl) PsiFileFactory.getInstance(project).createFileFromText("dummy." + TaraFileType.INSTANCE.getDefaultExtension(), TaraFileType.INSTANCE, text);
	}

	public MetaIdentifier createMetaIdentifier(String type) {
		return PsiTreeUtil.getChildOfType(createNodeWithType(type).getSignature(), MetaIdentifier.class);
	}

	public Identifier createNameIdentifier(String name) {
		return PsiTreeUtil.getChildOfType(createNode(name).getSignature(), Identifier.class);
	}

	public TaraVariable createVariable(String name, Primitive type) {
		final TaraModelImpl file = createDummyFile(
			CONCEPT_DUMMY + "\n" +
				"\tvar " + type.getName() + " " + name + "\n" +
				"\t" + CONCEPT_DUMMY + "2\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, TaraNode.class).getBody();
		return body != null ? (TaraVariable) body.getFirstChild().getNextSibling() : null;
	}

	public TaraVariable createResource(String name, String type) {
		final TaraModelImpl file = createDummyFile(
			CONCEPT_DUMMY + "\n" +
				"\tvar resource:" + type + " " + name + "\n" +
				"\tConcept Ontology\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, TaraNode.class).getBody();
		return body != null ? (TaraVariable) body.getFirstChild().getNextSibling() : null;
	}

	@Override
	public PsiElement createMetaWordIdentifier(String module, String node, String name) {
		final TaraModelImpl file = createDummyFile(
			node + "(" + node + "." + name + ")" + " Dummy" + "\n"
		);
		Collection<Parameter> parameters = PsiTreeUtil.getChildOfType(file, TaraNode.class).getSignature().getParameters().getParameters();
		return ((PsiElement) parameters.iterator().next()).getLastChild().getLastChild();
	}

	@Override
	public TaraVariable createWord(String name, String[] types) {
		final TaraModelImpl file = createDummyFile(
			CONCEPT_DUMMY + "\n" +
				"\tvar word " + name + "\n" +
				getWordTypesToString(types));
		Body body = PsiTreeUtil.getChildOfType(file, TaraNode.class).getBody();
		return body != null ? (TaraVariable) body.getVariableList().get(0) : null;
	}

	private String getWordTypesToString(String[] types) {
		StringBuilder builder = new StringBuilder();
		for (String type : types) builder.append("\t\t").append(type).append("\n");
		return builder.toString();
	}


	public TaraImports createImport(String reference) {
		final TaraModelImpl file = createDummyFile(
			"dsl Proteo\n\n" +
				"use " + reference + "\n" +
				CONCEPT_DUMMY + "\n"
		);
		return PsiTreeUtil.getChildrenOfType(file, TaraImports.class)[0];
	}

	public TaraDslDeclaration createDslDeclaration(String name) {
		final TaraModelImpl file = createDummyFile(
			"dsl " + name + "\n\n" +
				CONCEPT_DUMMY + "\n"
		);
		return PsiTreeUtil.getChildrenOfType(file, TaraDslDeclaration.class)[0];
	}

	public PsiElement createNewLine() {
		final TaraModelImpl file = createDummyFile("\n");
		return file.getFirstChild();
	}

	@Override
	public Parameters createEmptyParameters() {
		final TaraModelImpl file = createDummyFile(
			"Form(" + ")" + "Ficha\n"
		);
		final Node next = file.components().iterator().next();
		return ((TaraNode) next).getSignature().getParameters();
	}

	@Override
	public Parameters createParameters(boolean stringValue) {
		final TaraModelImpl file = createDummyFile(
			"Form(" + (stringValue ? "\"\"" : "") + ")" + "Ficha\n"
		);
		final Node next = file.components().iterator().next();
		return ((TaraNode) next).getSignature().getParameters();
	}

	@Override
	public Parameters createParameters(String... names) {
		String parameters = buildParameters(names);
		final TaraModelImpl file = createDummyFile(
			"Dummy(" + parameters + ")" + " Ficha\n"
		);
		final Node next = file.components().iterator().next();
		return ((TaraNode) next).getSignature().getParameters();
	}

	private String buildParameters(String[] names) {
		StringBuilder parms = new StringBuilder();
		String separator = ", ";
		for (String name : names) parms.append(separator).append(name);
		return parms.substring(separator.length());
	}

	@Override
	public Parameters createExplicitParameters(Map<String, String> parameters) {
		String assigns = buildExplicitParameters(parameters);
		final TaraModelImpl file = createDummyFile(
			"Concept(" + assigns + ")" + " Ficha\n"
		);
		final Node next = file.components().iterator().next();
		return ((TaraNode) next).getSignature().getParameters();
	}

	@Override
	public PsiElement createParameterSeparator() {
		final TaraModelImpl file = createDummyFile(
			"Dummy(" + "x = 1, y = 2" + ")" + " Ficha\n"
		);
		final TaraNode node = (TaraNode) file.components().iterator().next();
		return node.getSignature().getParameters().getNode().getChildren(TokenSet.create(TaraTypes.COMMA))[0].getPsi();
	}

	private String buildExplicitParameters(Map<String, String> parameters) {
		StringBuilder params = new StringBuilder();
		String separator = ", ";
		for (Map.Entry<String, String> name : parameters.entrySet())
			params.append(separator).append(name.getKey()).append(" = ").append(name.getValue());
		return params.substring(separator.length());
	}

	public TaraAnchor createAnchor(String value) {
		final TaraModelImpl file = createDummyFile(
			DUMMY_CONCEPT + " *" + value + "*\n"
		);
		final Node next = file.components().iterator().next();
		return ((TaraNode) next).getSignature().getAnchor();
	}

	@Override
	public TaraAnnotation createAnnotation(String annotation) {
		final TaraModelImpl file = createDummyFile(
			DUMMY_CONCEPT + " is " + annotation + "\n"
		);
		Node node = file.components().iterator().next();
		return (TaraAnnotation) ((TaraNode) node).getSignature().getAnnotations().getAnnotationList().get(0);
	}

	@Override
	public TaraAnnotations createAnnotations(String annotation) {
		final TaraModelImpl file = createDummyFile(DUMMY_CONCEPT + " is " + annotation + "\n");
		Node node = file.components().iterator().next();
		return (TaraAnnotations) ((TaraNode) node).getSignature().getAnnotations();
	}

	@Override
	public PsiElement createNewLineIndent() {
		final TaraModelImpl file = createDummyFile(DUMMY_CONCEPT + "\n\t\tDummy Ficha2");
		TaraNode node = (TaraNode) file.components().iterator().next();
		return node.getBody().getFirstChild();
	}

	@Override
	public PsiElement createNewLineIndent(int level) {
		StringBuilder indents = new StringBuilder();
		for (int i = 0; i < level; i++)
			indents.append("\t");
		final TaraModelImpl file = createDummyFile(DUMMY_CONCEPT + "\n" + indents.toString() + DUMMY_CONCEPT);
		TaraNode node = (TaraNode) file.components().iterator().next();
		return node.getBody().getFirstChild();
	}

	@Override
	public PsiElement createInlineNewLineIndent() {
		final TaraModelImpl file = createDummyFile(DUMMY_CONCEPT + " > Dummy Ficha2");
		TaraNode node = (TaraNode) file.components().iterator().next();
		return node.getBody().getFirstChild();
	}

	@Override
	public PsiElement createDedent(int level) {
		StringBuilder indents = new StringBuilder();
		for (int i = 0; i < level; i++) indents.append("\t");
		final TaraModelImpl file = createDummyFile(DUMMY_CONCEPT + "\n" + indents.toString() + DUMMY_CONCEPT + "2\n" + indents.toString().substring(1) + DUMMY_CONCEPT + 3);
		TaraNode node = (TaraNode) file.components().iterator().next();
		return node.getBody().getFirstChild();

	}

	@Override
	public PsiElement createWhiteSpace() {
		final TaraModelImpl file = createDummyFile(" " + DUMMY_CONCEPT + " > Dummy Ficha2");
		return file.getFirstChild();
	}

	public PsiElement createBodyNewLine() {
		final TaraModelImpl file = createDummyFile(DUMMY_CONCEPT + "\n" +
			"\t" + DUMMY_CONCEPT + "2\n" +
			"\t" + DUMMY_CONCEPT + "3");
		Node node = file.components().iterator().next();
		LeafPsiElement[] childrenOfType = PsiTreeUtil.getChildrenOfType(((TaraNode) node).getBody(), LeafPsiElement.class);
		return childrenOfType != null ? childrenOfType[1] : null;
	}

	public PsiElement createBodyNewLine(int level) {
		StringBuilder indents = new StringBuilder();
		for (int i = 0; i < level; i++)
			indents.append("\t");
		final TaraModelImpl file = createDummyFile(DUMMY_CONCEPT + "\n" +
			indents + DUMMY_CONCEPT + "2\n" +
			indents + DUMMY_CONCEPT + "3");
		Node node = file.components().iterator().next();
		LeafPsiElement[] childrenOfType = PsiTreeUtil.getChildrenOfType(((TaraNode) node).getBody(), LeafPsiElement.class);
		return childrenOfType != null ? childrenOfType[1] : null;
	}

	public PsiElement createInlineNewLine() {
		final TaraModelImpl file = createDummyFile(DUMMY_CONCEPT + " >" + DUMMY_CONCEPT + "2; Dummy Ficha3");
		Node node = file.components().iterator().next();
		LeafPsiElement[] childrenOfType = PsiTreeUtil.getChildrenOfType(((TaraNode) node).getBody(), LeafPsiElement.class);
		return childrenOfType != null ? childrenOfType[1] : null;
	}

	public PsiElement createExpression(String text) {
		char quote = '\'';
		final TaraModelImpl file = createDummyFile(
			CONCEPT_DUMMY + "\n" +
				"\tvar function:Dummy dummy " + "= " + quote + formatted(text) + quote + "\n");
		Node node = PsiTreeUtil.getChildOfType(file, TaraNode.class);
		if (node == null) return null;
		Body body = ((TaraNode) node).getBody();
		if (body == null) return null;
		Variable variable = (Variable) body.getFirstChild().getNextSibling();
		return ((Valued) variable).getValue().getExpressionList().get(0);
	}

	public PsiElement createMultiLineExpression(String text, String oldIndent, String newIndent, String quote) {
		final TaraModelImpl file = (text.trim().startsWith("--")) ?
			createDummyFile(CONCEPT_DUMMY + "\n" +
				"\tvar function:Dummy dummy " + "= \n" +
				"\t" + formatted(text, oldIndent, newIndent).trim() + "\n") :
			createDummyFile(
				CONCEPT_DUMMY + "\n" +
					"\tvar function:Dummy dummy " + "= \n" +
					"\t" + quote + "\n" + newIndent + formatted(text, oldIndent, newIndent) + '\n' + newIndent + quote + "\n");
		TaraNode node = PsiTreeUtil.getChildOfType(file, TaraNode.class);
		if (node == null) return null;
		Body body = node.getBody();
		if (body == null) return null;
		Variable variable = (Variable) body.getFirstChild().getNextSibling();
		return ((Valued) variable).getValue().getExpressionList().get(0);
	}

	@Override
	public Parameter createVarInit(String name, String value) {
		final TaraModelImpl file = createDummyFile(
			CONCEPT_DUMMY + "\n" +
				"\t" + name + " = " + value + "\n" +
				"\t" + CONCEPT_DUMMY + "2\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, TaraNode.class).getBody();
		return body != null ? (TaraVarInit) body.getFirstChild().getNextSibling() : null;

	}


	private String formatted(String text) {
		return text.replace("\n", "\\n").replace("'", "\'");
	}

	private String formatted(String text, String oldIndent, String indent) {
		return text.replaceAll("(\n|\r\n)" + oldIndent, "\n" + indent);
	}

}
