package org.jetbrains.jps.tara.model.impl;

import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.maven.model.JpsMavenExtensionService;
import org.jetbrains.jps.maven.model.impl.MavenModuleResourceConfiguration;
import org.jetbrains.jps.maven.model.impl.MavenProjectConfiguration;
import org.jetbrains.jps.model.module.JpsModule;

import java.util.Map;

import static tara.compiler.constants.TaraBuildConstants.*;

public class JpsConfigurationLoader {
	private final JpsModule module;
	private final CompileContext context;

	private static String TARA = "tara.";

	JpsConfigurationLoader(JpsModule module, CompileContext context) {
		this.module = module;
		this.context = context;
	}

	JpsModuleConfiguration load() {
		final JpsModuleConfiguration conf = new JpsModuleConfiguration();
		final MavenProjectConfiguration maven = JpsMavenExtensionService.getInstance().getMavenProjectConfiguration(context.getProjectDescriptor().dataManager.getDataPaths());
		if (maven == null) return conf;
		final MavenModuleResourceConfiguration moduleMaven = maven.moduleConfigurations.get(module.getName());
		fillFromMaven(conf, moduleMaven);
		return conf;
	}

	private void fillFromMaven(JpsModuleConfiguration conf, MavenModuleResourceConfiguration pom) {
		if (pom == null) return;
		final Map<String, String> props = pom.properties;
		conf.outDSL = props.getOrDefault(TARA + OUT_DSL, "");
		conf.level = props.getOrDefault(TARA + LEVEL, "");
		conf.dsl = props.getOrDefault(TARA + DSL, "Proteo");
		conf.workingPackage = props.getOrDefault(TARA + WORKING_PACKAGE, props.getOrDefault(TARA + OUT_DSL, ""));
		conf.refactorId = Integer.parseInt(props.getOrDefault(TARA + REFACTOR_ID, "-1"));
		conf.persistent = Boolean.parseBoolean(props.getOrDefault(TARA + PERSISTENT, "false"));
	}
}
