package monet.::projectName::.intellij.project.view;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.BasePsiNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.navigation.NavigationItemFileStatus;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import monet.::projectName::.intellij.metamodel.::projectProperName::Icons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collection;

public class DefinitionNode extends ProjectViewNode<DefinitionTreeView> {
	private final Collection<BasePsiNode<? extends PsiElement>> myChildren;

	public DefinitionNode(Project project,
	                   DefinitionTreeView value,
	                   ViewSettings viewSettings,
	                   Icon icon,
	                   Collection<BasePsiNode<? extends PsiElement>> children) {
		super(project, value, viewSettings);
		setIcon(icon);
		myChildren = children;
	}

	\@NotNull
	public Collection<BasePsiNode<? extends PsiElement>> getChildren() {
		return myChildren;
	}

	public boolean contains(\@NotNull VirtualFile file) {
		for (final AbstractTreeNode aMyChildren \: myChildren)
			if (((ProjectViewNode) aMyChildren).contains(file)) return true;
		return false;
	}

	public void update(PresentationData presentation) {
		if (getValue() == null || !getValue().isValid())
			setValue(null);
		else {
			presentation.setPresentableText(getValue().getName());
			presentation.setIcon(::projectProperName::Icons.ICON_13);
		}
	}

	public void navigate(final boolean requestFocus) {
		getValue().navigate(requestFocus);
	}

	public boolean canNavigate() {
		final DefinitionTreeView value = getValue();
		return value != null && value.canNavigate();
	}

	public boolean canNavigateToSource() {
		final DefinitionTreeView value = getValue();
		return value != null && value.canNavigateToSource();
	}

	\@Override
	public FileStatus getFileStatus() {
		for (BasePsiNode<? extends PsiElement> child \: myChildren) {
			final PsiElement value = child.getValue();
			if (value == null || !value.isValid()) continue;
			final FileStatus fileStatus = NavigationItemFileStatus.get(child);
			if (fileStatus != FileStatus.NOT_CHANGED)
				return fileStatus;
		}
		return FileStatus.NOT_CHANGED;
	}

	\@Override
	public boolean canHaveChildrenMatching(final Condition<PsiFile> condition) {
		for (BasePsiNode<? extends PsiElement> child \: myChildren) {
			if (condition.value(child.getValue().getContainingFile())) {
				return true;
			}
		}
		return false;
	}
}