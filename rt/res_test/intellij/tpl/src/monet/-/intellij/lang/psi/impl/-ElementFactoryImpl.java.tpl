package monet.::projectName::.intellij.lang.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import monet.::projectName::.intellij.lang.file.::projectProperName::FileType;
import monet.::projectName::.intellij.lang.psi.*;

public class ::projectProperName::ElementFactoryImpl extends ::projectProperName::ElementFactory {

	private final Project project;

	public ::projectProperName::ElementFactoryImpl(Project project) {
		this.project = project;
	}

	public Definition createDefinition(String name) {
		final ::projectProperName::FileImpl file = createDummyFile(
			"package ::projectName::\\n" +
				"Definition abstract " + name + " <root>\\n" +
				"\\tDefinition Ontology <optional>\\n" +
				"\\tvar Alias uid"
		);
		return file.getDefinition();
	}

	public ::projectProperName::FileImpl createDummyFile(String text) {
		return (::projectProperName::FileImpl) PsiFileFactory.getInstance(project).createFileFromText("dummy.m1", ::projectProperName::FileType.INSTANCE, text);
	}

	public Identifier createNameIdentifier(String name) {
		return PsiTreeUtil.getChildOfType(createDefinition(name).getSignature(), Identifier.class);
	}

	public Attribute createAttribute(String name, String type) {
		final ::projectProperName::FileImpl file = createDummyFile(
			"package ::projectName::\\n" +
				"Definition abstract Source\\n" +
				"\\tvar " + type + " " + name + "\\n" +
				"\\tDefinition Ontology\\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, Definition.class).getBody();
		return body != null ? (Attribute) body.getFirstChild().getNextSibling() \: null;
	}

	public Attribute createResource(String name, String type) {
		final ::projectProperName::FileImpl file = createDummyFile(
			"package ::projectName::\\n" +
				"Definition abstract Source\\n" +
				"\\tvar Resource\:" + type + " " + name + "\\n" +
				"\\tDefinition Ontology\\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, Definition.class).getBody();
		return body != null ? (Attribute) body.getFirstChild().getNextSibling() \: null;
	}

	public Attribute createWord(String name, String[] types) {
		final ::projectProperName::FileImpl file = createDummyFile(
			"package ::projectName::\\n" +
				"Definition abstract Source\\n" +
				"\\tvar Word " + name + "\\n" +
				getWordTypesToString(types) +
				"\\tDefinition Ontology\\n"
		);
		Body body = PsiTreeUtil.getChildOfType(file, Definition.class).getBody();
		return body != null ? (Attribute) body.getFirstChild().getNextSibling() \: null;
	}

	private String getWordTypesToString(String[] types) {
		StringBuilder builder = new StringBuilder();
		for (String type \: types) builder.append("\\t\\t").append(type).append("\\n");
		return builder.toString();
	}


	public Import createImport(String reference) {
		final ::projectProperName::FileImpl file = createDummyFile(
			"package ::projectName::\\n" +
				"import " + reference + "\\n\\n" +
				"Definition abstract Source <root>\\n"
		);
		Import[] imp = file.getImports();
		return imp != null ? imp[0] \: null;
	}

	public ::projectProperName::Packet createPackage(String reference) {
		final ::projectProperName::FileImpl file = createDummyFile(
			"package " + reference + "\\n" +
				"Definition abstract Source <root>\\n"
		);
		::projectProperName::Packet pack = file.getPackage();
		return pack != null ? pack \: null;
	}

	public PsiElement createNewLine() {
		final ::projectProperName::FileImpl file = createDummyFile("\\n");
		return file.getFirstChild();
	}

	\@Override
	public Parameters createParameters(boolean string) {
		final ::projectProperName::FileImpl file = createDummyFile(
			"package ::projectName::\\n" +
				"Form Ficha(" + (string ? "\\"\\"" \: "") + ")\\n"
		);
		return file.getDefinition().getSignature().getParameters();
	}

}
