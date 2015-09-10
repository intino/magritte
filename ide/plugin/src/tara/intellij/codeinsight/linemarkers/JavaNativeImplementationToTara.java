package tara.intellij.codeinsight.linemarkers;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaFile;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;

import java.util.Collection;

public class JavaNativeImplementationToTara extends RelatedItemLineMarkerProvider {
	//TODO
	public static final String NATIVE_PACKAGE = "natives";
	private static final String DOC_SEPARATOR = "#";

	@Override
	protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (!(element instanceof PsiClass)) return;
		PsiClass psiClass = (PsiClass) element;
		if (!isAvailable(psiClass, getDSL(element))) return;
		PsiElement destiny = ReferenceManager.resolveNativeImplementation(psiClass);
		if (destiny != null) addResult(element, result, destiny);
	}

	private boolean isAvailable(PsiClass psiClass, String dsl) {
		return psiClass.getContainingFile() != null &&
			psiClass.getParent() instanceof PsiClass &&
			((PsiJavaFile) psiClass.getContainingFile()).getPackageName().startsWith(dsl.toLowerCase() + '.' + NATIVE_PACKAGE);
	}

	private void addResult(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result, PsiElement destiny) {
		NavigationGutterIconBuilder<PsiElement> builder =
			NavigationGutterIconBuilder.create(TaraIcons.ICON_13).setTarget(destiny).setTooltipText("Navigate to the native Variable");
		result.add(builder.createLineMarkerInfo(element));
	}

	private String getDSL(@NotNull PsiElement element) {
		final TaraFacet facet = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf(element));
		if (facet == null) return "";
		final TaraFacetConfiguration configuration = facet.getConfiguration();
		return configuration.getGeneratedDslName();
	}

}