package siani.tara.intellij.project.view;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.BasePsiNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraIcons;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;

public class TreeNode extends ProjectViewNode<NodeView> {

	private final BasePsiNode<? extends PsiElement> child;

	public TreeNode(Project project,
	                NodeView value,
	                ViewSettings viewSettings,
	                Icon icon,
	                BasePsiNode<? extends PsiElement> child) {
		super(project, value, viewSettings);
		this.child = child;
		setIcon(icon);
	}

	@NotNull
	public Collection<BasePsiNode<? extends PsiElement>> getChildren() {
		return Collections.singleton(child);
	}

	public boolean contains(@NotNull VirtualFile file) {
		return false;
	}

	public void update(PresentationData presentation) {
		if (getValue() == null || !getValue().isValid())
			setValue(null);
		else {
			presentation.setPresentableText(getValue().getName());
			presentation.setIcon(TaraIcons.MODEL);
		}
	}

	public void navigate(final boolean requestFocus) {
		getValue().navigate(requestFocus);
	}

	public boolean canNavigate() {
		final NodeView value = getValue();
		return value != null && value.canNavigate();
	}

	public boolean canNavigateToSource() {
		final NodeView value = getValue();
		return value != null && value.canNavigateToSource();
	}

	@Override
	public FileStatus getFileStatus() {
		return FileStatus.NOT_CHANGED;
	}

	@Override
	public boolean canHaveChildrenMatching(final Condition<PsiFile> condition) {
		return false;
	}
}