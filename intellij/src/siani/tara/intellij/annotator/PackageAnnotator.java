package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.lang.psi.HeaderReference;
import siani.tara.intellij.lang.psi.TaraBox;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.TaraHeaderReference;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;

public class PackageAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element.getParent() instanceof HeaderReference && element.getParent().getParent() instanceof TaraBox)
			isWellPlaced((TaraHeaderReference) element.getParent());
		else if (element instanceof TaraFile)
			checkBoxExistence((TaraFile) element);
	}

	private void checkBoxExistence(TaraFile file) {
		if (file.getBoxReference() == null)
			holder.createErrorAnnotation(file.getNode(), TaraBundle.message("box.reference.error.message"));
	}

	private void isWellPlaced(TaraHeaderReference reference) {
		VirtualFile file = ReferenceManager.resolveRoute(reference.getIdentifierList());
		if (!reference.getContainingFile().getVirtualFile().getParent().equals(file)) {
			holder.createErrorAnnotation(reference.getNode(), TaraBundle.message("box.reference.error.message"));
		}
	}
}
