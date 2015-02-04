package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.psi.util.PsiTreeUtil;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.annotator.fix.AddMetamodelReferenceFix;
import siani.tara.intellij.annotator.fix.ConfigureModuleFix;
import siani.tara.intellij.annotator.fix.ImportMetamodelFix;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.TaraDslDeclaration;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.Model;

import static siani.tara.intellij.MessageProvider.message;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class DSLDeclarationAnalyzer extends TaraAnalyzer {

	private final TaraBoxFile file;

	public DSLDeclarationAnalyzer(TaraBoxFile file) {
		this.file = file;
	}

	@Override
	public void analyze() {
		if (!hasErrors()) analyzeMetamodelExistence();
	}

	private void analyzeMetamodelExistence() {
		ModuleConfiguration instance = ModuleConfiguration.getInstance(ModuleProvider.getModuleOf(file));
		if (instance == null) return;
		String dslName = instance.getMetamodelName();
		if (dslName != null && !dslName.isEmpty() && file.getDSL() == null)
			results.put(file, new AnnotateAndFix(ERROR, message("model.not.found"), new AddMetamodelReferenceFix(file)));
		else if (dslName != null && !dslName.isEmpty()) {
			Model dsl = TaraUtil.getMetamodel(file);
			if ((dsl == null && !dslName.isEmpty()) || (!dslName.equals(file.getDSL())))
				results.put(file, new AnnotateAndFix(ERROR, message("parent.model.file.found"), new ImportMetamodelFix(file), new ConfigureModuleFix(file)));
		}
		if (hasErrors()) return;
		TaraDslDeclaration[] declarations = PsiTreeUtil.getChildrenOfType(file, TaraDslDeclaration.class);
		if (declarations != null && declarations.length > 1)
			for (TaraDslDeclaration declaration : declarations)
				results.put(declaration,
					new AnnotateAndFix(ERROR, message("duplicated.dsl.declaration"), new ImportMetamodelFix(file), new ConfigureModuleFix(file)));
	}
}
