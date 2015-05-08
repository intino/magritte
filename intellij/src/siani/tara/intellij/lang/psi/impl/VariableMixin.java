package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.*;

public class VariableMixin extends ASTWrapperPsiElement {


	public VariableMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	public PsiElement setName(String newName) {
		ASTNode keyNode = getNode().findChildByType(TaraTypes.IDENTIFIER);
		if (keyNode != null) {
			Variable variable = TaraElementFactoryImpl.getInstance(this.getProject()).createAttribute(newName, getType());
			ASTNode node = variable.getFirstChild().getChildren()[0].getNode();
			this.getNode().replaceChild(keyNode, node);
		}
		return this;
	}

	public NativeName getNativeName(){
		TaraAttributeType attributeType = ((TaraVariable) this).getAttributeType();
		if (attributeType == null) return null;
		return attributeType.getNativeName();
	}

	@Nullable
	@Override
	public String getName() {
		ASTNode[] child = this.getNode().getChildren(TokenSet.create(TaraTypes.IDENTIFIER));
		if (child.length == 0) return null;
		return child[0].getText();
	}

	@Nullable
	public String getType() {
		TaraVariableType type = ((TaraVariable) this).getVariableType();
		return type == null ? null : type.getText();
	}

	@Override
	public String toString() {
		return getType() + " " + getName();
	}
}
