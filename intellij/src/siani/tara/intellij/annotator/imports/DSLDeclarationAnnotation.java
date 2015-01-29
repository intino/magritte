package siani.tara.intellij.annotator.imports;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.TaraDslDeclaration;

public class DSLDeclarationAnnotation extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (!TaraDslDeclaration.class.isInstance(element)) return;
		TaraBoxFile file = (TaraBoxFile) element.getContainingFile();
		TaraDslDeclaration[] declarations = PsiTreeUtil.getChildrenOfType(file, TaraDslDeclaration.class);
		if (declarations != null && declarations.length > 1)
			for (TaraDslDeclaration declaration : declarations)
				holder.createErrorAnnotation(declaration.getNode(), MessageProvider.message("duplicated.dsl.declaration"));
	}
}
