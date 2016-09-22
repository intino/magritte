package legio.plugin.project;

import com.intellij.openapi.module.Module;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.project.configuration.Configuration;
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.Variable;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LegioConfiguration implements Configuration {

	private static final String CONFIGURATION_LEGIO = "configuration.legio";
	private final Module module;
	private TaraModel legioModel;
	private Node project;

	public LegioConfiguration(Module module) {
		this.module = module;
	}

	@Override
	public Configuration init() {
		legioModel = new LegioModuleCreator(module).create();
		project = legioModel.components().get(0);
		return this;
	}

	@Override
	public boolean isSuitable() {
		return new File(new File(module.getModuleFilePath()).getParentFile(), CONFIGURATION_LEGIO).exists();
	}

	@Override
	public ModuleType type() {
		final List<Facet> facets = project.facets();
		for (Facet facet : facets) {
			if (facet.type().equals(ModuleType.Platform.name())) return ModuleType.Platform;
			else if (facet.type().equals(ModuleType.Application.name())) return ModuleType.Application;
			else if (facet.type().equals(ModuleType.System.name())) return ModuleType.System;
		}
		return null;
	}

	@Override
	public List<String> supportedLanguages() {
		for (Variable o : project.variables())
			if (o.name().equals("supportedLanguages")) return o.values().stream().map(Object::toString).collect(Collectors.toList());
		return Collections.singletonList("tara");
	}


	@Override
	public String repository() {
		for (Node node : project.components())
			if (node.type().equals("repository") && !node.variables().isEmpty()) return node.variables().get(0).values().get(0).toString();
		return null;
	}

	@Override
	public String dsl(ModuleType moduleType) {
		for (Node node : project.components())
			if (node.type().equals("dsl")) return node.name();
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
