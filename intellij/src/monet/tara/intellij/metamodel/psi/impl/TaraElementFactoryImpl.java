package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.intellij.metamodel.psi.TaraConcept;
import monet.tara.intellij.metamodel.psi.TaraConceptConstituents;
import monet.tara.intellij.metamodel.psi.TaraIdentifier;
import monet.tara.intellij.metamodel.file.TaraFileType;
import monet.tara.intellij.metamodel.psi.IAttribute;
import monet.tara.intellij.metamodel.psi.IConcept;
import monet.tara.intellij.metamodel.psi.TaraElementFactory;

import java.util.List;

public class TaraElementFactoryImpl extends TaraElementFactory {

	private final Project project;

	public TaraElementFactoryImpl(Project project) {
		this.project = project;
	}

	public IConcept createConcept(String name) {
		final TaraFileImpl file = createDummyFile("Concept abstract as " + name + " <has-code root>\n" +
			"\tConcept as Ontology <optional>\n" +
			"\tvar Uid uid");
		return (IConcept) file.getFirstChild();
	}

	public TaraFileImpl createDummyFile(String text) {
		return (TaraFileImpl) PsiFileFactory.getInstance(project).createFileFromText("dummy.m2", TaraFileType.INSTANCE, text);
	}

	public TaraIdentifier createNameIdentifier(String name) {
		return PsiTreeUtil.getChildOfType(((TaraConcept) createConcept(name)).getConceptSignature(), TaraIdentifier.class);
	}

	public IAttribute createAttribute(String name, String type) {
		final TaraFileImpl file = createDummyFile(
			"Concept abstract as Source <has-code root>\n" +
				"\tvar " + type + " " + name + "\n" +
				"\tConcept as Ontology <optional>\n");
		List<TaraConceptConstituents> list = ((TaraConcept) file.getFirstChild()).getConceptBody().getConceptConstituentsList();
		return (IAttribute) list.get(0).getFirstChild();
	}

}
