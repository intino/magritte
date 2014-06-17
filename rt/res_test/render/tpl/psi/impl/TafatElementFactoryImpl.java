package siani.tafat.intellij.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import siani.tafat.intellij.metamodel.file.TafatFile;
import siani.tafat.intellij.metamodel.file.TafatFileType;
import siani.tafat.intellij.psi.TafatDefinition;
import siani.tafat.intellij.psi.TafatElementFactory;
import siani.tafat.intellij.psi.TafatIdentifier;

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