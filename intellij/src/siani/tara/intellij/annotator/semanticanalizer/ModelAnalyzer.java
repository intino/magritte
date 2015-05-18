package siani.tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.PsiElement;
import siani.tara.Checker;
import siani.tara.Language;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.annotator.fix.FixFactory;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.lang.semantic.LanguageElement;
import siani.tara.intellij.lang.semantic.LanguageRoot;
import siani.tara.semantic.SemanticException;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

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
			new Checker(language).check(new LanguageRoot(model));
		} catch (SemanticException e) {
			if (e.getOrigin() == null) throw new RuntimeException("origin = null:" + e.getMessage());
			PsiElement destiny = ((LanguageElement) e.getOrigin()).element();
			if (destiny instanceof Node) destiny = ((Node) destiny).getSignature();
			results.put(destiny, annotateAndFix(e, destiny));
		}
	}

	private TaraAnnotator.AnnotateAndFix annotateAndFix(SemanticException e, PsiElement destiny) {
		return new TaraAnnotator.AnnotateAndFix(ERROR, e.getMessage(), FixFactory.get(e.key(), destiny));
	}
}
