package org.jetbrains.jps.tara.model.impl;

import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.maven.model.JpsMavenExtensionService;
import org.jetbrains.jps.maven.model.impl.MavenModuleResourceConfiguration;
import org.jetbrains.jps.maven.model.impl.MavenProjectConfiguration;
import org.jetbrains.jps.model.module.JpsModule;

import java.util.Map;

import static io.intino.tara.compiler.shared.TaraBuildConstants.*;

class JpsConfigurationLoader {
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
		conf.groupID = pom.id.groupId;
		conf.artifactID = pom.id.artifactId;
		conf.version = pom.id.version;
		conf.level = props.getOrDefault(TARA + LEVEL, "");
		conf.dsl = props.getOrDefault(TARA + DSL, "") + ":" + props.getOrDefault(TARA + DSL_VERSION, "");
		conf.dslVersion = props.getOrDefault(TARA + DSL_VERSION, "");
		conf.outDSL = props.getOrDefault(TARA + OUT_DSL, "");
		conf.workingPackage = props.getOrDefault(TARA + WORKING_PACKAGE, props.getOrDefault(TARA + OUT_DSL, ""));
	}
}
