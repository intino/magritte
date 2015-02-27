package siani.tara.intellij.annotator.semanticanalizer;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.highlighting.TaraSyntaxHighlighter;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.LinkNode;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.intellij.lang.psi.impl.TaraUtil.findNode;
import static siani.tara.lang.Annotation.ABSTRACT;

public class ConceptTypeAnalyzer extends TaraAnalyzer {

	private static final String CONCEPT = "Concept";
	private final Concept concept;
	private final MetaIdentifier metaIdentifier;
	private Model model;

	public ConceptTypeAnalyzer(MetaIdentifier metaIdentifier) {
		this.metaIdentifier = metaIdentifier;
		this.concept = TaraPsiImplUtil.getConceptContainerOf(metaIdentifier);
		model = TaraLanguage.getMetaModel(metaIdentifier.getContainingFile());
	}

	@Override
	public void analyze() {
		if (model != null) {
			if (analyzeModelCoherence())
				addError(MessageProvider.message("concept.position.key"), TaraSyntaxHighlighter.UNRESOLVED_ACCESS);
			else if (!existsConceptTypeInMetamodel())
				results.put(metaIdentifier, new AnnotateAndFix(ERROR, MessageProvider.message("Unknown.concept")));
			for (TaraConceptReference incorrectInnerLink : getIncorrectInnerLinks())
				results.put(incorrectInnerLink, new AnnotateAndFix(ERROR, MessageProvider.message("Unknown.concept")));
		} else {
			if (isConceptType()) {
				if (!hasName())
					addError("Concept without name");
				else if (!isWellPositioned())
					addError("Concept in bad position");
			} else
				results.put(metaIdentifier, new AnnotateAndFix(ERROR, MessageProvider.message("Unknown.concept")));
		}
	}

	private boolean existsConceptTypeInMetamodel() {
		Node node = findNode(concept, model);
		if (node == null || isAbstract(node)) return false;
		else if(!node.is(ABSTRACT)) return true;
		Concept container = TaraPsiImplUtil.getConceptContainerOf(concept);
		if (container == null) return false;
		Node containerNode = findNode(container, model);
		return containerNode != null && hasAny(containerNode, getFacets(concept.getFacetApplies()));
	}

	private boolean isAbstract(Node node) {
		if (node.is(LinkNode.class)) return ((LinkNode) node).getDestiny().is(ABSTRACT);
		return node.is(ABSTRACT);
	}

	private boolean hasAny(Node container, Collection<String> facets) {
		for (Node node : container.getInnerNodes())
			if (facets.contains(node.getName())) return true;
		return false;
	}

	private Collection<String> getFacets(FacetApply[] facetApplies) {
		List<String> facets = new ArrayList<>();
		for (FacetApply facetApply : facetApplies) facets.add(facetApply.getFacetName());
		return facets;
	}

	private boolean hasName() {
		return concept.getName() != null;
	}

	private boolean isConceptType() {
		return CONCEPT.equals(metaIdentifier.getText());
	}

	private boolean isWellPositioned() {
		IElementType elementType = getPreviousToken(concept);
		return elementType == null
			|| TaraTypes.DSL_DECLARATION.equals(elementType)
			|| TaraTypes.IMPORTS.equals(elementType)
			|| TaraTypes.NEWLINE.equals(elementType)
			|| TaraTypes.NEW_LINE_INDENT.equals(elementType);
	}

	private boolean analyzeModelCoherence() {
		return isConceptType() || model == null;
	}

	private IElementType getPreviousToken(Concept concept) {
		PsiElement prevSibling = concept.getPsiElement().getPrevSibling();
		if (prevSibling == null) return null;
		while (prevSibling.getNode().getElementType() == TokenType.WHITE_SPACE)
			prevSibling = prevSibling.getPrevSibling();
		return prevSibling.getNode().getElementType();
	}

	private List<TaraConceptReference> getIncorrectInnerLinks() {
		List<TaraConceptReference> list = new ArrayList();
		TaraConceptReference[] conceptLinks = concept.getConceptLinks();
		for (TaraConceptReference conceptLink : conceptLinks) {
			Node reference = TaraUtil.findNode(concept, model);
			if (reference == null) list.add(conceptLink);
		}
		return list;
	}

	private void addError(String message, TextAttributesKey key) {
		results.put(concept.getSignature(), new AnnotateAndFix(ERROR, message, key));
	}

	private void addError(String message) {
		results.put(concept.getSignature(), new AnnotateAndFix(ERROR, message));
	}
}
