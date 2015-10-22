package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.*;
import tara.language.model.NodeContainer;
import tara.language.model.Primitive;

import java.util.Collections;
import java.util.List;

import static tara.language.model.Primitive.REFERENCE;

public class ParameterMixin extends ASTWrapperPsiElement {

	private String contract = "";
	private Primitive inferredType;
	private String name = "";

	public ParameterMixin(@NotNull ASTNode node) {
		super(node);
	}


	public String name() {
		if (((TaraParameter) this).getIdentifier() != null) return ((TaraParameter) this).getIdentifier().getText();
		else if (this instanceof TaraVarInit) return ((TaraVarInit) this).getIdentifier().getText();
		return name;
	}

	public void name(String name) {
		this.name = name;
	}

	public String getParameter() {
		return this.getText();
	}

	public int position() {
		return ((Parameters) this.getParent()).getParameters().indexOf(this);
	}

	public String contract() {
		return contract;
	}

	public void contract(String contract) {
		this.contract = contract;
	}

	public Primitive inferredType() {
		return inferredType;
	}

	public void inferredType(Primitive type) {
		this.inferredType = type;
	}

	public List<Object> values() {
		Value value = ((Valued) this).getValue();
		return value == null ? Collections.emptyList() : value.values();
	}


	public List<String> flags() {
		return Collections.emptyList();
	}

	public void flags(List<String> flags) {

	}

	public void multiple(boolean multiple) {

	}

	public String metric() {
		return getMetric().getText();
	}

	public boolean isVariableInit() {
		return this instanceof TaraVarInit;
	}

	public boolean hasReferenceValue() {
		return REFERENCE.equals(((Valued) this).getInferredType());
	}

	public TaraMeasureValue getMetric() {
		return ((Valued) this).getValue().getMeasureValue();
	}

	public void metric(String metric) {
	}

	public void addAllowedValues(List<String> allowedValues) {

	}

	public String getUID() {
		return null;
	}

	public boolean isExplicit() {
		return ((TaraParameter) this).getIdentifier() != null;
	}

	public boolean isMultiple() {
		return ((Valued) this).getValue().getChildren().length - (((Valued) this).getValue().getMeasureValue() != null ? 1 : 0) > 1;
	}

	public int size() {
		return ((Valued) this).getValue().getChildren().length - (((Valued) this).getValue().getMeasureValue() != null ? 1 : 0);
	}

	public void addAllowedParameters(List<String> values) {
	}

	public void substituteValues(List<? extends Object> newValues) {
	}

	public List<String> getAllowedValues() {
		return Collections.emptyList();
	}

	@Override
	public String toString() {
		final NodeContainer contextOf = TaraPsiImplUtil.getContainerOf(this);
		return "Parameter " + name() + " in " + (contextOf != null ? contextOf.qualifiedName() : "");
	}

	public NodeContainer container() {
		return TaraPsiImplUtil.getContainerOf(this);
	}

	public String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}
}
