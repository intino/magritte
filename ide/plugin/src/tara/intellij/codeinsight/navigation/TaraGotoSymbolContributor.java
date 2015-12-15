package tara.intellij.codeinsight.navigation;

import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import org.intellij.plugins.relaxNG.GotoSymbolContributor;
import org.jetbrains.annotations.NotNull;
import tara.lang.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static tara.intellij.lang.psi.impl.TaraUtil.getAllNodesOfFile;
import static tara.intellij.lang.psi.impl.TaraUtil.getTaraFilesOfModule;

public class TaraGotoSymbolContributor extends GotoSymbolContributor {
	@NotNull
	@Override
	public String[] getNames(Project project, boolean includeNonProjectItems) {
		List<String> names = getNodes(project).stream().map(Node::qualifiedName).collect(Collectors.toList());
		return names.toArray(new  String[names.size()]);
	}

	@NotNull
	@Override
	public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
		final List<Node> all = getNodes(project);
		final List<NavigationItem> items = all.stream().filter(node -> node.qualifiedName().startsWith(pattern)).map(n -> (NavigationItem) n).collect(Collectors.toList());
		return items.toArray(new NavigationItem[items.size()]);
	}

	public List<Node> getNodes(Project project) {
		List<Node> names = new ArrayList<>();
		for (Module module : ModuleManager.getInstance(project).getModules())
			getTaraFilesOfModule(module).stream().forEach(model -> names.addAll(getAllNodesOfFile(model)));
		return names;
	}

}
