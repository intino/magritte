package siani.tara.intellij.actions;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.codegeneration.AddressGenerator;
import siani.tara.intellij.codegeneration.FacetApplyGenerator;
import siani.tara.intellij.codegeneration.IntentionsGenerator;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Annotation;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenerateAction extends AnAction implements DumbAware {
	@Override
	public void actionPerformed(@NotNull AnActionEvent e) {
		final Project project = getEventProject(e);
		final VirtualFile[] files = LangDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
		if (project == null || files == null) return;
		PsiDocumentManager.getInstance(project).commitAllDocuments();
		FileDocumentManager.getInstance().saveAllDocuments();
		for (VirtualFile file : files) {
			IntentionsGenerator intentionsGenerator = new IntentionsGenerator(project, (TaraBoxFile) PsiManager.getInstance(project).findFile(file));
//			FacetTargetsGenerator targetsGenerator = new FacetTargetsGenerator((TaraBoxFile) PsiManager.getInstance(project).findFile(file));
			FacetApplyGenerator applyGenerator = new FacetApplyGenerator((TaraBoxFile) PsiManager.getInstance(project).findFile(file));
			generateAddresses((TaraBoxFile) PsiManager.getInstance(project).findFile(file));
			intentionsGenerator.generate();
//			targetsGenerator.generate();
			applyGenerator.generate();
		}
		String report = String.format("Facet & Intention Classes have been Generated Sucessfully");
		Notifications.Bus.notify(new Notification("Tara Generator", "", report, NotificationType.INFORMATION), project);
		VirtualFile srcDirectory = getSrcDirectory(TaraUtil.getSourceRoots(e.getData(LangDataKeys.PSI_FILE)));
		VfsUtil.markDirtyAndRefresh(true, true, true, srcDirectory);
		srcDirectory.refresh(true, true);
	}

	private void generateAddresses(TaraBoxFile box) {
		Model model = TaraLanguage.getMetaModel(box);
		if (model == null) return;
		Concept[] addressedConcepts = getAddressedConcepts(model, TaraUtil.getAllConceptsOfFile(box));
		if (addressedConcepts.length == 0) return;
		AddressGenerator addressGenerator = new AddressGenerator(addressedConcepts);
		addressGenerator.generate();
	}

	@Override
	public void update(@NotNull AnActionEvent e) {
		Project project = getEventProject(e);
		VirtualFile[] files = LangDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
		boolean taraFound = false;
		if (project != null && files != null) {
			PsiManager manager = PsiManager.getInstance(project);
			for (VirtualFile virtualFile : files) {
				PsiFile psiFile = manager.findFile(virtualFile);
				taraFound = psiFile instanceof TaraBoxFile;
				if (taraFound) break;
			}
		}
		e.getPresentation().setEnabled(taraFound);
		e.getPresentation().setVisible(taraFound);
	}

	private Concept[] getAddressedConcepts(Model model, List<Concept> allConceptsOfFile) {
		List<Concept> concepts = new ArrayList<>();
		for (Concept concept : allConceptsOfFile) {
			Node node = TaraUtil.findNode(concept, model);
			if (node != null && node.getObject().is(Annotation.ADDRESSED))
				concepts.add(concept);
		}
		return concepts.toArray(new Concept[concepts.size()]);
	}

	private VirtualFile getSrcDirectory(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && "src".equals(file.getName())) return file;
		throw new RuntimeException("src directory not found");
	}
}
