package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.*;

import java.util.Collections;
import java.util.List;

public class ParameterMixin extends ASTWrapperPsiElement {

	private String contract = "";
	private String inferredType;
	private String inferredName;

	public ParameterMixin(@NotNull ASTNode node) {
		super(node);
	}


	public String getParameter() {
		return this.getText();
	}

	public int getIndexInParent() {
		List<Parameter> parameters = ((Parameters) this.getParent()).getParameters();
		return parameters.indexOf(this);
	}

	public boolean isExplicit() {
		return this instanceof TaraExplicitParameter;
	}

	@NotNull
	public String getExplicitName() {
		return this instanceof TaraExplicitParameter ? ((TaraExplicitParameter) this).getIdentifier().getText() : "";
	}

	public TaraMeasureValue getMetric() {
		return ((Parameter) this).getValue().getMeasureValue();
	}

	public boolean isList() {
		return ((Parameter) this).getValue().getChildren().length - (((Parameter) this).getValue().getMeasureValue() != null ? 1 : 0) > 1;
	}

	public int getValuesLength() {
		return ((Parameter) this).getValue().getChildren().length - (((Parameter) this).getValue().getMeasureValue() != null ? 1 : 0);
	}

	public List<Object> getValues() {
		Value value = ((Parameter) this).getValue();
		return value == null ? Collections.emptyList() : value.getValues();
	}

	public TaraFacetApply isInFacet() {
		PsiElement aElement = this;
		while (!(aElement.getParent() instanceof Node) && !(aElement.getParent() instanceof TaraFacetApply))
			aElement = aElement.getParent();
		return (aElement.getParent() instanceof TaraFacetApply) ? (TaraFacetApply) aElement.getParent() : null;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getInferredType() {
		return inferredType;
	}

	public void setInferredType(String type) {
		this.inferredType = type;
	}

	public void setInferredName(String name) {
		this.inferredName = name;
	}

	@Override
	public String toString() {
		final NodeContainer contextOf = TaraPsiImplUtil.getContextOf(this);
		return "Parameter in" + (contextOf != null ? contextOf.getQualifiedName() : "");
	}
}
