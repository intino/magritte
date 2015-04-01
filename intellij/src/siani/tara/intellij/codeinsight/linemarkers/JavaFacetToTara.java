package siani.tara.intellij.codeinsight.linemarkers;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.Collection;

public class JavaFacetToTara extends RelatedItemLineMarkerProvider {

	private static final String INTENTION = "Intention";

	@Override
	protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (element instanceof PsiClass) {
			PsiClass psiClass = (PsiClass) element;
			if (element.getContainingFile() == null) return;
			if (((PsiClass) element).getImplementsList() == null) return;
			addNavigationMark(element, result, TaraUtil.findNodeByQN(findCorrespondentConcept(psiClass), element.getContainingFile()));
		}
	}

	private void addNavigationMark(PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result, Node node) {
		if (node != null) {
			NavigationGutterIconBuilder<PsiElement> builder =
				NavigationGutterIconBuilder.create(TaraIcons.getIcon(TaraIcons.ICON_13)).setTarget(node).setTooltipText("Navigate to the concept");
			result.add(builder.createLineMarkerInfo(element));
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
		return findConcept(aClass, intention);
	}

	private String findConcept(PsiClass aClass, String intention) {
		for (TaraModelImpl taraBoxFile : TaraUtil.getTaraFilesOfModule(ModuleProvider.getModuleOf(aClass)))
			for (Node node : TaraUtil.getAllNodesOfFile(taraBoxFile))
				if (intention.equals(node.getName()))
					return node.getQualifiedName();
		return "";
	}
}