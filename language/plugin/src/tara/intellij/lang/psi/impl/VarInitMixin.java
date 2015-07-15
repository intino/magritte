package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.language.model.Primitives;

import java.util.Collections;
import java.util.List;

public class VarInitMixin extends ASTWrapperPsiElement {

	private static final String EMPTY = "empty";

	private String contract = "";
	private String inferredType;

	public VarInitMixin(@NotNull ASTNode node) {
		super(node);
	}


	public String name() {
		ASTNode childByType = this.getNode().findChildByType(TaraTypes.IDENTIFIER);
		return childByType != null ? childByType.getText() : null;
	}

	public String getValueType() {
		TaraValue value = this.getValue();
		if (value == null) return "null";
		if (!value.getBooleanValueList().isEmpty()) return Primitives.BOOLEAN;
		if (!value.getDoubleValueList().isEmpty()) return Primitives.DOUBLE;
		if (!value.getIntegerValueList().isEmpty()) return Primitives.INTEGER;
		if (!value.getNaturalValueList().isEmpty()) return Primitives.NATURAL;
		if (!value.getInstanceNameList().isEmpty()
			|| !value.getIdentifierReferenceList().isEmpty()) return Primitives.REFERENCE;
		if (!value.getStringValueList().isEmpty()) return Primitives.STRING;
		if (value.getEmptyField() != null) return EMPTY;
		return "null";
	}

	public List<Object> values() {
		return ((Parameter) this).getValue() == null ? Collections.emptyList() : ((Parameter) this).getValue().values();
	}

	public TaraMeasureValue getMetric() {
		return ((Parameter) this).getValue().getMeasureValue();
	}

	public String contract() {
		return contract;
	}

	public void contract(String contract) {
		this.contract = contract;
	}

	public String inferredType() {
		return inferredType;
	}

	public void inferredType(String type) {
		this.inferredType = type;
	}

	public String toString() {
		final NodeContainer contextOf = TaraPsiImplUtil.getContextOf(this);
		return "Parameter in" + (contextOf != null ? contextOf.qualifiedName() : "");
	}

	public int position() {
		return 0;
	}

	public boolean isExplicit() {
		return true;
	}

	@Nullable
	public TaraValue getValue() {
		return findChildByClass(TaraValue.class);
	}

	public boolean isMultiple() {
		return ((Parameter) this).getValue().getChildren().length - (((Parameter) this).getValue().getMeasureValue() != null ? 1 : 0) > 1;
	}

	public FacetApply isInFacet() {
		final NodeContainer contextOf = TaraPsiImplUtil.getContextOf(this);
		return contextOf instanceof FacetApply ? (FacetApply) contextOf : null;
	}

	public void name(String name) {
	}

	public List<String> flags() {
		return null;
	}

	public void flags(List<String> annotations) {

	}

	public void multiple(boolean multiple) {
	}

	public String metric() {
		return getMetric().getText();
	}

	public void metric(String metric) {
	}

	public boolean isVariableInit() {
		return true;
	}

	public void addAllowedParameters(List<String> values) {

	}

	public boolean hasReferenceValue() {
		return getValueType().equals(Primitives.REFERENCE);
	}

	public List<String> getAllowedValues() {
		return null;
	}

	public void addAllowedValues(List<String> allowedValues) {

	}

	public void substituteValues(List<? extends Object> newValues) {

	}

	public String getUID() {
		return null;
	}
}