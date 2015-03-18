package siani.tara.intellij.codegeneration;

import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.formatter.Inflector;
import org.siani.itrules.formatter.InflectorFactory;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.TaraIdentifier;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.*;

import static com.intellij.psi.JavaPsiFacade.getElementFactory;
import static com.intellij.psi.JavaPsiFacade.getInstance;
import static siani.tara.intellij.lang.psi.impl.ReferenceManager.resolveToNode;
import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getParentOf;

public class IntentionsGenerator {

	private static final Logger LOG = Logger.getInstance(IntentionsGenerator.class.getName());
	private static final String INTENTIONS = "intentions";
	private static final String INTENTION = "Intention";
	private static final String DOT = ".";
	private static final String MAGRITTE_INTENTION = "magritte.Intention";
	private final Project project;
	private final String basePath;
	private final TaraModel taraModel;
	private final PsiDirectory srcDirectory;
	private PsiDirectory destiny;
	private final Module module;
	Map<String, PsiClass> createdClasses = new HashMap();

	public IntentionsGenerator(Project project, TaraModel taraModel) {
		this.project = project;
		basePath = project.getName().toLowerCase() + DOT + INTENTIONS;
		this.taraModel = taraModel;
		VirtualFile srcVDirectory = TaraUtil.getSrcRoot(TaraUtil.getSourceRoots(taraModel));
		this.srcDirectory = new PsiDirectoryImpl((com.intellij.psi.impl.PsiManagerImpl) taraModel.getManager(), srcVDirectory);
		this.module = ModuleProvider.getModuleOf(taraModel);
	}

	public void generate() {
		WriteCommandAction action = new WriteCommandAction(project, taraModel) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				try {
					processFile(taraModel);
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		};
		action.execute();
	}

	private void processFile(PsiFile psiFile) {
		if (psiFile instanceof TaraModel) {
			Node[] intentions = getIntentions((TaraModel) psiFile);
			if (intentions.length > 0) this.destiny = findIntentionsDestiny();
			for (Node intention : intentions) {
				createIntentionClass(intention);
				createTargetInterfaces(intention);
			}
		}
	}

	private PsiClass createIntentionClass(Node node) {
		PsiClass aClass = findClass(node);
		if (aClass == null) aClass = JavaDirectoryService.getInstance().createInterface(destiny, getClassName(node));
		Node parentNode = node.getParentNode() != null ? findParent(node) : null;
		setParent(parentNode != null ? parentNode.getName() : MAGRITTE_INTENTION, aClass);
		return aClass;
	}

	private void createTargetInterfaces(Node node) {
		Collection<TaraFacetTarget> facetTargets = node.getFacetTargets();
		sort(facetTargets);
		for (TaraFacetTarget facetTarget : facetTargets)
			createTargetInterface(node, facetTarget);
	}

	private void sort(Collection<TaraFacetTarget> facetTargets) {
		Collections.sort((List<TaraFacetTarget>) facetTargets, new Comparator<TaraFacetTarget>() {
			@Override
			public int compare(TaraFacetTarget f1, TaraFacetTarget f2) {
				Node d1 = resolveToNode(f1.getIdentifierReference());
				Node d2 = resolveToNode(f2.getIdentifierReference());
				if (d1 != null && !isChildOf(d1, d2) && d2 != null && !isChildOf(d2, d1)) return 0;
				if (d1 != null && !isChildOf(d1, d2)) return -1;
				if (d1 != null && !isChildOf(d1, d2)) return 1;
				return 0;
			}

			private boolean isChildOf(Node child, Node d2) {
				Node parent = child.getParentNode();
				while (parent != null)
					if (parent.equals(d2)) return true;
					else parent = parent.getParentNode();
				return false;
			}
		});
	}

	private void createTargetInterface(Node facetNode, TaraFacetTarget target) {
		PsiDirectory facetDirectory = findFacetDirectory(facetNode.getName());
		String facetsBasePath = basePath + DOT + facetDirectory.getName().toLowerCase();
		List<Node> compositionRoute = TaraUtil.buildConceptCompositionPathOf(resolveToNode(target.getIdentifierReference()));

		for (int i = 0; i < compositionRoute.size(); i++) {
			Node targetNode = compositionRoute.get(i);
			PsiClass aClass = findClass(facetsBasePath + DOT + buildMiddlePath(createdClasses, subList(compositionRoute, targetNode)) + getClassName(targetNode, facetNode));
			if (aClass == null) {
				aClass = i == 0 ? createTargetInterface(getClassName(targetNode, facetNode), facetDirectory) :
					createTargetInnerInterface(getClassName(targetNode, facetNode), createdClasses.get(compositionRoute.get(i - 1).getName()));
			}
			if (targetNode.equals(resolveToNode(target.getIdentifierReference()))) {
				TaraFacetTarget parentTarget = searchParentTarget(facetNode, target, targetNode);
				setParent(parentTarget == null ? basePath + DOT + getClassName(facetNode) : getTargetClassQN(facetNode, parentTarget), aClass);
			}
			createdClasses.put(targetNode.getName(), aClass);
		}
	}

	private PsiClass createTargetInnerInterface(String className, PsiClass container) {
		return (PsiClass) container.add(JavaPsiFacade.getElementFactory(project).createInterface(className));
	}

	private String buildMiddlePath(Map<String, PsiClass> createdClasses, List<Node> nodes) {
		String path = "";
		for (Node node : nodes) {
			PsiClass psiClass = createdClasses.get(node.getName());
			if (psiClass != null)
				path += psiClass.getName() + DOT;
		}
		return path;
	}

	private List<Node> subList(List<Node> compositionRoute, Node targetNode) {
		List<Node> subList = new ArrayList<>();
		for (Node node : compositionRoute) {
			if (node.equals(targetNode)) break;
			subList.add(node);
		}
		return subList;
	}

	private PsiClass createTargetInterface(String qn, PsiDirectory facetDirectory) {
		return JavaDirectoryService.getInstance().createInterface(facetDirectory, qn);
	}

	private TaraFacetTarget searchParentTarget(Node facet, TaraFacetTarget facetTarget, Node target) {
		if (target == null) return null;
		List<Node> nodes = getConceptHierarchy(target);
		if (nodes.isEmpty()) return null;
		return searchParentTarget(facet, facetTarget, nodes);
	}

	private TaraFacetTarget searchParentTarget(Node node, TaraFacetTarget target, List<Node> hierarchy) {
		TaraFacetTarget closestParent = null;
		int parentIndex = hierarchy.size();
		for (TaraFacetTarget taraFacetTarget : node.getFacetTargets()) {
			if (taraFacetTarget.equals(target)) continue;
			int indexOf = hierarchy.indexOf(resolveTargetDestiny(taraFacetTarget));
			if (indexOf >= 0 && indexOf < parentIndex) {
				parentIndex = indexOf;
				closestParent = taraFacetTarget;
			}
		}
		return closestParent;
	}

	private Node resolveTargetDestiny(TaraFacetTarget target) {
		return ReferenceManager.resolveToNode(target.getIdentifierReference());
	}

	private List<Node> getConceptHierarchy(Node nodeTarget) {
		List<Node> nodes = new ArrayList<>();
		Node parent = getParentOf(nodeTarget);
		while (parent != null) {
			nodes.add(parent);
			parent = getParentOf(parent);
		}
		return nodes;
	}

	private String getClassName(Node target, Node facet) {
		return target.getName() + facet.getName() + INTENTION;
	}

	private String getTargetClassQN(Node node, TaraFacetTarget target) {
		if (target.getIdentifierReference() == null) return null;
		List<TaraIdentifier> identifierList = target.getIdentifierReference().getIdentifierList();
		String name = identifierList.get(identifierList.size() - 1).getName() + node.getName() + INTENTION;
		return basePath + DOT + getInflector().plural(node.getName()).toLowerCase() + DOT + name;
	}

	private void setParent(String parent, PsiClass aClass) {
		PsiClass parentClass = findClass(parent);
		if (!isParentAdded(aClass.getExtendsList().getReferencedTypes(), parentClass))
			setParent(aClass, parentClass);
	}

	private void setParent(final PsiClass aClass, final PsiClass parentClass) {
		if (parentClass == null) return;
		PsiJavaCodeReferenceElement classReferenceElement = getElementFactory(project).createClassReferenceElement(parentClass);
		if (!isParentAdded(aClass.getExtendsListTypes(), parentClass))
			aClass.getExtendsList().add(classReferenceElement);
	}

	private boolean isParentAdded(PsiClassType[] extendsList, PsiClass parentClass) {
		for (PsiClassType psiClassType : extendsList)
			if (parentClass.getName().equals(psiClassType.getClassName())) return true;
		return false;
	}

	private Node findParent(Node node) {
		return node.getParentNode();
	}

	private PsiClass findClass(Node node) {
		return getInstance(project).findClass(basePath + DOT + getClassName(node),
			GlobalSearchScope.moduleScope(module));
	}

	private String getClassName(Node node) {
		return node.getName() + INTENTION;
	}

	private PsiClass findClass(String qn) {
		return getInstance(project).findClass(qn, GlobalSearchScope.moduleWithLibrariesScope(module));
	}

	private PsiDirectory findFacetDirectory(String name) {
		final PsiDirectory intentionsDir = findIntentionsDestiny();
		final String pluralName = getInflector().plural(name).toLowerCase();
		PsiDirectory subdirectory = intentionsDir.findSubdirectory(pluralName);
		if (subdirectory != null) return subdirectory;
		final PsiDirectory[] facetDestiny = new PsiDirectory[1];
		WriteCommandAction action = new WriteCommandAction(project, taraModel) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				facetDestiny[0] = DirectoryUtil.createSubdirectories(pluralName, intentionsDir, DOT);
			}
		};
		action.execute();
		return facetDestiny[0];
	}

	private Inflector getInflector() {
		return InflectorFactory.getInflector(ModuleConfiguration.getInstance(module).getLanguage());
	}

	private PsiDirectory findIntentionsDestiny() {
		String[] path = new String[]{project.getName().toLowerCase(), INTENTIONS};
		PsiDirectory destinyDir = srcDirectory;
		for (String name : path)
			destinyDir = destinyDir.findSubdirectory(name) == null ? createDirectory(destinyDir, name) : destinyDir.findSubdirectory(name);
		return destinyDir;
	}

	@NotNull
	private PsiDirectory createDirectory(final PsiDirectory basePath, final String name) {
		final PsiDirectory[] newDir = new PsiDirectory[1];
		WriteCommandAction action = new WriteCommandAction(project, taraModel) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				newDir[0] = DirectoryUtil.createSubdirectories(name, basePath, DOT);
			}
		};
		action.execute();
		return newDir[0];
	}


	private Node[] getIntentions(TaraModel taraModel) {
		List<Node> intentions = new ArrayList<>();
		List<Node> allConceptsOfFile = TaraUtil.getAllConceptsOfFile(taraModel);
		for (Node node : allConceptsOfFile)
			if (node.isIntention())
				intentions.add(node);
		return intentions.toArray(new Node[intentions.size()]);
	}
}
