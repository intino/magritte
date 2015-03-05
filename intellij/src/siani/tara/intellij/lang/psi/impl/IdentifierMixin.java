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
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.resolve.TaraConceptReferenceSolver;
import siani.tara.intellij.lang.psi.resolve.TaraFileReferenceSolver;
import siani.tara.intellij.lang.psi.resolve.TaraParameterReferenceSolver;
import siani.tara.lang.*;
import siani.tara.lang.Variable;
import siani.tara.lang.Word;

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
		PsiReference reference = getReference();
		return reference == null ? new PsiReference[0] : new PsiReference[]{reference};
	}

	@Nullable
	@Override
	public PsiReference getReference() {
		Parameter parameter;
		if ((parameter = isParameterReference()) != null)
			return createResolverForParameter(parameter);
		else if (isFileReference()) return creteFileResolver();
		else return createConceptResolver();
	}

	private PsiReference creteFileResolver() {
		return new TaraFileReferenceSolver((HeaderReference) this.getParent(), getRange());
	}

	private PsiReference createConceptResolver() {
		Concept container = TaraPsiImplUtil.getConceptContainerOf(this);
		Node node = TaraUtil.getMetaConcept(container);
		return new TaraConceptReferenceSolver(this, getRange(), container, node);
	}

	private PsiReference createResolverForParameter(Parameter parameter) {
		Concept container = TaraPsiImplUtil.getConceptContainerOf(this);
		Node node = TaraUtil.getMetaConcept((Concept) container.getOriginalElement());
		if (node == null)
			return null;
		List<Variable> variables = node.getObject().getVariables();
		if (variables.isEmpty() || variables.size() <= parameter.getIndexInParent()) return null;
		Variable variable = parameter.isExplicit() ? findVar(variables, parameter.getExplicitName()) : variables.get(parameter.getIndexInParent());
		if (variable == null)
			return null;
		if (variable instanceof siani.tara.lang.Word)
			return new TaraMetaWordReferenceSolver(this, getRange(), (Word) variable);
		else if (variable instanceof Reference)
			return new TaraParameterReferenceSolver(this, getRange(), container, node, variable);
		return null;
	}

	private TextRange getRange() {
		return new TextRange(0, getIdentifier().length());
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

	public boolean isFileReference() {
		return this.getParent() instanceof TaraHeaderReference;
	}
}
