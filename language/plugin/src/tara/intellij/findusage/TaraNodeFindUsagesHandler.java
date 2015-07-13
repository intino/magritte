package tara.intellij.findusage;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Node;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.module.ModuleProvider;

import java.util.*;

public class TaraNodeFindUsagesHandler extends FindUsagesHandler {
	private final Node node;

	public TaraNodeFindUsagesHandler(@NotNull Node node) {
		super(node);
		this.node = node;
	}

	@NotNull
	@Override
	public PsiElement[] getPrimaryElements() {
		return new PsiElement[]{node.getIdentifierNode()};
	}

	@NotNull
	@Override
	public PsiElement[] getSecondaryElements() {
		return getInstancesOfElement();
	}

	private PsiElement[] getInstancesOfElement() {
		if (node.getType() == null) return PsiElement.EMPTY_ARRAY;
		Project project = node.getProject();
		List<? extends PsiElement> conceptList = new ArrayList();
		Map<Module, List<TaraModel>> childModules = new HashMap<>();
		Module moduleForFile = ModuleProvider.getModuleOf(node.getFile());
		if (moduleForFile == null) return PsiElement.EMPTY_ARRAY;
		for (Module module : ModuleManager.getInstance(project).getModules()) {
			List<TaraModel> taraFilesOfModule = TaraUtil.getTaraFilesOfModule(module);
			if (taraFilesOfModule.isEmpty()) continue;
			if ((project.getName() + "." + moduleForFile.getName()).equals(taraFilesOfModule.get(0).getDSL()))
				childModules.put(module, taraFilesOfModule);
		}
		for (Map.Entry<Module, List<TaraModel>> moduleEntry : childModules.entrySet())
			conceptList.addAll(collectChildConceptsByType(moduleEntry.getValue()));
		return conceptList.toArray(new PsiElement[conceptList.size()]);
	}

	private Collection collectChildConceptsByType(List<TaraModel> files) {
		List<Node> list = new ArrayList();
		for (TaraModel file : files)
			for (Node cpt : TaraUtil.getMainNodesOfFile(file))
				if (node.getName().equals(cpt.getType()))
					list.add(cpt);
		return list;
	}


	@Override
	protected boolean isSearchForTextOccurencesAvailable(@NotNull PsiElement psiElement, boolean isSingleFile) {
		return true;
	}

	@Override
	protected Collection<String> getStringsToSearch(PsiElement element) {
		return super.getStringsToSearch(element);
	}
}