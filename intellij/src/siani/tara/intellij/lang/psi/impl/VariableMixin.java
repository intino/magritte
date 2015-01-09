package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
		TaraVariableType variableType = ((TaraVariable) this).getVariableType();
		if (variableType == null) return "unName";
		if (variableType.getWord() != null)
			return getAsWord();
		ASTNode[] child = variableType.getNode().getChildren(TokenSet.create(TaraTypes.IDENTIFIER_KEY));
		if (child == null || child.length == 0) {
			PsiElement lastChild = variableType.getLastChild();
			ASTNode[] children = lastChild.getNode().getChildren(TokenSet.create(TaraTypes.IDENTIFIER_KEY));
			return (children.length == 0) ? "" : children[0].getText();
		}
		return child[child.length - 1].getText();
	}

	private String getAsWord() {
		TaraVariableType type = ((TaraVariable) this).getVariableType();
		if (type == null || type.getWord() == null) return "";
		return type.getWord().getNode().findChildByType(TaraTypes.IDENTIFIER_KEY).getText();
	}

	@NotNull
	public String getType() {
		TaraVariableType type = ((TaraVariable) this).getVariableType();
		if (type == null) return "null";
		return type.getNode().getFirstChildNode().getText();
	}

	@NotNull
	public Collection<String> getDefaultValuesAsString() {
		List<String> list = new ArrayList<>();
		TaraVariableType type = ((TaraVariable) this).getVariableType();
		if (type == null) return Collections.EMPTY_LIST;
		if (type.getBooleanAttribute() != null)
			list.addAll(getElementsAsString(type.getBooleanAttribute().getBooleanValueList()));
		else if (type.getDoubleAttribute() != null)
			list.addAll(getElementsAsString(type.getDoubleAttribute().getDoubleValueList()));
		else if (type.getNaturalAttribute() != null)
			list.addAll(getElementsAsString(type.getNaturalAttribute().getNaturalValueList()));
		else if (type.getIntegerAttribute() != null)
			list.addAll(getElementsAsString(type.getIntegerAttribute().getIntegerValueList()));
		else if (type.getStringAttribute() != null)
			list.addAll(getElementsAsString(type.getStringAttribute().getStringValueList()));
		else if (type.getDateAttribute() != null)
			list.addAll(getElementsAsString(type.getDateAttribute().getStringValueList()));
		else if (type.getReferenceAttribute() != null && type.getReferenceAttribute().getEmptyField() != null)
			list.add((type).getReferenceAttribute().getEmptyField().getText());
		return list;
	}

	private Collection<String> getElementsAsString(List<? extends PsiElement> elements) {
		List<String> list = new ArrayList<>();
		for (PsiElement element : elements) list.add(element.getText());
		return list;
	}

	@Override
	public String toString() {
		return getType() + " " + getName();
	}
}
