package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.fix.AddAnnotationFix;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static siani.tara.lang.Annotations.Annotation.AGGREGATED;

public class AggregatedConceptAnnotator extends TaraAnnotator {


	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!Concept.class.isInstance(element)) return;
		this.holder = holder;
		Concept concept = (Concept) element;
		Model metamodel = TaraLanguage.getMetaModel(concept.getFile());
		if (metamodel == null) return;
		Node node = findNode(concept, metamodel);
		boolean terminal = ModuleConfiguration.getInstance(TaraUtil.getModuleOfFile(element.getContainingFile())).isTerminal();
		if (!terminal & node.isAggregated() && !concept.isAggregated())
			annotateAndFix(concept.getSignature(), new AddAnnotationFix(concept, AGGREGATED), "This concept should be aggregated");
		if (node.isAggregated()) {
			checkRootDuplicates(concept);
			if (terminal) addAggregatedAnnotation(concept);
		}
	}

	private void addAggregatedAnnotation(Concept concept) {
		Annotation aggregated = holder.createInfoAnnotation(concept.getIdentifierNode(), "Root");
		aggregated.setTextAttributes(createAggregatedHighlight());
	}

	private Collection<Concept> checkRootDuplicates(Concept concept) {
		if (concept.getName() == null) return Collections.EMPTY_LIST;
		List<Concept> concepts = new ArrayList<>();
		for (Concept rootConcept : TaraUtil.getRootConceptsOfFile(concept.getFile()))
			if (concept.getName().equals(rootConcept.getName())) concepts.add(rootConcept);
		return concepts;
	}

	@SuppressWarnings("deprecation")
	private TextAttributesKey createAggregatedHighlight() {
		return createTextAttributesKey("CONCEPT_ROOT", new TextAttributes(null, null, null, null, Font.BOLD));
	}
}
