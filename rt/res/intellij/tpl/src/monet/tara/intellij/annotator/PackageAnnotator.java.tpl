package monet.::projectName::.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.::projectProperName::Bundle;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::HeaderReference;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Packet;
import monet.::projectName::.intellij.metamodel.psi.impl.ReferenceManager;
import org.jetbrains.annotations.NotNull;

public class PackageAnnotator extends ::projectProperName::Annotator {

	\@Override
	public void annotate(\@NotNull PsiElement element, \@NotNull AnnotationHolder holder) {
		this.holder = holder;
		if (element.getParent().getParent() instanceof ::projectProperName::Packet)
			isWellPlaced((::projectProperName::HeaderReference) element.getParent());
	}

	private void isWellPlaced(::projectProperName::HeaderReference reference) {
		VirtualFile file = ReferenceManager.resolveRoute(reference.getIdentifierList());
		if (!reference.getContainingFile().getVirtualFile().getParent().equals(file)) {
			holder.createErrorAnnotation(reference.getNode(), ::projectProperName::Bundle.message("package.error.message"));
		}
	}
}
