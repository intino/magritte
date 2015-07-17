package tara.intellij.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.impl.TaraModelImpl;

import java.util.List;

public interface Body extends TaraPsiElement {

	TaraModelImpl getFile() throws PsiInvalidElementAccessException;

	@NotNull
	List<? extends Variable> getVariableList();

	@NotNull
	List<? extends Parameter> getVarInitList();

	@NotNull
	List<? extends Node> getNodeList();

	List<NodeReference> getNodeLinks();

	@NotNull
	List<TaraFacetTarget> getFacetTargetList();

	@NotNull
	List<? extends FacetApply> getFacetApplyList();

}

