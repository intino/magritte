package monet.tara.intellij.compiler;

import com.intellij.openapi.compiler.options.ExcludedEntriesConfigurable;
import com.intellij.openapi.compiler.options.ExcludedEntriesConfiguration;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaModuleSourceRootTypes;

import javax.swing.*;
import java.util.List;

public class TaraCompilerConfigurable implements SearchableConfigurable, Configurable.NoScroll {
	private JTextField myHeapSize;
	private JPanel myMainPanel;
	private JPanel myExcludesPanel;
	private JBCheckBox myInvokeDynamicSupportCB;

	private final ExcludedEntriesConfigurable myExcludes;
	private final TaraCompilerConfiguration myConfig;

	public TaraCompilerConfigurable(Project project) {
		myConfig = TaraCompilerConfiguration.getInstance(project);
		myExcludes = createExcludedConfigurable(project);
	}

	public ExcludedEntriesConfigurable getExcludes() {
		return myExcludes;
	}

	private ExcludedEntriesConfigurable createExcludedConfigurable(final Project project) {
		final ExcludedEntriesConfiguration configuration = myConfig.getExcludeFromStubGeneration();
		final ProjectFileIndex index = ProjectRootManager.getInstance(project).getFileIndex();
		final FileChooserDescriptor descriptor = new FileChooserDescriptor(true, true, false, false, false, true) {
			public boolean isFileVisible(VirtualFile file, boolean showHiddenFiles) {
				return super.isFileVisible(file, showHiddenFiles) && !index.isIgnored(file);
			}
		};
		descriptor.setRoots(ContainerUtil.concat(
				ContainerUtil.map(ModuleManager.getInstance(project).getModules(), new Function<Module, List<VirtualFile>>() {
					@Override
					public List<VirtualFile> fun(final Module module) {
						return ModuleRootManager.getInstance(module).getSourceRoots(JavaModuleSourceRootTypes.SOURCES);
					}
				})));
		return new ExcludedEntriesConfigurable(project, descriptor, configuration);
	}


	@NotNull
	public String getId() {
		return "Tara compiler";
	}

	public Runnable enableSearch(String option) {
		return null;
	}

	@Nls
	public String getDisplayName() {
		return "Tara Compiler";
	}

	public String getHelpTopic() {
		return "reference.projectsettings.compiler.tara";
	}

	public JComponent createComponent() {
		myExcludesPanel.add(myExcludes.createComponent());
		return myMainPanel;
	}

	public boolean isModified() {
		return !Comparing.equal(myConfig.getHeapSize(), myHeapSize.getText()) ||
				myInvokeDynamicSupportCB.isSelected() != myConfig.isInvokeDynamic() ||
				myExcludes.isModified();
	}

	public void apply() throws ConfigurationException {
		myExcludes.apply();
		myConfig.setHeapSize(myHeapSize.getText());
		myConfig.setInvokeDynamic(myInvokeDynamicSupportCB.isSelected());
	}

	public void reset() {
		myHeapSize.setText(myConfig.getHeapSize());
		myInvokeDynamicSupportCB.setSelected(myConfig.isInvokeDynamic());
		myExcludes.reset();
	}

	public void disposeUIResources() {
		myExcludes.disposeUIResources();
	}
}
