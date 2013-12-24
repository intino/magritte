package monet.tara.project.runner;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import monet.tara.metamodel.TaraIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class TaraRunConfigurationType implements ConfigurationType {
	private final TaraFactory myConfigurationFactory;

	public TaraRunConfigurationType() {
		myConfigurationFactory = new TaraFactory(this);
	}

	public String getDisplayName() {
		return "Tara";
	}

	public String getConfigurationTypeDescription() {
		return "Tara metamodel";
	}

	public Icon getIcon() {
		return TaraIcons.ICON;
	}

	@NonNls
	@NotNull
	public String getId() {
		return "TaraRunConfiguration";
	}

	public ConfigurationFactory[] getConfigurationFactories() {
		return new ConfigurationFactory[]{myConfigurationFactory};
	}

	public static TaraRunConfigurationType getInstance() {
		return ConfigurationTypeUtil.findConfigurationType(TaraRunConfigurationType.class);
	}

	private static class TaraFactory extends ConfigurationFactory {
		public TaraFactory(ConfigurationType type) {
			super(type);
		}

		public RunConfiguration createTemplateConfiguration(Project project) {
			return new TaraRunConfiguration("Tara metamodel", project, this);
		}

	}
}