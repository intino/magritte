package siani.tara.intellij.annotator.semanticanalizer;

import com.intellij.psi.util.PsiTreeUtil;
import siani.tara.Language;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.annotator.fix.FixFactory;
import siani.tara.intellij.lang.psi.TaraDslDeclaration;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;

import static siani.tara.intellij.MessageProvider.message;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class DSLDeclarationAnalyzer extends TaraAnalyzer {

	private static final String PROTEO = "Proteo";
	private final TaraModel file;

	public DSLDeclarationAnalyzer(TaraModel file) {
		this.file = file;
	}

	@Override
	public void analyze() {
		if (!hasErrors()) analyzeDslExistence();
	}

	private void analyzeDslExistence() {
		ModuleConfiguration instance = ModuleConfiguration.getInstance(ModuleProvider.getModuleOf(file));
		if (instance == null) return;
		String dslName = instance.getMetamodelName();
		if (dslName != null && !dslName.isEmpty() && file.getDSL() == null)
			results.put(file, new AnnotateAndFix(ERROR, message("dsl.not.found"), FixFactory.get("parent.model.file.found", file)));
		else checkDslExistence(dslName);
		if (hasErrors()) return;
		findDuplicates();
	}

	private void checkDslExistence(String dslName) {
		if (dslName != null && !dslName.isEmpty()) {
			Language dsl = TaraUtil.getLanguage(file);
			if ((dsl == null && !dslName.isEmpty() && !PROTEO.equals(dslName)) || (!dslName.equals(file.getDSL())))
				results.put(file, new AnnotateAndFix(ERROR, message("parent.model.file.found"), FixFactory.get("parent.model.file.found", file)));
		}
	}

	private void findDuplicates() {
		TaraDslDeclaration[] declarations = PsiTreeUtil.getChildrenOfType(file, TaraDslDeclaration.class);
		if (declarations != null && declarations.length > 1)
			for (TaraDslDeclaration declaration : declarations)
				results.put(declaration,
					new AnnotateAndFix(ERROR, message("duplicated.dsl.declaration"), FixFactory.get("parent.model.file.found", file)));
	}
}
