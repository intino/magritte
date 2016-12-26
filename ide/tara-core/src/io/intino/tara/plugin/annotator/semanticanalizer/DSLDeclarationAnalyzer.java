package io.intino.tara.plugin.annotator.semanticanalizer;

import com.intellij.psi.util.PsiTreeUtil;
import io.intino.tara.Language;
import io.intino.tara.plugin.annotator.fix.FixFactory;
import io.intino.tara.plugin.lang.psi.TaraModel;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import io.intino.tara.plugin.messages.MessageProvider;
import io.intino.tara.plugin.annotator.TaraAnnotator.AnnotateAndFix;
import io.intino.tara.plugin.lang.psi.TaraDslDeclaration;

import static io.intino.tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

public class DSLDeclarationAnalyzer extends TaraAnalyzer {

	private static final String PROTEO = "Proteo";
	private static final String MESSAGE = "dsl.not.found";
	private final TaraModel file;

	public DSLDeclarationAnalyzer(TaraModel file) {
		this.file = file;
	}

	@Override
	public void analyze() {
		if (!hasErrors()) analyzeDslExistence();
	}

	private void analyzeDslExistence() {
		checkDslExistence(this.file.dsl());
		if (hasErrors()) return;
		findDuplicates();
	}

	private void checkDslExistence(String dslName) {
		if (dslName != null && !dslName.isEmpty()) {
			Language dsl = TaraUtil.getLanguage(file);
			if ((dsl == null && !dslName.isEmpty() && !PROTEO.equals(dslName)) || (!dslName.equals(file.dsl()))) {
				new Thread(() -> {
					final String languageVersion = tryToImport(dslName);
					if (languageVersion == null) results.put(file, new AnnotateAndFix(ERROR, MessageProvider.message(MESSAGE), FixFactory.get(MESSAGE, file)));
				}).start();

			}
		}
	}

	private String tryToImport(String dslName) {
//		LanguageImporter importer = new LanguageImporter(ModuleProvider.moduleOf(file));
//		return importer.importLanguage(dslName, "LATEST");
		return "dslName";
	}

	private void findDuplicates() {
		TaraDslDeclaration[] declarations = PsiTreeUtil.getChildrenOfType(file, TaraDslDeclaration.class);
		if (declarations != null && declarations.length > 1)
			for (TaraDslDeclaration declaration : declarations)
				results.put(declaration, new AnnotateAndFix(ERROR, MessageProvider.message("duplicated.dsl.declaration"), FixFactory.get(MESSAGE, file)));
	}
}
