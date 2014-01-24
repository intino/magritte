package monet.::projectName::.intellij.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.psi.::projectProperName::Definition;
import monet.::projectName::.intellij.psi.::projectProperName::DefinitionSignature;
import monet.::projectName::.intellij.psi.::projectProperName::Identifier;
import monet.::projectName::.intellij.psi.::projectProperName::Types;

public class ::projectProperName::PsiImplUtil {

	public static String getIdentifier(::projectProperName::Identifier keyNode) {
		if (keyNode != null) return keyNode.getText();
		else return null;
	}

	public static String getIdentifier(::projectProperName::Definition element) {
		ASTNode valueNode = element.getNode().findChildByType(::projectProperName::Types.DEFINITION_SIGNATURE).findChildByType(::projectProperName::Types.IDENTIFIER);
		if (valueNode != null) return valueNode.getText();
		else return null;
	}

	public static String getIdentifier(::projectProperName::ReferenceStatementImpl element) {
		ASTNode valueNode = element.getNode().findChildByType(::projectProperName::Types.IDENTIFIER);
		if (valueNode != null) return valueNode.getText();
		else return null;
	}

	public static PsiElement setName(::projectProperName::DefinitionSignature element, String newName) {
		ASTNode keyNode = element.getNode().findChildByType(::projectProperName::Types.IDENTIFIER);
		if (keyNode != null) {
			::projectProperName::Definition concept = ::projectProperName::ElementFactoryImpl.getInstance(element.getProject()).createDefinition(newName);
			ASTNode newKeyNode = concept.getFirstChild().getChildren()[0].getNode();
			element.getNode().replaceChild(keyNode, newKeyNode);
		}
		return element;
	}

	public static PsiElement getIdentifier(::projectProperName::DefinitionSignature element) {
		ASTNode keyNode = element.getNode().findChildByType(::projectProperName::Types.IDENTIFIER);
		if (keyNode != null) return keyNode.getPsi();
		else return null;
	}

}
