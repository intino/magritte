package siani.tara.intellij.findusage;

import com.intellij.find.findUsages.FindUsagesHandler;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.*;

public class TaraConceptFindUsagesHandler extends FindUsagesHandler {
	private final Concept concept;

	public TaraConceptFindUsagesHandler(@NotNull Concept concept) {
		super(concept);
		this.concept = concept;
	}

	@NotNull
	@Override
	public PsiElement[] getPrimaryElements() {
		return new PsiElement[]{concept.getIdentifierNode()};
	}

	@NotNull
	@Override
	public PsiElement[] getSecondaryElements() {
		return getInstancesOfElement();
	}

	private PsiElement[] getInstancesOfElement() {
		if (concept.getType() == null) return PsiElement.EMPTY_ARRAY;
		Project project = concept.getProject();
		List<? extends PsiElement> conceptList = new ArrayList();
		Map<Module, List<TaraBoxFileImpl>> childModules = new HashMap<>();
		Module moduleForFile = ModuleProvider.getModuleOf(concept.getFile());
		if (moduleForFile == null) return PsiElement.EMPTY_ARRAY;
		for (Module module : ModuleManager.getInstance(project).getModules()) {
			List<TaraBoxFileImpl> taraFilesOfModule = TaraUtil.getTaraFilesOfModule(module);
			if (taraFilesOfModule.isEmpty()) continue;
			if ((project.getName() + "." + moduleForFile.getName()).equals(taraFilesOfModule.get(0).getDSL()))
				childModules.put(module, taraFilesOfModule);
		}
		for (Map.Entry<Module, List<TaraBoxFileImpl>> moduleEntry : childModules.entrySet())
			conceptList.addAll(collectChildConceptsByType(moduleEntry.getValue()));
		return conceptList.toArray(new PsiElement[conceptList.size()]);
	}

	private Collection collectChildConceptsByType(List<TaraBoxFileImpl> files) {
		List<Concept> list = new ArrayList();
		for (TaraBoxFileImpl file : files)
			for (Concept cpt : TaraUtil.getRootConceptsOfFile(file))
				if (concept.getName().equals(cpt.getType()))
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