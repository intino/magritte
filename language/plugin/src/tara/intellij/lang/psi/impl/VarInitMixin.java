package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.lexer.TaraPrimitives;
import tara.intellij.lang.psi.*;

import java.util.Collections;
import java.util.List;

public class VarInitMixin extends ASTWrapperPsiElement {

	private static final String EMPTY = "empty";

	private String contract = "";
	private String inferredType;
	private String name;

	public VarInitMixin(@NotNull ASTNode node) {
		super(node);
	}


	public String getName() {
		ASTNode childByType = this.getNode().findChildByType(TaraTypes.IDENTIFIER);
		return childByType != null ? childByType.getText() : null;
	}

	public String getValueType() {
		TaraValue value = this.getValue();
		if (value == null) return "null";
		if (!value.getBooleanValueList().isEmpty()) return TaraPrimitives.BOOLEAN;
		if (!value.getDoubleValueList().isEmpty()) return TaraPrimitives.DOUBLE;
		if (!value.getIntegerValueList().isEmpty()) return TaraPrimitives.INTEGER;
		if (!value.getNaturalValueList().isEmpty()) return TaraPrimitives.NATURAL;
		if (!value.getInstanceNameList().isEmpty()
			|| !value.getIdentifierReferenceList().isEmpty()) return TaraPrimitives.REFERENCE;
		if (!value.getStringValueList().isEmpty()) return TaraPrimitives.STRING;
		if (value.getEmptyField() != null) return EMPTY;
		return "null";
	}

	public List<Object> getValues() {
		return ((Parameter) this).getValue() == null ? Collections.emptyList() : ((Parameter) this).getValue().getValues();
	}

	public TaraMeasureValue getMetric() {
		return ((Parameter) this).getValue().getMeasureValue();
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

	@Override
	public String toString() {
		final NodeContainer contextOf = TaraPsiImplUtil.getContextOf(this);
		return "Parameter in" + (contextOf != null ? contextOf.getQualifiedName() : "");
	}

	public int getIndexInParent() {
		return 0;
	}

	public boolean isExplicit() {
		return true;
	}

	@Nullable
	public TaraValue getValue() {
		return findChildByClass(TaraValue.class);
	}

	public boolean isList() {
		return ((Parameter) this).getValue().getChildren().length - (((Parameter) this).getValue().getMeasureValue() != null ? 1 : 0) > 1;
	}

	public FacetApply isInFacet() {
		final NodeContainer contextOf = TaraPsiImplUtil.getContextOf(this);
		return contextOf instanceof FacetApply ? (FacetApply) contextOf : null;
	}

	public void setInferredName(String name) {
	}
}