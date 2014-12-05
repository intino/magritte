package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.semanticAnalizers.ConceptAnalyzer;
import siani.tara.intellij.annotator.semanticAnalizers.TaraAnalyzer;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.awt.*;
import java.util.Collection;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class ConceptAnnotator extends TaraAnnotator {


	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!Concept.class.isInstance(element)) return;
		this.holder = holder;
		TaraAnalyzer analyzer = new ConceptAnalyzer((Concept) element);
		analyzeAndAnnotate(analyzer);
		if (!analyzer.hasErrors()) addRootAnnotation((Concept) element);
	}

	@SuppressWarnings("deprecation")
	private void addRootAnnotation(Concept concept) {
		Collection<Concept> rootConcepts = TaraUtil.getRootConceptsOfFile(concept.getFile());
		if (rootConcepts.contains(concept) && concept.getIdentifierNode() != null) {
			TextAttributesKey root = createTextAttributesKey("CONCEPT_ROOT", new TextAttributes(null, null, null, null, Font.BOLD));
			holder.createInfoAnnotation(concept.getIdentifierNode(), "Root").setTextAttributes(root);
		}
	}
}
