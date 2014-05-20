package monet.tara.intellij.project.view;

import com.intellij.ide.DeleteProvider;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.TreeStructureProvider;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.BasePsiNode;
import com.intellij.ide.util.DeleteHandler;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassOwner;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.util.containers.ContainerUtil;
import monet.tara.intellij.lang.TaraIcons;
import monet.tara.intellij.lang.file.TaraFileType;
import monet.tara.intellij.lang.psi.TaraFile;
import monet.tara.intellij.lang.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MergerTreeStructureProvider implements TreeStructureProvider {
	private final Project project;

	public MergerTreeStructureProvider(Project project) {
		this.project = project;
	}

	private static Collection<PsiFile> convertToFiles(Collection<BasePsiNode<? extends PsiElement>> conceptNodes) {
		List<PsiFile> psiFiles = new ArrayList<>();
		for (AbstractTreeNode treeNode : conceptNodes)
			psiFiles.add((PsiFile) treeNode.getValue());
		return psiFiles;
	}

	private static Collection<BasePsiNode<? extends PsiElement>> findConceptsIn(Collection<AbstractTreeNode> children, List<PsiFile> concepts) {
		if (children.isEmpty() || concepts.isEmpty()) return Collections.emptyList();
		List<BasePsiNode<? extends PsiElement>> result = new ArrayList<>();
		Set<PsiFile> psiFiles = new HashSet<>(concepts);
		for (final AbstractTreeNode child : children) {
			if (child instanceof BasePsiNode) {
				BasePsiNode<? extends PsiElement> treeNode = (BasePsiNode<? extends PsiElement>) child;
				//noinspection SuspiciousMethodCalls
				if (psiFiles.contains(treeNode.getValue())) result.add(treeNode);
			}
		}
		return result;
	}

	@NotNull
	public Collection<AbstractTreeNode> modify(@NotNull AbstractTreeNode parent, @NotNull Collection<AbstractTreeNode> children, ViewSettings settings) {
		if (parent.getValue() instanceof ConceptTreeView) return children;
		if (!conceptExists(children)) return children;
		Collection<AbstractTreeNode> result = new LinkedHashSet<>(children);
		ProjectViewNode[] copy = children.toArray(new ProjectViewNode[children.size()]);
		for (ProjectViewNode element : copy) {
			PsiClass psiClass = getPsiClass(element);
			if (psiClass == null || psiClass.getName() == null) continue;
			List<PsiFile> concepts;
			concepts = findConceptsBoundToClass(psiClass);
			Collection<BasePsiNode<? extends PsiElement>> conceptNodes = findConceptsIn(children, concepts);
			if (!conceptNodes.isEmpty()) {
				Collection<PsiFile> conceptFiles = convertToFiles(conceptNodes);
				Collection<BasePsiNode<? extends PsiElement>> subNodes = new ArrayList<>();
				subNodes.add((BasePsiNode<? extends PsiElement>) element);
				subNodes.addAll(conceptNodes);
				result.add(new ConceptNode(project, new ConceptTreeView(psiClass, conceptFiles), settings, TaraIcons.getIcon(TaraIcons.ICON_13), subNodes));
				result.remove(element);
				result.removeAll(conceptNodes);
			}
		}
		return result;
	}

	private PsiClass getPsiClass(ProjectViewNode element) {
		PsiClass psiClass = null;
		if (element.getValue() instanceof PsiClass)
			psiClass = (PsiClass) element.getValue();
		else if (element.getValue() instanceof PsiClassOwner) {
			final PsiClass[] psiClasses = ((PsiClassOwner) element.getValue()).getClasses();
			if (psiClasses.length == 1) psiClass = psiClasses[0];
		}
		return psiClass;
	}

	private boolean conceptExists(Collection<AbstractTreeNode> children) {
		boolean conceptFound = false;
		for (AbstractTreeNode node : children)
			if (node.getValue() instanceof PsiFile) {
				PsiFile file = (PsiFile) node.getValue();
				if (file.getFileType() == TaraFileType.INSTANCE) {
					conceptFound = true;
					break;
				}
			}
		return conceptFound;
	}

	private List<PsiFile> findConceptsBoundToClass(PsiClass psiClass) {
		List<PsiFile> files = new ArrayList<>();
		for (PsiElement element : psiClass.getParent().getParent().getChildren())
			if (element instanceof TaraFile && ((TaraFileImpl) element).getPresentableName().equals(psiClass.getName()))
				files.add((PsiFile) element);
		return files;
	}

	public Object getData(Collection<AbstractTreeNode> selected, String dataId) {
		if (selected != null)
			if (ConceptTreeView.DATA_KEY.is(dataId)) {
				List<ConceptTreeView> result = getConceptTreeViews(selected);
				if (!result.isEmpty())
					return result.toArray(new ConceptTreeView[result.size()]);
			} else if (PlatformDataKeys.DELETE_ELEMENT_PROVIDER.is(dataId))
				for (AbstractTreeNode node : selected)
					if (node.getValue() instanceof ConceptTreeView)
						return new MyDeleteProvider(selected);
		return null;
	}

	private List<ConceptTreeView> getConceptTreeViews(Collection<AbstractTreeNode> selected) {
		List<ConceptTreeView> result = new ArrayList<>();
		for (AbstractTreeNode node : selected)
			if (node.getValue() instanceof ConceptTreeView)
				result.add((ConceptTreeView) node.getValue());
		return result;
	}

	private static class MyDeleteProvider implements DeleteProvider {
		private final PsiElement[] myElements;

		public MyDeleteProvider(final Collection<AbstractTreeNode> selected) {
			myElements = collectConceptPsiElements(selected);
		}

		private static PsiElement[] collectConceptPsiElements(Collection<AbstractTreeNode> selected) {
			Set<PsiElement> result = new HashSet<>();
			for (AbstractTreeNode node : selected) {
				if (node.getValue() instanceof ConceptTreeView) {
					ConceptTreeView concept = (ConceptTreeView) node.getValue();
					result.add(concept.getClassToBind());
					ContainerUtil.addAll(result, concept.getTaraFiles());
				} else if (node.getValue() instanceof PsiElement)
					result.add((PsiElement) node.getValue());
			}
			return PsiUtilCore.toPsiElementArray(result);
		}

		public void deleteElement(@NotNull DataContext dataContext) {
			DeleteHandler.deletePsiElement(myElements, CommonDataKeys.PROJECT.getData(dataContext));
		}

		public boolean canDeleteElement(@NotNull DataContext dataContext) {
			return DeleteHandler.shouldEnableDeleteAction(myElements);
		}
	}
}
