package legio.plugin.project;

import com.intellij.openapi.module.Module;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.project.configuration.Configuration;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class LegioConfiguration implements Configuration {

	private static final String CONFIGURATION_LEGIO = "configuration.legio";
	private final Module module;
	private TaraModel legioModel;

	public LegioConfiguration(Module module) {
		this.module = module;
	}

	@Override
	public Configuration init() {
		legioModel = new LegioModuleCreator(module).create();
//		GraphLoader.loadGraph(module, legioModel);
		return this;
	}

	@Override
	public boolean isSuitable() {
		return new File(new File(module.getModuleFilePath()).getParentFile(), CONFIGURATION_LEGIO).exists();
	}

	@Override
	public ModuleType type() {
		return null;
	}

	@Override
	public List<String> supportedLanguages() {
		return Collections.emptyList();
	}


	@Override
	public String repository() {
		return null;
	}

	@Override
	public String dsl(ModuleType moduleType) {
		return null;
	}

	@Override
	public String platformDsl() {
		return null;
	}

	@Override
	public String applicationDsl() {
		return null;
	}

	@Override
	public String systemDsl() {
		return null;
	}

	@Override
	public String outDsl(ModuleType moduleType) {
		return null;
	}

	@Override
	public String platformOutDsl() {
		return null;
	}

	@Override
	public String applicationOutDsl() {
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
}
