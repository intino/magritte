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
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.util.Collection;

public class LinkToJava {

	private LinkToJava() {
	}

	public static void link(Module module) {
		Project project = module.getProject();
		PsiDocumentManager.getInstance(project).commitAllDocuments();
		FileDocumentManager.getInstance().saveAllDocuments();
		VirtualFile srcDirectory = getSrcDirectory(TaraUtil.getSourceRoots(module));
		if (srcDirectory == null) {
			Notifications.Bus.notify(new Notification("Tara Generator", "", "Src directory not found", NotificationType.ERROR), project);
			return;
		}
		for (TaraModelImpl taraBoxFile : TaraUtil.getTaraFilesOfModule(module)) {
			generateAddresses(taraBoxFile);
			new IntentionsGenerator(project, taraBoxFile).generate();
			new FacetApplyCodeGenerator(taraBoxFile).generate();
			new IntentionInstancesGenerator(taraBoxFile).generate();
		}
		String report = String.format("Facet & Intention Classes have been Generated Successfully");
		Notifications.Bus.notify(new Notification("Tara Generator", "", report, NotificationType.INFORMATION), project);
		VfsUtil.markDirtyAndRefresh(true, true, true, srcDirectory);
		srcDirectory.refresh(true, true);
	}

	private static void generateAddresses(TaraModel box) {
//		Model model = TaraLanguage.getLanguage(box);
//		if (model == null) return;
		Node[] addressedNodes = new Node[0];//getAddressedConcepts(model, TaraUtil.getAllNodesOfFile(box));
		if (addressedNodes.length == 0) return;
		AddressGenerator addressGenerator = new AddressGenerator(addressedNodes);
		addressGenerator.generate();
	}

//	private static Concept[] getAddressedConcepts(Model model, List<Concept> allConceptsOfFile) {
//		List<Concept> concepts = new ArrayList<>();
//		for (Concept concept : allConceptsOfFile) {
//			Node node = TaraUtil.findNode(concept, model);
//			if (node != null && node.getObject().is(Annotation.ADDRESSED))
//				concepts.add(concept);
//		}
//		return concepts.toArray(new Concept[concepts.size()]);
//	}

	private static VirtualFile getSrcDirectory(Collection<VirtualFile> virtualFiles) {
		for (VirtualFile file : virtualFiles)
			if (file.isDirectory() && "src".equals(file.getName())) return file;
		return null;
	}
}
