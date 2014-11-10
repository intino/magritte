package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.annotator.fix.AddAnnotationFix;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
		boolean system = ModuleConfiguration.getInstance(TaraUtil.getModuleOfFile(element.getContainingFile())).isSystem();
		if (!system & node.isAggregated() && !concept.isAggregated())
			annotateAndFix(concept.getSignature(), new AddAnnotationFix(concept, AGGREGATED), "This concept should be aggregable");
		if (concept.isAggregated())
			checkRootDuplicates(concept);
	}

	private Collection<Concept> checkRootDuplicates(Concept concept) {
		if (concept.getName() == null) return Collections.EMPTY_LIST;
		List<Concept> concepts = new ArrayList<>();
		for (Concept rootConcept : TaraUtil.getRootConceptsOfFile(concept.getFile()))
			if (concept.getName().equals(rootConcept.getName())) concepts.add(rootConcept);
		return concepts;
	}
}
