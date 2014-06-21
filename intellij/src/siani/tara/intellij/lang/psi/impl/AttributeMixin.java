package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Attribute;
import siani.tara.intellij.lang.psi.TaraTypes;
import siani.tara.intellij.lang.psi.TaraWord;

public class AttributeMixin extends ASTWrapperPsiElement {


	public AttributeMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	public PsiElement setName(String newName) {
		ASTNode keyNode = getNode().findChildByType(TaraTypes.IDENTIFIER_KEY);
		if (keyNode != null) {
			Attribute attribute = TaraElementFactoryImpl.getInstance(this.getProject()).createAttribute(newName, getType());
			ASTNode node = attribute.getFirstChild().getChildren()[0].getNode();
			this.getNode().replaceChild(keyNode, node);
		}
		return this;
	}

	@Override
	public String getName() {
		ASTNode[] child = this.getNode().getChildren(TokenSet.create(TaraTypes.IDENTIFIER_KEY));
		if (child == null) {
			PsiElement lastChild = this.getLastChild();
			if (lastChild instanceof TaraWord) return getAsWord();
		}
		return child[child.length - 1].getText();
	}

	private String getAsWord() {
		return this.getLastChild().getNode().findChildByType(TaraTypes.IDENTIFIER_KEY).getText();
	}

	public String getType() {
		ASTNode keyNode = getNode().findChildByType(TaraTypes.ALIAS_TYPE);
		if (keyNode != null) return keyNode.getText();
		return null;
	}


}
