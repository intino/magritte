package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.unmodifiableList;
import static tara.intellij.lang.psi.impl.TaraUtil.getInnerNodesOf;
import static tara.intellij.lang.psi.impl.TaraUtil.getVariablesOf;

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

	@NotNull
	public List<Node> components() {
		return unmodifiableList(getInnerNodesOf((FacetApply) this));
	}

	@NotNull
	public List<Variable> variables() {
		return unmodifiableList(getVariablesOf((FacetApply) this));
	}


	@NotNull
	public List<Parameter> getParameterList() {
		List<Parameter> parameterList = new ArrayList<>();
		final TaraParameters parameters = ((TaraFacetApply) this).getParameters();
		if (parameters != null) parameterList.addAll(parameters.getParameters());
		parameterList.addAll(getVarInits());
		return parameterList;
	}

	@NotNull
	private List<Parameter> getVarInits() {
		if (((FacetApply) this).getBody() == null) return EMPTY_LIST;
		return unmodifiableList(((FacetApply) this).getBody().getVarInitList());
	}

	public String getQualifiedName() {
		return container().getQualifiedName() + "." + container().getName() + "_" + getType();
	}

	public Node container() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

}
