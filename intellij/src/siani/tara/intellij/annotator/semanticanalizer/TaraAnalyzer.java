package siani.tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.HashMap;
import java.util.Map;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

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

	protected Model getMetamodel(PsiElement element) {
		return TaraUtil.getMetamodel(element);
	}

	protected Node findMetaConcept(Concept concept) {
		return TaraUtil.getMetaConcept(concept.isSub() ? concept.getParentConcept() : concept);
	}
}
