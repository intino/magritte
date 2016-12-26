package io.intino.tara.plugin.stash;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;

import java.util.List;

class StoreInspectorManager extends SimpleToolWindowPanel {
	private static final Logger LOG = Logger.getInstance("StoreInspectorManager");

	private final StoreInspectorView view;

	StoreInspectorManager(Project project) {
		super(true, true);
		view = new StoreInspectorView(project);
		add(view.getContentPane());
	}

	void setSavedStores(SelectedStoresState storesState) {
		view.savedStores(storesState.getStores());
	}

	List<String> getSavedStores() {
		return view.savedStores();
	}
}
