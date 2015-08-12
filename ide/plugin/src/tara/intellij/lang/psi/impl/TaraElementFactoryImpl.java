package tara.intellij.lang.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import tara.intellij.lang.file.TaraFileType;
import tara.intellij.lang.psi.*;
import tara.language.model.Node;
import tara.language.model.Parameter;
import tara.language.model.Variable;

import java.util.Collection;
import java.util.Map;

public class TaraElementFactoryImpl extends TaraElementFactory {

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


	public TaraModelImpl createDummyFile(String text) {
		return (TaraModelImpl) PsiFileFactory.getInstance(project).createFileFromText("dummy." + TaraFileType.INSTANCE.getDefaultExtension(), TaraFileType.INSTANCE, text);
	}

	public Identifier createNameIdentifier(String name) {
		return PsiTreeUtil.getChildOfType(((TaraNode) createNode(name)).getSignature(), Identifier.class);
	}

	public TaraVariable createVariable(String name, String type) {
		final TaraModelImpl file = createDummyFile(
			"Concept Dummy\n" +
				"\tvar " + type + " " + name + "\n" +
				"\tConcept Dummy2\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, TaraNode.class).getBody();
		return body != null ? (TaraVariable) body.getFirstChild().getNextSibling() : null;
	}

	public TaraVariable createResource(String name, String type) {
		final TaraModelImpl file = createDummyFile(
			"Concept Dummy\n" +
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
			"Concept Dummy\n" +
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
				"Concept Dummy\n"
		);
		return PsiTreeUtil.getChildrenOfType(file, TaraImports.class)[0];
	}

	public TaraDslDeclaration createDslDeclaration(String name) {
		final TaraModelImpl file = createDummyFile(
			"dsl " + name + "\n\n" +
				"Concept Dummy\n"
		);
		return PsiTreeUtil.getChildrenOfType(file, TaraDslDeclaration.class)[0];
	}

	public PsiElement createNewLine() {
		final TaraModelImpl file = createDummyFile("\n");
		return file.getFirstChild();
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
		String parms = "";
		String separator = ", ";
		for (String name : names) parms += separator + name;
		return parms.substring(separator.length());
	}

	@Override
	public Parameters createExplicitParameters(Map<String, String> parameters) {
		String assigns = buildExplicitParameters(parameters);
		final TaraModelImpl file = createDummyFile(
			"Dummy(" + assigns + ")" + " Ficha\n"
		);
		final Node next = file.components().iterator().next();
		return ((TaraNode) next).getSignature().getParameters();
	}

	private String buildExplicitParameters(Map<String, String> parameters) {
		StringBuilder params = new StringBuilder();
		String separator = ", ";
		for (Map.Entry<String, String> name : parameters.entrySet())
			params.append(separator).append(name.getKey()).append(" = ").append(name.getValue());
		return params.substring(separator.length());
	}

	public TaraAddress createAddress(String value) {
		final TaraModelImpl file = createDummyFile(
			"Dummy Ficha #" + value + "\n"
		);
		final Node next = file.components().iterator().next();
		return ((TaraNode) next).getSignature().getAddress();
	}

	@Override
	public TaraAnnotation createAnnotation(String annotation) {
		final TaraModelImpl file = createDummyFile(
			"Dummy Ficha is " + annotation + "\n"
		);
		Node node = file.components().iterator().next();
		return (TaraAnnotation) ((TaraNode) node).getSignature().getAnnotations().getAnnotationList().get(0);
	}

	@Override
	public TaraAnnotations createAnnotations(String annotation) {
		final TaraModelImpl file = createDummyFile("Dummy Ficha is " + annotation + "\n");
		Node node = file.components().iterator().next();
		return (TaraAnnotations) ((TaraNode) node).getSignature().getAnnotations();
	}

	@Override
	public PsiElement createNewLineIndent() {
		final TaraModelImpl file = createDummyFile("Dummy Ficha\n\t\tDummy Ficha2");
		TaraNode node = (TaraNode) file.components().iterator().next();
		return node.getBody().getFirstChild();
	}

	@Override
	public PsiElement createNewLineIndent(int level) {
		StringBuilder indents = new StringBuilder();
		for (int i = 0; i < level; i++)
			indents.append("\t");
		final TaraModelImpl file = createDummyFile("Dummy Ficha\n" + indents.toString() + "Dummy Ficha2");
		TaraNode node = (TaraNode) file.components().iterator().next();
		return node.getBody().getFirstChild();
	}

	@Override
	public PsiElement createInlineNewLineIndent() {
		final TaraModelImpl file = createDummyFile("Dummy Ficha > Dummy Ficha2");
		TaraNode node = (TaraNode) file.components().iterator().next();
		return node.getBody().getFirstChild();
	}

	@Override
	public PsiElement createWhiteSpace() {
		final TaraModelImpl file = createDummyFile(" Dummy Ficha > Dummy Ficha2");
		return file.getFirstChild();
	}

	public PsiElement createBodyNewLine() {
		final TaraModelImpl file = createDummyFile("Dummy Ficha\n" +
			"\tDummy Ficha2\n" +
			"\tDummy Ficha3");
		Node node = file.components().iterator().next();
		LeafPsiElement[] childrenOfType = PsiTreeUtil.getChildrenOfType(((TaraNode) node).getBody(), LeafPsiElement.class);
		return childrenOfType != null ? childrenOfType[1] : null;
	}

	public PsiElement createBodyNewLine(int level) {
		String indents = "";
		for (int i = 0; i < level; i++) indents += "\t";
		final TaraModelImpl file = createDummyFile("Dummy Ficha\n" +
			indents + "Dummy Ficha2\n" +
			indents + "Dummy Ficha3");
		Node node = file.components().iterator().next();
		LeafPsiElement[] childrenOfType = PsiTreeUtil.getChildrenOfType(((TaraNode) node).getBody(), LeafPsiElement.class);
		return childrenOfType != null ? childrenOfType[1] : null;
	}

	public PsiElement createInlineNewLine() {
		final TaraModelImpl file = createDummyFile("Dummy Ficha >" + "Dummy Ficha2; Dummy Ficha3");
		Node node = file.components().iterator().next();
		LeafPsiElement[] childrenOfType = PsiTreeUtil.getChildrenOfType(((TaraNode) node).getBody(), LeafPsiElement.class);
		return childrenOfType != null ? childrenOfType[1] : null;
	}

	public PsiElement createExpression(String text) {
		char quote = '\'';
		final TaraModelImpl file = createDummyFile(
			"Concept Dummy\n" +
				"\tvar native:Dummy dummy " + "= " + quote + formatted(text) + quote + "\n");
		Node node = PsiTreeUtil.getChildOfType(file, TaraNode.class);
		if (node == null) return null;
		Body body = ((TaraNode) node).getBody();
		if (body == null) return null;
		Variable variable = (Variable) body.getFirstChild().getNextSibling();
		return ((Valued) variable).getValue().getExpressionList().get(0);
	}

	public PsiElement createMultiLineExpression(String text, String indent, String quote) {
		final TaraModelImpl file = (text.trim().startsWith("--")) ? createDummyFile(
			"Concept Dummy\n" +
				"\tvar native:Dummy dummy " + "= \n" +
				"\t" + formatted(text, indent).trim() + "\n") :
			createDummyFile(
				"Concept Dummy\n" +
					"\tvar native dummy " + "= \n" +
					"\t" + quote + "\n" + indent + formatted(text, indent) + '\n' + indent + quote + "\n");
		TaraNode node = PsiTreeUtil.getChildOfType(file, TaraNode.class);
		if (node == null) return null;
		Body body = node.getBody();
		if (body == null) return null;
		Variable variable = (Variable) body.getFirstChild().getNextSibling();
		return ((Valued) variable).getValue().getExpressionList().get(0);
	}

	private String formatted(String text) {
		return text.replace("\n", "\\n").replace("'", "\'");
	}

	private String formatted(String text, String indent) {
		return text.replaceAll("(\n|\r\n)\t\t", "\n" + indent);
	}

}
