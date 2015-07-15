package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.unmodifiableList;
import static tara.intellij.lang.psi.impl.TaraUtil.getVariablesOf;

public class FacetApplyMixin extends ASTWrapperPsiElement {

	public FacetApplyMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	public List<Node> components() {
		return unmodifiableList(TaraUtil.getComponentsOf((FacetApply) this));
	}

	@NotNull
	public List<Variable> variables() {
		return unmodifiableList(getVariablesOf((FacetApply) this));
	}

	@NotNull
	public List<Parameter> parameters() {
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


}
