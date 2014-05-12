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
		return keyNode != null ? keyNode.getText() \: null;
	}

	public static String getIdentifier(Definition element) {
		if (element.getSignature().getIdentifier() != null) {
			ASTNode valueNode = element.getSignature().getIdentifier().getNode();
			return valueNode.getText();
		}
		return null;
	}

	public static Definition getExtensibleOfExtension(Definition definition) {
		if (definition.isExtension()) {
			Definition context = getContextOf(definition);
			while (context != null && !context.isExtensible())
				context = getContextOf(definition);
			if (context != null) return context;
		}
		return null;
	}

	public static PsiElement getIdentifierNode(Definition element) {
		if (element.getSignature().getIdentifier() != null) {
			ASTNode valueNode = element.getSignature().getIdentifier().getNode();
			return valueNode.getPsi();
		}
		return null;
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

	public static Definition getContextOfRef(IdentifierReference identifier) {
		PsiElement element = identifier;
		while (!(element.getParent() instanceof Definition))
			element = element.getParent();
		return (Definition) element.getParent();
	}

	public static Definition getContextOf(PsiElement element) {
		try {
			PsiElement aElement = element;
			while ((aElement.getParent() != null) && !(aElement.getParent() instanceof ::projectProperName::File) && !(aElement.getParent() instanceof Definition))
				aElement = aElement.getParent();
			return (aElement.getParent() instanceof Definition) ? (Definition) aElement.getParent() \: null;
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
