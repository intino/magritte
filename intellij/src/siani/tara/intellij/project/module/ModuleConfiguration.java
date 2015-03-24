package siani.tara.intellij.project.module;

import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleComponent;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.Locale;


public class ModuleConfiguration implements ModuleComponent, JDOMExternalizable {

	private static final Logger LOG = Logger.getInstance(ModuleConfiguration.class.getName());
	private Module module;
	Configuration configuration;

	public ModuleConfiguration(Module module) {
		if (TaraModuleType.isOfType(module)) {
			this.module = module;
			configuration = new Configuration();
		}
	}

	@Nullable
	public static ModuleConfiguration getInstance(Module module) {
		if (module != null && TaraModuleType.isOfType(module))
			return module.getComponent(ModuleConfiguration.class);
		return null;
	}

	@State(
		name = "TaraConfiguration",
		storages = {
			@Storage(id = "default", file = StoragePathMacros.PROJECT_FILE),
			@Storage(id = "dir", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/tara.xml", scheme = StorageScheme.DIRECTORY_BASED)
		}
	)

	public void initComponent() {
	}

	public void disposeComponent() {
	}

	@NotNull
	public String getComponentName() {
		return "ModuleConfiguration";
	}

	public void projectOpened() {
	}

	public void projectClosed() {
	}

	public void moduleAdded() {
		if (module != null && TaraModuleType.isOfType(module)) loadConfiguration();
	}

	private void loadConfiguration() {
		File parent = new File(module.getModuleFilePath()).getParentFile();
		File file = new File(parent + File.separator + ".config", "tara.conf");
		if (file.exists())
			try {
				String aux;
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				configuration.dslName = "null".equals(aux = br.readLine()) ? "" : aux;
				configuration.dictionary = "null".equals(aux = br.readLine()) ? "" : aux;
				configuration.generatedDslName = "null".equals(aux = br.readLine()) ? "" : aux;
				configuration.terminal = Boolean.parseBoolean(br.readLine());
				br.close();
				file.delete();
				file.getParentFile().delete();
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
	}

	@Override
	public void readExternal(Element element) throws InvalidDataException {
		if (configuration != null)
			configuration.readExternal(element);
	}

	@Override
	public void writeExternal(Element element) throws WriteExternalException {
		if (configuration != null)
			configuration.writeExternal(element);
	}

	public String getMetamodelName() {
		return configuration.getDslName();
	}

	public Locale getLanguage() {
		if ("English".equals(configuration.getDictionary())) return Locale.ENGLISH;
		return new Locale("Spanish", "Spain", "es_ES");
	}

	public void setLanguage(String language) {
		configuration.dictionary = language;
	}

	public void setMetamodelName(String parent) {
		configuration.dslName = parent;
	}

	public String getGeneratedModelName() {
		return configuration.getGeneratedDslName();
	}

	public void setGeneratedModelName(String generatedModelName) {
		configuration.generatedDslName = generatedModelName;
	}

	public boolean isTerminal() {
		return configuration.isTerminal();
	}

	public void setTerminal(boolean terminal) {
		configuration.terminal = terminal;
	}

	public void loadState(ModuleConfiguration state) {
		XmlSerializerUtil.copyBean(state, this);
	}

	public ModuleConfiguration getState() {
		return this;
	}

	class Configuration implements JDOMExternalizable {
		public String dslName = "";
		public String generatedDslName = "";
		public String dictionary = "";
		public boolean terminal;

		@Override
		public void readExternal(Element element) throws InvalidDataException {
			DefaultJDOMExternalizer.readExternal(this, element);
		}

		@Override
		public void writeExternal(Element element) throws WriteExternalException {
			DefaultJDOMExternalizer.writeExternal(this, element);
		}

		public String getDslName() {
			return dslName;
		}

		public String getDictionary() {
			return dictionary;
		}

		public String getGeneratedDslName() {
			return generatedDslName;
		}

		public boolean isTerminal() {
			return terminal || generatedDslName.isEmpty();
		}
	}
}
