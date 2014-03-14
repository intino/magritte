package monet.tara.intellij.metamodel.psi;

import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Body extends Navigatable, PsiElement {

	TaraFileImpl getFile() throws PsiInvalidElementAccessException;

	@NotNull
	List<? extends Attribute> getAttributeList();


	List<? extends Concept> getConceptList();

	@NotNull
	List<? extends ConceptInjection> getConceptInjectionList();

	@NotNull
	List<? extends ReferenceStatement> getReferenceStatementList();

	@NotNull
	List<? extends Word> getWordList();

}

