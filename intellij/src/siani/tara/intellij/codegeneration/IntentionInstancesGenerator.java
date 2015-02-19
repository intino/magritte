package siani.tara.intellij.codegeneration;

import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.TaraIdentifier;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.*;

import static siani.tara.intellij.project.module.ModuleProvider.getModuleOf;
import static siani.tara.lang.Annotation.INTENTION;
import static siani.tara.lang.Annotation.META_FACET;

public class IntentionInstancesGenerator extends CodeGenerator {

	private PsiDirectory facetsHome;

	public IntentionInstancesGenerator(TaraBoxFile taraBoxFile) {
		super(taraBoxFile);
	}

	public void generate() {
		final Set<VirtualFile> pathsToRefresh = new HashSet<>();
		final List<PsiClass> classes = new ArrayList<>();
		WriteCommandAction action = new WriteCommandAction(project, file) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				try {
					classes.addAll(processFile(file));
					if (!classes.isEmpty() && facetsHome != null)
						pathsToRefresh.add(facetsHome.getVirtualFile());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		action.execute();
		for (VirtualFile virtualFile : pathsToRefresh) virtualFile.refresh(true, true);
	}

	private List<PsiClass> processFile(PsiFile psiFile) {
		List<PsiClass> psiClasses = new ArrayList<>();
		if (psiFile instanceof TaraBoxFile) {
			final TaraBoxFile taraBoxFile = ((TaraBoxFile) psiFile);
			final Concept[] facetConcepts = getFacets(taraBoxFile);
			if (facetConcepts.length > 0) facetsHome = findFacetsDestiny();
			for (Concept facet : facetConcepts)
				if (facet.getName() != null && isIntention(facet)) {
					psiClasses.add(createFacetClass(facet));
					psiClasses.addAll(createTargetClasses(facet));
				}
		}
		return psiClasses;
	}

	private PsiClass createFacetClass(Concept concept) {
		PsiClass aClass = findClassInModule(concept.getName() + concept.getType(), getModuleOf(concept.getContainingFile()));
		return (aClass != null) ? aClass :
			JavaDirectoryService.getInstance().
				createClass(facetsHome, concept.getName(), "TaraFacetTargetClass", false, getOptionsForFacetClass(concept.getType()));
	}

	private List<PsiClass> createTargetClasses(Concept facet) {
		List<PsiClass> psiClasses = new ArrayList<>();
		for (TaraFacetTarget target : facet.getFacetTargets())
			psiClasses.add(createTargetClass(facet, target));
		return psiClasses;
	}

	private PsiClass createTargetClass(Concept concept, TaraFacetTarget target) {
		if (target.getIdentifierReference() == null) return null;
		List<TaraIdentifier> identifierList = target.getIdentifierReference().getIdentifierList();
		String name = identifierList.get(identifierList.size() - 1).getName();
		if (name == null) return null;
		String fullName = name + concept.getType();
		String aPackage = makePackage(concept);
		PsiClass aClass = findClassInModule(aPackage + "." + fullName, getModuleOf(concept.getContainingFile()));
		return (aClass != null) ? aClass : JavaDirectoryService.getInstance().
			createClass(createTargetDestiny(aPackage), name, "TaraFacetTargetClass", false, getOptionsForFacetClass(concept.getType()));
	}

	private PsiDirectory createTargetDestiny(final String aPackage) {
		PsiDirectory directory = facetsHome.findSubdirectory(aPackage);
		if (directory != null) return directory;
		else directory = srcDirectory;
		String[] packages = aPackage.split("\\.");
		for (String name : packages) {
			PsiDirectory subdirectory = directory.findSubdirectory(name);
			directory = subdirectory != null ? subdirectory : createPackageDirectory(directory, name);
		}
		return directory;
	}

	private String makePackage(Concept concept) {
		String path = "";
		for (String subpath : FACETS_PATH) path += subpath + ".";
		return path + inflector.plural(concept.getType()).toLowerCase() + "." + inflector.plural(concept.getName()).toLowerCase();
	}

	private boolean isIntention(Concept facet) {
		if (facet.isIntention()) return true;
		Node node = TaraUtil.findNode(facet, TaraLanguage.getMetaModel(facet.getFile()));
		return node != null && node.getObject().is(INTENTION);
	}

	private Map<String, String> getOptionsForFacetClass(String type) {
		Map<String, String> map = new HashMap<>();
		map.put("TYPE", type);
		return map;
	}

	private Concept[] getFacets(TaraBoxFile taraBoxFile) {
		List<Concept> facets = new ArrayList<>();
		List<Concept> allConceptsOfFile = TaraUtil.getAllConceptsOfFile(taraBoxFile);
		Model model = TaraLanguage.getMetaModel(taraBoxFile);
		for (Concept concept : allConceptsOfFile)
			if ((concept.isFacet() || isMetaFacet(model, concept)) && !concept.isIntention())
				facets.add(concept);
		return facets.toArray(new Concept[facets.size()]);
	}

	private boolean isMetaFacet(Model model, Concept concept) {
		if (model == null) return false;
		Node node = TaraUtil.findNode(concept, model);
		return node != null && node.is(META_FACET);
	}

	private PsiClass findClassInModule(String qn, Module module) {
		return JavaPsiFacade.getInstance(project).findClass(qn, GlobalSearchScope.moduleScope(module));
	}

	private PsiDirectory findFacetsDestiny() {
		PsiDirectory directory = srcDirectory;
		for (String name : FACETS_PATH) {
			PsiDirectory subdirectory = directory.findSubdirectory(name.toLowerCase());
			directory = subdirectory != null ? subdirectory : createPackageDirectory(directory, name);
		}
		return directory;
	}

}
