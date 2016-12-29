package io.intino.tara.plugin.findusage;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import io.intino.tara.plugin.lang.psi.TaraModel;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import io.intino.tara.plugin.project.module.ModuleProvider;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.plugin.lang.psi.TaraNode;
import io.intino.tara.lang.model.Node;

import java.util.*;
import java.util.stream.Collectors;

public class TaraNodeFindUsagesHandler extends FindUsagesHandler {
	private final TaraNode node;

	public TaraNodeFindUsagesHandler(@NotNull Node node) {
		super((PsiElement) node);
		this.node = (TaraNode) node;
	}

	@NotNull
	@Override
	public PsiElement[] getPrimaryElements() {
		return new PsiElement[]{node.getSignature().getIdentifier()};
	}

	@NotNull
	@Override
	public PsiElement[] getSecondaryElements() {
		return getInstancesOfElement();
	}

	private PsiElement[] getInstancesOfElement() {
		if (node.type() == null) return PsiElement.EMPTY_ARRAY;
		Project project = node.getProject();
		List<? extends PsiElement> conceptList = new ArrayList();
		Map<Module, List<TaraModel>> childModules = new HashMap<>();
		Module moduleForFile = ModuleProvider.moduleOf(node.getContainingFile().getOriginalFile());
		if (moduleForFile == null) return PsiElement.EMPTY_ARRAY;
		for (Module module : ModuleManager.getInstance(project).getModules()) {
			List<TaraModel> taraFilesOfModule = TaraUtil.getTaraFilesOfModule(module);
			if (taraFilesOfModule.isEmpty()) continue;
			if ((project.getName() + "." + moduleForFile.getName()).equals(taraFilesOfModule.get(0).dsl()))
				childModules.put(module, taraFilesOfModule);
		}
		for (Map.Entry<Module, List<TaraModel>> moduleEntry : childModules.entrySet())
			conceptList.addAll(collectChildConceptsByType(moduleEntry.getValue()));
		return conceptList.toArray(new PsiElement[conceptList.size()]);
	}

	private Collection collectChildConceptsByType(List<TaraModel> files) {
		List<Node> list = new ArrayList();
		for (TaraModel file : files)
			list.addAll(TaraUtil.getMainNodesOfFile(file).stream().
				filter(cpt -> node.name().equals(cpt.type())).
				collect(Collectors.toList()));
		return list;
	}


	@Override
	protected boolean isSearchForTextOccurencesAvailable(@NotNull PsiElement psiElement, boolean isSingleFile) {
		return true;
	}
}