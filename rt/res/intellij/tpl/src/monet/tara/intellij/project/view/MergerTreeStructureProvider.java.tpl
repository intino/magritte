package monet.::projectName::.intellij.project.view;

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
import monet.::projectName::.intellij.lang.::projectProperName::Icons;
import monet.::projectName::.intellij.lang.file.::projectProperName::FileType;
import monet.::projectName::.intellij.lang.psi.::projectProperName::File;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::FileImpl;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MergerTreeStructureProvider implements TreeStructureProvider {
	private final Project project;

	public MergerTreeStructureProvider(Project project) {
		this.project = project;
	}

	private static Collection<PsiFile> convertToFiles(Collection<BasePsiNode<? extends PsiElement>> definitionNodes) {
		List<PsiFile> psiFiles = new ArrayList<>();
		for (AbstractTreeNode treeNode \: definitionNodes)
			psiFiles.add((PsiFile) treeNode.getValue());
		return psiFiles;
	}

	private static Collection<BasePsiNode<? extends PsiElement>> findDefinitionsIn(Collection<AbstractTreeNode> children, List<PsiFile> definitions) {
		if (children.isEmpty() || definitions.isEmpty()) return Collections.emptyList();
		List<BasePsiNode<? extends PsiElement>> result = new ArrayList<>();
		Set<PsiFile> psiFiles = new HashSet<>(definitions);
		for (final AbstractTreeNode child \: children) {
			if (child instanceof BasePsiNode) {
				BasePsiNode<? extends PsiElement> treeNode = (BasePsiNode<? extends PsiElement>) child;
				//noinspection SuspiciousMethodCalls
				if (psiFiles.contains(treeNode.getValue())) result.add(treeNode);
			}
		}
		return result;
	}

	\@NotNull
	public Collection<AbstractTreeNode> modify(\@NotNull AbstractTreeNode parent, \@NotNull Collection<AbstractTreeNode> children, ViewSettings settings) {
		if (parent.getValue() instanceof DefinitionTreeView) return children;
		if (!definitionExists(children)) return children;
		Collection<AbstractTreeNode> result = new LinkedHashSet<>(children);
		ProjectViewNode[] copy = children.toArray(new ProjectViewNode[children.size()]);
		for (ProjectViewNode element \: copy) {
			PsiClass psiClass = getPsiClass(element);
			if (psiClass == null || psiClass.getName() == null) continue;
			List<PsiFile> definitions;
			definitions = findDefinitionsBoundToClass(psiClass);
			Collection<BasePsiNode<? extends PsiElement>> definitionNodes = findDefinitionsIn(children, definitions);
			if (!definitionNodes.isEmpty()) {
				Collection<PsiFile> definitionFiles = convertToFiles(definitionNodes);
				Collection<BasePsiNode<? extends PsiElement>> subNodes = new ArrayList<>();
				subNodes.add((BasePsiNode<? extends PsiElement>) element);
				subNodes.addAll(definitionNodes);
				result.add(new DefinitionNode(project, new DefinitionTreeView(psiClass, definitionFiles), settings, ::projectProperName::Icons.getIcon(::projectProperName::Icons.ICON_13), subNodes));
				result.remove(element);
				result.removeAll(definitionNodes);
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

	private boolean definitionExists(Collection<AbstractTreeNode> children) {
		boolean definitionFound = false;
		for (AbstractTreeNode node \: children)
			if (node.getValue() instanceof PsiFile) {
				PsiFile file = (PsiFile) node.getValue();
				if (file.getFileType() == ::projectProperName::FileType.INSTANCE) {
					definitionFound = true;
					break;
				}
			}
		return definitionFound;
	}

	private List<PsiFile> findDefinitionsBoundToClass(PsiClass psiClass) {
		List<PsiFile> files = new ArrayList<>();
		for (PsiElement element \: psiClass.getParent().getParent().getChildren())
			if (element instanceof ::projectProperName::File && ((::projectProperName::FileImpl) element).getPresentableName().equals(psiClass.getName()))
				files.add((PsiFile) element);
		return files;
	}

	public Object getData(Collection<AbstractTreeNode> selected, String dataId) {
		if (selected != null)
			if (DefinitionTreeView.DATA_KEY.is(dataId)) {
				List<DefinitionTreeView> result = getDefinitionTreeViews(selected);
				if (!result.isEmpty())
					return result.toArray(new DefinitionTreeView[result.size()]);
			} else if (PlatformDataKeys.DELETE_ELEMENT_PROVIDER.is(dataId))
				for (AbstractTreeNode node \: selected)
					if (node.getValue() instanceof DefinitionTreeView)
						return new MyDeleteProvider(selected);
		return null;
	}

	private List<DefinitionTreeView> getDefinitionTreeViews(Collection<AbstractTreeNode> selected) {
		List<DefinitionTreeView> result = new ArrayList<>();
		for (AbstractTreeNode node \: selected)
			if (node.getValue() instanceof DefinitionTreeView)
				result.add((DefinitionTreeView) node.getValue());
		return result;
	}

	private static class MyDeleteProvider implements DeleteProvider {
		private final PsiElement[] myElements;

		public MyDeleteProvider(final Collection<AbstractTreeNode> selected) {
			myElements = collectDefinitionPsiElements(selected);
		}

		private static PsiElement[] collectDefinitionPsiElements(Collection<AbstractTreeNode> selected) {
			Set<PsiElement> result = new HashSet<>();
			for (AbstractTreeNode node \: selected) {
				if (node.getValue() instanceof DefinitionTreeView) {
					DefinitionTreeView definition = (DefinitionTreeView) node.getValue();
					result.add(definition.getClassToBind());
					ContainerUtil.addAll(result, definition.get::projectProperName::Files());
				} else if (node.getValue() instanceof PsiElement)
					result.add((PsiElement) node.getValue());
			}
			return PsiUtilCore.toPsiElementArray(result);
		}

		public void deleteElement(\@NotNull DataContext dataContext) {
			DeleteHandler.deletePsiElement(myElements, CommonDataKeys.PROJECT.getData(dataContext));
		}

		public boolean canDeleteElement(\@NotNull DataContext dataContext) {
			return DeleteHandler.shouldEnableDeleteAction(myElements);
		}
	}
}
