package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.*;
import tara.lang.model.NodeContainer;
import tara.lang.model.Primitive;
import tara.lang.model.Tag;
import tara.lang.model.rules.variable.VariableRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static tara.lang.model.Primitive.REFERENCE;

public class ParameterMixin extends ASTWrapperPsiElement {

	private VariableRule rule = null;
	private Primitive type;
	private String name = "";
	private List<Tag> flags = new ArrayList<>();
	private String scope;

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

	public VariableRule rule() {
		return rule;
	}

	public void rule(VariableRule rule) {
		this.rule = rule;
	}

	public String scope() {
		return scope;
	}


	public void scope(String scope) {
		this.scope = scope;
	}

	public Primitive type() {
		return type;
	}

	public void type(Primitive type) {
		this.type = type;
	}

	public List<Object> values() {
		Value value = ((Valued) this).getValue();
		return value == null ? Collections.emptyList() : Value.makeUp(value.values(), type, this);
	}


	public TaraBodyValue getBodyValue() {
		return null;
	}

	public List<Tag> flags() {
		return this.flags;
	}

	public void flags(List<Tag> flags) {
		this.flags = new ArrayList<>(flags);
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
		final TaraMetric newMetric = TaraElementFactory.getInstance(this.getProject()).createMetric(metric);
		if (getMetric() == null) addAfter(newMetric.copy(), ((TaraParameter) this).getValue());
		else getMetric().replace(newMetric.copy());
	}

	public void values(List<Object> objects) {

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
