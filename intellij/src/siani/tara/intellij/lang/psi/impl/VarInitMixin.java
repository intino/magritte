package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.*;

import java.util.Collections;
import java.util.List;

import static siani.tara.intellij.lang.lexer.TaraPrimitives.*;

public class VarInitMixin extends ASTWrapperPsiElement {

	private static final String EMPTY = "empty";

	private String contract = "";
	private String inferredType;

	public VarInitMixin(@NotNull ASTNode node) {
		super(node);
	}


	public String getName() {
		ASTNode childByType = this.getNode().findChildByType(TaraTypes.IDENTIFIER);
		return childByType != null ? childByType.getText() : null;
	}

	public String getValueType() {
		TaraValue value = ((TaraVarInit) this).getValue();
		if (value == null) return "null";
		if (!value.getBooleanValueList().isEmpty()) return BOOLEAN;
		if (!value.getDoubleValueList().isEmpty()) return DOUBLE;
		if (!value.getIntegerValueList().isEmpty()) return INTEGER;
		if (!value.getNaturalValueList().isEmpty()) return NATURAL;
		if (!value.getInstanceNameList().isEmpty()
			|| !value.getIdentifierReferenceList().isEmpty()) return REFERENCE;
		if (!value.getStringValueList().isEmpty()) return STRING;
		if (value.getEmptyField() != null) return EMPTY;
		return "null";
	}

	public List<Object> getValues() {
		return ((VarInit) this).getValue() == null ? Collections.emptyList(): ((VarInit) this).getValue().getValues();
	}


	public String getMetric() {
		TaraMeasureValue measureValue = ((VarInit) this).getValue().getMeasureValue();
		return measureValue == null ? null : measureValue.getText();
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
}