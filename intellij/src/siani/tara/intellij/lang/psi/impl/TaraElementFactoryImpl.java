package siani.tara.intellij.lang.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.*;

import java.util.Map;

public class TaraElementFactoryImpl extends TaraElementFactory {

	private final Project project;

	public TaraElementFactoryImpl(Project project) {
		this.project = project;
	}

	public Concept createConcept(String name, String type) {
		final TaraBoxFileImpl file = createDummyFile(
			type + " " + name + "\n"
		);
		return file.getConcepts().iterator().next();
	}

	public Concept createConcept(String name) {
		final TaraBoxFileImpl file = createDummyFile(
			"Concept" + " " + name + "\n"
		);
		return file.getConcepts().iterator().next();
	}


	public TaraBoxFileImpl createDummyFile(String text) {
		return (TaraBoxFileImpl) PsiFileFactory.getInstance(project).createFileFromText("dummy." + TaraFileType.INSTANCE.getDefaultExtension(), TaraFileType.INSTANCE, text);
	}

	public Identifier createNameIdentifier(String name) {
		return PsiTreeUtil.getChildOfType(createConcept(name).getSignature(), Identifier.class);
	}

	public Variable createAttribute(String name, String type) {
		final TaraBoxFileImpl file = createDummyFile(
			"Concept Dummy\n" +
				"\tvar " + type + " " + name + "\n" +
				"\tConcept Dummy2\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, Concept.class).getBody();
		return body != null ? (Variable) body.getFirstChild().getNextSibling() : null;
	}

	public Variable createResource(String name, String type) {
		final TaraBoxFileImpl file = createDummyFile(
			"Concept Source\n" +
				"\tvar resource:" + type + " " + name + "\n" +
				"\tConcept Ontology\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, Concept.class).getBody();
		return body != null ? (Variable) body.getFirstChild().getNextSibling() : null;
	}

	@Override
	public PsiElement createMetaWordIdentifier(String module, String node, String name) {
		final TaraBoxFileImpl file = createDummyFile(
			node + "(" + node + "." + name + ")" + " Dummy" + "\n"
		);
		Parameter[] parameters = PsiTreeUtil.getChildOfType(file, Concept.class).getSignature().getParameters().getParameters();
		return parameters[0].getLastChild().getLastChild();
	}

	@Override
	public Variable createWord(String name, String[] types) {
		final TaraBoxFileImpl file = createDummyFile(
			"Concept Dummy\n" +
				"\tvar word " + name + "\n" +
				getWordTypesToString(types));
		Body body = PsiTreeUtil.getChildOfType(file, Concept.class).getBody();
		return body != null ? body.getAttributeList().get(0) : null;
	}

	private String getWordTypesToString(String[] types) {
		StringBuilder builder = new StringBuilder();
		for (String type : types) builder.append("\t\t").append(type).append("\n");
		return builder.toString();
	}


	public TaraImports createImport(String reference) {
		final TaraBoxFileImpl file = createDummyFile(
			"use " + reference + "\n" +
				"Concept Source\n"
		);
		return PsiTreeUtil.getChildrenOfType(file, TaraImports.class)[0];
	}

	public TaraDslDeclaration createDslDeclaration(String name) {
		final TaraBoxFileImpl file = createDummyFile(
			"dsl " + name + "\n\n" +
				"Concept Source\n"
		);
		return PsiTreeUtil.getChildrenOfType(file, TaraDslDeclaration.class)[0];
	}

	public PsiElement createNewLine() {
		final TaraBoxFileImpl file = createDummyFile("\n");
		return file.getFirstChild();
	}

	@Override
	public Parameters createParameters(boolean stringValue) {
		final TaraBoxFileImpl file = createDummyFile(
			"Form(" + (stringValue ? "\"\"" : "") + ")" + "Ficha\n"
		);
		return file.getConcepts().iterator().next().getSignature().getParameters();
	}

	@Override
	public Parameters createParameters(String... names) {
		String parameters = buildParameters(names);
		final TaraBoxFileImpl file = createDummyFile(
			"Form(" + parameters + ")" + " Ficha\n"
		);
		return file.getConcepts().iterator().next().getSignature().getParameters();
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
		final TaraBoxFileImpl file = createDummyFile(
			"Form(" + assigns + ")" + " Ficha\n"
		);
		return file.getConcepts().iterator().next().getSignature().getParameters();
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
		final TaraBoxFileImpl file = createDummyFile(
			"Form Ficha &" + addr.substring(0, 3) + "." + addr.substring(3, 6) + "." + addr.substring(6, 9) + "\n"
		);
		return file.getConcepts().iterator().next().getAddress();
	}

	private String format(String address) {
		String newAddress = address;
		for (int i = address.length(); i <= 9; i++)
			newAddress = "0" + newAddress;
		return newAddress;
	}

	@Override
	public Annotation createAnnotation(String annotation) {
		final TaraBoxFileImpl file = createDummyFile(
			"Form Ficha is " + annotation + "\n"
		);
		TaraConcept next = (TaraConcept) file.getConcepts().iterator().next();
		return next.getNormalAnnotations().get(0);
	}

	@Override
	public TaraAnnotations createAnnotations(String annotation) {
		final TaraBoxFileImpl file = createDummyFile("Form Ficha is " + annotation + "\n");
		TaraConcept next = (TaraConcept) file.getConcepts().iterator().next();
		return next.getAnnotations();
	}

	@Override
	public PsiElement createNewLineIndent() {
		final TaraBoxFileImpl file = createDummyFile("Form Ficha\n\t\tForm Ficha2");
		TaraConcept next = (TaraConcept) file.getConcepts().iterator().next();
		return next.getBody().getFirstChild();
	}

	@Override
	public PsiElement createNewLineIndent(int level) {
		String indents = "";
		for (int i = 0; i < level; i++) indents += "\t";
		final TaraBoxFileImpl file = createDummyFile("Form Ficha\n" + indents + "Form Ficha2");
		TaraConcept next = (TaraConcept) file.getConcepts().iterator().next();
		return next.getBody().getFirstChild();
	}

	@Override
	public PsiElement createInlineNewLineIndent() {
		final TaraBoxFileImpl file = createDummyFile("Form Ficha > Form Ficha2");
		TaraConcept next = (TaraConcept) file.getConcepts().iterator().next();
		return next.getBody().getFirstChild();
	}

	@Override
	public PsiElement createWhiteSpace() {
		final TaraBoxFileImpl file = createDummyFile(" Form Ficha > Form Ficha2");
		return file.getFirstChild();
	}

	public PsiElement createBodyNewLine() {
		final TaraBoxFileImpl file = createDummyFile("Form Ficha\n" +
			"\tForm Ficha2\n" +
			"\tForm Ficha3");
		Concept concept = file.getConcepts().iterator().next();
		LeafPsiElement[] childrenOfType = PsiTreeUtil.getChildrenOfType(concept.getBody(), LeafPsiElement.class);
		return childrenOfType[1];
	}

	public PsiElement createBodyNewLine(int level) {
		String indents = "";
		for (int i = 0; i < level; i++) indents += "\t";
		final TaraBoxFileImpl file = createDummyFile("Form Ficha\n" +
			indents + "Form Ficha2\n" +
			indents + "Form Ficha3");
		Concept concept = file.getConcepts().iterator().next();
		LeafPsiElement[] childrenOfType = PsiTreeUtil.getChildrenOfType(concept.getBody(), LeafPsiElement.class);
		return childrenOfType[1];
	}

	public PsiElement createInlineNewLine() {
		final TaraBoxFileImpl file = createDummyFile("Form Ficha >" + "Form Ficha2; Form Ficha3");
		Concept concept = file.getConcepts().iterator().next();
		LeafPsiElement[] childrenOfType = PsiTreeUtil.getChildrenOfType(concept.getBody(), LeafPsiElement.class);
		return childrenOfType[1];
	}

}
