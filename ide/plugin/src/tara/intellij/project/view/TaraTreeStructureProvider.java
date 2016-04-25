package tara.intellij.project.view;

import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.PsiDirectoryNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.TaraModel;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class TaraTreeStructureProvider implements com.intellij.ide.projectView.TreeStructureProvider {
	private final Project project;

	public TaraTreeStructureProvider(Project project) {
		this.project = project;
	}

	@NotNull
	public Collection<AbstractTreeNode> modify(@NotNull AbstractTreeNode parent, @NotNull Collection<AbstractTreeNode> children, ViewSettings settings) {
		if (parent.getValue() instanceof NodeView) return children;
		Collection<AbstractTreeNode> result = new LinkedHashSet<>();
		for (AbstractTreeNode element : children) {
			if (element instanceof PsiDirectoryNode) {
				result.add(element);
				continue;
			}
			TaraModel taraModel = getTaraFile(element);
			if (taraModel == null) result.add(element);
			else result.add(new NodeView(project, taraModel, settings));
		}
		return result;
	}

	private TaraModel getTaraFile(AbstractTreeNode element) {
		TaraModel model = null;
		if (element.getValue() instanceof TaraModel)
			model = (TaraModel) element.getValue();
		return model;
	}

	public Object getData(Collection<AbstractTreeNode> selected, String dataId) {
		if (selected == null) return null;
		if (NodeView.DATA_KEY.is(dataId)) {
			List<NodeView> result = getNodeTreeViews(selected);
			if (!result.isEmpty()) return result.toArray(new NodeView[result.size()]);
		}
		return null;
	}

	private List<NodeView> getNodeTreeViews(Collection<AbstractTreeNode> selected) {
		return selected.stream().
			filter(node -> node.getValue() instanceof NodeView).
			map(node -> (NodeView) node.getValue()).collect(Collectors.toList());
	}
}
