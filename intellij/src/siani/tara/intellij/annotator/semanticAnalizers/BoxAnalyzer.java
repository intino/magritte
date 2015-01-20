package siani.tara.intellij.annotator.semanticAnalizers;

import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.annotator.fix.AddMetamodelReferenceFix;
import siani.tara.intellij.annotator.fix.ImportMetamodelFix;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.TaraHeaderReference;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.Model;

import static siani.tara.intellij.MessageProvider.message;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class BoxAnalyzer extends TaraAnalyzer {

	private final TaraHeaderReference boxReference;

	public BoxAnalyzer(TaraHeaderReference boxReference) {
		this.boxReference = boxReference;
	}

	@Override
	public void analyze() {
		TaraBoxFile file = (TaraBoxFile) boxReference.getContainingFile();
		if (!hasErrors()) analyzeMetamodelExistence(file);
	}

	private void analyzeMetamodelExistence(TaraBoxFile file) {
		ModuleConfiguration instance = ModuleConfiguration.getInstance(ModuleProvider.getModuleOf(file));
		if (instance == null) return;
		String metamodelName = instance.getMetamodelName();
		if (file.getParentModel() == null && metamodelName != null && !metamodelName.isEmpty())
			results.put(file, new AnnotateAndFix(ERROR, message("model.not.found"), new AddMetamodelReferenceFix(file)));
		else if (metamodelName != null && !metamodelName.isEmpty()) {
			Model parentModel = TaraLanguage.getMetaModel(file);
			if (parentModel == null && !metamodelName.isEmpty())
				results.put(file, new AnnotateAndFix(ERROR, message("parent.model.file.found"), new ImportMetamodelFix(file)));
		}
	}
}
