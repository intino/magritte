package monet.tara.intellij.project.runner;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.openapi.project.Project;
import monet.tara.intellij.lang.TaraIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class RunConfigurationType implements ConfigurationType {
	private final TaraFactory myConfigurationFactory;

	public RunConfigurationType() {
		myConfigurationFactory = new TaraFactory(this);
	}

	public String getDisplayName() {
		return "Tara";
	}

	public String getConfigurationTypeDescription() {
		return "Tara lang";
	}

	public Icon getIcon() {
		return TaraIcons.getIcon(TaraIcons.ICON_13);
	}

	@NonNls
	@NotNull
	public String getId() {
		return "RunConfiguration";
	}

	public ConfigurationFactory[] getConfigurationFactories() {
		return new ConfigurationFactory[]{myConfigurationFactory};
	}

	public static RunConfigurationType getInstance() {
		return ConfigurationTypeUtil.findConfigurationType(RunConfigurationType.class);
	}

	private static class TaraFactory extends ConfigurationFactory {
		public TaraFactory(ConfigurationType type) {
			super(type);
		}

		public com.intellij.execution.configurations.RunConfiguration createTemplateConfiguration(Project project) {
			return new RunConfiguration("Tara lang", project, this);
		}

	}
}