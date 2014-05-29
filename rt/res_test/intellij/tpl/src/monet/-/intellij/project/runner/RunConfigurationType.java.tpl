package monet.::projectName::.intellij.project.runner;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.openapi.project.Project;
import monet.::projectName::.intellij.lang.::projectProperName::Icons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class RunConfigurationType implements ConfigurationType {
	private final ::projectProperName::Factory myConfigurationFactory;

	public RunConfigurationType() {
		myConfigurationFactory = new ::projectProperName::Factory(this);
	}

	public String getDisplayName() {
		return "::projectProperName::";
	}

	public String getConfigurationTypeDescription() {
		return "::projectProperName:: lang";
	}

	public Icon getIcon() {
		return ::projectProperName::Icons.getIcon(::projectProperName::Icons.ICON_13);
	}

	\@NonNls
	\@NotNull
	public String getId() {
		return "RunConfiguration";
	}

	public ConfigurationFactory[] getConfigurationFactories() {
		return new ConfigurationFactory[]{myConfigurationFactory};
	}

	public static RunConfigurationType getInstance() {
		return ConfigurationTypeUtil.findConfigurationType(RunConfigurationType.class);
	}

	private static class ::projectProperName::Factory extends ConfigurationFactory {
		public ::projectProperName::Factory(ConfigurationType type) {
			super(type);
		}

		public com.intellij.execution.configurations.RunConfiguration createTemplateConfiguration(Project project) {
			return new RunConfiguration("::projectProperName:: lang", project, this);
		}

	}
}