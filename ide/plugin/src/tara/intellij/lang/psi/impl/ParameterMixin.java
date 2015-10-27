package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaResourceRootType;
import tara.intellij.lang.psi.*;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.NodeContainer;
import tara.lang.model.Primitive;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static tara.lang.model.Primitive.FILE;
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
		return value == null ? Collections.emptyList() : cast(value.values());
	}

	private List<Object> cast(List<Object> values) {
		if (inferredType != null && inferredType.equals(FILE))
			return values.stream().map(o -> new File("./" + o.toString().substring(1, o.toString().length() - 1))).collect(Collectors.toList());
//		else if (inferredType != null && inferredType.equals(Primitive.WORD))
//			return values.stream().map(o -> o.toString().substring(Parameter.REFERENCE_PREFIX.length())).collect(Collectors.toList());
		return values;
	}

	private String findResourcesPath() {
		final Module module = ModuleProvider.getModuleOf(this);
		if (module == null) return File.separator;
		final List<VirtualFile> roots = ModuleRootManager.getInstance(module).getModifiableModel().getSourceRoots(JavaResourceRootType.RESOURCE);
		return roots.stream().filter(r -> r.getName().equals("res")).findAny().get().getPath();
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

	public void addAllowedValues(List<String> allowedValues) {
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
