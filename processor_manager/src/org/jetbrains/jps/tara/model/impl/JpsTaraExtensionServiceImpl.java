package org.jetbrains.jps.tara.model.impl;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.JDOMUtil;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.containers.ConcurrentFactoryMap;
import com.intellij.util.containers.FactoryMap;
import com.intellij.util.xmlb.XmlSerializer;
import gnu.trove.THashMap;
import org.jdom.Document;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.storage.BuildDataPaths;
import org.jetbrains.jps.incremental.resources.ResourcesBuilder;
import org.jetbrains.jps.incremental.resources.StandardResourceBuilderEnabler;
import org.jetbrains.jps.model.JpsElementChildRole;
import org.jetbrains.jps.model.JpsElementFactory;
import org.jetbrains.jps.model.JpsSimpleElement;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;
import org.jetbrains.jps.model.module.JpsDependencyElement;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.tara.model.JpsTaraExtensionService;
import org.jetbrains.jps.tara.model.JpsTaraModuleExtension;

import java.io.File;
import java.util.Map;

public class JpsTaraExtensionServiceImpl extends JpsTaraExtensionService {
	private static final Logger LOG = Logger.getInstance("#org.jetbrains.jps.tara.model.impl.JpsTaraExtensionServiceImpl");
	private static final JpsElementChildRole<JpsSimpleElement<Boolean>> PRODUCTION_ON_TEST_ROLE = JpsElementChildRoleBase.create("production on test");
	private final Map<File, TaraProjectConfiguration> myLoadedConfigs = new THashMap<>(FileUtil.FILE_HASHING_STRATEGY);
	private final FactoryMap<File, Boolean> myConfigFileExists = new ConcurrentFactoryMap<File, Boolean>() {
		@Nullable
		@Override
		protected Boolean create(File key) {
			return key.exists();
		}
	};

	public JpsTaraExtensionServiceImpl() {
		ResourcesBuilder.registerEnabler(new StandardResourceBuilderEnabler() {
			@Override
			public boolean isResourceProcessingEnabled(JpsModule module) {
				return getExtension(module) == null;
			}
		});
	}

	@Nullable
	@Override
	public JpsTaraModuleExtension getExtension(@NotNull JpsModule module) {
		return module.getContainer().getChild(JpsTaraModuleExtensionImpl.ROLE);
	}

	@NotNull
	@Override
	public JpsTaraModuleExtension getOrCreateExtension(@NotNull JpsModule module) {
		JpsTaraModuleExtension extension = module.getContainer().getChild(JpsTaraModuleExtensionImpl.ROLE);
		if (extension == null) {
			extension = new JpsTaraModuleExtensionImpl();
			module.getContainer().setChild(JpsTaraModuleExtensionImpl.ROLE, extension);
		}
		return extension;
	}

	@Override
	public void setProductionOnTestDependency(@NotNull JpsDependencyElement dependency, boolean value) {
		if (value)
			dependency.getContainer().setChild(PRODUCTION_ON_TEST_ROLE, JpsElementFactory.getInstance().createSimpleElement(true));
		else dependency.getContainer().removeChild(PRODUCTION_ON_TEST_ROLE);
	}

	@Override
	public boolean isProductionOnTestDependency(@NotNull JpsDependencyElement dependency) {
		JpsSimpleElement<Boolean> child = dependency.getContainer().getChild(PRODUCTION_ON_TEST_ROLE);
		return child != null && child.getData();
	}

	@Override
	public boolean hasTaraProjectConfiguration(@NotNull BuildDataPaths paths) {
		return myConfigFileExists.get(new File(paths.getDataStorageRoot(), TaraProjectConfiguration.CONFIGURATION_FILE_RELATIVE_PATH));
	}

	@NotNull
	@Override
	public TaraProjectConfiguration getTaraProjectConfiguration(BuildDataPaths paths) {
		final File dataStorageRoot = paths.getDataStorageRoot();
		return getTaraProjectConfiguration(dataStorageRoot);
	}

	@NotNull
	public TaraProjectConfiguration getTaraProjectConfiguration(@NotNull File dataStorageRoot) {
		final File configFile = new File(dataStorageRoot, TaraProjectConfiguration.CONFIGURATION_FILE_RELATIVE_PATH);
		TaraProjectConfiguration config;
		synchronized (myLoadedConfigs) {
			config = myLoadedConfigs.get(configFile);
			if (config == null) {
				config = new TaraProjectConfiguration();
				try {
					final Document document = JDOMUtil.loadDocument(configFile);
					XmlSerializer.deserializeInto(config, document.getRootElement());
				} catch (Exception e) {
					LOG.info(e);
				}
				myLoadedConfigs.put(configFile, config);
			}
		}
		return config;
	}
}

