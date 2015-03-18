package siani.tara.intellij.codegeneration;

import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.TaraIdentifier;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.util.*;

import static siani.tara.intellij.project.module.ModuleProvider.getModuleOf;

public class IntentionInstancesGenerator extends CodeGenerator {

	private static final Logger LOG = Logger.getInstance(IntentionInstancesGenerator.class.getName());
	private PsiDirectory facetsHome;

	public IntentionInstancesGenerator(TaraModel taraModel) {
		super(taraModel);
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
					LOG.error(e.getMessage(), e);
				}
			}
		};
		action.execute();
		for (VirtualFile virtualFile : pathsToRefresh) virtualFile.refresh(true, true);
	}

	private List<PsiClass> processFile(PsiFile psiFile) {
		List<PsiClass> psiClasses = new ArrayList<>();
		if (psiFile instanceof TaraModel) {
			final TaraModel taraModel = (TaraModel) psiFile;
			final Node[] facetNodes = getFacets(taraModel);
			if (facetNodes.length > 0) facetsHome = findFacetsDestiny();
			for (Node facet : facetNodes)
				if (facet.getName() != null && isIntention(facet)) {
					psiClasses.add(createFacetClass(facet));
					psiClasses.addAll(createTargetClasses(facet));
				}
		}
		return psiClasses;
	}

	private PsiClass createFacetClass(Node node) {
		PsiClass aClass = findClassInModule(makeConceptPackage(node), getModuleOf(node.getContainingFile()));
		return (aClass != null) ? aClass :
			JavaDirectoryService.getInstance().
				createClass(facetsHome, node.getName(), "TaraFacetTargetClass", false, getOptionsForFacetClass(node.getType()));
	}

	private List<PsiClass> createTargetClasses(Node facet) {
		List<PsiClass> psiClasses = new ArrayList<>();
		for (TaraFacetTarget target : facet.getFacetTargets())
			psiClasses.add(createTargetClass(facet, target));
		return psiClasses;
	}

	private PsiClass createTargetClass(Node node, TaraFacetTarget target) {
		if (target.getIdentifierReference() == null) return null;
		List<TaraIdentifier> identifierList = target.getIdentifierReference().getIdentifierList();
		String name = identifierList.get(identifierList.size() - 1).getName();
		if (name == null) return null;
		String fullName = name + node.getType();
		String aPackage = makeFacetPackage(node);
		PsiClass aClass = findClassInModule(aPackage + "." + fullName, getModuleOf(node.getContainingFile()));
		return (aClass != null) ? aClass : JavaDirectoryService.getInstance().
			createClass(createTargetDestiny(aPackage), name, "TaraFacetTargetClass", false, getOptionsForFacetClass(node.getType()));
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

	private String makeConceptPackage(Node node) {
		String path = "";
		for (String subpath : facetsPath) path += subpath + ".";
		return path + node.getName() + node.getType();
	}

	private String makeFacetPackage(Node facetedNode) {
		String path = "";
		for (String subpath : facetsPath) path += subpath + ".";
		return path + inflector.plural(facetedNode.getType()).toLowerCase() + "." + inflector.plural(facetedNode.getName()).toLowerCase();
	}

	private boolean isIntention(Node facet) {
//		if (facet.isIntention()) return true;
//		Node node = TaraUtil.findNode(facet, TaraLanguage.getLanguage(facet.getFile()));
//		return node != null && node.getObject().is(INTENTION);
		return false;
	}

	private Map<String, String> getOptionsForFacetClass(String type) {
		Map<String, String> map = new HashMap<>();
		map.put("TYPE", type);
		return map;
	}

	private Node[] getFacets(TaraModel taraModel) {
		List<Node> facets = new ArrayList<>();
		List<Node> allConceptsOfFile = TaraUtil.getAllConceptsOfFile(taraModel);
//		Model model = TaraLanguage.getLanguage(taraModel);
//		for (Concept concept : allConceptsOfFile)
//			if ((concept.isFacet() || isMetaFacet(model, concept)) && !concept.isIntention()) {
//				facets.add(concept);
//				facets.addAll(concept.getSubNodes());
//			}
		return facets.toArray(new Node[facets.size()]);
	}

//	private boolean isMetaFacet(Model model, Concept concept) {
//		if (model == null) return false;
//		Node node = TaraUtil.findNode(concept, model);
//		return node != null && node.is(META_FACET);
//	}

	private PsiClass findClassInModule(String qn, Module module) {
		return JavaPsiFacade.getInstance(project).findClass(qn, GlobalSearchScope.moduleScope(module));
	}

	private PsiDirectory findFacetsDestiny() {
		PsiDirectory directory = srcDirectory;
		for (String name : facetsPath) {
			PsiDirectory subdirectory = directory.findSubdirectory(name.toLowerCase());
			directory = subdirectory != null ? subdirectory : createPackageDirectory(directory, name);
		}
		return directory;
	}

}
