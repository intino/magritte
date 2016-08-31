package tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.util.PsiTreeUtil;
import tara.Language;
import tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import tara.intellij.framework.LanguageImporter;
import tara.intellij.lang.psi.TaraDslDeclaration;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.module.ModuleProvider;

import static tara.intellij.annotator.fix.FixFactory.get;
import static tara.intellij.messages.MessageProvider.message;
import static tara.lang.semantics.errorcollector.SemanticNotification.Level.ERROR;

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
					if (languageVersion == null) results.put(file, new AnnotateAndFix(ERROR, message(MESSAGE), get(MESSAGE, file)));
				}).start();

			}
		}
	}

	private String tryToImport(String dslName) {
		LanguageImporter importer = new LanguageImporter(ModuleProvider.getModuleOf(file));
		return importer.importLanguage(dslName, "LATEST");
	}

	private void findDuplicates() {
		TaraDslDeclaration[] declarations = PsiTreeUtil.getChildrenOfType(file, TaraDslDeclaration.class);
		if (declarations != null && declarations.length > 1)
			for (TaraDslDeclaration declaration : declarations)
				results.put(declaration, new AnnotateAndFix(ERROR, message("duplicated.dsl.declaration"), get(MESSAGE, file)));
	}
}
