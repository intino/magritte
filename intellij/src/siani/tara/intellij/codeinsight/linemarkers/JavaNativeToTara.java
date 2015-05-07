package siani.tara.intellij.codeinsight.linemarkers;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Variable;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.Collection;

public class JavaNativeToTara extends RelatedItemLineMarkerProvider {

	private static final String NATIVES = "natives";

	@Override
	protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (element instanceof PsiClass) {
			PsiClass psiClass = (PsiClass) element;
			if (element.getContainingFile() == null) return;
			Variable variable = TaraUtil.findNativeVariable(findCorrespondentConcept(psiClass), element.getContainingFile());
			if (variable != null) {
				NavigationGutterIconBuilder<PsiElement> builder =
					NavigationGutterIconBuilder.create(TaraIcons.ICON_13).setTarget(variable).setTooltipText("Navigate to the native Variable");
				result.add(builder.createLineMarkerInfo(element));
			}
		}
	}

	private String findCorrespondentConcept(PsiClass aClass) {
		String qn = aClass.getQualifiedName();
		if (qn == null) return "";
		if (isFacetTargetClass(aClass)) {
			PsiClass conceptClassOfTarget = findConceptClassOfTarget(aClass);
			if (conceptClassOfTarget == null) return "";
			qn = conceptClassOfTarget.getQualifiedName();
		}
		if (qn != null) {
			Module moduleOf = ModuleProvider.getModuleOf(aClass);
			if (moduleOf == null) return "";
			String moduleName = moduleOf.getName().toLowerCase();
			qn = qn.replaceFirst(moduleName + "." + NATIVES + ".", "");
		}
		return qn == null ? "" : qn;
	}

	private PsiClass findConceptClassOfTarget(PsiClass aClass) {
		PsiClass psiClass = aClass;
		while (psiClass.getInterfaces().length > 0 && !"magritte.Intention".equals(psiClass.getInterfaces()[0].getQualifiedName()))
			psiClass = psiClass.getInterfaces()[0];
		return psiClass;
	}

	private boolean isFacetTargetClass(PsiClass aClass) {
		if (aClass == null || aClass.getContainingFile().getParent() == null) return false;
		return !aClass.getContainingFile().getParent().getName().equals(NATIVES);
	}


}