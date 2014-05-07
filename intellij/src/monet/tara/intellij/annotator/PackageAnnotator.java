package monet.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.TaraBundle;
import monet.tara.intellij.metamodel.psi.TaraHeaderReference;
import monet.tara.intellij.metamodel.psi.TaraPacket;
import monet.tara.intellij.metamodel.psi.impl.ReferenceManager;
import org.jetbrains.annotations.NotNull;

public class PackageAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element.getParent().getParent() instanceof TaraPacket)
			isWellPlaced((TaraHeaderReference) element.getParent());
	}

	private void isWellPlaced(TaraHeaderReference reference) {
		VirtualFile file = ReferenceManager.resolveRoute(reference.getIdentifierList());
		if (!reference.getContainingFile().getVirtualFile().getParent().equals(file)) {
			holder.createErrorAnnotation(reference.getNode(), TaraBundle.message("package.error.message"));
		}
	}
}
