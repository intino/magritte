package monet.tara.metamodel.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.metamodel.file.TaraFile;
import monet.tara.metamodel.file.TaraFileType;
import monet.tara.metamodel.psi.TaraConceptDefinition;
import monet.tara.metamodel.psi.TaraElementFactory;
import monet.tara.metamodel.psi.TaraIdentifier;

public class TaraElementFactoryImpl extends TaraElementFactory {

	private final Project project;

	public TaraElementFactoryImpl(Project project) {
		this.project = project;
	}

	public TaraConceptDefinition createConcept(String name) {
		final TaraFile file = createDummyFile("concept " + name + " @root");
		return (TaraConceptDefinition) file.getFirstChild();
	}

	public TaraFile createDummyFile(String text) {
		return (TaraFile) PsiFileFactory.getInstance(project).createFileFromText("dummy.m2", TaraFileType.INSTANCE, text);
	}

	@Override
	public TaraIdentifier createNameIdentifier(String name) {
		return PsiTreeUtil.getChildOfType(createConcept(name).getConceptSignature(), TaraIdentifier.class);
	}
}