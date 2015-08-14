package tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.PsiElement;
import tara.Checker;
import tara.Language;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.fix.FixFactory;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.language.model.Node;
import tara.language.semantics.SemanticException;

public class ModelAnalyzer extends TaraAnalyzer {
	private TaraModel model;

	public ModelAnalyzer(TaraModel model) {
		this.model = model;
	}

	@Override
	public void analyze() {
		try {
			Language language = TaraUtil.getLanguage(model);
			if (language == null) return;
			new Checker(language).check(model);
		} catch (SemanticException e) {
			if (e.getOrigin() == null) throw new RuntimeException("origin = null: " + e.getMessage(), e);
			PsiElement destiny = (PsiElement) e.getOrigin();
			if (destiny instanceof Node) {
				destiny = ((TaraNode) destiny).getSignature();
				results.put(destiny, annotateAndFix(e, destiny));
			}
		}
	}

	private TaraAnnotator.AnnotateAndFix annotateAndFix(SemanticException e, PsiElement destiny) {
		return new TaraAnnotator.AnnotateAndFix(TaraAnnotator.AnnotateAndFix.Level.ERROR, e.getMessage(), FixFactory.get(e.key(), destiny));
	}
}
