package siani.tara.intellij.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;

import java.util.Collection;
import java.util.List;

public interface Body extends TaraPsiElement {

	TaraModelImpl getFile() throws PsiInvalidElementAccessException;

	@NotNull
	List<? extends Variable> getVariableList();

	@NotNull
	List<? extends VarInit> getVarInitList();

	@NotNull
	List<? extends Node> getNodeList();

	Collection<NodeReference> getNodeLinks();

	@NotNull
	List<? extends ReferenceStatement> getReferenceStatementList();

	@NotNull
	List<TaraFacetTarget> getFacetTargetList();

	@NotNull
	List<? extends FacetApply> getFacetApplyList();

}

