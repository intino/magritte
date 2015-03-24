package siani.tara.intellij.lang.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.*;

import java.util.Collection;
import java.util.Map;

public class TaraElementFactoryImpl extends TaraElementFactory {

	private final Project project;

	public TaraElementFactoryImpl(Project project) {
		this.project = project;
	}

	public Node createConcept(String name, String type) {
		final TaraModelImpl file = createDummyFile(
			type + " " + name + "\n"
		);
		return file.getNodes().iterator().next();
	}

	public Node createConcept(String name) {
		final TaraModelImpl file = createDummyFile(
			"Concept" + " " + name + "\n"
		);
		return file.getNodes().iterator().next();
	}


	public TaraModelImpl createDummyFile(String text) {
		return (TaraModelImpl) PsiFileFactory.getInstance(project).createFileFromText("dummy." + TaraFileType.INSTANCE.getDefaultExtension(), TaraFileType.INSTANCE, text);
	}

	public Identifier createNameIdentifier(String name) {
		return PsiTreeUtil.getChildOfType(createConcept(name).getSignature(), Identifier.class);
	}

	public Variable createAttribute(String name, String type) {
		final TaraModelImpl file = createDummyFile(
			"Concept Dummy\n" +
				"\tvar " + type + " " + name + "\n" +
				"\tConcept Dummy2\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, Node.class).getBody();
		return body != null ? (Variable) body.getFirstChild().getNextSibling() : null;
	}

	public Variable createResource(String name, String type) {
		final TaraModelImpl file = createDummyFile(
			"Concept Dummy\n" +
				"\tvar resource:" + type + " " + name + "\n" +
				"\tConcept Ontology\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, Node.class).getBody();
		return body != null ? (Variable) body.getFirstChild().getNextSibling() : null;
	}

	@Override
	public PsiElement createMetaWordIdentifier(String module, String node, String name) {
		final TaraModelImpl file = createDummyFile(
			node + "(" + node + "." + name + ")" + " Dummy" + "\n"
		);
		Collection<Parameter> parameters = PsiTreeUtil.getChildOfType(file, Node.class).getSignature().getParameters().getParameters();
		return parameters.iterator().next().getLastChild().getLastChild();
	}

	@Override
	public Variable createWord(String name, String[] types) {
		final TaraModelImpl file = createDummyFile(
			"Concept Dummy\n" +
				"\tvar word " + name + "\n" +
				getWordTypesToString(types));
		Body body = PsiTreeUtil.getChildOfType(file, Node.class).getBody();
		return body != null ? body.getAttributeList().get(0) : null;
	}

	private String getWordTypesToString(String[] types) {
		StringBuilder builder = new StringBuilder();
		for (String type : types) builder.append("\t\t").append(type).append("\n");
		return builder.toString();
	}


	public TaraImports createImport(String reference) {
		final TaraModelImpl file = createDummyFile(
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
		return file.getNodes().iterator().next().getSignature().getParameters();
	}

	@Override
	public Parameters createParameters(String... names) {
		String parameters = buildParameters(names);
		final TaraModelImpl file = createDummyFile(
			"Dummy(" + parameters + ")" + " Ficha\n"
		);
		return file.getNodes().iterator().next().getSignature().getParameters();
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
		return file.getNodes().iterator().next().getSignature().getParameters();
	}

	private String buildExplicitParameters(Map<String, String> parameters) {
		String params = "";
		String separator = ", ";
		for (Map.Entry<String, String> name : parameters.entrySet())
			params += separator + name.getKey() + " = " + name.getValue();
		return params.substring(separator.length());
	}

	public TaraAddress createAddress(long value) {
		String addr = value + "";
		if (addr.length() < 9)
			addr = format(addr);
		final TaraModelImpl file = createDummyFile(
			"Dummy Ficha &" + addr.substring(0, 3) + "." + addr.substring(3, 6) + "." + addr.substring(6, 9) + "\n"
		);
		return file.getNodes().iterator().next().getAddress();
	}

	private String format(String address) {
		String newAddress = address;
		for (int i = address.length(); i <= 9; i++)
			newAddress = "0" + newAddress;
		return newAddress;
	}

	@Override
	public Annotation createAnnotation(String annotation) {
		final TaraModelImpl file = createDummyFile(
			"Dummy Ficha is " + annotation + "\n"
		);
		Node next = file.getNodes().iterator().next();
		return next.getAnnotations().get(0);
	}

	@Override
	public Annotations createAnnotations(String annotation) {
		final TaraModelImpl file = createDummyFile("Dummy Ficha is " + annotation + "\n");
		Node next = file.getNodes().iterator().next();
		return next.getAnnotationsNode();
	}

	@Override
	public PsiElement createNewLineIndent() {
		final TaraModelImpl file = createDummyFile("Dummy Ficha\n\t\tDummy Ficha2");
		Node next = file.getNodes().iterator().next();
		return next.getBody().getFirstChild();
	}

	@Override
	public PsiElement createNewLineIndent(int level) {
		String indents = "";
		for (int i = 0; i < level; i++) indents += "\t";
		final TaraModelImpl file = createDummyFile("Dummy Ficha\n" + indents + "Dummy Ficha2");
		Node next = file.getNodes().iterator().next();
		return next.getBody().getFirstChild();
	}

	@Override
	public PsiElement createInlineNewLineIndent() {
		final TaraModelImpl file = createDummyFile("Dummy Ficha > Dummy Ficha2");
		Node next = file.getNodes().iterator().next();
		return next.getBody().getFirstChild();
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
		Node node = file.getNodes().iterator().next();
		LeafPsiElement[] childrenOfType = PsiTreeUtil.getChildrenOfType(node.getBody(), LeafPsiElement.class);
		return childrenOfType[1];
	}

	public PsiElement createBodyNewLine(int level) {
		String indents = "";
		for (int i = 0; i < level; i++) indents += "\t";
		final TaraModelImpl file = createDummyFile("Dummy Ficha\n" +
			indents + "Dummy Ficha2\n" +
			indents + "Dummy Ficha3");
		Node node = file.getNodes().iterator().next();
		LeafPsiElement[] childrenOfType = PsiTreeUtil.getChildrenOfType(node.getBody(), LeafPsiElement.class);
		return childrenOfType[1];
	}

	public PsiElement createInlineNewLine() {
		final TaraModelImpl file = createDummyFile("Dummy Ficha >" + "Dummy Ficha2; Dummy Ficha3");
		Node node = file.getNodes().iterator().next();
		LeafPsiElement[] childrenOfType = PsiTreeUtil.getChildrenOfType(node.getBody(), LeafPsiElement.class);
		return childrenOfType[1];
	}

}
