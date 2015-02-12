package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.Parameter;
import siani.tara.intellij.lang.psi.resolve.TaraInternalReferenceSolver;
import siani.tara.intellij.lang.psi.resolve.TaraParameterReferenceSolver;
import siani.tara.lang.Node;
import siani.tara.lang.Reference;
import siani.tara.lang.Variable;

import javax.swing.*;
import java.util.List;

public class IdentifierMixin extends ASTWrapperPsiElement {

	public IdentifierMixin(@NotNull ASTNode node) {
		super(node);
	}

	public String getIdentifier() {
		return TaraPsiImplUtil.getIdentifier((Identifier) this);
	}

	@NotNull
	@Override
	public PsiReference[] getReferences() {
		return new PsiReference[]{getReference()};
	}

	@Nullable
	@Override
	public PsiReference getReference() {
		PsiReference[] references;
		Parameter parameter;
		references = (parameter = isParameterReference()) != null ?
			createResolverForParameter(parameter) :
			new PsiReference[]{new TaraInternalReferenceSolver(this, new TextRange(0, getIdentifier().length()))};
		return references.length == 0 ? null : references[0];
	}

	private PsiReference[] createResolverForParameter(Parameter parameter) {
		Node node = TaraUtil.getMetaConcept(TaraPsiImplUtil.getConceptContainerOf(this));
		if (node == null) return new PsiReference[0];
		List<Variable> variables = node.getObject().getVariables();
		if (variables.isEmpty() || variables.size() <= parameter.getIndexInParent()) return new PsiReference[]{};
		Variable variable = parameter.isExplicit() ? findVar(variables, parameter.getExplicitName()) : variables.get(parameter.getIndexInParent());
		if (variable == null) return new PsiReference[0];
		if (variable instanceof siani.tara.lang.Word)
			return new PsiReference[]{new TaraMetaWordReferenceSolver(this, new TextRange(0, parameter.getText().length()), node, variable)};
		else if (variable instanceof Reference)
			return new PsiReference[]{new TaraParameterReferenceSolver(this, new TextRange(0, parameter.getText().length()), TaraPsiImplUtil.getConceptContainerOf(this), node, variable)};
		return new PsiReference[0];
	}

	@NotNull
	public PsiElement setName(String name) {
		Identifier identifier = TaraElementFactoryImpl.getInstance(this.getProject()).createNameIdentifier(name);
		ASTNode node = identifier.getNode();
		this.getParent().getNode().replaceChild(getNode(), node);
		return identifier;
	}

	@Override
	public Icon getIcon(@IconFlags int i) {
		return TaraIcons.getIcon(TaraIcons.CONCEPT);
	}

	@Override
	public String getName() {
		return this.getText();
	}

	public String toString() {
		return this.getName();
	}

	public Parameter isParameterReference() {
		PsiElement parent = this.getParent();
		while (!PsiFile.class.isInstance(parent)) {
			if (parent instanceof Parameter) return (Parameter) parent;
			parent = parent.getParent();
		}

		return null;
	}

	private Variable findVar(List<Variable> variables, String name) {
		for (Variable variable : variables)
			if (variable.getName().equals(name))
				return variable;
		return null;
	}
}
