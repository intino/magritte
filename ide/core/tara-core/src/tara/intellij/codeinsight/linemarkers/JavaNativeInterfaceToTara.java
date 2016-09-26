package tara.intellij.codeinsight.linemarkers;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.TaraRule;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Node;
import tara.lang.model.Variable;

import java.util.Collection;
import java.util.List;

public class JavaNativeInterfaceToTara extends RelatedItemLineMarkerProvider {

	private static final String NATIVES = "functions";

	@Override
	protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (element instanceof PsiClass) {
			PsiClass psiClass = (PsiClass) element;
			if (element.getContainingFile() == null) return;
			Variable variable = findNativeVariable(findCorrespondentNode(psiClass), element.getContainingFile());
			if (variable != null) {
				NavigationGutterIconBuilder<PsiElement> builder =
					NavigationGutterIconBuilder.create(TaraIcons.ICON_16).setTarget((PsiElement) variable).setTooltipText("Navigate to the native Variable");
				result.add(builder.createLineMarkerInfo(element));
			}
		}
	}

	private String findCorrespondentNode(PsiClass aClass) {
		String qn = aClass.getQualifiedName();
		if (qn == null) return "";
		if (isFacetTargetClass(aClass)) {
			PsiClass nodeClassOfTarget = findNodeClassOfTarget(aClass);
			if (nodeClassOfTarget == null) return "";
			qn = nodeClassOfTarget.getQualifiedName();
		}
		if (qn != null) {
			Module moduleOf = ModuleProvider.moduleOf(aClass);
			if (moduleOf == null) return "";
			String moduleName = moduleOf.getName().toLowerCase();
			qn = qn.replaceFirst(moduleName + "." + NATIVES + ".", "");
		}
		return qn == null ? "" : qn;
	}

	private PsiClass findNodeClassOfTarget(PsiClass aClass) {
		PsiClass psiClass = aClass;
		while (psiClass.getInterfaces().length > 0 && !"magritte.Intention".equals(psiClass.getInterfaces()[0].getQualifiedName()))
			psiClass = psiClass.getInterfaces()[0];
		return psiClass;
	}

	private boolean isFacetTargetClass(PsiClass aClass) {
		return !(aClass == null || aClass.getContainingFile().getParent() == null) && !NATIVES.equals(aClass.getContainingFile().getParent().getName());
	}


	private static Variable findNativeVariable(String name, PsiFile file) {
		if (file == null) return null;
		List<TaraModel> filesOfModule = TaraUtil.getTaraFilesOfModule(ModuleProvider.moduleOf(file));
		for (TaraModel taraFile : filesOfModule) {
			Variable variable = searchNativeInFile(name, taraFile);
			if (variable != null) return variable;
		}
		return null;
	}

	@Nullable
	private static Variable searchNativeInFile(String name, TaraModel taraFile) {
		for (Node node : TaraUtil.getAllNodesOfFile(taraFile)) {
			Variable variable = searchNativeInNode(name, node);
			if (variable != null) return variable;
		}
		return null;
	}

	@Nullable
	private static Variable searchNativeInNode(String name, Node node) {
		for (Variable variable : node.variables())
			if (name.equals(((TaraRule) variable.rule()).getText()))
				return variable;
		return null;
	}

}