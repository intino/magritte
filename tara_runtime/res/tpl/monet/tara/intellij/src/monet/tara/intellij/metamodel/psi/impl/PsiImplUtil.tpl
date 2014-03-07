package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.metamodel.psi.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ::projectProperName::PsiImplUtil {

	public static String getIdentifier(::projectProperName::Identifier keyNode) {
		if (keyNode != null) return keyNode.getText();
		else return null;

	}

	public static String getIdentifier(Definition element) {
		ASTNode valueNode;
		if (element.getNode().findChildByType(::projectProperName::Types.SIGNATURE) != null) {
			valueNode = element.getNode().findChildByType(::projectProperName::Types.SIGNATURE).findChildByType(::projectProperName::Types.IDENTIFIER);
			if (valueNode != null) return valueNode.getText();
		}
		return null;
	}

	public static PsiElement getIdentifierNode(Definition element) {
		if (element.getNode().findChildByType(::projectProperName::Types.SIGNATURE) != null) {
			ASTNode valueNode = element.getNode().findChildByType(::projectProperName::Types.SIGNATURE).findChildByType(::projectProperName::Types.IDENTIFIER);
			if (valueNode != null) return valueNode.getPsi();
		}
		return null;
	}

	public static String getIdentifier(::projectProperName::ExtendedDefinition element) {
		ASTNode valueNode = element.getNode().findChildByType(::projectProperName::Types.IDENTIFIER_KEY);
		if (valueNode != null) return valueNode.getText();
		else return null;
	}

	public static String getIdentifier(::projectProperName::ReferenceStatementImpl element) {
		ASTNode valueNode = element.getNode().findChildByType(::projectProperName::Types.IDENTIFIER_KEY);
		if (valueNode != null) return valueNode.getText();
		else return null;
	}

	public static PsiElement setName(Signature element, String newName) {
		ASTNode keyNode = element.getNode().findChildByType(::projectProperName::Types.IDENTIFIER);
		if (keyNode != null) {
			Definition definition = ::projectProperName::ElementFactoryImpl.getInstance(element.getProject()).createDefinition(newName);
			ASTNode newKeyNode = definition.getIdentifierNode().getNode();
			element.getNode().replaceChild(keyNode, newKeyNode);
		}
		return element;
	}

	public static PsiElement getIdentifier(::projectProperName::Signature element) {
		ASTNode keyNode = element.getNode().findChildByType(::projectProperName::Types.IDENTIFIER);
		if (keyNode != null) return keyNode.getPsi();
		else return null;
	}


	public static List<::projectProperName::Definition> getChildrenInBody(::projectProperName::Body body) {
		return body.getDefinitionList();
	}

	public static List<::projectProperName::Attribute> getAttributesInBody(::projectProperName::Body body) {
		return body.getAttributeList();
	}


	public static List<::projectProperName::Definition> getChildrenOf(Definition definition) {
		::projectProperName::Body body;
		if ((body = definition.getBody()) != null)
			return getChildrenInBody(body);
		return Collections.EMPTY_LIST;
	}

	public static ::projectProperName::Definition resolveContextOfRef(::projectProperName::ExtendedDefinition identifier) {
		PsiElement element = identifier;
		while (!(element.getParent() instanceof Definition))
			element = element.getParent();
		return (::projectProperName::Definition) element.getParent();
	}

	public static ::projectProperName::Definition getContextOf(PsiElement element1) {
		try {
			PsiElement element = element1;
			while ((element.getParent() != null) && !(element.getParent() instanceof ::projectProperName::File) && !(element.getParent() instanceof Definition))
				element = element.getParent();
			return (element.getParent() instanceof ::projectProperName::Definition) ? (::projectProperName::Definition) element.getParent() : null;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static PsiElement[] getAnnotations(::projectProperName::Annotations annotations) {
		PsiElement child = annotations.getFirstChild();
		List<PsiElement> annotationList = new ArrayList<>();
		while (child.getNextSibling().getNextSibling() != null) {
			if (!child.getNextSibling().getText().equals(" ")) annotationList.add(child.getNextSibling());
			child = child.getNextSibling();
		}
		return annotationList.toArray(new PsiElement[annotationList.size()]);
	}

}
