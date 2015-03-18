package siani.tara.intellij.codegeneration;

import com.intellij.codeInsight.generation.OverrideImplementExploreUtil;
import com.intellij.codeInsight.generation.OverrideImplementUtil;
import com.intellij.codeInsight.generation.PsiMethodMember;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.diagnostic.Logger;
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
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.semantic.Assumption;

import java.util.*;

import static com.intellij.psi.JavaPsiFacade.getElementFactory;

public class FacetApplyCodeGenerator extends CodeGenerator {

	private static final Logger LOG = Logger.getInstance(FacetApplyCodeGenerator.class.getName());
	private PsiDirectory facetsHome;

	public FacetApplyCodeGenerator(TaraModel file) {
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
					LOG.error(e.getMessage(), e);
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
					LOG.error(e.getMessage(), e);
				}
			}
		};
		action.execute();
		for (VirtualFile virtualFile : pathsToRefresh) virtualFile.refresh(true, true);
	}

	private Set<PsiClass> processFile() {
		Set<PsiClass> psiClasses = new LinkedHashSet<>();
		final Node[] facetedNodes = getFacets(file);
		if (facetedNodes.length > 0) facetsHome = findFacetsDestiny();
		for (Node node : facetedNodes)
			if (node.getName() != null)
				psiClasses.addAll(createFacetApplyClasses(node));
		return psiClasses;
	}


	private Collection<PsiClass> createFacetApplyClasses(Node node) {
		List<PsiClass> psiClasses = new ArrayList<>();
		for (FacetApply apply : node.getFacetApplies()) {
			if (!isIntentionImplementation(node, apply)) continue;
			Collections.addAll(psiClasses, createClasses(node, apply));
		}
		return psiClasses;
	}

	private boolean isIntentionImplementation(Node node, FacetApply facetApply) {
		//TODO
//		Node node = TaraUtil.getMetaConcept(concept);
//		if (!hasFacet(node, facetApply.getFacetName())) return false;
//		for (Node modelNode : TaraUtil.getLanguage(facetApply).getTreeModel())
//			if (facetApply.getFacetName().equals(modelNode.getName()) && modelNode.is(INTENTION))
//				return true;
		return false;
	}

//	private boolean hasFacet(Node node, String facetApply) {
//		return node.getObject().getAllowedFacets().containsKey(facetApply);
//	}

	private PsiClass[] createClasses(Node facetedNode, FacetApply apply) {
		if (isRootClass(facetedNode)) return createRootFacetClass(facetedNode, apply.getFacetName());
		return createInnerClass(facetedNode, apply);
	}

	private PsiClass[] createInnerClass(Node facetedNode, FacetApply apply) {
		Set<PsiClass> psiClasses = new LinkedHashSet<>();
		for (Node node : TaraUtil.buildConceptCompositionPathOf(facetedNode))
			if (isRootClass(node)) psiClasses.add(createRootClass(node, apply));
			else psiClasses.add(createInnerClass(getLast(psiClasses), node, apply));
		return psiClasses.toArray(new PsiClass[psiClasses.size()]);
	}

	private PsiClass getLast(Set<PsiClass> psiClasses) {
		PsiClass[] array = psiClasses.toArray(new PsiClass[psiClasses.size()]);
		return array[array.length - 1];
	}

	private PsiClass createRootClass(Node node, FacetApply apply) {
		if (!hasFacet(node, apply)) return createRootClass(node, apply.getFacetName());
		else return createRootFacetClass(node, apply.getFacetName())[0];
	}

	private PsiClass createInnerClass(PsiClass container, Node node, FacetApply apply) {
		if (hasFacet(node, apply))
			return createInnerFacetClass(container, node, apply.getFacetName());
		return createInnerClass(container, node.getName() == null ? node.getType() : node.getName());
	}

	private boolean isRootClass(Node node) {
		return node.isRoot() || node.isAggregated();
	}

	private PsiClass createInnerClass(PsiClass container, String conceptName) {
		PsiClass innerClassByName = container.findInnerClassByName(conceptName, false);
		if (innerClassByName != null) return innerClassByName;
		return (PsiClass) container.add(JavaPsiFacade.getElementFactory(project).createClass(conceptName));
	}

	private PsiClass createInnerFacetClass(PsiClass container, Node node, String facetName) {
		String name = node.getName() + facetName;
		PsiClass innerClassByName = container.findInnerClassByName(name, false);
		if (innerClassByName != null) return innerClassByName;
		PsiClass aClass = JavaPsiFacade.getElementFactory(project).createClass(name);
		setParent(node, aClass);
		implementInterface(node, facetName, aClass);
		container.add(aClass);
		return aClass;
	}

	private void implementInterface(Node node, String facetName, PsiClass aClass) {
		String interfaceName = node.getType() + facetName + "Intention";
		PsiClass interfaceClass = findClassInModule(interfaceName);
		String project = "";//TaraLanguage.getLanguage(concept.getFile()).getName().split("\\.")[0].toLowerCase();
		if (interfaceClass == null)
			interfaceClass = findClassInProject(project + "." + "intentions." + getInflector(node).plural(facetName).toLowerCase() + "." + interfaceName);
		if (interfaceClass == null) return;
		PsiJavaCodeReferenceElement referenceElement = getElementFactory(this.project).createClassReferenceElement(interfaceClass);
		aClass.getImplementsList().add(referenceElement);
	}

	private void setParent(Node parent, PsiClass aClass) {
		List<Node> nodes = TaraUtil.buildConceptCompositionPathOf(parent);
		String qn = MAGRITTE_MORPHS;
		for (Node node : nodes) {
			if (node.isProperty() || isPropertyInstance(node)) break;
			qn += "." + (node.getName() == null ? node.getType() : node.getName());
		}
		PsiClass parentClass = findClassInModule(qn);
		setParent(aClass, parentClass);
	}

	private boolean isPropertyInstance(Node node) {
		Collection<Assumption> assumptionsOf = TaraUtil.getAssumptionsOf(node);
		for (Assumption assumption : assumptionsOf)
			if (assumption instanceof Assumption.PropertyInstance)
				return true;
		return false;
	}

	private void setParent(final PsiClass aClass, final PsiClass parentClass) {
		if (parentClass == null) return;
		PsiJavaCodeReferenceElement classReferenceElement = getElementFactory(project).createClassReferenceElement(parentClass);
		aClass.getExtendsList().add(classReferenceElement);
	}

	private boolean hasFacet(Node node, FacetApply apply) {
		for (FacetApply facetApply : node.getFacetApplies())
			if (facetApply.getFacetName().equals(apply.getFacetName()))
				return true;
		return false;
	}

	private PsiClass createRootClass(Node node, String facetName) {
		JavaDirectoryService instance = JavaDirectoryService.getInstance();
		String className = node.getName();
		String packageName = getDestinyPackage(facetName).toLowerCase();
		String qn = packageName + "." + className;
		PsiClass aClass = findClassInModule(qn);
		if (aClass == null)
			aClass = instance.createClass(createPackageDirectory(facetsHome, inflector.plural(facetName.toLowerCase())), className);
		return aClass;
	}

	private PsiClass[] createRootFacetClass(Node node, String facetName) {
		JavaDirectoryService instance = JavaDirectoryService.getInstance();
		String className = node.getName();
		String packageName = getDestinyPackage(facetName).toLowerCase();
		String qn = packageName + "." + className + facetName;
		PsiClass aClass = findClassInModule(qn);
		return (aClass != null) ? new PsiClass[]{aClass} :
			new PsiClass[]{instance.createClass(createPackageDirectory(facetsHome, inflector.plural(facetName.toLowerCase())),
				className, "TaraFacetApplyClass", false, options(node.getType(), facetName))};
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

	private Node[] getFacets(TaraModel file) {
		Set<Node> facets = new LinkedHashSet<>();
		for (Node node : TaraUtil.getAllNodesOfFile(file))
			if (node.getFacetApplies().length > 0) facets.add(node);
		return facets.toArray(new Node[facets.size()]);
	}

	private PsiDirectory findFacetsDestiny() {
		PsiDirectory directory = srcDirectory;
		for (String name : facetsPath) {
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