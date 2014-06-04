package monet.tara.intellij.lang.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.intellij.lang.file.TaraFileType;
import monet.tara.intellij.lang.psi.*;

public class TaraElementFactoryImpl extends TaraElementFactory {

	private final Project project;

	public TaraElementFactoryImpl(Project project) {
		this.project = project;
	}

	public Concept createConcept(String name) {
		final TaraFileImpl file = createDummyFile(
			"package tara\n" +
				"Concept abstract " + name + " <root>\n" +
				"\tConcept Ontology <optional>\n" +
				"\tvar Alias uid"
		);
		return file.getConcept();
	}

	public TaraFileImpl createDummyFile(String text) {
		return (TaraFileImpl) PsiFileFactory.getInstance(project).createFileFromText("dummy.m2", TaraFileType.INSTANCE, text);
	}

	public Identifier createNameIdentifier(String name) {
		return PsiTreeUtil.getChildOfType(createConcept(name).getSignature(), Identifier.class);
	}

	public Attribute createAttribute(String name, String type) {
		final TaraFileImpl file = createDummyFile(
			"package tara\n" +
				"Concept abstract Source\n" +
				"\tvar " + type + " " + name + "\n" +
				"\tConcept Ontology\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, Concept.class).getBody();
		return body != null ? (Attribute) body.getFirstChild().getNextSibling() : null;
	}

	public Attribute createResource(String name, String type) {
		final TaraFileImpl file = createDummyFile(
			"package tara\n" +
				"Definition abstract Source\n" +
				"\tvar Resource:" + type + " " + name + "\n" +
				"\tDefinition Ontology\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, Concept.class).getBody();
		return body != null ? (Attribute) body.getFirstChild().getNextSibling() : null;
	}

	public Attribute createWord(String name, String[] types) {
		final TaraFileImpl file = createDummyFile(
			"package tara\n" +
				"Concept abstract Source\n" +
				"\tvar Word " + name + "\n" +
				getWordTypesToString(types) +
				"\tConcept Ontology\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, Concept.class).getBody();
		return body != null ? (Attribute) body.getFirstChild().getNextSibling() : null;
	}

	private String getWordTypesToString(String[] types) {
		StringBuilder builder = new StringBuilder();
		for (String type : types) builder.append("\t\t").append(type).append("\n");
		return builder.toString();
	}


	public Import createImport(String reference) {
		final TaraFileImpl file = createDummyFile(
			"package tara\n" +
				"import " + reference + "\n\n" +
				"Concept abstract Source <root>\n"
		);
		Import[] imp = file.getImports();
		return imp != null ? imp[0] : null;
	}

	public TaraPacket createPackage(String reference) {
		final TaraFileImpl file = createDummyFile(
			"package " + reference + "\n" +
				"Concept abstract Source <root>\n"
		);
		TaraPacket pack = file.getPackage();
		return pack != null ? pack : null;
	}

	public PsiElement createNewLine() {
		final TaraFileImpl file = createDummyFile("\n");
		return file.getFirstChild();
	}

	@Override
	public Parameters createParameters(boolean string) {
		final TaraFileImpl file = createDummyFile(
			"package tara\n" +
				"Form Ficha(" + (string ? "\"\"" : "") + ")\n"
		);
		return file.getConcept().getSignature().getParameters();
	}

}
