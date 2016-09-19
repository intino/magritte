package tara.intellij.project.facet;

import com.intellij.facet.impl.ui.FacetErrorPanel;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorValidator;
import com.intellij.facet.ui.ValidationResult;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.JavaSdkVersion;
import com.intellij.openapi.projectRoots.Sdk;
import org.jetbrains.annotations.NotNull;
import tara.intellij.framework.ArtifactoryConnector;
import tara.intellij.framework.LanguageInfo;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.maven.MavenHelper;
import tara.intellij.settings.TaraSettings;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.intellij.facet.ui.ValidationResult.OK;
import static tara.dsl.ProteoConstants.PROTEO;
import static tara.intellij.messages.MessageProvider.message;
import static tara.intellij.project.facet.TaraFacetConfiguration.ModuleType.*;
import static tara.intellij.project.facet.TaraFacetConfiguration.ModuleType.System;

class FacetEditorUICreator {
	private static final Logger LOG = Logger.getInstance(FacetEditorUICreator.class.getName());

	private final TaraFacetEditor editor;
	private final TaraFacetConfiguration conf;
	private final FacetEditorContext context;
	private Module[] candidates;
	private List<String> versions = new ArrayList<>();

	FacetEditorUICreator(TaraFacetEditor editor, FacetEditorContext context) {
		this.editor = editor;
		this.context = context;
		this.conf = TaraUtil.getFacetConfiguration(context.getModule());
		this.candidates = getParentModulesCandidates(this.context.getProject());
		editor.moduleInfo = collectModulesInfo();
	}

	void createUI() {
		createDslBox();
		addOutDsls();
		selectType(conf.type());
		updateVisibility();
		updateValues();
		versions();
		initVersionBox();
		initUpdateButton();
		initErrorValidation();
		testBox();
	}

	boolean isModified() {
		return !conf.dslVersion(context.getModule(), editor.inputDsl.getSelectedItem().toString()).equals(editor.versionBox.getSelectedItem().toString()) ||
			editor.applicationDsl.isEnabled() && !editor.applicationDsl.getText().equals(conf.platformOutDsl()) ||
			editor.systemDsl.isEnabled() && !editor.systemDsl.getText().equals(conf.applicationOutDsl()) ||
			editor.persistentCheckBox.isEnabled() && editor.persistentCheckBox.isSelected() != conf.isPersistent() ||
			editor.testBox.isEnabled() && editor.testBox.isSelected() != conf.isTest();

	}

	private void initErrorValidation() {
		editor.facetErrorPanel = new FacetErrorPanel();
		editor.errorPanel.add(editor.facetErrorPanel.getComponent(), BorderLayout.CENTER);
		editor.facetErrorPanel.getValidatorsManager().registerValidator(new FacetEditorValidator() {
			@NotNull
			@Override
			public ValidationResult check() {
				if (requiresOutputDsl() && (editor.applicationDsl.getText().isEmpty() && editor.systemDsl.getText().isEmpty()))
					return new ValidationResult(message("required.tara.facet.outdsl", context.getModule().getName()));
				else if (!editor.applicationDsl.getText().isEmpty() && invalidOutDslName())
					return new ValidationResult(message("required.outdsl.wrong.pattern"));
				else {
					Sdk sdk = editor.context.getRootModel().getSdk();
					if (sdk == null ||
						!((JavaSdk) sdk.getSdkType()).isOfVersionOrHigher(sdk, JavaSdkVersion.JDK_1_8))
						return new ValidationResult(message("required.suitable.jdk"));
					else return OK;
				}
			}
		}, editor.applicationDsl);
		editor.facetErrorPanel.getValidatorsManager().

			validate();
	}

	private boolean requiresOutputDsl() {
		return !conf.type().equals(System);
	}


	private boolean invalidOutDslName() {
		return !editor.applicationDsl.getText().matches("^[a-zA-Z][a-zA-Z0-9]*$");
	}

	private void updateVisibility() {
		final TaraFacetConfiguration.ModuleType type = conf.type();
		if (TaraFacetConfiguration.ModuleType.ProductLine.equals(type)) {
			editor.inputDslLabel.setText("Platform DSL");
			editor.applicationDslLabel.setText("System DSL");
			mask(true, true, true, true);
		} else if (Platform.equals(type)) {
			editor.inputDslLabel.setText("Platform DSL");
			editor.applicationDslLabel.setText("Output DSL");
			editor.applicationDsl.setEnabled(true);
			mask(true, true, false, true);
		} else if (Application.equals(type)) {
			editor.inputDslLabel.setText("Application DSL");
			editor.systemDslLabel.setText("Output DSL");
			editor.systemDsl.setEnabled(true);
			mask(true, false, true, false);
		} else if (Ontology.equals(type)) {
			editor.inputDslLabel.setText("Ontology DSL");
			editor.systemDslLabel.setText("Output DSL");
			mask(true, false, true, true);
		} else {
			editor.inputDslLabel.setText("System DSL");
			mask(true, false, false, false);
		}
	}


	private void mask(boolean a, boolean b, boolean c, boolean d) {
		editor.inputDslLabel.setVisible(a);
		editor.inputDsl.setVisible(a);
		editor.applicationDsl.setVisible(b);
		editor.applicationDslLabel.setVisible(b);
		editor.systemDslLabel.setVisible(c);
		editor.systemDsl.setVisible(c);
		editor.persistentCheckBox.setEnabled(d);
	}

	private void createDslBox() {
		updateDslBox(conf.dsl(conf.type()));
	}

	private void addOutDsls() {
		editor.applicationDsl.setText(conf.platformOutDsl().isEmpty() ? conf.applicationDsl() : conf.platformOutDsl());
		editor.systemDsl.setText(conf.applicationOutDsl().isEmpty() ? conf.applicationDsl() : conf.applicationOutDsl());
	}

	private void versions() {
		if (!conf.isApplicationImportedDsl() && !PROTEO.equals(conf.platformDsl()))
			this.versions = Collections.singletonList(conf.dslVersion(this.context.getModule(), conf.dsl(conf.type())));
		else
			try {
				ArtifactoryConnector connector = new ArtifactoryConnector(TaraSettings.getSafeInstance(editor.context.getProject()), new MavenHelper(editor.context.getModule()).snapshotRepository());
				versions = connector.versions(conf.dsl(conf.type()));
				Collections.reverse(versions);
			} catch (IOException e) {
				LOG.error(e.getMessage());
			}
	}

	private void initVersionBox() {
		editor.versionBox.removeAllItems();
		final Module module = editor.context.getModule();
		for (String version : versions) editor.versionBox.addItem(version);
		final String dsl = conf.dsl(conf.type());
		if (!versions.contains(conf.dslVersion(module, dsl)))
			editor.versionBox.addItem(conf.dslVersion(module, dsl));
		editor.versionBox.setSelectedItem(conf.dslVersion(module, dsl));
	}

	private void updateDslBox(String selection) {
		editor.inputDsl.removeAllItems();
		editor.inputDsl.addItem(selection);
	}

	private void updateValues() {
		editor.persistentCheckBox.setEnabled(conf.type() == Platform);
		if (conf.type() == Platform) editor.persistentCheckBox.setSelected(conf.isPersistent());
		else {
			editor.testBox.setSelected(conf.isTest());
			resolveInheritedValues();
		}
	}

	private boolean resolveInheritedValues() {
		final Module parent = getSelectedParentModule();
		if (parent == null || TaraFacet.of(parent) == null) return true;
		final TaraFacetConfiguration parentConf = TaraFacet.of(parent).getConfiguration();
		editor.persistentCheckBox.setSelected(parentConf.isPersistent());
		return false;
	}

	private void testBox() {
		editor.testBox.setSelected(conf.isTest());
	}

	private void selectType(TaraFacetConfiguration.ModuleType type) {
		editor.modelType.setSelectedItem(type.name());
	}

	private void initUpdateButton() {
		editor.update.setContentAreaFilled(false);
		editor.update.addActionListener(e -> {
			editor.updateLanguage(LanguageInfo.LATEST_VERSION);
			initVersionBox();
		});
		final int versions = countVersions();
		editor.update.setVisible(versions != 0);
		editor.reloadLabel.setVisible(versions != 0);
	}

	private int countVersions() {
		final String dsl = conf.dsl(conf.type());
		if (dsl.isEmpty() || editor.inputDsl.getSelectedItem() == null || !dsl.equals(editor.inputDsl.getSelectedItem().toString()))
			return 0;
		final String dslVersion = conf.dslVersion(editor.context.getModule(), dsl);
		if (versions.isEmpty() || versions.get(0).isEmpty() || dslVersion.isEmpty()) return 0;
		try {
			return Integer.parseInt(versionNumber(versions.get(0))) - Integer.parseInt(versionNumber(dslVersion));
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	private String versionNumber(String version) {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(version);
		String result = "";
		while (m.find()) result += m.group() + m.start() + m.end();
		return result;
	}

	private Module getSelectedParentModule() {
		for (Map.Entry<Module, tara.intellij.project.facet.ModuleInfo> entry : editor.moduleInfo.entrySet())
			if (entry.getValue().applicationDsl().equals(editor.inputDsl.getSelectedItem().toString()))
				return entry.getKey();
		return null;
	}

	private Module[] getParentModulesCandidates(Project project) {
		if (project == null || !project.isInitialized()) return new Module[0];
		List<Module> moduleCandidates = new ArrayList<>();
		for (Module aModule : ModuleManager.getInstance(project).getModules()) {
			TaraFacet taraFacet = TaraFacet.of(aModule);
			if (taraFacet != null && !taraFacet.getConfiguration().type().equals(TaraFacetConfiguration.ModuleType.System))
				moduleCandidates.add(aModule);
		}
		return moduleCandidates.toArray(new Module[moduleCandidates.size()]);
	}

	private Map<Module, tara.intellij.project.facet.ModuleInfo> collectModulesInfo() {
		Map<Module, tara.intellij.project.facet.ModuleInfo> map = new HashMap<>();
		for (Module candidate : candidates) {
			final TaraFacet facet = TaraFacet.of(candidate);
			if (facet == null) continue;
			TaraFacetConfiguration conf = facet.getConfiguration();
			map.put(candidate, new tara.intellij.project.facet.ModuleInfo(conf.type(), conf.platformOutDsl(), conf.applicationOutDsl()));
		}
		return map;
	}
}
