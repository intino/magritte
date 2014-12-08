package siani.tara.intellij.refactoring.move;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.TextComponentAccessor;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.refactoring.ui.RefactoringDialog;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.psi.Concept;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public class TaraMoveConceptDialog extends RefactoringDialog {
	private TaraMoveConceptPanel myPanel;

	public TaraMoveConceptDialog(@NotNull Project project, @NotNull PsiNamedElement[] elements, @Nullable String destination) {
		super(project, true);
		assert elements.length > 0;
		String moveText = "";

		if (elements.length == 1) {
			PsiNamedElement e = elements[0];
			if (e instanceof Concept) moveText = MessageProvider.message("refactoring.move.concept", e.getName());
		}
		if (destination == null) destination = getContainingFileName(elements[0]);

		myPanel = new TaraMoveConceptPanel(moveText, destination);
		setTitle(MessageProvider.message("refactoring.move.concept.dialog.title"));

		final FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor();
		descriptor.setRoots(ProjectRootManager.getInstance(project).getContentRoots());
		descriptor.withTreeRootVisible(true);

		myPanel.getBrowseTargetFileButton().addBrowseFolderListener(MessageProvider.message("refactoring.move.concept.choose.destination.file.title"),
			null, project, descriptor,
			TextComponentAccessor.TEXT_FIELD_WHOLE_TEXT);
		init();
	}

	private static String getContainingFileName(PsiElement element) {
		VirtualFile file = element.getContainingFile().getVirtualFile();
		if (file != null) {
			return FileUtil.toSystemDependentName(file.getPath());
		} else {
			return "";
		}
	}

	@Override
	protected JComponent createCenterPanel() {
		return myPanel;
	}

	@Override
	protected void doAction() {
		close(OK_EXIT_CODE);
	}

	@Override
	protected String getHelpId() {
		return "python.reference.moveClass";
	}

	@Override
	public JComponent getPreferredFocusedComponent() {
		return myPanel.getBrowseTargetFileButton().getTextField();
	}

	public String getTargetPath() {
		return myPanel.getBrowseTargetFileButton().getText();
	}
}
