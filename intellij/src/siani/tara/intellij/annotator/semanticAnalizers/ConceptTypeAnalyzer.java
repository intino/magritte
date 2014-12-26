package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.highlighting.TaraSyntaxHighlighter;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.MetaIdentifier;
import siani.tara.intellij.lang.psi.TaraConceptReference;
import siani.tara.intellij.lang.psi.TaraTypes;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Annotation;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

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
			for (TaraConceptReference incorrectInnerLink : getIncorrectInnerLinks(concept, model))
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
		Node node = TaraUtil.findNode(concept, model);
		return node != null && !node.is(Annotation.ABSTRACT);
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
			|| TaraTypes.IMPORTS.equals(elementType)
			|| TaraTypes.NEWLINE.equals(elementType)
			|| TaraTypes.NEW_LINE_INDENT.equals(elementType);
	}

	private boolean analyzeModelCoherence() {
		return (isConceptType() || model == null);
	}

	private IElementType getPreviousToken(Concept concept) {
		PsiElement prevSibling = concept.getPsiElement().getPrevSibling();
		if (prevSibling == null) return null;
		while (prevSibling.getNode().getElementType() == TokenType.WHITE_SPACE)
			prevSibling = prevSibling.getPrevSibling();
		return prevSibling.getNode().getElementType();
	}

	private List<TaraConceptReference> getIncorrectInnerLinks(Concept concept, Model model) {
		List<TaraConceptReference> list = new ArrayList();
		TaraConceptReference[] conceptLinks = concept.getConceptLinks();
		for (TaraConceptReference conceptLink : conceptLinks) {
			Node reference = model.searchNode(TaraUtil.getMetaQualifiedName(conceptLink));
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
