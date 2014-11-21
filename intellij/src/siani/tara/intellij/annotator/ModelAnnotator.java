package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.fix.AddMetamodelReferenceFix;
import siani.tara.intellij.annotator.fix.ImportMetamodelFix;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.TaraBox;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.lang.Model;

public class ModelAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof TaraBox) checkMetamodelExistence((TaraBoxFile) element.getContainingFile());
	}

	private void checkMetamodelExistence(TaraBoxFile file) {
		String parentName = ModuleConfiguration.getInstance(TaraUtil.getModuleOfFile(file)).getParentName();
		if (file.getParentModel() == null && parentName != null && !parentName.isEmpty())
			annotateAndFix(file, new AddMetamodelReferenceFix(file), MessageProvider.message("model.not.found.key.error.message"));
		else if (parentName != null && !parentName.isEmpty()) {
			Model parentModel = TaraLanguage.getMetaModel(file);
			if (parentModel == null && !parentName.isEmpty())
				annotateAndFix(file, new ImportMetamodelFix(file), MessageProvider.message("parent.model.file.found.error.message"));
		}
	}
}
