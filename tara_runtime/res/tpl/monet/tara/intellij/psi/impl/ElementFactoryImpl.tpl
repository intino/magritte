package monet.::projectName::.intellij.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import monet.::projectName::.intellij.metamodel.file.::projectProperName::File;
import monet.::projectName::.intellij.metamodel.file.::projectProperName::FileType;
import monet.::projectName::.intellij.psi.::projectProperName::Definition;
import monet.::projectName::.intellij.psi.::projectProperName::ElementFactory;
import monet.::projectName::.intellij.psi.::projectProperName::Identifier;

public class ::projectProperName::ElementFactoryImpl extends ::projectProperName::ElementFactory {

	private final Project project;

	public ::projectProperName::ElementFactoryImpl(Project project) {
		this.project = project;
	}

	public ::projectProperName::Definition createDefinition(String name) {
		final ::projectProperName::File file = createDummyFile("Definition as " + name);
		return (::projectProperName::Definition) file.getFirstChild();
	}

	public ::projectProperName::File createDummyFile(String text) {
		return (::projectProperName::File) PsiFileFactory.getInstance(project).createFileFromText("dummy.m1", ::projectProperName::FileType.INSTANCE, text);
	}

	\@Override
	public ::projectProperName::Identifier createNameIdentifier(String name) {
		return PsiTreeUtil.getChildOfType(createDefinition(name).getDefinitionSignature(), ::projectProperName::Identifier.class);
	}
}