package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.DeclaredNode;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static siani.tara.lang.Annotations.Annotation.REQUIRED;
import static siani.tara.lang.Annotations.Annotation.TERMINAL;

public class RequiredConceptsAnnotator extends TaraAnnotator {

	private Model model;

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!Concept.class.isInstance(element)) return;
		model = TaraLanguage.getMetaModel(element.getContainingFile());
		if (model == null) return;
		this.holder = holder;
		Concept concept = (Concept) element;
		Collection<Concept> childrenOf = TaraUtil.getInnerConceptsOf(concept);
		Node node = TaraUtil.findNode(concept, model);
		Collection<Node> requiredNodes = getRequiredInnerNodes(node);
		if (requiredNodes.isEmpty()) return;
		for (Node requiredNode : requiredNodes)
			if (!existInstanceOf(requiredNode, childrenOf))
				holder.createErrorAnnotation(((Concept) element).getSignature(), "This concept requires an inner " + requiredNode.getName());
	}

	private boolean existInstanceOf(Node requiredNode, Collection<Concept> childrenOf) {
		for (Concept concept : childrenOf)
			if (requiredNode.getName().equals(concept.getType()) || checkInSubs(requiredNode.getSubConcepts(), concept))
				return true;
		return false;
	}

	private boolean checkInSubs(DeclaredNode[] subConcepts, Concept concept) {
		for (DeclaredNode subConcept : subConcepts)
			if (subConcept.getName().equals(concept.getType())) return true;
		return false;
	}

	private Collection<Node> getRequiredInnerNodes(Node node) {
		List<Node> required = new ArrayList<>();
		if (node == null) return Collections.EMPTY_LIST;
		for (Node inner : node.getInnerNodes())
			if (inner.getObject().is(REQUIRED) &&
				(model.isTerminal() && node.getObject().is(TERMINAL) ||
					!model.isTerminal() && !node.getObject().is(TERMINAL)))
				required.add(inner);
		return required;
	}
}
