package monet.::projectName::.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.lang.psi.Attribute;
import monet.::projectName::.intellij.lang.psi.::projectProperName::Types;
import org.jetbrains.annotations.NotNull;

public class AttributeMixin extends ASTWrapperPsiElement {


	public AttributeMixin(\@NotNull ASTNode node) {
		super(node);
	}

	\@NotNull
	public PsiElement setName(String newName) {
		ASTNode keyNode = getNode().findChildByType(::projectProperName::Types.IDENTIFIER_KEY);
		if (keyNode != null) {
			Attribute attribute = ::projectProperName::ElementFactoryImpl.getInstance(this.getProject()).createAttribute(newName, getType());
			ASTNode node = attribute.getFirstChild().getChildren()[0].getNode();
			this.getNode().replaceChild(keyNode, node);
		}
		return this;
	}

	\@Override
	public String getName() {
		ASTNode childByType = this.getNode().findChildByType(::projectProperName::Types.IDENTIFIER_KEY);
		return childByType.getText();
	}

	public String getType() {
		ASTNode keyNode = getNode().findChildByType(::projectProperName::Types.UID_TYPE);
		if (keyNode != null) return keyNode.getText();
		return null;
	}


}
