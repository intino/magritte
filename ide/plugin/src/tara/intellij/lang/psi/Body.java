package tara.intellij.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.impl.TaraModelImpl;
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.Parameter;
import tara.lang.model.Variable;

import java.util.List;

public interface Body extends TaraPsiElement {

	TaraModelImpl getFile() throws PsiInvalidElementAccessException;

	@NotNull
	List<? extends Variable> getVariableList();

	@NotNull
	List<? extends Parameter> getVarInitList();

	@NotNull
	List<? extends Node> getNodeList();

	List<Node> getNodeLinks();

	@NotNull
	List<TaraFacetTarget> getFacetTargetList();

	@NotNull
	List<? extends Facet> getFacetApplyList();

}

