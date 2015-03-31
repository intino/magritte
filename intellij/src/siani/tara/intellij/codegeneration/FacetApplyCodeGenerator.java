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
import com.intellij.openapi.editor.impl.EditorImpl;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.infos.CandidateInfo;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import siani.tara.Language;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.semantic.Allow;
import siani.tara.semantic.Assumption;

import java.util.*;

import static com.intellij.psi.JavaPsiFacade.getElementFactory;
import static siani.tara.intellij.lang.psi.impl.TaraUtil.getAllowsOf;

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
		if (!hasFacet(getAllowsOf(node.resolve(), node.getFullType()), facetApply.getType()))
			return false;
		Language language = TaraLanguage.getLanguage(node.getFile());
		if (language == null) return false;
		for (Assumption assumption : language.assumptions(facetApply.getType()))
			if (assumption instanceof Assumption.IntentionInstance) return true;
		return false;
	}

	private PsiClass[] createClasses(Node facetedNode, FacetApply apply) {
		if (isRootClass(facetedNode)) return createRootFacetClass(facetedNode, apply.getType());
		return createInnerClass(facetedNode, apply);
	}

	private PsiClass[] createInnerClass(Node facetedNode, FacetApply apply) {
		Set<PsiClass> psiClasses = new LinkedHashSet<>();
		for (Node node : TaraUtil.buildNodeCompositionPathOf(facetedNode))
			if (isRootClass(node)) psiClasses.add(createRootClass(node, apply));
			else psiClasses.add(createInnerClass(getLast(psiClasses), node, apply));
		return psiClasses.toArray(new PsiClass[psiClasses.size()]);
	}

	private PsiClass getLast(Set<PsiClass> psiClasses) {
		PsiClass[] array = psiClasses.toArray(new PsiClass[psiClasses.size()]);
		return array[array.length - 1];
	}

	private PsiClass createRootClass(Node node, FacetApply apply) {
		if (!hasFacet(getAllowsOf(node.resolve(), node.getFullType()), apply.getType()))
			return createRootClass(node, apply.getType());
		else return createRootFacetClass(node, apply.getType())[0];
	}

	private PsiClass createInnerClass(PsiClass container, Node node, FacetApply apply) {
		if (hasFacet(getAllowsOf(node.resolve(), node.getFullType()), apply.getType()))
			return createInnerFacetClass(container, node, apply.getType());
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
		PsiClass interfaceClass = findClassInModule(node.getType() + facetName + "Intention");
		Language language = TaraLanguage.getLanguage(node.getFile());
		if (language == null) return;
		String project = language.languageName().toLowerCase();
		if (interfaceClass == null)
			interfaceClass = findClassInProject(buildQn(node, facetName, project));
		if (interfaceClass == null) return;
		PsiJavaCodeReferenceElement referenceElement = getElementFactory(this.project).createClassReferenceElement(interfaceClass);
		aClass.getImplementsList().add(referenceElement);
	}

	@NotNull
	private String buildQn(Node node, String facetName, String project) {
		String qn = project + "." + "intentions." + getInflector(node).plural(facetName).toLowerCase();
		for (String name : node.getFullType().split("\\."))
			qn += "." + name + facetName + "Intention";
		return qn;
	}

	private void setParent(Node parent, PsiClass aClass) {
		List<Node> nodes = TaraUtil.buildNodeCompositionPathOf(parent);
		String qn = MAGRITTE_MORPHS;
		for (Node node : nodes) {
			if (node.isProperty() || node.isPropertyInstance()) break;
			qn += "." + (node.getName() == null ? node.getType() : node.getName());
		}
		PsiClass parentClass = findClassInModule(qn);
		setParent(aClass, parentClass);
	}

	private void setParent(final PsiClass aClass, final PsiClass parentClass) {
		if (parentClass == null) return;
		PsiJavaCodeReferenceElement classReferenceElement = getElementFactory(project).createClassReferenceElement(parentClass);
		aClass.getExtendsList().add(classReferenceElement);
	}

	private boolean hasFacet(Collection<Allow> allows, String apply) {
		if (allows == null) return false;
		for (Allow allow : allows)
			if (allow instanceof Allow.Facet && ((Allow.Facet) allow).type().equals(apply)) return true;
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
			if (!node.getFacetApplies().isEmpty()) facets.add(node);
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
				((EditorImpl) editor).release();
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