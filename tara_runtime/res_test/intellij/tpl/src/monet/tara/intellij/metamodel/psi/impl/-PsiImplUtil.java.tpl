package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.metamodel.psi.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ::projectProperName::PsiImplUtil {

	private static final Logger LOG = Logger.getInstance(::projectProperName::PsiImplUtil.class.getName());

	private ::projectProperName::PsiImplUtil() {
	}

	public static String getIdentifier(Identifier keyNode) {
		if (keyNode != null) return keyNode.getText();
		else return null;

	}

	public static String getIdentifier(Definition element) {
		if (element.getSignature().getIdentifier() != null) {
			ASTNode valueNode = element.getSignature().getIdentifier().getNode();
			if (valueNode != null) return valueNode.getText();
		}
		return null;
	}

	public static PsiElement getIdentifierNode(Definition element) {
		if (element.getSignature().getIdentifier() != null) {
			ASTNode valueNode = element.getSignature().getIdentifier().getNode();
			if (valueNode != null) return valueNode.getPsi();
		}
		return null;
	}

	public static String getIdentifier(ReferenceIdentifier element) {
		ASTNode valueNode = element.getNode().findChildByType(::projectProperName::Types.IDENTIFIER_KEY);
		if (valueNode != null) return valueNode.getText();
		else return null;
	}

	public static String getIdentifier(ReferenceStatement element) {
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

	public static PsiElement getIdentifier(Signature element) {
		ASTNode keyNode = element.getNode().findChildByType(::projectProperName::Types.IDENTIFIER);
		if (keyNode != null) return keyNode.getPsi();
		else return null;
	}


	public static List<Definition> getChildrenInBody(Body body) {
		return (List<Definition>) body.getDefinitionList();
	}

	public static List<Attribute> getAttributesInBody(Body body) {
		return (List<Attribute>) body.getAttributeList();
	}


	public static List<Definition> getChildrenOf(Definition definition) {
		if (definition != null) {
			Body body = definition.getBody();
			if (body != null) return getChildrenInBody(body);
		}
		return Collections.EMPTY_LIST;
	}

	public static Definition resolveContextOfRef(ReferenceIdentifier identifier) {
		PsiElement element = identifier;
		while (!(element.getParent() instanceof Definition))
			element = element.getParent();
		return (Definition) element.getParent();
	}

	public static Definition getContextOf(PsiElement element1) {
		try {
			PsiElement element = element1;
			while ((element.getParent() != null) && !(element.getParent() instanceof ::projectProperName::File) && !(element.getParent() instanceof Definition))
				element = element.getParent();
			return (element.getParent() instanceof Definition) ? (Definition) element.getParent() \: null;
		} catch (NullPointerException e) {
			LOG.error(e.getMessage());
			return null;
		}
	}

	public static PsiElement[] getAnnotations(Annotations annotations) {
		PsiElement child = annotations.getFirstChild();
		List<PsiElement> annotationList = new ArrayList<>();
		while (child.getNextSibling().getNextSibling() != null) {
			if (!" ".equals(child.getNextSibling().getText())) annotationList.add(child.getNextSibling());
			child = child.getNextSibling();
		}
		return annotationList.toArray(new PsiElement[annotationList.size()]);
	}

}
