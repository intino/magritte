package tara.intellij.codeinsight.linemarkers;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaFile;
import org.jetbrains.annotations.NotNull;
import tara.intellij.codeinsight.languageinjection.helpers.Format;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.module.ModuleProvider;

import java.util.Collection;

public class JavaNativeImplementationToTara extends RelatedItemLineMarkerProvider {
	private static final String NATIVE_PACKAGE = "natives";

	@Override
	protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (!(element instanceof PsiClass)) return;
		PsiClass psiClass = (PsiClass) element;
		PsiElement destiny = ReferenceManager.resolveJavaNativeImplementation(psiClass);
		if (destiny == null || !isAvailable(psiClass, outDsl(destiny))) return;
		addResult(element, result, destiny);
	}

	private boolean isAvailable(PsiClass psiClass, String dsl) {
		return psiClass.getDocComment() != null && psiClass.getContainingFile() != null &&
			psiClass.getParent() instanceof PsiJavaFile &&
			correctPackage(psiClass, dsl);
	}

	private boolean correctPackage(PsiClass psiClass, String dsl) {
		final Module module = ModuleProvider.getModuleOf(psiClass);
		final String packageName = ((PsiJavaFile) psiClass.getContainingFile()).getPackageName();
		return packageName.startsWith(dsl.toLowerCase() + '.' + NATIVE_PACKAGE) ||
			(module != null && packageName.startsWith(Format.javaValidName().format(module.getName()).toString().toLowerCase() + '.' + NATIVE_PACKAGE));
	}

	private void addResult(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result, PsiElement destiny) {
		NavigationGutterIconBuilder<PsiElement> builder =
			NavigationGutterIconBuilder.create(TaraIcons.ICON_16).setTarget(destiny).setTooltipText("Navigate to the native declaration");
		result.add(builder.createLineMarkerInfo(element));
	}


	//TODO
	private String outDsl(@NotNull PsiElement element) {
		return TaraUtil.outputDsl(element).isEmpty() ? ModuleProvider.getModuleOf(element).getName() : TaraUtil.outputDsl(element);
	}
}