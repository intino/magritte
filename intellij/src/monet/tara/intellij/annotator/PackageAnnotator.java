package monet.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.TaraBundle;
import monet.tara.intellij.lang.psi.TaraFile;
import monet.tara.intellij.lang.psi.TaraHeaderReference;
import monet.tara.intellij.lang.psi.TaraPacket;
import monet.tara.intellij.lang.psi.impl.ReferenceManager;
import org.jetbrains.annotations.NotNull;

public class PackageAnnotator extends TaraAnnotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element.getParent().getParent() instanceof TaraPacket)
			isWellPlaced((TaraHeaderReference) element.getParent());
		else if (element instanceof TaraFile)
			checkPackageExistence((TaraFile) element);
	}

	private void checkPackageExistence(TaraFile file) {
		if (file.getPackage() == null && shouldHavePackage(file))
			holder.createErrorAnnotation(file.getNode(), TaraBundle.message("package.error.message"));
	}

	private boolean shouldHavePackage(TaraFile file) {
		final VirtualFile contentRoot = ProjectFileIndex.SERVICE.getInstance(file.getProject()).getSourceRootForFile(file.getVirtualFile());
		if (contentRoot != null) {
			final VirtualFile supposedFile = contentRoot.findFileByRelativePath(file.getName());
			if (supposedFile != null && supposedFile.equals(file)) return false;
		}
		return true;
	}

	private void isWellPlaced(TaraHeaderReference reference) {
		VirtualFile file = ReferenceManager.resolveRoute(reference.getIdentifierList());
		if (!reference.getContainingFile().getVirtualFile().getParent().equals(file)) {
			holder.createErrorAnnotation(reference.getNode(), TaraBundle.message("package.error.message"));
		}
	}
}
