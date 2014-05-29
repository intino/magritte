package monet.::projectName::.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.lang.psi.::projectProperName::File;
import monet.::projectName::.intellij.lang.psi.::projectProperName::HeaderReference;
import monet.::projectName::.intellij.lang.psi.::projectProperName::Packet;
import monet.::projectName::.intellij.lang.psi.impl.ReferenceManager;
import org.jetbrains.annotations.NotNull;

public class PackageAnnotator extends ::projectProperName::Annotator {

	\@Override
	public void annotate(\@NotNull PsiElement element, \@NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element.getParent().getParent() instanceof ::projectProperName::Packet)
			isWellPlaced((::projectProperName::HeaderReference) element.getParent());
		else if (element instanceof ::projectProperName::File)
			checkPackageExistence((::projectProperName::File) element);
	}

	private void checkPackageExistence(::projectProperName::File file) {
		if (file.getPackage() == null && shouldHavePackage(file))
			holder.createErrorAnnotation(file.getNode(), ::projectProperName::Bundle.message("package.error.message"));
	}

	private boolean shouldHavePackage(::projectProperName::File file) {
		final VirtualFile contentRoot = ProjectFileIndex.SERVICE.getInstance(file.getProject()).getSourceRootForFile(file.getVirtualFile());
		if (contentRoot != null) {
			final VirtualFile supposedFile = contentRoot.findFileByRelativePath(file.getName());
			if (supposedFile != null && supposedFile.equals(file)) return false;
		}
		return true;
	}

	private void isWellPlaced(::projectProperName::HeaderReference reference) {
		VirtualFile file = ReferenceManager.resolveRoute(reference.getIdentifierList());
		if (!reference.getContainingFile().getVirtualFile().getParent().equals(file)) {
			holder.createErrorAnnotation(reference.getNode(), ::projectProperName::Bundle.message("package.error.message"));
		}
	}
}
