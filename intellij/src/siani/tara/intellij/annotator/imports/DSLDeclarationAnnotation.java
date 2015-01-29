package siani.tara.intellij.annotator.imports;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.lang.psi.TaraAnImport;
import siani.tara.intellij.lang.psi.TaraImports;

import java.util.ArrayList;
import java.util.List;

public class DSLDeclarationAnnotation extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!TaraImports.class.isInstance(element)) return;
		TaraImports header = (TaraImports) element;
		List<TaraAnImport> imports = new ArrayList();
		for (TaraAnImport anImport : header.getAnImportList())
			if (anImport.isMetamodelImport())
				imports.add(anImport);
		if (imports.size() > 1)
			for (TaraAnImport anImport : imports)
				holder.createErrorAnnotation(anImport.getNode(), MessageProvider.message("duplicated.metamodel.import"));
	}
}
