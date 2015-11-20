package tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.util.PsiTreeUtil;
import tara.Language;
import tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import tara.intellij.annotator.fix.FixFactory;
import tara.intellij.lang.psi.TaraDslDeclaration;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;

import static tara.intellij.MessageProvider.message;
import static tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

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
		TaraFacet facet = TaraFacet.of(ModuleProvider.getModuleOf(file));
		if (facet == null) return;
		TaraFacetConfiguration configuration = facet.getConfiguration();
		String dslName = configuration.getDsl();
		checkDslExistence(dslName);
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
