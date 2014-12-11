package siani.tara.intellij.codeinsight.linemarkers;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.util.Collection;

public class JavaToTaraLineMarkerProvider extends RelatedItemLineMarkerProvider {

	private static final String INTENTIONS = "intentions.";
	private static final String INTENTION = "Intention";

	@Override
	protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (element instanceof PsiClass) {
			PsiClass psiClass = (PsiClass) element;
			if (element.getContainingFile() == null) return;
			Concept concept = TaraUtil.findConceptByQN(getQualifiedName(psiClass), element.getContainingFile());
			if (concept != null) {
				NavigationGutterIconBuilder<PsiElement> builder =
					NavigationGutterIconBuilder.create(TaraIcons.getIcon(TaraIcons.ICON_13)).setTarget(concept).setTooltipText("Navigate to the concept");
				result.add(builder.createLineMarkerInfo(element));
			}
		}
	}

	private String getQualifiedName(PsiClass psiClass) {
		String qualifiedName = psiClass.getQualifiedName();
		assert qualifiedName != null;
		return qualifiedName.length() > INTENTIONS.length() && qualifiedName.startsWith(INTENTIONS) && qualifiedName.endsWith(INTENTION) ?
			qualifiedName.substring(qualifiedName.indexOf(".") + 1).replaceAll(INTENTION, "")
			: qualifiedName;
	}
}