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
		List<Object> values = new ArrayList<>();
		for (PsiElement element : ((Parameter) this).getValue().getChildren()) {
			if (element instanceof TaraMeasureValue) continue;
			values.add(cast(element));
		}
		return values.toArray();
	}

	private Object cast(PsiElement element) {
		String value = element.getText();
		if (element instanceof TaraStringValue) return value;
		else if (element instanceof TaraBooleanValue) return Boolean.parseBoolean(value);
		else if (element instanceof TaraLinkValue) return value;
		else if (element instanceof TaraNaturalValue || element instanceof TaraIntegerValue)
			return Integer.parseInt(value);
		else if (element instanceof TaraDoubleValue) return Double.parseDouble(value);
		else if (element instanceof TaraEmptyField) return "$" + value;
		else if (element instanceof IdentifierReference) {
			Node node = ReferenceManager.resolveToNode((IdentifierReference) element);
			return node != null ? node : value;
		}
		return "";
	}

	public TaraFacetApply isInFacet() {
		PsiElement aElement = this;
		while (!(aElement.getParent() instanceof Node) && !(aElement.getParent() instanceof TaraFacetApply))
			aElement = aElement.getParent();
		return (aElement.getParent() instanceof TaraFacetApply) ? (TaraFacetApply) aElement.getParent() : null;
	}
}
