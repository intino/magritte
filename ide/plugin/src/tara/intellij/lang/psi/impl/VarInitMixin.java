package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.language.model.Facet;
import tara.language.model.NodeContainer;
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
		String primitive = asPrimitive(value);
		if (primitive != null) return primitive;
		if (!value.getInstanceNameList().isEmpty()|| !value.getIdentifierReferenceList().isEmpty()) return Primitives.REFERENCE;
		if (value.getEmptyField() != null) return EMPTY;
		return "null";
	}

	@Nullable
	private String asPrimitive(TaraValue value) {
		if (!value.getBooleanValueList().isEmpty()) return Primitives.BOOLEAN;
		if (!value.getDoubleValueList().isEmpty()) return Primitives.DOUBLE;
		if (!value.getIntegerValueList().isEmpty()) return Primitives.INTEGER;
		if (!value.getNaturalValueList().isEmpty()) return Primitives.NATURAL;
		if (!value.getStringValueList().isEmpty()) return Primitives.STRING;
		return null;
	}

	public List<Object> values() {
		return this.getValue() == null ? Collections.emptyList() : this.getValue().values();
	}

	public TaraMeasureValue getMetric() {
		return this.getValue().getMeasureValue();
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
		final NodeContainer contextOf = TaraPsiImplUtil.getContainerOf(this);
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
		return this.getValue().getChildren().length - (this.getValue().getMeasureValue() != null ? 1 : 0) > 1;
	}

	public Facet isInFacet() {
		final NodeContainer contextOf = TaraPsiImplUtil.getContainerOf(this);
		return contextOf instanceof Facet ? (Facet) contextOf : null;
	}

	public void name(String name) {
	}

	public List<String> flags() {
		return Collections.emptyList();
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
		return Collections.emptyList();
	}

	public void addAllowedValues(List<String> allowedValues) {

	}

	public void substituteValues(List<? extends Object> newValues) {

	}

	public String getUID() {
		return null;
	}

	public NodeContainer container() {
		return TaraPsiImplUtil.getContainerOf(this);
	}

	public String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}
}