package siani.tara.intellij.codegeneration;

import com.intellij.codeInsight.generation.OverrideImplementExploreUtil;
import com.intellij.codeInsight.generation.OverrideImplementUtil;
import com.intellij.codeInsight.generation.PsiMethodMember;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.infos.CandidateInfo;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Node;

import java.util.*;

import static com.intellij.psi.JavaPsiFacade.getElementFactory;
import static siani.tara.lang.Annotation.INTENTION;

public class FacetApplyCodeGenerator extends CodeGenerator {


	private PsiDirectory facetsHome;

	public FacetApplyCodeGenerator(TaraBoxFile file) {
		super(file);
	}

	public void generate() {
		final Set<VirtualFile> pathsToRefresh = new HashSet<>();
		final Set<PsiClass> classes = new LinkedHashSet<>();
		WriteCommandAction action = new WriteCommandAction(project, file) {
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
		final Concept[] facetedConcepts = getFacets(file);
		if (facetedConcepts.length > 0) facetsHome = findFacetsDestiny();
		for (Concept concept : facetedConcepts)
			if (concept.getName() != null)
				psiClasses.addAll(createFacetApplyClasses(concept));
		return psiClasses;
	}


	private Collection<PsiClass> createFacetApplyClasses(Concept concept) {
		List<PsiClass> psiClasses = new ArrayList<>();
		for (FacetApply apply : concept.getFacetApplies()) {
			if (!isIntentionImplementation(concept, apply)) continue;
			Collections.addAll(psiClasses, createClasses(concept, apply));
		}
		return psiClasses;
	}

	private boolean isIntentionImplementation(Concept concept, FacetApply facetApply) {
		Node node = TaraUtil.getMetaConcept(concept);
		if (!hasFacet(node, facetApply.getFacetName())) return false;
		for (Node modelNode : TaraUtil.getMetamodel(facetApply).getTreeModel())
			if (facetApply.getFacetName().equals(modelNode.getName()) && modelNode.is(INTENTION))
				return true;
		return false;
	}

	private boolean hasFacet(Node node, String facetApply) {
		return node.getObject().getAllowedFacets().containsKey(facetApply);
	}

	private PsiClass[] createClasses(Concept facetedConcept, FacetApply apply) {
		if (isRootClass(facetedConcept)) return createRootFacetClass(facetedConcept, apply.getFacetName());
		return createInnerClass(facetedConcept, apply);
	}

	private PsiClass[] createInnerClass(Concept facetedConcept, FacetApply apply) {
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

	private PsiClass createRootClass(Concept concept, FacetApply apply) {
		if (!hasFacet(concept, apply)) return createRootClass(concept, apply.getFacetName());
		else return createRootFacetClass(concept, apply.getFacetName())[0];
	}

	private PsiClass createInnerClass(PsiClass container, Concept concept, FacetApply apply) {
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
		String name = concept.getName() + facetName;
		PsiClass innerClassByName = container.findInnerClassByName(name, false);
		if (innerClassByName != null) return innerClassByName;
		PsiClass aClass = JavaPsiFacade.getElementFactory(project).createClass(name);
		setParent(concept, aClass);
		implementInterface(concept, facetName, aClass);
		container.add(aClass);
		return aClass;
	}

	private void implementInterface(Concept concept, String facetName, PsiClass aClass) {
		String interfaceName = concept.getType() + facetName + "Intention";
		PsiClass interfaceClass = findClassInModule(interfaceName);
		String project = TaraLanguage.getMetaModel(concept.getFile()).getName().split("\\.")[0].toLowerCase();
		if (interfaceClass == null)
			interfaceClass = findClassInProject(project + "." + "intentions." + getInflector(concept).plural(facetName).toLowerCase() + "." + interfaceName);
		if (interfaceClass == null) return;
		PsiJavaCodeReferenceElement referenceElement = getElementFactory(this.project).createClassReferenceElement(interfaceClass);
		aClass.getImplementsList().add(referenceElement);
	}

	private void setParent(Concept parent, PsiClass aClass) {
		List<Concept> concepts = TaraUtil.buildConceptCompositionPathOf(parent);
		String qn = MAGRITTE_MORPHS;
		for (Concept concept : concepts)
			qn += "." + concept.getName();//TODO QUE PASA SI NO TIENE NOMBRE
		PsiClass parentClass = findClassInModule(qn);
		setParent(aClass, parentClass);
	}

	private void setParent(final PsiClass aClass, final PsiClass parentClass) {
		if (parentClass == null) return; //CANNOT BE POSSIBLE
		PsiJavaCodeReferenceElement classReferenceElement = getElementFactory(project).createClassReferenceElement(parentClass);
		aClass.getExtendsList().add(classReferenceElement);
	}

	private boolean hasFacet(Concept concept, FacetApply apply) {
		for (FacetApply facetApply : concept.getFacetApplies())
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
		String qn = packageName + "." + className + facetName;
		PsiClass aClass = findClassInModule(qn);
		return (aClass != null) ? new PsiClass[]{aClass} :
			new PsiClass[]{instance.createClass(createPackageDirectory(facetsHome, inflector.plural(facetName.toLowerCase())),
				className, "TaraFacetApplyClass", false, options(concept.getType(), facetName))};
	}

	private String getDestinyPackage(String facetName) {
		return project.getName() + "." + facetsHome.getName() + "." + inflector.plural(facetName.toLowerCase());
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

	private static PsiMethodMember[] convertToMethodMembers(Collection<CandidateInfo> candidates) {
		return ContainerUtil.map2Array(candidates, PsiMethodMember.class, new Function<CandidateInfo, PsiMethodMember>() {
			@Override
			public PsiMethodMember fun(final CandidateInfo s) {
				return new PsiMethodMember(s);
			}
		});
	}
}