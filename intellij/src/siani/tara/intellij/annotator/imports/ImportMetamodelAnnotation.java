package siani.tara.intellij.annotator.imports;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.lang.psi.TaraAnImport;
import siani.tara.intellij.lang.psi.TaraHeader;

import java.util.ArrayList;
import java.util.List;

public class ImportMetamodelAnnotation extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!TaraHeader.class.isInstance(element)) return;
		TaraHeader header = (TaraHeader) element;
		List<TaraAnImport> imports = new ArrayList();
		for (TaraAnImport anImport : header.getAnImportList())
			if (anImport.isMetamodelImport())
				imports.add(anImport);
		if (imports.size() > 1)
			for (TaraAnImport anImport : imports)
				holder.createErrorAnnotation(anImport.getNode(), TaraBundle.message("duplicated.metamodel.import.error.message"));
	}
}
