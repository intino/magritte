package siani.tara.intellij.project.module;

import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleComponent;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

import java.io.*;


public class ModuleConfiguration implements ModuleComponent, JDOMExternalizable {

	private final Module module;
	Configuration configuration;

	public ModuleConfiguration(Module module) {
		this.module = module;
		configuration = new Configuration();
	}

	public static ModuleConfiguration getInstance(Module module) {
		return module.getComponent(ModuleConfiguration.class);
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

		// called when project is opened
	}

	public void projectClosed() {
		// called when project is being closed
	}

	public void moduleAdded() {
		loadConfiguration();
	}

	private void loadConfiguration() {
		File parent = new File(module.getModuleFilePath()).getParentFile();
		File file = new File(parent + File.separator + ".config", "tara.conf");
		if (file.exists())
			try {
				String aux;
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				configuration.metamodelName = ((aux = br.readLine()).equals("null")) ? "" : aux;
				configuration.metamodelFilePath = ((aux = br.readLine()).equals("null")) ? "" : aux;
				configuration.terminal = Boolean.parseBoolean(br.readLine());
				file.delete();
				file.getParentFile().delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void readExternal(Element element) throws InvalidDataException {
		configuration.readExternal(element);
	}

	@Override
	public void writeExternal(Element element) throws WriteExternalException {
		configuration.writeExternal(element);
	}

	public String getMetamodelName() {
		return configuration.getMetamodelName();
	}

	public void setMetamodelName(String parent) {
		configuration.metamodelName = parent;
	}

	public boolean isTerminal() {
		return configuration.isTerminal();
	}

	public void setTerminal(boolean system) {
		configuration.terminal = system;
	}

	public String getMetamodelFilePath() {
		return configuration.getMetamodelFilePath();
	}

	public void setMetamodelFilePath(String path) {
		configuration.metamodelFilePath = path;
	}

	public void loadState(ModuleConfiguration state) {
		XmlSerializerUtil.copyBean(state, this);
	}

	public ModuleConfiguration getState() {
		return this;
	}

	class Configuration implements JDOMExternalizable {
		public String metamodelName = "";
		public String metamodelFilePath = "";
		public boolean terminal = false;

		@Override
		public void readExternal(Element element) throws InvalidDataException {
			DefaultJDOMExternalizer.readExternal(this, element);
		}

		@Override
		public void writeExternal(Element element) throws WriteExternalException {
			DefaultJDOMExternalizer.writeExternal(this, element);
		}

		public String getMetamodelName() {
			return metamodelName;
		}

		public String getMetamodelFilePath() {
			return metamodelFilePath;
		}

		public boolean isTerminal() {
			return terminal;
		}
	}
}
