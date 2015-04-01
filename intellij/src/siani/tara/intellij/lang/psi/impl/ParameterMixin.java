package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.*;

import java.util.List;

public class ParameterMixin extends ASTWrapperPsiElement {
	public ParameterMixin(@NotNull ASTNode node) {
		super(node);
	}


	public String getParameter() {
		return this.getText();
	}

	public int getIndexInParent() {
		List<Parameter> parameters = (List<Parameter>) ((Parameters) this.getParent()).getParameters();
		return parameters.indexOf(this);
	}

	public boolean isExplicit() {
		return this instanceof TaraExplicitParameter;
	}

	@NotNull
	public String getExplicitName() {
		return this instanceof TaraExplicitParameter ? ((TaraExplicitParameter) this).getIdentifier().getText() : "";
	}

	public TaraMeasureValue getMeasure() {
		return ((Parameter) this).getValue().getMeasureValue();
	}

	public boolean isList() {
		return ((Parameter) this).getValue().getChildren().length - (((Parameter) this).getValue().getMeasureValue() != null ? 1 : 0) > 1;
	}

	public int getValuesLength() {
		return ((Parameter) this).getValue().getChildren().length - (((Parameter) this).getValue().getMeasureValue() != null ? 1 : 0);
	}

	public Object[] getValues() {
		Value value = ((Parameter) this).getValue();
		return value == null ? new Object[0] : value.getValues();
	}

	public TaraFacetApply isInFacet() {
		PsiElement aElement = this;
		while (!(aElement.getParent() instanceof Node) && !(aElement.getParent() instanceof TaraFacetApply))
			aElement = aElement.getParent();
		return (aElement.getParent() instanceof TaraFacetApply) ? (TaraFacetApply) aElement.getParent() : null;
	}
}
