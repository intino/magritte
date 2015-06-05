package siani.tara.intellij.codegeneration;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import siani.tara.Language;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.io.File;
import java.util.Collection;

public class LinkToJava {

	private static void generateAddresses(TaraModel box) {
		Language language = TaraLanguage.getLanguage(box);
		if (language == null) return;
		Node[] addressedNodes = new Node[0];//= getAddressedConcepts(language, TaraUtil.getAllNodesOfFile(box));
		if (addressedNodes.length == 0) return;
		Mbroller addressGenerator = new Mbroller(addressedNodes);
		addressGenerator.generate();
	}

	private static VirtualFile getSrcDirectory(Module module) {
		Collection<VirtualFile> sourceRoots = TaraUtil.getSourceRoots(module);
		for (VirtualFile file : sourceRoots)
			if (file.isDirectory() && "src".equals(file.getName())) return file;
		if (sourceRoots.size() > 0) {
			VirtualFile parent = sourceRoots.iterator().next().getParent();
			File src = new File(parent.getPath(), "src");
			src.mkdir();
			return LocalFileSystem.getInstance().refreshAndFindFileByPath(FileUtil.toSystemIndependentName(src.getPath()));
		}
		return null;
	}
//TODO
//	private static Concept[] getAddressedConcepts(Model model, List<Concept> allConceptsOfFile) {
//		List<Concept> concepts = new ArrayList<>();
//		for (Concept concept : allConceptsOfFile) {
//			Node node = TaraUtil.findNode(concept, model);
//			if (node != null && node.getObject().is(Annotation.ADDRESSED))
//				concepts.add(concept);
//		}
//		return concepts.toArray(new Concept[concepts.size()]);
//	}

	public void link(Module module) {
		Project project = module.getProject();
		PsiDocumentManager.getInstance(project).commitAllDocuments();
		FileDocumentManager.getInstance().saveAllDocuments();
		VirtualFile srcDirectory = getSrcDirectory(module);
		if (srcDirectory == null) {
			Notifications.Bus.notify(new Notification("Tara Generator", "", "Src directory not found", NotificationType.ERROR), project);
			return;
		}
		for (TaraModelImpl taraBoxFile : TaraUtil.getTaraFilesOfModule(module)) {
			generateAddresses(taraBoxFile);
			new NativesGenerator(project, taraBoxFile).generate();
		}
		String report = "Native Classes have been Generated Successfully";
		final Notification notification = new Notification("Tara Generator", "", report, NotificationType.INFORMATION);
		Notifications.Bus.notify(notification, project);
		VfsUtil.markDirtyAndRefresh(true, true, true, srcDirectory);
		srcDirectory.refresh(true, true);
	}
}
