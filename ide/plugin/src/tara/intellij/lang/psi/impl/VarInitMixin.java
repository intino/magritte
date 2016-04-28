package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.Valued;
import tara.lang.model.*;
import tara.lang.model.rules.variable.VariableRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static tara.lang.model.Primitive.EMPTY;
import static tara.lang.model.Primitive.REFERENCE;

public class VarInitMixin extends ASTWrapperPsiElement {

	private VariableRule rule = null;
	private String scope = null;
	private String facet = "";
	private Primitive inferredType;
	private List<Tag> flags = new ArrayList<>();

	public VarInitMixin(@NotNull ASTNode node) {
		super(node);
	}


	public String name() {
		ASTNode childByType = this.getNode().findChildByType(TaraTypes.IDENTIFIER);
		return childByType != null ? childByType.getText() : null;
	}

	public Primitive getValueType() {
		TaraValue value = this.getValue();
		if (value == null) return null;
		Primitive primitive = ((Valued) this).asPrimitive(value);
		if (primitive != null) return primitive;
		if (!value.getIdentifierReferenceList().isEmpty())
			return REFERENCE;
		if (value.getEmptyField() != null) return EMPTY;
		return null;
	}

	public List<Object> values() {
		Value value = this.getValue();
		Value bodyValue = ((TaraVarInit) this).getBodyValue();
		if (value == null && bodyValue == null) return Collections.emptyList();
		else if (bodyValue != null) return Value.makeUp(bodyValue.values(), type(), this);
		else return Value.makeUp(value.values(), type(), this);
	}

	public void values(List<Object> objects) {

	}

	public TaraMetric getMetric() {
		final TaraValue value = this.getValue();
		return value != null ? value.getMetric() : null;
	}

	public VariableRule rule() {
		return rule;
	}

	public void rule(VariableRule rule) {
		this.rule = rule;
	}

	public Primitive type() {
		return inferredType;
	}

	public void type(Primitive type) {
		this.inferredType = type;
	}

	public String facet() {
		return facet;
	}

	public void facet(String facet) {
		this.facet = facet;
	}

	public String toString() {
		final Node contextOf = container();
		return "Parameter " + name() + " in " + (contextOf != null ? contextOf.qualifiedName() : "");
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
		return this.getValue() != null && this.getValue().getChildren().length - (this.getValue().getMetric() != null ? 1 : 0) > 1;
	}

	public Facet isInFacet() {
		final NodeContainer contextOf = TaraPsiImplUtil.getContainerOf(this);
		return contextOf instanceof Facet ? (Facet) contextOf : null;
	}

	public void name(String name) {
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
		final TaraMetric metric = getMetric();
		return metric != null ? metric.getText() : "";
	}

	public void metric(String metric) {
	}

	public boolean isVariableInit() {
		return true;
	}

	public boolean hasReferenceValue() {
		return getValueType().equals(Primitive.REFERENCE);
	}

	public void substituteValues(List<? extends Object> newValues) {

	}

	public String getUID() {
		return null;
	}

	public Node container() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	public String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}

	public String scope() {
		return scope;
	}

	public void scope(String scope) {
		this.scope = scope;
	}
}