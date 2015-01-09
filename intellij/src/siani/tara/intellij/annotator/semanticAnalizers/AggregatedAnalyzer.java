package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.Node;

import java.awt.*;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.INFO;

public class AggregatedAnalyzer extends TaraAnalyzer {

	private final Concept concept;

	public AggregatedAnalyzer(Concept concept) {
		this.concept = concept;
	}

	@Override
	public void analyze() {
		Node node = getMetaConcept(concept);
		if (node == null) return;
		boolean terminal = ModuleConfiguration.getInstance(ModuleProvider.getModuleOfFile(concept.getContainingFile())).isTerminal();
		if (!hasErrors() && (node.isAggregated() && terminal || concept.isAnnotatedAsAggregated()))
			addAggregatedAnnotation(concept);
	}

	private void addAggregatedAnnotation(Concept concept) {
		if (!concept.isRoot() && concept.getName() != null)
			results.put(concept.getIdentifierNode(), new TaraAnnotator.AnnotateAndFix(INFO, "Root", createAggregatedHighlight()));
	}

	@SuppressWarnings("deprecation")
	private TextAttributesKey createAggregatedHighlight() {
		return createTextAttributesKey("CONCEPT_ROOT", new TextAttributes(null, null, null, null, Font.BOLD));
	}

}
