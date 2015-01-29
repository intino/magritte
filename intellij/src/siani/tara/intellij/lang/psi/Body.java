package siani.tara.intellij.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;

import java.util.List;

public interface Body extends TaraPsiElement {

	TaraBoxFileImpl getFile() throws PsiInvalidElementAccessException;

	@NotNull
	List<? extends Variable> getAttributeList();

	@NotNull
	List<? extends VarInit> getVarInitList();

	@NotNull
	List<? extends Concept> getConceptList();

	TaraConceptReference[] getConceptLinks();

	@NotNull
	List<? extends ReferenceStatement> getReferenceStatementList();

	@NotNull
	List<? extends Word> getWordList();

	@NotNull
	List<TaraFacetTarget> getFacetTargetList();

	@NotNull
	List<? extends FacetApply> getFacetApplyList();

}

