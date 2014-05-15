package monet.::projectName::.intellij.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::FileImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Body extends ::projectProperName::PsiElement {

	::projectProperName::FileImpl getFile() throws PsiInvalidElementAccessException;

	\@NotNull
	List<? extends Attribute> getAttributeList();


	List<? extends Definition> getDefinitionList();

	\@NotNull
	List<? extends DefinitionInjection> getDefinitionInjectionList();

	\@NotNull
	List<? extends ReferenceStatement> getReferenceStatementList();

	\@NotNull
	List<? extends Word> getWordList();

}

