package siani.tara.intellij.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import siani.tara.intellij.codegeneration.AddressGenerator;
import siani.tara.intellij.codegeneration.FacetsGenerator;
import siani.tara.intellij.codegeneration.IntentionsGenerator;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Annotations;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenerateAction extends AnAction implements DumbAware {
	@Override
	public void actionPerformed(AnActionEvent e) {
		final Project project = getEventProject(e);
		final VirtualFile[] files = LangDataKeys.VIRTUAL_FILE_ARRAY.getData(e.getDataContext());
		if (project == null || files == null) return;
		PsiDocumentManager.getInstance(project).commitAllDocuments();
		FileDocumentManager.getInstance().saveAllDocuments();
		for (VirtualFile file : files) {
			IntentionsGenerator generator = new IntentionsGenerator(project, (TaraBoxFile) PsiManager.getInstance(project).findFile(file));
			FacetsGenerator facetsGenerator = new FacetsGenerator(project, (TaraBoxFile) PsiManager.getInstance(project).findFile(file));
			generateAddresses((TaraBoxFile) PsiManager.getInstance(project).findFile(file));
			for (int i = 0; i < 4; i++) {
				generator.generate();
				facetsGenerator.generate();
			}
		}
	}

	private void generateAddresses(TaraBoxFile box) {
		Model model = TaraLanguage.getMetaModel(box);
		AddressGenerator addressGenerator = new AddressGenerator(getAddressedConcepts(model, TaraUtil.getAllConceptsOfFile(box)));
		addressGenerator.generate();
	}

	@Override
	public void update(AnActionEvent e) {
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

	private Collection<Concept> getAddressedConcepts(Model model, List<Concept> allConceptsOfFile) {
		List<Concept> concepts = new ArrayList<>();
		for (Concept concept : allConceptsOfFile) {
			Node node = model.searchNode(TaraUtil.getMetaQualifiedName(concept));
			if (node != null && node.getObject().is(Annotations.Annotation.ADDRESSED))
				concepts.add(concept);
		}
		return concepts;
	}


}
