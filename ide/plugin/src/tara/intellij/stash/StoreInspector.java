package tara.intellij.stash;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowEx;
import com.intellij.openapi.wm.ex.ToolWindowManagerEx;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static tara.intellij.lang.TaraIcons.STASH_16;
import static tara.intellij.stash.StoreInspector.PROJECT_COMPONENT_NAME;

@State(name = PROJECT_COMPONENT_NAME)
public class StoreInspector implements ProjectComponent, PersistentStateComponent<SelectedStoresState> {

	static final String PROJECT_COMPONENT_NAME = "StoreProjectComponent";
	private final Project project;
	private StoreInspectorManager inspectorPanel;
	private ToolWindowEx myToolWindow;
	private String PLUGIN_NAME = "Store Inspector";
	private String ID_TOOL_WINDOW = "Store Inspector";
	private SelectedStoresState storesState = new SelectedStoresState();

	public StoreInspector(Project project) {
		this.project = project;
	}

	@Override
	public void projectOpened() {
	}

	@Override
	public void projectClosed() {
		unregisterToolWindow();
	}

	private void unregisterToolWindow() {
		if (inspectorPanel != null) inspectorPanel = null;
		if (isToolWindowRegistered()) ToolWindowManager.getInstance(project).unregisterToolWindow(ID_TOOL_WINDOW);
	}

	private boolean isToolWindowRegistered() {
		return ToolWindowManager.getInstance(project).getToolWindow(ID_TOOL_WINDOW) != null;
	}

	@NotNull
	@Override
	public String getComponentName() {
		return PLUGIN_NAME + '.' + PROJECT_COMPONENT_NAME;
	}

	@Override
	public void initComponent() {
		StartupManager.getInstance(project).registerPostStartupActivity(this::doInit);
	}

	private void doInit() {
		inspectorPanel = new StoreInspectorManager(project);
		inspectorPanel.setSavedStores(storesState);
		final ToolWindowManagerEx manager = ToolWindowManagerEx.getInstanceEx(project);
		myToolWindow = (ToolWindowEx) manager.registerToolWindow(ID_TOOL_WINDOW, false, ToolWindowAnchor.RIGHT, project, true);
		myToolWindow.setIcon(STASH_16);
		final ContentFactory contentFactory = ServiceManager.getService(ContentFactory.class);
		final Content content = contentFactory.createContent(inspectorPanel, "", false);
		ContentManager contentManager = myToolWindow.getContentManager();
		contentManager.addContent(content);
		contentManager.setSelectedContent(content, false);
	}

	@Override
	public void disposeComponent() {
		unregisterToolWindow();
		myToolWindow = null;
	}

	@Nullable
	@Override
	public SelectedStoresState getState() {
		if (inspectorPanel != null) storesState.setStores(inspectorPanel.getSavedStores());
		return storesState;
	}

	@Override
	public void loadState(SelectedStoresState state) {
		this.storesState = state;
		if (inspectorPanel != null) inspectorPanel.setSavedStores(storesState);
	}

}
