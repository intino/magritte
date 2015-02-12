package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParameterMixin extends ASTWrapperPsiElement {
	public ParameterMixin(@NotNull ASTNode node) {
		super(node);
	}


	public String getParameter() {
		return this.getText();
	}

	public int getIndexInParent() {
		return Arrays.asList(((Parameters) this.getParent()).getParameters()).indexOf(this);
	}

	public boolean isExplicit() {
		return this instanceof TaraExplicitParameter;
	}

	public String getExplicitName() {
		if (this instanceof TaraExplicitParameter) return ((TaraExplicitParameter) this).getIdentifier().getText();
		return null;
	}

	public TaraParameterValue getValue() {
		if (this instanceof TaraExplicitParameter) return ((TaraExplicitParameter) this).getParameterValue();
		else return ((TaraImplicitParameter) this).getParameterValue();
	}

	public TaraMeasureValue getMeasure() {
		if (this instanceof TaraExplicitParameter)
			return ((TaraExplicitParameter) this).getParameterValue() != null ? ((TaraExplicitParameter) this).getParameterValue().getMeasureValue() : null;
		else return ((TaraImplicitParameter) this).getParameterValue().getMeasureValue();
	}

	public boolean isList() {
		return getValue().getChildren().length - (getValue().getMeasureValue() != null ? 1 : 0) > 1;
	}

	public int getValuesLength() {
		return getValue().getChildren().length - (getValue().getMeasureValue() != null ? 1 : 0);
	}

	public String[] getValues() {
		List<String> values = new ArrayList<>();
		for (PsiElement element : getValue().getChildren()) {
			if (element instanceof TaraMeasureValue) continue;
			values.add(element.getText());
		}
		return values.toArray(new String[values.size()]);
	}


	public TaraFacetApply isInFacet() {
		PsiElement aElement = this;
		while (!(aElement.getParent() instanceof Concept) && !(aElement.getParent() instanceof TaraFacetApply))
			aElement = aElement.getParent();
		return (aElement.getParent() instanceof TaraFacetApply) ? (TaraFacetApply) aElement.getParent() : null;
	}
}
