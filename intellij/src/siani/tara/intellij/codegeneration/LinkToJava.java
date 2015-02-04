package siani.tara.intellij.codegeneration;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraBoxFile;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Annotation;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LinkToJava {

	public static void link(Module module) {
		Project project = module.getProject();
		PsiDocumentManager.getInstance(project).commitAllDocuments();
		FileDocumentManager.getInstance().saveAllDocuments();
		for (TaraBoxFileImpl taraBoxFile : TaraUtil.getTaraFilesOfModule(module)) {
			generateAddresses(taraBoxFile);
			new IntentionsGenerator(project, taraBoxFile).generate();
			new FacetApplyCodeGenerator(taraBoxFile).generate();
			new IntentionInstancesGenerator(taraBoxFile).generate();
		}
		String report = String.format("Facet & Intention Classes have been Generated Successfully");
		Notifications.Bus.notify(new Notification("Tara Generator", "", report, NotificationType.INFORMATION), project);
		VirtualFile srcDirectory = getSrcDirectory(TaraUtil.getSourceRoots(module));
		VfsUtil.markDirtyAndRefresh(true, true, true, srcDirectory);
		srcDirectory.refresh(true, true);
	}

	private static void generateAddresses(TaraBoxFile box) {
		Model model = TaraLanguage.getMetaModel(box);
		if (model == null) return;
		Concept[] addressedConcepts = getAddressedConcepts(model, TaraUtil.getAllConceptsOfFile(box));
		if (addressedConcepts.length == 0) return;
		AddressGenerator addressGenerator = new AddressGenerator(addressedConcepts);
		addressGenerator.generate();
	}

	private static Concept[] getAddressedConcepts(Model model, List<Concept> allConceptsOfFile) {
		List<Concept> concepts = new ArrayList<>();
		for (Concept concept : allConceptsOfFile) {
			Node node = TaraUtil.findNode(concept, model);
			if (node != null && node.getObject().is(Annotation.ADDRESSED))
				concepts.add(concept);
		}
		return concepts.toArray(new Concept[concepts.size()]);
	}

	private static VirtualFile getSrcDirectory(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && "src".equals(file.getName())) return file;
		throw new RuntimeException("src directory not found");
	}
}
