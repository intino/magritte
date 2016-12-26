package io.intino.tara.plugin.codeinsight.linemarkers;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import io.intino.tara.plugin.lang.TaraIcons;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import io.intino.tara.plugin.project.module.ModuleProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.groovy.lang.psi.GroovyFile;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.typedef.GrClassDefinition;
import io.intino.tara.plugin.lang.psi.resolve.ReferenceManager;

import java.util.Collection;

public class GroovyNativeImplementationToTara extends RelatedItemLineMarkerProvider {
	private static final String NATIVE_PACKAGE = "natives";

	@Override
	protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (!(element instanceof GrClassDefinition)) return;
		PsiClass psiClass = (GrClassDefinition) element;
		if (!isAvailable(psiClass, outDsl(element))) return;
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

	private String outDsl(@NotNull PsiElement element) {
		return TaraUtil.workingPackage(element).isEmpty() ? ModuleProvider.moduleOf(element).getName() : TaraUtil.workingPackage(element);
	}
}