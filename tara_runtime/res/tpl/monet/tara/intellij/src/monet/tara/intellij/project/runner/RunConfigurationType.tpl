package monet.::projectName::.intellij.project.runner;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import monet.::projectName::.intellij.metamodel.::projectProperName::Icons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ::projectProperName::RunConfigurationType implements ConfigurationType {
	private final ::projectProperName::Factory myConfigurationFactory;

	public ::projectProperName::RunConfigurationType() {
		myConfigurationFactory = new ::projectProperName::Factory(this);
	}

	public String getDisplayName() {
		return "::projectProperName::";
	}

	public String getConfigurationTypeDescription() {
		return "::projectProperName:: metamodel";
	}

	public Icon getIcon() {
		return ::projectProperName::Icons.ICON_13;
	}

	@NonNls
	@NotNull
	public String getId() {
		return "::projectProperName::RunConfiguration";
	}

	public ConfigurationFactory[] getConfigurationFactories() {
		return new ConfigurationFactory[]{myConfigurationFactory};
	}

	public static ::projectProperName::RunConfigurationType getInstance() {
		return ConfigurationTypeUtil.findConfigurationType(::projectProperName::RunConfigurationType.class);
	}

	private static class ::projectProperName::Factory extends ConfigurationFactory {
		public ::projectProperName::Factory(ConfigurationType type) {
			super(type);
		}

		public RunConfiguration createTemplateConfiguration(Project project) {
			return new ::projectProperName::RunConfiguration("::projectProperName:: metamodel", project, this);
		}

	}
}