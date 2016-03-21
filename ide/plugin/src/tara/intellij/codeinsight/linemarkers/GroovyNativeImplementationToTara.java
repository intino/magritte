package tara.intellij.codeinsight.linemarkers;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.groovy.lang.psi.GroovyFile;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.typedef.GrClassDefinition;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;

import java.util.Collection;

public class GroovyNativeImplementationToTara extends RelatedItemLineMarkerProvider {
	public static final String NATIVE_PACKAGE = "natives";

	@Override
	protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (!(element instanceof GrClassDefinition)) return;
		PsiClass psiClass = (GrClassDefinition) element;
		if (!isAvailable(psiClass, getDSL(element))) return;
		PsiElement destiny = ReferenceManager.resolveJavaNativeImplementation(psiClass);
		if (destiny != null) addResult(element, result, destiny);
	}

	private boolean isAvailable(PsiClass psiClass, String dsl) {
		return psiClass.getDocComment() != null && psiClass.getContainingFile() != null &&
			psiClass.getParent() instanceof GroovyFile &&
			((GroovyFile) psiClass.getContainingFile()).getPackageName().startsWith(dsl.toLowerCase() + '.' + NATIVE_PACKAGE);
	}

	private void addResult(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result, PsiElement destiny) {
		NavigationGutterIconBuilder<PsiElement> builder =
			NavigationGutterIconBuilder.create(TaraIcons.ICON_16).setTarget(destiny).setTooltipText("Navigate to the native declaration");
		result.add(builder.createLineMarkerInfo(element));
	}

	private String getDSL(@NotNull PsiElement element) {
		final Module module = ModuleProvider.getModuleOf(element);
		final TaraFacet facet = TaraFacet.of(module);
		if (facet == null) return "";
		final TaraFacetConfiguration configuration = facet.getConfiguration();
		return configuration.outputDsl().isEmpty() ? module.getName() : configuration.outputDsl();
	}
}