package tara.intellij.codeinsight.navigation;

import com.intellij.navigation.GotoClassContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.language.model.NodeContainer;

public class TaraGotoSymbolContributor implements GotoClassContributor {
	@NotNull
	@Override
	public String[] getNames(Project project, boolean includeNonProjectItems) {
		return new String[0];
	}

	@NotNull
	@Override
	public NavigationItem[] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
		return new NavigationItem[0];
	}


	@Nullable
	@Override
	public String getQualifiedName(NavigationItem item) {
		return item instanceof NodeContainer ? ((NodeContainer) item).qualifiedName() : null;
	}

	@Nullable
	@Override
	public String getQualifiedNameSeparator() {
		return ".";
	}
}
