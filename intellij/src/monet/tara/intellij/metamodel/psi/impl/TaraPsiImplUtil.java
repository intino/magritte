package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.metamodel.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaraPsiImplUtil {

	public static String getIdentifier(TaraIdentifier keyNode) {
		if (keyNode != null) return keyNode.getText();
		else return null;
	}

	public static String getIdentifier(IConcept element) {
		ASTNode valueNode;
		if (element.getNode().findChildByType(TaraTypes.CONCEPT_SIGNATURE) != null) {
			valueNode = element.getNode().findChildByType(TaraTypes.CONCEPT_SIGNATURE).findChildByType(TaraTypes.IDENTIFIER);
			if (valueNode != null) return valueNode.getText();
		}
		return null;
	}

	public static PsiElement getIdentifierNode(IConcept element) {
		if (element.getNode().findChildByType(TaraTypes.CONCEPT_SIGNATURE) != null) {
			ASTNode valueNode = element.getNode().findChildByType(TaraTypes.CONCEPT_SIGNATURE).findChildByType(TaraTypes.IDENTIFIER);
			if (valueNode != null) return valueNode.getPsi();
		}
		return null;
	}

	public static String getIdentifier(TaraExtendedConcept element) {
		ASTNode valueNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER_KEY);
		if (valueNode != null) return valueNode.getText();
		else return null;
	}

//	public static String getIdentifier(TaraExtendedConcept element) {
//		List<String> extendedNodes = new ArrayList<>();
//		ASTNode astNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER_KEY);
//		while (astNode != null) {
//			extendedNodes.add(astNode.getText());
//			astNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER_KEY, astNode.getTreeNext());
//		}
//		return join(extendedNodes.toArray(new String[extendedNodes.size()]), ".");
//	}

	public static String getIdentifier(TaraReferenceStatementImpl element) {
		ASTNode valueNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER_KEY);
		if (valueNode != null) return valueNode.getText();
		else return null;
	}

	public static PsiElement setName(TaraConceptSignature element, String newName) {
		ASTNode keyNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER);
		if (keyNode != null) {
			IConcept concept = TaraElementFactoryImpl.getInstance(element.getProject()).createConcept(newName);
			ASTNode newKeyNode = concept.getIdentifierNode().getNode();
			element.getNode().replaceChild(keyNode, newKeyNode);
		}
		return element;
	}

	public static PsiElement getIdentifier(TaraConceptSignature element) {
		ASTNode keyNode = element.getNode().findChildByType(TaraTypes.IDENTIFIER);
		if (keyNode != null) return keyNode.getPsi();
		else return null;
	}

	@NotNull
	public static List<IConcept> getChildren(TaraIdentifier extendedConcept) {
		List<IConcept> result = new ArrayList<>();
		if (extendedConcept.getParent() instanceof TaraConceptSignature) {
			if (extendedConcept.getParent().getParent() instanceof TaraConcept) {
				TaraConcept concept = (TaraConcept) extendedConcept.getParent().getParent();
				if (concept.getConceptBody() != null) result.addAll(getChildrenInBody(concept.getConceptBody()));
			} else if (extendedConcept.getParent().getParent() instanceof TaraComponent) {
				TaraComponent component = (TaraComponent) extendedConcept.getParent().getParent();
				if (component.getConceptBody() != null) result.addAll(getChildrenInBody(component.getConceptBody()));
			} else { //TaraFromComponent
				TaraFromBody fromBody = (TaraFromBody) extendedConcept.getParent().getParent().getParent();
				result.addAll(getChildrenInBody(fromBody));
			}
		} else if (extendedConcept.getParent() instanceof TaraReferenceStatement) {
			//IConcept concept = (IConcept) extendedConcept.getParent().getParent().getParent();
			//result.addAll(getChildrenInBody(component.getConceptBody()));
		}
		return result;
	}

	public static List<TaraComponent> getChildrenInBody(TaraConceptBody conceptBody) {
		List<TaraComponent> result = new ArrayList<>();
		try {
			for (TaraConceptConstituents constituent : conceptBody.getConceptConstituentsList())
				if (constituent.getFirstChild() instanceof TaraComponent)
					result.add((TaraComponent) constituent.getFirstChild());
			return result;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<TaraAttribute> getAttributesInBody(TaraConceptBody conceptBody) {
		List<TaraAttribute> result = new ArrayList<>();
		try {
			for (TaraConceptConstituents constituent : conceptBody.getConceptConstituentsList())
				if (constituent.getFirstChild() instanceof TaraAttribute)
					result.add((TaraAttribute) constituent.getFirstChild());
			return result;
		} catch (NullPointerException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<IConcept> getChildrenInBody(TaraFromBody taraFromBody) {
		List<IConcept> result = new ArrayList<>();
		for (TaraFromComponent constituent : taraFromBody.getFromComponentList())
			result.add(constituent);
		return result;
	}

	public static List<TaraComponent> getChildrenOf(IConcept concept) {
		TaraConceptBody body;
		if ((body = concept.getConceptBody()) != null)
			return getChildrenInBody(body);
		return Collections.EMPTY_LIST;
	}

}
