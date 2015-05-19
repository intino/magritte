// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TaraBody extends Body {

	@NotNull
	List<TaraDoc> getDocList();

	@NotNull
	List<TaraFacetApply> getFacetApplyList();

	@NotNull
	List<TaraFacetTarget> getFacetTargetList();

	@NotNull
	List<TaraNode> getNodeList();

	@NotNull
	List<TaraNodeReference> getNodeReferenceList();

	@NotNull
	List<TaraVarInit> getVarInitList();

	@NotNull
	List<TaraVariable> getVariableList();

}
