package tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.PsiElement;
import tara.Checker;
import tara.Language;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.fix.FixFactory;
import tara.intellij.diagnostic.errorreporting.TaraRuntimeException;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Node;
import tara.lang.model.NodeRoot;
import tara.lang.semantics.errorcollector.SemanticException;
import tara.lang.semantics.errorcollector.SemanticFatalException;

import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

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
		} catch (SemanticFatalException fatal) {
			for (SemanticException e : fatal.exceptions()) {
				if (e.origin() == null) throw new TaraRuntimeException("origin = null: " + e.getMessage(), e);
				PsiElement[] origins = (PsiElement[]) e.origin();
				for (PsiElement origin : origins) {
					if (origin instanceof Node && !(origin instanceof NodeRoot)) {
						origin = ((TaraNode) origin).getSignature();
						results.put(origin, annotateAndFix(e, origin));
					}
				}
			}
		}
	}

	private TaraAnnotator.AnnotateAndFix annotateAndFix(SemanticException e, PsiElement destiny) {
		return new TaraAnnotator.AnnotateAndFix(ERROR, e.getMessage(), FixFactory.get(e.key(), destiny));
	}
}
