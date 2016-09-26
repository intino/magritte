package org.siani.legio.plugin.project;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.DocumentAdapter;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiFile;
import org.siani.legio.LegioApplication;
import org.siani.legio.SnapshotRepository;
import tara.StashBuilder;
import tara.intellij.project.configuration.Configuration;
import tara.magritte.Predicate;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class LegioConfiguration implements Configuration {

	private static final String CONFIGURATION_LEGIO = "configuration.legio";
	private final Module module;
	private PsiFile legioConf;
	private LegioApplication legio;
	private Thread legioThread;

	public LegioConfiguration(Module module) {
		this.module = module;
	}

	@Override
	public Configuration init() {
		legioConf = new LegioModuleCreator(module).create();
		final Document document = FileDocumentManager.getInstance().getDocument(legioConf.getVirtualFile());
		if (document == null) return this;
		document.addDocumentListener(new DocumentAdapter() {
			@Override
			public void documentChanged(DocumentEvent e) {
				reload();
			}
		});
		return this;
	}

	@Override
	public boolean isSuitable() {
		return new File(new File(module.getModuleFilePath()).getParentFile(), CONFIGURATION_LEGIO).exists();
	}

	@Override
	public void reload() {
		if (legioThread != null) legioThread.interrupt();
		legioThread = new Thread(() -> legio = GraphLoader.loadGraph(module, new StashBuilder(new File(legioConf.getVirtualFile().getPath()), "Legio", module.getName()).build()));
		legioThread.start();
		try {
			legioThread.join();
			legioThread = null;
		} catch (InterruptedException ignored) {
			legioThread = null;
		}
	}

	@Override
	public ModuleType type() {
		return ModuleType.valueOf(legio.project().node().conceptList().stream().map(Predicate::name).findFirst().orElse("Platform"));
	}

	@Override
	public String workingPackage() {
		return null;
	}

	@Override
	public List<String> repository() {
		return legio.project().repositoryList().stream().
			filter(repository -> !repository.is(SnapshotRepository.class)).
			map(repository -> repository.url()).collect(Collectors.toList());
	}

	@Override
	public List<String> snapshotRepository() {
		return legio.project().repositoryList().stream().
			filter(repository -> repository.is(SnapshotRepository.class)).
			map(repository -> repository.url()).collect(Collectors.toList());
	}

	@Override
	public String dsl() {
		return "";
	}

	@Override
	public boolean isImportedDsl() {
		return false;
	}

	@Override
	public String outDSL() {
		return "";
	}

	@Override
	public String dslVersion() {
		return null;
	}

	@Override
	public void dslVersion(String version) {
		reload();
	}

	@Override
	public String modelVersion() {
		return null;
	}

	@Override
	public void modelVersion(String s) {

		reload();
	}

	@Override
	public int refactorId() {
		return 0;
	}

	@Override
	public void refactorId(int i) {

		reload();
	}

	@Override
	public boolean isPersistent() {
		return false;
	}
}
