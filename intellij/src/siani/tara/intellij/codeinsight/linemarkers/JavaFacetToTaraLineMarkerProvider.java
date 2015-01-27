package siani.tara.intellij.codeinsight.linemarkers;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.Collection;
import java.util.List;

public class JavaFacetToTaraLineMarkerProvider extends RelatedItemLineMarkerProvider {

	private static final String INTENTION = "Intention";

	@Override
	protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (element instanceof PsiClass) {
			PsiClass psiClass = (PsiClass) element;
			if (element.getContainingFile() == null) return;
			if (((PsiClass) element).getImplementsList() == null) return;
			Concept concept = TaraUtil.findConceptByQN(findCorrespondentConcept(psiClass), element.getContainingFile());
			if (concept != null) {
				NavigationGutterIconBuilder<PsiElement> builder =
					NavigationGutterIconBuilder.create(TaraIcons.getIcon(TaraIcons.ICON_13)).setTarget(concept).setTooltipText("Navigate to the concept");
				result.add(builder.createLineMarkerInfo(element));
			}
		}
	}

	private String findCorrespondentConcept(PsiClass aClass) {
		String intention = "";
		for (PsiJavaCodeReferenceElement element : aClass.getImplementsList().getReferenceElements())
			if (element.getReferenceName() != null && element.getReferenceName().endsWith(INTENTION)) {
				intention = aClass.getName().replace(element.getReferenceName().replace(INTENTION, ""), "");
				break;
			}
		if (intention.isEmpty()) return "";
		List<TaraBoxFileImpl> taraFilesOfModule = TaraUtil.getTaraFilesOfModule(ModuleProvider.getModuleOf(aClass));
		for (TaraBoxFileImpl taraBoxFile : taraFilesOfModule)
			for (Concept concept : TaraUtil.getAllConceptsOfFile(taraBoxFile))
				if (intention.equals(concept.getName()))
					return concept.getQualifiedName();
		return "";
	}

}