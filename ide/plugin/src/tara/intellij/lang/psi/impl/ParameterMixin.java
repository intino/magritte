package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.*;
import tara.lang.model.NodeContainer;
import tara.lang.model.Primitive;

import java.util.Collections;
import java.util.List;

import static tara.lang.model.Primitive.REFERENCE;

public class ParameterMixin extends ASTWrapperPsiElement {

	private tara.lang.model.Rule rule = null;
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

	public tara.lang.model.Rule rule() {
		return rule;
	}

	public void rule(tara.lang.model.Rule rule) {
		this.rule = rule;
	}

	public Primitive inferredType() {
		return inferredType;
	}

	public void inferredType(Primitive type) {
		this.inferredType = type;
	}

	public List<Object> values() {
		Value value = ((Valued) this).getValue();
		return value == null ? Collections.emptyList() : Value.makeUp(value.values(), inferredType, this);
	}

	public List<String> flags() {
		return Collections.emptyList();
	}

	public void flags(List<String> flags) {
	}

	public void multiple(boolean multiple) {
	}

	public String metric() {
		TaraMetric metric = getMetric();
		return metric != null ? metric.getText() : "";
	}

	public boolean isVariableInit() {
		return this instanceof TaraVarInit;
	}

	public boolean hasReferenceValue() {
		return REFERENCE.equals(((Valued) this).getInferredType());
	}

	public TaraMetric getMetric() {
		return ((Valued) this).getValue().getMetric();
	}

	public void metric(String metric) {
	}

	public String getUID() {
		return null;
	}

	public boolean isExplicit() {
		return ((TaraParameter) this).getIdentifier() != null;
	}

	public boolean isMultiple() {
		return ((Valued) this).getValue().getChildren().length - (((Valued) this).getValue().getMetric() != null ? 1 : 0) > 1;
	}

	public int size() {
		return ((Valued) this).getValue().getChildren().length - (((Valued) this).getValue().getMetric() != null ? 1 : 0);
	}

	public void substituteValues(List<? extends Object> newValues) {
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
