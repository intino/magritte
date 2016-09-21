package org.jetbrains.jps.tara.model.impl;

import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.maven.model.JpsMavenExtensionService;
import org.jetbrains.jps.maven.model.impl.MavenModuleResourceConfiguration;
import org.jetbrains.jps.maven.model.impl.MavenProjectConfiguration;
import org.jetbrains.jps.model.module.JpsModule;

import java.util.Arrays;
import java.util.Map;

public class JpsConfigurationLoader {
	private final JpsModule module;
	private final CompileContext context;

	private static final String MODULE_TYPE = "tara.moduletype";
	private static final String PLATFORM_OUT_DSL = "tara.application.dsl";
	private static final String APPLICATION_DSL = "tara.application.dsl";
	private static final String APPLICATION_OUT_DSL = "tara.system.dsl";
	private static final String SYSTEM_DSL = "tara.system.dsl";
	private static final String SUPPORTED_LANGUAGES = "tara.supported.languages";
	private static final String WORKING_PACKAGE = "tara.working.package";
	private static final String PLATFORM_REFACTOR = "tara.platform.refactor.id";
	private static final String APPLICATION_REFACTOR = "tara.platform.refactor.id";
	private static final String PERSISTENT = "tara.platform.refactor.id";

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
		final Map<String, String> props = pom.properties;
		conf.type = props.get(MODULE_TYPE);
		conf.platformOutDsl = props.getOrDefault(PLATFORM_OUT_DSL, "");
		conf.applicationDsl = props.getOrDefault(APPLICATION_DSL, "");
		conf.applicationOutDsl = props.getOrDefault(APPLICATION_OUT_DSL, "");
		conf.systemDsl = props.getOrDefault(SYSTEM_DSL, "");
		conf.supportedLanguages = Arrays.asList(props.getOrDefault(SUPPORTED_LANGUAGES, "").split(" "));
		conf.workingDirectory = props.getOrDefault(WORKING_PACKAGE, "");
		conf.platformRefactorId = Integer.parseInt(props.getOrDefault(PLATFORM_REFACTOR, "-1"));
		conf.applicationRefactorId = Integer.parseInt(props.getOrDefault(APPLICATION_REFACTOR, "-1"));
		conf.persistent = Boolean.parseBoolean(props.getOrDefault(PERSISTENT, "false"));
	}
}
