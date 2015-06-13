package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraFacetApply;

import java.util.List;

import static java.util.Collections.unmodifiableList;
import static siani.tara.intellij.lang.psi.impl.TaraUtil.getInnerNodesOf;

public class FacetApplyMixin extends ASTWrapperPsiElement {

	public FacetApplyMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	public String getType() {
		if (!((TaraFacetApply) this).getMetaIdentifierList().isEmpty())
			return ((TaraFacetApply) this).getMetaIdentifierList().get(0).getText();
		return "";
	}

	public List<Node> getIncludes() {
		return unmodifiableList(getInnerNodesOf((FacetApply) this));
	}
}
