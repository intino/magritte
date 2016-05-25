package tara.intellij.stash;

import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;

class StoreInspectorManager extends SimpleToolWindowPanel implements DataProvider {
	private static final Logger LOG = Logger.getInstance("StoreInspectorManager");

	private final Project project;
	private final StoreInspectorView view;

	StoreInspectorManager(Project project) {
		super(true, true);
		this.project = project;
		view = new StoreInspectorView(project);
		add(view.getContentPane());
	}
}
