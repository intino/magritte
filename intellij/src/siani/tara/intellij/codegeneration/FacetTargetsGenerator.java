package siani.tara.intellij.codegeneration;

import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.formatter.Inflector;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.TaraIdentifier;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.*;

import static org.siani.itrules.formatter.InflectorFactory.getInflector;
import static siani.tara.intellij.project.module.ModuleProvider.getModuleOf;
import static siani.tara.lang.Annotation.INTENTION;
import static siani.tara.lang.Annotation.META_FACET;

public class FacetTargetsGenerator {

	public static final String SRC = "src";
	private final Project project;
	private final Module module;
	private final String[] FACETS_PATH;
	private final TaraBoxFile taraBoxFile;
	private final PsiDirectory facetsHome;
	private final PsiDirectory srcDirectory;

	public FacetTargetsGenerator(TaraBoxFile taraBoxFile) {
		this.taraBoxFile = taraBoxFile;
		this.project = taraBoxFile.getProject();
		module = ModuleProvider.getModuleOf(taraBoxFile);
		VirtualFile src = getSrcDirectory(TaraUtil.getSourceRoots(taraBoxFile));
		srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) taraBoxFile.getManager(), src);
		FACETS_PATH = new String[]{project.getName().toLowerCase(), "extensions"};
		facetsHome = findFacetsDestiny();

	}

	public void generate() {
		final Set<VirtualFile> pathsToRefresh = new HashSet<>();
		final List<PsiClass> classes = new ArrayList<>();
		WriteCommandAction action = new WriteCommandAction(project, taraBoxFile) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				try {
					pathsToRefresh.add(facetsHome.getVirtualFile());
					classes.addAll(processFile(taraBoxFile));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		action.execute();
		for (VirtualFile virtualFile : pathsToRefresh) virtualFile.refresh(true, true);
		action = new WriteCommandAction(project, getFiles(classes)) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				for (PsiClass aClass : classes) {
					if (aClass.getImplementsList() == null) return;
					implementAbstractMethods(aClass, aClass.getImplementsList().getReferenceElements());
				}
			}
		};
		action.execute();
		for (VirtualFile virtualFile : pathsToRefresh) virtualFile.refresh(true, true);
	}

	private PsiFile[] getFiles(List<PsiClass> classes) {
		List<PsiFile> psiFiles = new ArrayList<>();
		for (PsiClass aClass : classes) psiFiles.add(aClass.getContainingFile());
		return psiFiles.toArray(new PsiFile[psiFiles.size()]);
	}

	private List<PsiClass> processFile(PsiFile psiFile) {
		List<PsiClass> psiClasses = new ArrayList<>();
		if (psiFile instanceof TaraBoxFile) {
			final TaraBoxFile taraBoxFile = ((TaraBoxFile) psiFile);
			final Concept[] facets = getFacets(taraBoxFile);
			for (Concept facet : facets)
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

	private void implementAbstractMethods(PsiClass destiny, PsiJavaCodeReferenceElement[] referenceElements) {
		for (PsiJavaCodeReferenceElement referenceElement : referenceElements) {
			PsiClass aClass = findClassInProject(referenceElement.getQualifiedName(), getModuleOf(referenceElement));
			if (aClass == null) continue;
			implementMethods(destiny, getAbstractMethods(aClass));
		}
	}

	private void implementMethods(PsiClass aClass, List<PsiMethod> abstractMethods) {
		for (PsiMethod psiMethod : abstractMethods) {
			if (containsMethod(aClass, psiMethod) != null) continue;
			PsiMethod method = getElementFactory(aClass).createMethod(psiMethod.getName(), psiMethod.getReturnType());
			for (PsiParameter psiParameter : psiMethod.getParameterList().getParameters())
				method.getParameterList().add(getElementFactory(aClass).createParameter(psiParameter.getName(), psiParameter.getType()));
			method.getModifierList().addAnnotation("Override");
			aClass.add(method);
		}
	}

	private PsiMethod containsMethod(PsiClass aClass, PsiMethod psiMethod) {
		for (PsiMethod method : aClass.getMethods()) {
			if (!psiMethod.getName().equals(method.getName())) continue;
			if (psiMethod.getParameterList().getParameters().length != method.getParameterList().getParameters().length)
				continue;
			if (sameParameters(psiMethod, method)) return method;
		}
		return null;
	}

	private boolean sameParameters(PsiMethod psiMethod, PsiMethod method) {
		for (PsiParameter parameter : psiMethod.getParameterList().getParameters())
			for (PsiParameter param : method.getParameterList().getParameters())
				if (param.getType().equals(parameter.getType())) return false;
		return true;
	}


	private PsiElementFactory getElementFactory(PsiElement element) {
		return JavaPsiFacade.getElementFactory(element.getProject());
	}

	private List<PsiMethod> getAbstractMethods(PsiClass aClass) {
		List<PsiMethod> psiMethods = new ArrayList<>();
		for (PsiMethod psiMethod : aClass.getMethods()) if (psiMethod.getBody() == null) psiMethods.add(psiMethod);
		return psiMethods;
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
			directory = subdirectory != null ? subdirectory : createPackage(directory, name);
		}
		return directory;
	}

	private String makePackage(Concept concept) {
		String path = "";
		for (String subpath : FACETS_PATH) path += subpath + ".";
		Inflector inflector = getInflector(Locale.getDefault());
		return path + inflector.plural(concept.getType()).toLowerCase() + "." + inflector.plural(concept.getName()).toLowerCase();
	}

	private boolean isIntention(Concept facet) {
		if (facet.isIntention()) return true;
		Model model = TaraLanguage.getMetaModel(facet.getFile());
		if (model == null) return false;
		Node node = model.searchNode(facet.getMetaQualifiedName());
		return node.getObject().is(INTENTION);
	}

	private Map<String, String> getOptionsForFacetClass(String type) {
		Map<String, String> map = new HashMap<>();
		map.put("TYPE", type);
		return map;
	}

	private VirtualFile getSrcDirectory(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && SRC.equals(file.getName())) return file;
		throw new RuntimeException("Src directory not found");
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
		Node node = model.searchNode(concept.getMetaQualifiedName());
		return node != null && node.is(META_FACET);
	}

	private PsiClass findClassInModule(String qn, Module module) {
		return JavaPsiFacade.getInstance(project).findClass(qn, GlobalSearchScope.moduleScope(module));
	}

	private PsiClass findClassInProject(String qn, Module module) {
		return JavaPsiFacade.getInstance(project).findClass(qn, GlobalSearchScope.projectScope(module.getProject()));
	}

	private PsiDirectory findFacetsDestiny() {
		PsiDirectory directory = srcDirectory;
		for (String name : FACETS_PATH) {
			PsiDirectory subdirectory = directory.findSubdirectory(name.toLowerCase());
			directory = subdirectory != null ? subdirectory : createPackage(directory, name);
		}
		return directory;
	}

	private PsiDirectory createPackage(final PsiDirectory parent, final String name) {
		final PsiDirectory[] destiny = new PsiDirectory[1];
		WriteCommandAction action = new WriteCommandAction(project, parent.getContainingFile()) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				destiny[0] = DirectoryUtil.createSubdirectories(name, parent, ".");
			}
		};
		action.execute();
		return destiny[0];
	}

}
