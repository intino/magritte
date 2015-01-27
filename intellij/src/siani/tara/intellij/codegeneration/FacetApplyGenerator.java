package siani.tara.intellij.codegeneration;

import com.intellij.codeInsight.generation.OverrideImplementExploreUtil;
import com.intellij.codeInsight.generation.OverrideImplementUtil;
import com.intellij.codeInsight.generation.PsiMethodMember;
import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.infos.CandidateInfo;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.formatter.Inflector;
import org.siani.itrules.formatter.InflectorFactory;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.TaraFacetApply;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.*;

import static com.intellij.psi.JavaPsiFacade.getElementFactory;

public class FacetApplyGenerator {

	public static final String SRC = "src";
	private static final String MAGRITTE_MORPHS = "magritte.morphs.";
	private final Project project;
	private final Module module;
	private final String[] FACETS_PATH;
	private final TaraBoxFile taraBoxFile;
	private PsiDirectory facetsHome;
	private final PsiDirectory srcDirectory;
	private final Inflector inflector;

	public FacetApplyGenerator(TaraBoxFile file) {
		this.taraBoxFile = file;
		this.project = taraBoxFile.getProject();
		module = ModuleProvider.getModuleOf(taraBoxFile);
		VirtualFile src = getSrcDirectory(TaraUtil.getSourceRoots(taraBoxFile));
		srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) taraBoxFile.getManager(), src);
		FACETS_PATH = new String[]{project.getName().toLowerCase(), "extensions"};
		inflector = InflectorFactory.getInflector(ModuleConfiguration.getInstance(module).getLanguage());
	}

	public void generate() {
		final Set<VirtualFile> pathsToRefresh = new HashSet<>();
		final Set<PsiClass> classes = new LinkedHashSet<>();
		WriteCommandAction action = new WriteCommandAction(project, taraBoxFile) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				try {
					classes.addAll(processFile());
					if (!classes.isEmpty() && facetsHome != null)
						pathsToRefresh.add(facetsHome.getVirtualFile());
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
				try {
					implementMethods(classes.toArray(new PsiClass[classes.size()]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		action.execute();
		for (VirtualFile virtualFile : pathsToRefresh) virtualFile.refresh(true, true);
	}

	private Set<PsiClass> processFile() {
		Set<PsiClass> psiClasses = new LinkedHashSet<>();
		final Concept[] facetedConcepts = getFacets(taraBoxFile);
		if (facetedConcepts.length > 0) facetsHome = findFacetsDestiny();
		for (Concept concept : facetedConcepts)
			if (concept.getName() != null)
				psiClasses.addAll(createFacetApplyClasses(concept));
		return psiClasses;
	}


	private Collection<PsiClass> createFacetApplyClasses(Concept concept) {
		List<PsiClass> psiClasses = new ArrayList<>();
		for (TaraFacetApply apply : concept.getFacetApplies()) {
			Collections.addAll(psiClasses, createClasses(concept, apply));
		}
		return psiClasses;
	}

	private PsiClass[] createClasses(Concept facetedConcept, TaraFacetApply apply) {
		if (isRootClass(facetedConcept)) return createRootFacetClass(facetedConcept, apply.getFacetName());
		return createInnerClass(facetedConcept, apply);
	}

	private PsiClass[] createInnerClass(Concept facetedConcept, TaraFacetApply apply) {
		Set<PsiClass> psiClasses = new LinkedHashSet<>();
		for (Concept concept : TaraUtil.buildConceptCompositionPathOf(facetedConcept))
			if (isRootClass(concept)) psiClasses.add(createRootClass(concept, apply));
			else psiClasses.add(createInnerClass(getLast(psiClasses), concept, apply));
		return psiClasses.toArray(new PsiClass[psiClasses.size()]);
	}

	private PsiClass getLast(Set<PsiClass> psiClasses) {
		PsiClass[] array = psiClasses.toArray(new PsiClass[psiClasses.size()]);
		return array[array.length - 1];
	}

	private PsiClass createRootClass(Concept concept, TaraFacetApply apply) {
		if (!hasFacet(concept, apply)) return createRootClass(concept, apply.getFacetName());
		else return createRootFacetClass(concept, apply.getFacetName())[0];
	}

	private PsiClass createInnerClass(PsiClass container, Concept concept, TaraFacetApply apply) {
		if (hasFacet(concept, apply))
			return createInnerFacetClass(container, concept, apply.getFacetName());
		return createInnerClass(container, concept.getName() == null ? concept.getType() : concept.getName());
	}

	private boolean isRootClass(Concept concept) {
		return concept.isRoot() || concept.isAggregated();
	}

	private PsiClass createInnerClass(PsiClass container, String conceptName) {
		PsiClass innerClassByName = container.findInnerClassByName(conceptName, false);
		if (innerClassByName != null) return innerClassByName;
		return (PsiClass) container.add(JavaPsiFacade.getElementFactory(project).createClass(conceptName));
	}

	private PsiClass createInnerFacetClass(PsiClass container, Concept concept, String facetName) {
		String name = concept.getName() + concept.getType() + facetName;
		PsiClass innerClassByName = container.findInnerClassByName(name, false);
		if (innerClassByName != null) return innerClassByName;

		PsiClass aClass = JavaPsiFacade.getElementFactory(project).createClass(name);
		setParent(MAGRITTE_MORPHS + concept.getName(), aClass);
		implementInterface(concept, facetName, aClass);
		container.add(aClass);
		return aClass;
	}

	private void implementInterface(Concept concept, String facetName, PsiClass aClass) {
		String interfaceName = concept.getType() + facetName + "Intention";
		PsiClass interfaceClass = findClassInModule(interfaceName);
		String project = TaraLanguage.getMetaModel(concept.getFile()).getModelName().split("\\.")[0].toLowerCase();
		if (interfaceClass == null)
			interfaceClass = findClassInProject(project + "." + "intentions." + getInflector().plural(facetName).toLowerCase() + "." + interfaceName);
		if (interfaceClass == null) return;
		PsiJavaCodeReferenceElement referenceElement = getElementFactory(this.project).createClassReferenceElement(interfaceClass);
		aClass.getImplementsList().add(referenceElement);
	}

	private void setParent(String parent, PsiClass aClass) {
		PsiClass parentClass = findClassInModule(parent);
		setParent(aClass, parentClass);
	}

	private void setParent(final PsiClass aClass, final PsiClass parentClass) {
		if (parentClass == null) return; //CANNOT BE POSSIBLE
		PsiJavaCodeReferenceElement classReferenceElement = getElementFactory(project).createClassReferenceElement(parentClass);
		aClass.getExtendsList().add(classReferenceElement);
	}

	private boolean hasFacet(Concept concept, TaraFacetApply apply) {
		for (TaraFacetApply facetApply : concept.getFacetApplies())
			if (facetApply.getFacetName().equals(apply.getFacetName()))
				return true;
		return false;
	}

	private PsiClass createRootClass(Concept concept, String facetName) {
		JavaDirectoryService instance = JavaDirectoryService.getInstance();
		String className = concept.getName();
		String packageName = getDestinyPackage(facetName).toLowerCase();
		String qn = packageName + "." + className;
		PsiClass aClass = findClassInModule(qn);
		if (aClass == null)
			aClass = instance.createClass(createPackageDirectory(facetsHome, inflector.plural(facetName.toLowerCase())), className);
		return aClass;
	}

	private PsiClass[] createRootFacetClass(Concept concept, String facetName) {
		JavaDirectoryService instance = JavaDirectoryService.getInstance();
		String className = concept.getName();
		String packageName = getDestinyPackage(facetName).toLowerCase();
		String qn = packageName + "." + className + concept.getType() + facetName;
		PsiClass aClass = findClassInModule(qn);
		return (aClass != null) ? new PsiClass[]{aClass} :
			new PsiClass[]{instance.createClass(createPackageDirectory(facetsHome, inflector.plural(facetName.toLowerCase())),
				className, "TaraFacetApplyClass", false, options(concept.getType(), facetName))};
	}

	private String getDestinyPackage(String facetName) {
		return project.getName() + "." + facetsHome.getName() + "." + inflector.plural(facetName.toLowerCase());
	}


	private PsiDirectory createPackageDirectory(final PsiDirectory parent, final String name) {
		PsiDirectory subdirectory = parent.findSubdirectory(name);
		if (subdirectory != null) return subdirectory;
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

	private Map<String, String> options(String conceptType, String facet) {
		Map<String, String> map = new HashMap<>();
		map.put("TYPE", conceptType);
		map.put("FACET", facet);
		map.put("FACET_PLURAL", inflector.plural(facet));
		return map;
	}

	private Concept[] getFacets(TaraBoxFile file) {
		Set<Concept> facets = new LinkedHashSet<>();
		for (Concept concept : TaraUtil.getAllConceptsOfFile(file))
			if (concept.getFacetApplies().length > 0) facets.add(concept);
		return facets.toArray(new Concept[facets.size()]);
	}

	private VirtualFile getSrcDirectory(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && SRC.equals(file.getName())) return file;
		throw new RuntimeException("Src directory not found");
	}


	private PsiDirectory findFacetsDestiny() {
		PsiDirectory directory = srcDirectory;
		for (String name : FACETS_PATH) {
			if (directory.findSubdirectory(name.toLowerCase()) != null)
				directory = directory.findSubdirectory(name.toLowerCase());
			else directory = createPackageDirectory(directory, name);
		}
		return directory;
	}


	private PsiClass findClassInModule(String qn) {
		return JavaPsiFacade.getInstance(project).findClass(qn, GlobalSearchScope.moduleScope(module));
	}

	private PsiClass findClassInProject(String qn) {
		return JavaPsiFacade.getInstance(project).findClass(qn, GlobalSearchScope.projectScope(project));
	}

	private void implementMethods(PsiClass... facetClasses) {
		Document doc = null;
		for (PsiClass facetClass : facetClasses) {
			PsiDocumentManager dm = PsiDocumentManager.getInstance(project);
			if (dm.getDocument(facetClass.getContainingFile()) != null)
				doc = dm.getDocument(facetClass.getContainingFile());
			if (doc != null) {
				Editor editor = EditorFactory.getInstance().createEditor(doc, project, facetClass.getContainingFile().getFileType(), false);
				Collection<CandidateInfo> values = OverrideImplementExploreUtil.getMapToOverrideImplement(facetClass, true, false).values();
				OverrideImplementUtil.overrideOrImplementMethodsInRightPlace(editor, facetClass, Arrays.asList(convertToMethodMembers(values)), false, true);
			}
		}
	}

	private Document searchDoc(PsiClass facetClass) {
		PsiClass container = facetClass;
		PsiDocumentManager dm = PsiDocumentManager.getInstance(project);
		Document doc = null;
		while (container != null && doc == null) {
			doc = dm.getDocument(container.getContainingFile());
			container = container.getContainingClass();
		}
		return doc;
	}

	private static PsiMethodMember[] convertToMethodMembers(Collection<CandidateInfo> candidates) {
		return ContainerUtil.map2Array(candidates, PsiMethodMember.class, new Function<CandidateInfo, PsiMethodMember>() {
			@Override
			public PsiMethodMember fun(final CandidateInfo s) {
				return new PsiMethodMember(s);
			}
		});
	}

	private Inflector getInflector() {
		return InflectorFactory.getInflector(ModuleConfiguration.getInstance(module).getLanguage());
	}

	private PsiFile[] getFiles(Set<PsiClass> classes) {
		Set<PsiFile> psiFiles = new HashSet<>();
		for (PsiClass aClass : classes) psiFiles.add(aClass.getContainingFile());
		return psiFiles.toArray(new PsiFile[psiFiles.size()]);
	}
}