package monet.tara.intellij.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import monet.tara.intellij.lang.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Body extends TaraPsiElement {

	TaraFileImpl getFile() throws PsiInvalidElementAccessException;

	@NotNull
	List<? extends Attribute> getAttributeList();


	List<? extends Concept> getConceptList();

	@NotNull
	List<? extends ReferenceStatement> getReferenceStatementList();

	@NotNull
	List<? extends Word> getWordList();

}

