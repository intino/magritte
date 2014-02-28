package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.metamodel.psi.Attribute;
import monet.tara.intellij.metamodel.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;

public class TaraAttributeMixin extends ASTWrapperPsiElement {


	public TaraAttributeMixin(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	public PsiElement setName(String newName) {
		ASTNode keyNode = getNode().findChildByType(TaraTypes.IDENTIFIER_KEY);
		if (keyNode != null) {
			Attribute attribute = TaraElementFactoryImpl.getInstance(this.getProject()).createAttribute(newName, getType());
			ASTNode newKeyNode = attribute.getFirstChild().getChildren()[0].getNode();
			this.getNode().replaceChild(keyNode, newKeyNode);
		}
		return this;
	}

	@Override
	public String getName() {
		ASTNode keyNode = getNode().findChildByType(TaraTypes.IDENTIFIER_KEY);
		return (keyNode == null) ? null : this.getNode().findChildByType(TaraTypes.IDENTIFIER_KEY).getText();

	}

	public String getType() {
		ASTNode keyNode = getNode().findChildByType(TaraTypes.UID_TYPE);
		if (keyNode != null) return keyNode.getText();
		return null;
	}


}
