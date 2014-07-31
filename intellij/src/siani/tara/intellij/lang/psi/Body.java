package siani.tara.intellij.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.impl.TaraFileImpl;

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

	@NotNull
	List<TaraFacetTarget> getFacetTargets();

	@NotNull
	List<TaraFacetApply> getFacetApplies();

}

