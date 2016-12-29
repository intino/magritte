package io.intino.tara.plugin.annotator.semanticanalizer;

import com.intellij.psi.PsiElement;
import io.intino.tara.plugin.annotator.TaraAnnotator;

import java.util.HashMap;
import java.util.Map;

import static io.intino.tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

public abstract class TaraAnalyzer {

	protected Map<PsiElement, TaraAnnotator.AnnotateAndFix> results = new HashMap<>();
	private boolean hasErrors = false;

	public abstract void analyze();

	public Map<PsiElement, TaraAnnotator.AnnotateAndFix> results() {
		return results;
	}

	public boolean hasErrors() {
		if (hasErrors) return true;
		for (TaraAnnotator.AnnotateAndFix annotateAndFix : results.values())
			if (hasErrors = annotateAndFix.level().equals(ERROR)) return true;
		return false;
	}

}
