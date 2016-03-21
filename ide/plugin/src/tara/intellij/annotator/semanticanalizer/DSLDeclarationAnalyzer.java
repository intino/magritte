package tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.util.PsiTreeUtil;
import tara.Language;
import tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import tara.intellij.annotator.fix.FixFactory;
import tara.intellij.lang.psi.TaraDslDeclaration;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraUtil;

import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

import static tara.intellij.messages.MessageProvider.message;

public class DSLDeclarationAnalyzer extends TaraAnalyzer {

	private static final String PROTEO = "Proteo";
	private static final String MESSAGE = "parent.model.file.found";
	private final TaraModel file;

	public DSLDeclarationAnalyzer(TaraModel file) {
		this.file = file;
	}

	@Override
	public void analyze() {
		if (!hasErrors()) analyzeDslExistence();
	}

	private void analyzeDslExistence() {
		checkDslExistence(this.file.getDSL());
		if (hasErrors()) return;
		findDuplicates();
	}

	private void checkDslExistence(String dslName) {
		if (dslName != null && !dslName.isEmpty()) {
			Language dsl = TaraUtil.getLanguage(file);
			if ((dsl == null && !dslName.isEmpty() && !PROTEO.equals(dslName)) || (!dslName.equals(file.getDSL())))
				results.put(file, new AnnotateAndFix(ERROR, message(MESSAGE), FixFactory.get(MESSAGE, file)));
		}
	}

	private void findDuplicates() {
		TaraDslDeclaration[] declarations = PsiTreeUtil.getChildrenOfType(file, TaraDslDeclaration.class);
		if (declarations != null && declarations.length > 1)
			for (TaraDslDeclaration declaration : declarations)
				results.put(declaration,
					new AnnotateAndFix(ERROR, message("duplicated.dsl.declaration"), FixFactory.get(MESSAGE, file)));
	}
}
