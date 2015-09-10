package tara.intellij.annotator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaFile;
import org.jetbrains.annotations.NotNull;
import tara.intellij.annotator.fix.SyncJavaNativeToTara;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;

public class SyncNativeWithTaraAnnotation implements Annotator {

	public static final String NATIVE_PACKAGE = "natives";

	public boolean isAvailable(@NotNull PsiElement element) {
		if (!(element instanceof PsiClass)) return false;
		PsiClass psiClass = (PsiClass) element;
		return isAvailable(psiClass, getDSL(element)) && ReferenceManager.resolveNativeImplementation(psiClass) != null;
	}

	private boolean isAvailable(PsiClass psiClass, String dsl) {
		return psiClass.getContainingFile() != null &&
			psiClass.getParent() instanceof PsiClass &&
			((PsiJavaFile) psiClass.getContainingFile()).getPackageName().startsWith(dsl.toLowerCase() + '.' + NATIVE_PACKAGE);
	}

	private String getDSL(@NotNull PsiElement element) {
		final TaraFacet facet = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf(element));
		if (facet == null) return "";
		final TaraFacetConfiguration configuration = facet.getConfiguration();
		return configuration.getGeneratedDslName();
	}

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!isAvailable(element)) return;
		Annotation annotation = holder.createInfoAnnotation(element.getNode(), "Sync with tara code");
		annotation.registerFix(new SyncJavaNativeToTara((PsiClass)element));
	}
}
