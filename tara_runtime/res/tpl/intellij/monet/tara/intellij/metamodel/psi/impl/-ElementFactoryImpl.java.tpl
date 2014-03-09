package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import monet.::projectName::.intellij.metamodel.file.::projectProperName::FileType;
import monet.::projectName::.intellij.metamodel.psi.*;

public class ::projectProperName::ElementFactoryImpl extends ::projectProperName::ElementFactory {

	private final Project project;

	public ::projectProperName::ElementFactoryImpl(Project project) {
		this.project = project;
	}

	public Definition createDefinition(String name) {
		final ::projectProperName::FileImpl file = createDummyFile("Definition abstract as " + name + " <has-code root>\n" +
			"\tDefinition as Ontology <optional>\n" +
			"\tvar Uid uid");
		return (Definition) file.getFirstChild();
	}

	public ::projectProperName::FileImpl createDummyFile(String text) {
		return (::projectProperName::FileImpl) PsiFileFactory.getInstance(project).createFileFromText("dummy.m2", ::projectProperName::FileType.INSTANCE, text);
	}

	public ::projectProperName::Identifier createNameIdentifier(String name) {
		return PsiTreeUtil.getChildOfType(((::projectProperName::Definition) createDefinition(name)).getSignature(), ::projectProperName::Identifier.class);
	}

	public Attribute createAttribute(String name, String type) {
		final ::projectProperName::FileImpl file = createDummyFile(
			"Definition abstract as Source <has-code root>\n" +
				"\tvar " + type + " " + name + "\n" +
				"\tDefinition as Ontology <optional>\n");
		::projectProperName::Body body = ((::projectProperName::Definition) file.getFirstChild()).getBody();
		return body != null ? body.getAttributeList().get(0) : null;
	}

}
