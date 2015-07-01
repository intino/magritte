package siani.tara.intellij.project.view;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.openapi.actionSystem.DataKey;
import com.intellij.openapi.project.Project;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import siani.tara.intellij.lang.psi.TaraModel;

public class NodeView extends PsiFileNode implements Navigatable {
	public static final DataKey<NodeView> DATA_KEY = DataKey.create("form.array");

	private final PsiFile taraFile;

	public NodeView(Project project, TaraModel psiFile, ViewSettings settings) {
		super(project, psiFile, settings);
		taraFile = psiFile;
	}

	public boolean equals(Object object) {
		if (object instanceof NodeView) {
			NodeView form = (NodeView) object;
			return taraFile.equals(form.taraFile);
		}
		return false;
	}


	public int hashCode() {
		return taraFile.hashCode();
	}

	public String getName() {
		return taraFile.getName().substring(0, taraFile.getName().lastIndexOf("."));
	}

	public void navigate(boolean requestFocus) {
		taraFile.navigate(requestFocus);
	}

	public boolean canNavigateToSource() {
		return taraFile.canNavigateToSource();
	}

	public boolean canNavigate() {
		return taraFile.canNavigate();
	}

	@Override
	protected void updateImpl(PresentationData data) {

	}

	public boolean isValid() {
		return taraFile.isValid();
	}

}
