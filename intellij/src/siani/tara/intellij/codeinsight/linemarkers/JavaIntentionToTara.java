package siani.tara.intellij.codeinsight.linemarkers;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.util.Collection;

public class JavaIntentionToTara extends RelatedItemLineMarkerProvider {

	private static final String INTENTIONS = "intentions";
	private static final String INTENTION = "Intention";

	@Override
	protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (element instanceof PsiClass) {
			PsiClass psiClass = (PsiClass) element;
			if (element.getContainingFile() == null) return;
			Node node = TaraUtil.findNodeByQN(findCorrespondentConcept(psiClass), element.getContainingFile());
			if (node != null) {
				NavigationGutterIconBuilder<PsiElement> builder =
					NavigationGutterIconBuilder.create(TaraIcons.getIcon(TaraIcons.ICON_13)).setTarget(node).setTooltipText("Navigate to the concept");
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
		qn = qn.replaceFirst(aClass.getProject().getName().toLowerCase() + "." + INTENTIONS + ".", "");
		qn = qn.replace(INTENTION, "");
		return qn;
	}

	private PsiClass findConceptClassOfTarget(PsiClass aClass) {
		PsiClass psiClass = aClass;
		while (psiClass.getInterfaces().length > 0 && !"magritte.Intention".equals(psiClass.getInterfaces()[0].getQualifiedName()))
			psiClass = psiClass.getInterfaces()[0];
		return psiClass;
	}

	private boolean isFacetTargetClass(PsiClass aClass) {
		if (aClass == null || aClass.getContainingFile().getParent() == null) return false;
		return !aClass.getContainingFile().getParent().getName().equals(INTENTIONS);
	}


}