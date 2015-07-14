package tara.intellij.codeinsight.linemarkers;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiJavaFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.module.ModuleProvider;
import tara.intellij.lang.psi.*;

import java.util.*;

public class JavaNativeImplementationToTara extends RelatedItemLineMarkerProvider {

	public static final String MAGRITTE_ONTOLOGY = "magritte.ontology";

	@Override
	protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
		if (element instanceof PsiClass) {
			PsiClass psiClass = (PsiClass) element;
			if (element.getContainingFile() == null || !((PsiJavaFile) psiClass.getContainingFile()).getPackageName().startsWith(MAGRITTE_ONTOLOGY))
				return;
			if (!(psiClass.getParent() instanceof PsiClass)) return;
			Set<PsiElement> destiny = findNativeParameter(findCandidateNodes(psiClass), psiClass);
			if (!destiny.isEmpty()) {
				NavigationGutterIconBuilder<PsiElement> builder =
					NavigationGutterIconBuilder.create(TaraIcons.ICON_13).setPopupTitle("Multiple destinies found").setTargets(destiny).setTooltipText("Navigate to the native Variable");
				result.add(builder.createLineMarkerInfo(element));
			}
		}
	}


	private List<NodeContainer> findCandidateNodes(PsiClass aClass) {
		if (aClass.getExtendsListTypes().length == 0) return Collections.EMPTY_LIST;
		final PsiClass resolve = aClass.getExtendsListTypes()[0].resolve();
		List<NodeContainer> candidates = new ArrayList<>();
		for (TaraModel model : TaraUtil.getTaraFilesOfModule(ModuleProvider.getModuleOf(resolve))) {
			for (NodeContainer node : TaraUtil.getAllNodeContainersOfFile(model)) {
				if (resolve != null && !getCorrespondingQn(resolve.getQualifiedName()).equalsIgnoreCase(node.getQualifiedName()))
					continue;
				candidates.add(node);
			}
		}
		return candidates;
	}

	private Set<PsiElement> findNativeParameter(List<NodeContainer> candidateNodes, PsiClass psiClass) {
		for (NodeContainer candidateNode : candidateNodes) {
			final PsiElement element = searchNativeInNode(getSimpleName(psiClass), getContract(psiClass.getInterfaces()[0]), candidateNode);
			if (element != null) return Collections.singleton(element);
		}
		Set<PsiElement> nativeCandidates = new HashSet<>();
		searchInScope(candidateNodes, psiClass, nativeCandidates);
		return nativeCandidates;
	}

	private void searchInScope(List<? extends NodeContainer> candidateNodes, PsiClass psiClass, Set<PsiElement> nativeCandidates) {
		for (NodeContainer candidateNode : candidateNodes)
			searchInScope(candidateNode, psiClass, nativeCandidates);
	}

	private void searchInScope(NodeContainer candidateNode, PsiClass psiClass, Set<PsiElement> nativeCandidates) {
		final PsiElement element = searchNativeInNode(getSimpleName(psiClass), getContract(psiClass.getInterfaces()[0]), candidateNode);
		if (element != null) nativeCandidates.add(element);
		searchInScope(candidateNode.components(), psiClass, nativeCandidates);
	}

	@Nullable
	private static PsiElement searchNativeInNode(String name, String contract, NodeContainer node) {
		if (node instanceof Parametrized) {
			for (Parameter parameter : ((Parametrized) node).getParameterList())
				if (name.equals(parameter.getName()))
					return parameter;
		}
		for (Variable variable : node.variables())
			if (variable.getContract() != null && contract.equals(variable.getContract().getFormattedName()) && name.equals(variable.getName()))
				return variable;
		return null;
	}

	private String getContract(PsiClass psiClass) {
		return psiClass.getName();
	}

	@NotNull
	private String getSimpleName(PsiClass psiClass) {
		return psiClass.getName().substring(0, psiClass.getName().indexOf("_"));
	}

	@NotNull
	private String getCorrespondingQn(String qn) {
		return qn.substring(qn.indexOf(".") + 1);
	}

}