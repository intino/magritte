package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticAnalizers.ConceptAnalyzer;
import siani.tara.intellij.annotator.semanticAnalizers.TaraAnalyzer;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.awt.*;
import java.util.Collection;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static siani.tara.lang.Annotation.Annotation.PROPERTY;

public class ConceptAnnotator extends TaraAnnotator {


	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!Concept.class.isInstance(element)) return;
		this.holder = holder;
		Concept concept = (Concept) element;
		TaraAnalyzer analyzer = new ConceptAnalyzer(concept);
		analyzeAndAnnotate(analyzer);
		if (!analyzer.hasErrors())
			if (isRoot(concept)) addRootAnnotation(concept);
			else if (isProperty(concept)) addPropertyAnnotation(concept);
	}

	private boolean isRoot(Concept concept) {
		Collection<Concept> rootConcepts = TaraUtil.getRootConceptsOfFile(concept.getFile());
		return (rootConcepts.contains(concept) && concept.getIdentifierNode() != null);
	}

	private boolean isProperty(Concept concept) {
		Node node = getNode(concept);
		return node != null && node.getObject().is(PROPERTY);
	}

	@SuppressWarnings("deprecation")
	private void addRootAnnotation(Concept concept) {
		TextAttributesKey root = createTextAttributesKey("CONCEPT_ROOT", new TextAttributes(null, null, null, null, Font.BOLD));
		holder.createInfoAnnotation(concept.getIdentifierNode(), "Root").setTextAttributes(root);
	}

	private void addPropertyAnnotation(Concept concept) {
		TextAttributesKey keyword_property = createTextAttributesKey("KEYWORD_PROPERTY", DefaultLanguageHighlighterColors.STATIC_METHOD);
		holder.createInfoAnnotation(concept.getMetaIdentifier(), "Property").setTextAttributes(keyword_property);
	}

	private Node getNode(Concept concept) {
		Model model = TaraLanguage.getMetaModel(concept.getFile());
		if (model == null) return null;
		return TaraUtil.findNode(concept, model);
	}
}
