package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraFacetApply;
import tara.intellij.lang.psi.TaraParameters;
import tara.language.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.unmodifiableList;
import static tara.intellij.lang.psi.impl.TaraUtil.getVariablesOf;

public class FacetApplyMixin extends ASTWrapperPsiElement {

	public FacetApplyMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	public List<Node> components() {
		return unmodifiableList(TaraUtil.getComponentsOf((Facet) this));
	}

	@NotNull
	public List<Variable> variables() {
		return unmodifiableList(getVariablesOf((Facet) this));
	}

	@NotNull
	public List<Parameter> parameters() {
		List<Parameter> parameterList = new ArrayList<>();
		final TaraParameters parameters = ((TaraFacetApply) this).getParameters();
		if (parameters != null) parameterList.addAll(parameters.getParameters());
		parameterList.addAll(getVarInits());
		return parameterList;
	}

	@NotNull
	private List<Parameter> getVarInits() {
		if (((TaraFacetApply) this).getBody() == null) return EMPTY_LIST;
		return unmodifiableList(((TaraFacetApply) this).getBody().getVarInitList());
	}

	@NotNull
	public String qualifiedName() {
		return container().qualifiedName() + "." + ((Node) container()).name() + "_" + type();
	}

	@NotNull
	public String type() {
		if (!((TaraFacetApply) this).getMetaIdentifierList().isEmpty())
			return ((TaraFacetApply) this).getMetaIdentifierList().get(0).getText();
		return "";
	}

	public Node component(String name) {
		for (Node node : components()) if (name.equals(node.name())) return node;
		return null;
	}

	public <T extends Node> boolean contains(T node) {
		return components().contains(node);
	}

	public List<Node> siblings() {
		return container().components();
	}

	public NodeContainer container() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	public String doc() {
		return null;
	}

	public String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}

	public List<String> uses() {
		return Collections.emptyList();
	}
}
