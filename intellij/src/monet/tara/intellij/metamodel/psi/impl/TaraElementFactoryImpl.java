package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.intellij.metamodel.file.TaraFileType;
import monet.tara.intellij.metamodel.psi.*;

public class TaraElementFactoryImpl extends TaraElementFactory {

	private final Project project;

	public TaraElementFactoryImpl(Project project) {
		this.project = project;
	}

	public Concept createConcept(String name) {
		final TaraFileImpl file = createDummyFile("Concept abstract as " + name + " <has-code root>\n" +
			"\tConcept as Ontology <optional>\n" +
			"\tvar Uid uid");
		return (Concept) file.getFirstChild();
	}

	public TaraFileImpl createDummyFile(String text) {
		return (TaraFileImpl) PsiFileFactory.getInstance(project).createFileFromText("dummy.m2", TaraFileType.INSTANCE, text);
	}

	public TaraIdentifier createNameIdentifier(String name) {
		return PsiTreeUtil.getChildOfType(((TaraConcept) createConcept(name)).getSignature(), TaraIdentifier.class);
	}

	public Attribute createAttribute(String name, String type) {
		final TaraFileImpl file = createDummyFile(
			"Concept abstract as Source <has-code root>\n" +
				"\tvar " + type + " " + name + "\n" +
				"\tConcept as Ontology <optional>\n");
		TaraBody body = ((TaraConcept) file.getFirstChild()).getBody();
		return body != null ? body.getAttributeList().get(0) : null;
	}

}
