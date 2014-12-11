package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.annotator.fix.AddAnnotationFix;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.awt.*;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.INFO;
import static siani.tara.lang.Annotation.AGGREGATED;

public class AggregatedAnalyzer extends TaraAnalyzer {

	private final Concept concept;

	public AggregatedAnalyzer(Concept concept) {
		this.concept = concept;
	}

	@Override
	public void analyze() {
		Model metamodel = getMetamodel(concept);
		if (metamodel == null) return;
		Node node = TaraUtil.findNode(concept, metamodel);
		boolean terminal = ModuleConfiguration.getInstance(ModuleProvider.getModuleOfFile(concept.getContainingFile())).isTerminal();
		if (hasErrors = !terminal & node.isAggregated() && !concept.isAggregated())
			results.put(concept.getSignature(),
				new TaraAnnotator.AnnotateAndFix(ERROR, "This concept should be aggregated", new AddAnnotationFix(concept, AGGREGATED)));
		if (!hasErrors && (node.isAggregated() && terminal || concept.isAggregated()))
			addAggregatedAnnotation(concept);
	}

	private void addAggregatedAnnotation(Concept concept) {
		results.put(concept.getIdentifierNode(), new TaraAnnotator.AnnotateAndFix(INFO, "Root", createAggregatedHighlight()));
	}

	@SuppressWarnings("deprecation")
	private TextAttributesKey createAggregatedHighlight() {
		return createTextAttributesKey("CONCEPT_ROOT", new TextAttributes(null, null, null, null, Font.BOLD));
	}

}
