package siani.tara.intellij.lang.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.*;

public class TaraElementFactoryImpl extends TaraElementFactory {

	private final Project project;

	public TaraElementFactoryImpl(Project project) {
		this.project = project;
	}

	public Concept createConcept(String name) {
		final TaraBoxFileImpl file = createDummyFile(
			"box project.mod.tara\n" +
				"Concept " + name + " <root>\n" +
				"\tConcept Dummy <required>\n" +
				"\tvar reference uid"
		);
		return file.getConcepts()[0];
	}

	public TaraBoxFileImpl createDummyFile(String text) {
		return (TaraBoxFileImpl) PsiFileFactory.getInstance(project).createFileFromText("dummy." + TaraFileType.INSTANCE.getDefaultExtension(), TaraFileType.INSTANCE, text);
	}

	public Identifier createNameIdentifier(String name) {
		return PsiTreeUtil.getChildOfType(createConcept(name).getSignature(), Identifier.class);
	}

	public Attribute createAttribute(String name, String type) {
		final TaraBoxFileImpl file = createDummyFile(
			"box project.mod.tara\n" +
				"Concept Dummy\n" +
				"\tvar " + type + " " + name + "\n" +
				"\tConcept Dummy2\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, Concept.class).getBody();
		return body != null ? (Attribute) body.getFirstChild().getNextSibling() : null;
	}

	public Attribute createResource(String name, String type) {
		final TaraBoxFileImpl file = createDummyFile(
			"box project.mod.tara\n" +
				"Concept Source\n" +
				"\tvar resource:" + type + " " + name + "\n" +
				"\tConcept Ontology\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, Concept.class).getBody();
		return body != null ? (Attribute) body.getFirstChild().getNextSibling() : null;
	}

	@Override
	public MetaIdentifier createMetaIdentifier(String module, String name) {
		final TaraBoxFileImpl file = createDummyFile(
			"box " + "project." + module + ":tara\n" +
				name + " Dummy\n"
		);
		return PsiTreeUtil.getChildOfType(file, Concept.class).getMetaIdentifier();
	}

	@Override
	public PsiElement createMetaWordIdentifier(String module, String node, String name) {
		final TaraBoxFileImpl file = createDummyFile(
			"box " + "project." + module + ":tara\n" +
				node + " Dummy(" + node + "." + name + ")\n"
		);
		Parameter[] parameters = PsiTreeUtil.getChildOfType(file, Concept.class).getSignature().getParameters().getParameters();
		return parameters[0].getLastChild().getLastChild();
	}

	public Attribute createWord(String name, String[] types) {
		final TaraBoxFileImpl file = createDummyFile(
			"box project.mod.tara\n" +
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


	public Import createImport(String reference) {
		final TaraBoxFileImpl file = createDummyFile(
			"box project.mod.tara\n" +
				"use " + reference + "\n\n" +
				"Concept Source is root\n"
		);
		Import[] imp = file.getImports();
		return imp != null ? imp[0] : null;
	}

	public TaraBox createBox(String reference) {
		final TaraBoxFileImpl file = createDummyFile(
			"box " + reference + "\n" +
				"Concept Source <root>\n"
		);
		TaraBox pack = file.getBoxReference();
		return pack != null ? pack : null;
	}

	public PsiElement createNewLine() {
		final TaraBoxFileImpl file = createDummyFile("\n");
		return file.getFirstChild();
	}

	@Override
	public Parameters createParameters(boolean string) {
		final TaraBoxFileImpl file = createDummyFile(
			"box project.mod.tara\n" +
				"Form Ficha(" + (string ? "\"\"" : "") + ")\n"
		);
		return file.getConcepts()[0].getSignature().getParameters();
	}

}
