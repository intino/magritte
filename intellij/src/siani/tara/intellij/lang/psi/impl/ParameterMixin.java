package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.resolve.TaraParameterReferenceSolver;
import siani.tara.lang.*;
import siani.tara.lang.Variable;
import siani.tara.lang.Word;

import java.util.ArrayList;
import java.util.Arrays;
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
		Node node = TaraUtil.getMetaConcept(TaraPsiImplUtil.getConceptContainerOf(this));
		if (node == null) return new PsiReference[0];//TODO
		List<Variable> variables = node.getObject().getVariables();
		if (variables.isEmpty() || variables.size() <= getIndexInParent()) return new PsiReference[]{};
		Variable variable = variables.get(getIndexInParent());
		if (variable instanceof Word)
			return new PsiReference[]{new TaraMetaWordReferenceSolver(this, new TextRange(0, getParameter().length()), node, variable)};
		else if (variable instanceof Reference)
			return new PsiReference[]{new TaraParameterReferenceSolver(this, new TextRange(0, getParameter().length()))};
		return new PsiReference[0];
	}

	@Nullable
	@Override
	public PsiReference getReference() {
		Model heritage = TaraLanguage.getMetaModel(getContainingFile());
		Node node = heritage.getNodeTable().get(TaraUtil.getMetaQualifiedName(TaraPsiImplUtil.getConceptContainerOf(this)));
		if (node == null) return null;
		Variable variable = node.getObject().getVariables().get(getIndexInParent());
		if (siani.tara.lang.Word.class.isInstance(variable))
			return new TaraMetaWordReferenceSolver(this, new TextRange(0, getParameter().length()), node, variable);
		else if (this.getFirstChild() instanceof IdentifierReference) {
			PsiReference[] references = new PsiReference[]{new TaraParameterReferenceSolver(this, new TextRange(0, getParameter().length()))};
			return references.length == 0 ? null : references[0];
		}
		return null;
	}

	public int getIndexInParent() {
		return Arrays.asList(((Parameters) this.getParent()).getParameters()).indexOf(this);
	}

	public boolean isExplicit() {
		return this instanceof TaraExplicitParameter;
	}

	public String getExplicitName() {
		if (this instanceof TaraExplicitParameter) return ((TaraExplicitParameter) this).getIdentifier().getText();
		return null;
	}

	public TaraParameterValue getValue() {
		if (this instanceof TaraExplicitParameter) return ((TaraExplicitParameter) this).getParameterValue();
		else return ((TaraImplicitParameter) this).getParameterValue();
	}

	public TaraMeasureValue getMeasure() {
		if (this instanceof TaraExplicitParameter)
			return ((TaraExplicitParameter) this).getParameterValue() != null ? ((TaraExplicitParameter) this).getParameterValue().getMeasureValue() : null;
		else return ((TaraImplicitParameter) this).getParameterValue().getMeasureValue();
	}

	public boolean isList() {
		return getValue().getChildren().length - (getValue().getMeasureValue() != null ? 1 : 0) > 1;
	}

	public int getValuesLength() {
		return getValue().getChildren().length - (getValue().getMeasureValue() != null ? 1 : 0);
	}

	public String[] getValues() {
		List<String> values = new ArrayList<>();
		for (PsiElement element : getValue().getChildren()) {
			if (element instanceof TaraMeasureValue) continue;
			values.add(element.getText());
		}
		return values.toArray(new String[values.size()]);
	}


	public TaraFacetApply isInFacet() {
		PsiElement aElement = this;
		while (!(aElement.getParent() instanceof Concept) && !(aElement.getParent() instanceof TaraFacetApply))
			aElement = aElement.getParent();
		return (aElement.getParent() instanceof TaraFacetApply) ? (TaraFacetApply) aElement.getParent() : null;
	}
}
