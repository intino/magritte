package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.IdentifierReference;
import siani.tara.intellij.lang.psi.Parameter;
import siani.tara.intellij.lang.psi.Parameters;
import siani.tara.intellij.lang.psi.resolve.TaraParameterReferenceSolver;
import siani.tara.lang.Node;
import siani.tara.lang.NodeWord;
import siani.tara.lang.TreeWrapper;
import siani.tara.lang.Variable;

import java.util.List;

public class ParameterMixin extends ASTWrapperPsiElement {
	public ParameterMixin(@NotNull ASTNode node) {
		super(node);
	}


	public String getParameter() {
		return this.getText();
	}

	@NotNull
	@Override
	public PsiReference[] getReferences() {
		TreeWrapper heritage = TaraLanguage.getHeritage(getContainingFile());
		if (heritage == null) return new PsiReference[0];
		Node node = heritage.getNodeTable().get(TaraUtil.getMetaQualifiedName(TaraPsiImplUtil.getContextOf(this)));
		if (node == null) return new PsiReference[0];
		List<Variable> variables = node.getObject().getVariables();
		if (variables.isEmpty()) return new PsiReference[]{};
		Variable variable = variables.get(getIndexInParent());
		if (NodeWord.class.isInstance(variable))
			return new PsiReference[]{new TaraMetaWordReferenceSolver(this, new TextRange(0, getParameter().length()), node, variable)};
		else if (this.getFirstChild() instanceof IdentifierReference)
			return new PsiReference[]{new TaraParameterReferenceSolver(this, new TextRange(0, getParameter().length()))};
		return new PsiReference[0];
	}

	@Nullable
	@Override
	public PsiReference getReference() {
		TreeWrapper heritage = TaraLanguage.getHeritage(getContainingFile());
		Node node = heritage.getNodeTable().get(TaraUtil.getMetaQualifiedName(TaraPsiImplUtil.getContextOf(this)));
		if (node == null) return null;
		Variable variable = node.getObject().getVariables().get(getIndexInParent());
		if (NodeWord.class.isInstance(variable))
			return new TaraMetaWordReferenceSolver(this, new TextRange(0, getParameter().length()), node, variable);
		else if (this.getFirstChild() instanceof IdentifierReference) {
			PsiReference[] references = new PsiReference[]{new TaraParameterReferenceSolver(this, new TextRange(0, getParameter().length()))};
			return references.length == 0 ? null : references[0];
		}
		return null;
	}

	private int getIndexInParent() {
		Parameter[] parameters = ((Parameters) this.getParent()).getParameters();
		for (int i = 0; i < parameters.length; i++)
			if (parameters[i].equals(this)) return i;
		return -1;
	}
}
