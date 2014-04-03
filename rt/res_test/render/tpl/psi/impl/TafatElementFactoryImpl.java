package monet.tafat.intellij.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tafat.intellij.metamodel.file.TafatFile;
import monet.tafat.intellij.metamodel.file.TafatFileType;
import monet.tafat.intellij.psi.TafatDefinition;
import monet.tafat.intellij.psi.TafatElementFactory;
import monet.tafat.intellij.psi.TafatIdentifier;

public class TafatElementFactoryImpl extends TafatElementFactory {

	private final Project project;

	public TafatElementFactoryImpl(Project project) {
		this.project = project;
	}

	public TafatDefinition createDefinition(String name) {
		final TafatFile file = createDummyFile("Definition as " + name);
		return (TafatDefinition) file.getFirstChild();
	}

	public TafatFile createDummyFile(String text) {
		return (TafatFile) PsiFileFactory.getInstance(project).createFileFromText("dummy.m1", TafatFileType.INSTANCE, text);
	}

	@Override
	public TafatIdentifier createNameIdentifier(String name) {
		return PsiTreeUtil.getChildOfType(createDefinition(name).getDefinitionSignature(), TafatIdentifier.class);
	}
}