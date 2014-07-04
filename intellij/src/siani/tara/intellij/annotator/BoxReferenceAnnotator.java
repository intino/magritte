package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;

public class BoxReferenceAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element instanceof TaraHeaderReference && element.getParent() instanceof TaraBox)
			isWellPlaced((TaraHeaderReference) element);
		else if (element instanceof TaraFile)
			checkBoxExistence((TaraFile) element);
	}

	private void checkBoxExistence(TaraFile file) {
		if (file.getBoxReference() == null)
			holder.createErrorAnnotation(file.getNode(), TaraBundle.message("box.reference.error.message"));
	}

	private void isWellPlaced(TaraHeaderReference reference) {
		VirtualFile file = ReferenceManager.resolveRoute(reference.getIdentifierList());
		if (!reference.getContainingFile().getVirtualFile().equals(file)) {
			holder.createErrorAnnotation(reference.getNode(), TaraBundle.message("box.reference.error.message"));
		}
	}
}
