package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VariableMixin extends ASTWrapperPsiElement {


	public VariableMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	public PsiElement setName(String newName) {
		ASTNode keyNode = getNode().findChildByType(TaraTypes.IDENTIFIER_KEY);
		if (keyNode != null) {
			Variable variable = TaraElementFactoryImpl.getInstance(this.getProject()).createAttribute(newName, getType());
			ASTNode node = variable.getFirstChild().getChildren()[0].getNode();
			this.getNode().replaceChild(keyNode, node);
		}
		return this;
	}

	@Override
	public String getName() {
		ASTNode[] child = this.getNode().getChildren(TokenSet.create(TaraTypes.IDENTIFIER_KEY));
		if (child == null || child.length == 0) {
			PsiElement lastChild = this.getLastChild();
			if (lastChild instanceof TaraWord) return getAsWord();
			else {
				ASTNode[] children = lastChild.getNode().getChildren(TokenSet.create(TaraTypes.IDENTIFIER_KEY));
				return (children.length == 0) ? "" : children[0].getText();
			}
		}
		return child[child.length - 1].getText();
	}

	private String getAsWord() {
		return this.getLastChild().getNode().findChildByType(TaraTypes.IDENTIFIER_KEY).getText();
	}

	public String getType() {
		ASTNode keyNode = getNode().getFirstChildNode().getTreeNext();
		if (keyNode != null) return keyNode.getText();
		return null;
	}

	public Collection<String> getAnnotations() {
		List<String> list = new ArrayList<>();
		TaraAnnotationsAndFacets annotationsAndFacets = ((TaraVariable) this).getAnnotationsAndFacets();
		if (annotationsAndFacets != null)
			for (siani.tara.intellij.lang.psi.Annotations taraAnnotations : annotationsAndFacets.getAnnotationsList())
				for (PsiElement element : taraAnnotations.getAnnotations()) list.add(element.getText());
		return list;
	}

	public Collection<String> getDefaultValues() {
		List<String> list = new ArrayList<>();
		if (((TaraVariable) this).getBooleanValueList().isEmpty())
			list.addAll(getElementsAsString(((TaraVariable) this).getBooleanValueList()));
		else if (!((TaraVariable) this).getDoubleValueList().isEmpty())
			list.addAll(getElementsAsString(((TaraVariable) this).getDoubleValueList()));
		else if (!((TaraVariable) this).getNaturalValueList().isEmpty())
			list.addAll(getElementsAsString(((TaraVariable) this).getNaturalValueList()));
		else if (!((TaraVariable) this).getIntegerValueList().isEmpty())
			list.addAll(getElementsAsString(((TaraVariable) this).getIntegerValueList()));
		else if (!((TaraVariable) this).getStringValueList().isEmpty())
			list.addAll(getElementsAsString(((TaraVariable) this).getStringValueList()));
		else if (!((TaraVariable) this).getCoordinateValueList().isEmpty())
			list.addAll(getElementsAsString(((TaraVariable) this).getCoordinateValueList()));
		else if (!((TaraVariable) this).getDateValueList().isEmpty())
			list.addAll(getElementsAsString(((TaraVariable) this).getDateValueList()));
		else if (((TaraVariable) this).getEmptyField() != null)
			list.add(((TaraVariable) this).getEmptyField().getText());
		return list;
	}

	private Collection<String> getElementsAsString(List<? extends PsiElement> elements) {
		List<String> list = new ArrayList<>();
		for (PsiElement element : elements) list.add(element.getText());
		return list;
	}

}
