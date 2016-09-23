package legio.plugin.project;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.DocumentAdapter;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;
import legio.LegioApplication;
import legio.Repository;
import tara.StashBuilder;
import tara.intellij.project.configuration.Configuration;
import tara.magritte.Predicate;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class LegioConfiguration implements Configuration {

	private static final String CONFIGURATION_LEGIO = "configuration.legio";
	private final Module module;
	private VirtualFile legioConf;
	private LegioApplication legio;
	private Thread legioThread;

	public LegioConfiguration(Module module) {
		this.module = module;
	}

	@Override
	public Configuration init() {
		legioConf = new LegioModuleCreator(module).create();
		final Document document = FileDocumentManager.getInstance().getDocument(legioConf);
		if (document == null) return this;
		document.addDocumentListener(new DocumentAdapter() {
			@Override
			public void documentChanged(DocumentEvent e) {
				updateLegioConfiguration();
			}
		});
		return this;
	}

	@Override
	public boolean isSuitable() {
		return new File(new File(module.getModuleFilePath()).getParentFile(), CONFIGURATION_LEGIO).exists();
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
	public List<String> supportedLanguages() {
		return Collections.singletonList("tara");
	}

	@Override
	public String repository() {
		final List<Repository> repositories = legio.project().repositoryList();
		return repositories.isEmpty() ? "" : repositories.get(0).url();
	}

	@Override
	public String dsl(ModuleType moduleType) {
		return "";//legio.project().asLevel().dSL().name();
	}

	@Override
	public String outDSL(String s) {
		return null;
	}

	@Override
	public String outDSL(ModuleType moduleType) {
		return null;
	}

	@Override
	public String dslVersion(String s) {
		return null;
	}

	@Override
	public void dslVersion(String s, String s1) {

	}

	@Override
	public String modelVersion() {
		return null;
	}

	@Override
	public void modelVersion(String s) {

	}

	@Override
	public boolean isApplicationImportedDsl() {
		return false;
	}

	@Override
	public boolean isSystemImportedDsl() {
		return false;
	}

	@Override
	public int platformRefactorId() {
		return 0;
	}

	@Override
	public boolean isPersistent() {
		return false;
	}

	@Override
	public void platformRefactorId(int i) {

	}

	@Override
	public int applicationRefactorId() {
		return 0;
	}

	@Override
	public void applicationRefactorId(int i) {

	}

	private void updateLegioConfiguration() {
		if (legioThread != null) legioThread.interrupt();
		legioThread = new Thread(() -> legio = GraphLoader.loadGraph(module, new StashBuilder(new File(legioConf.getPath()), "Legio", module.getName()).build()));
		legioThread.start();
		try {
			legioThread.join();
			legioThread = null;
		} catch (InterruptedException ignored) {
			legioThread = null;
		}
	}
}
