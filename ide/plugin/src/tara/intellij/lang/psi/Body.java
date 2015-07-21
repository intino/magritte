package tara.intellij.lang.psi;

import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.impl.TaraModelImpl;
import tara.language.model.Facet;
import tara.language.model.Node;
import tara.language.model.Parameter;
import tara.language.model.Variable;

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

