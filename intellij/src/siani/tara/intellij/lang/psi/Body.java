package siani.tara.intellij.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;

import java.util.Collection;
import java.util.List;

public interface Body extends TaraPsiElement {

	TaraModelImpl getFile() throws PsiInvalidElementAccessException;

	@NotNull
	List<? extends Variable> getAttributeList();

	@NotNull
	List<? extends VarInit> getVarInitList();

	@NotNull
	List<? extends Node> getConceptList();

	Collection<NodeReference> getConceptLinks();

	@NotNull
	List<? extends ReferenceStatement> getReferenceStatementList();

	@NotNull
	List<? extends Word> getWordList();

	@NotNull
	List<TaraFacetTarget> getFacetTargetList();

	@NotNull
	List<? extends FacetApply> getFacetApplyList();

}

