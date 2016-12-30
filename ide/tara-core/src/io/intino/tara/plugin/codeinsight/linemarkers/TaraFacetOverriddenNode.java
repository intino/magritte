package io.intino.tara.plugin.codeinsight.linemarkers;

import com.intellij.codeHighlighting.Pass;
import com.intellij.codeInsight.daemon.DaemonBundle;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzerSettings;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.impl.*;
import com.intellij.icons.AllIcons;
import com.intellij.ide.util.MethodCellRenderer;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.project.DumbService;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.plugin.lang.psi.TaraNode;
import io.intino.tara.lang.model.Facet;
import io.intino.tara.lang.model.Node;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class TaraFacetOverriddenNode extends JavaLineMarkerProvider {

	public TaraFacetOverriddenNode(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		super(daemonSettings, colorsManager);
	}

	private final MarkerType markerType = new MarkerType("", element -> {
		if (!Node.class.isInstance(element)) return null;
		TaraNode reference = getOverriddenNode((Node) element);
		String start = "Node overridden by facet in ";
		@NonNls String pattern;
		if (reference == null) return null;
		pattern = reference.getNavigationElement().getContainingFile().getName();
		return GutterIconTooltipHelper.composeText(new PsiElement[]{reference}, start, pattern);
	}, new LineMarkerNavigator() {
		@Override
		public void browse(MouseEvent e, PsiElement element) {
			if (!Node.class.isInstance(element)) return;
			if (DumbService.isDumb(element.getProject())) {
				DumbService.getInstance(element.getProject()).showDumbModeNotification("Navigation to implementation classes is not possible during index update");
				return;
			}
			NavigatablePsiElement reference = (NavigatablePsiElement) getOverriddenNode((Node) element);
			if (reference == null) return;
			String title = DaemonBundle.message("navigation.title.overrider.method", element.getText(), 1);
			MethodCellRenderer renderer = new MethodCellRenderer(false);
			PsiElementListNavigator.openTargets(e, new NavigatablePsiElement[]{reference}, title, "Overridden Node of " + (reference.getName()), renderer);
		}
	}
	);

	@Override
	public LineMarkerInfo getLineMarkerInfo(@NotNull final PsiElement element) {
		if (!Node.class.isInstance(element) || !(TaraPsiImplUtil.getContainerOf(element) instanceof Facet))
			return super.getLineMarkerInfo(element);
		Node node = (Node) element;
		if (isOverridden(node)) {
			final Icon icon = AllIcons.Gutter.OverridenMethod;
			final MarkerType type = markerType;
			return new LineMarkerInfo(element, element.getTextRange(), icon, Pass.UPDATE_ALL, type.getTooltip(),
				type.getNavigationHandler(), GutterIconRenderer.Alignment.LEFT);
		} else return super.getLineMarkerInfo(element);
	}

	private TaraNode getOverriddenNode(Node inner) {
		Node container = TaraPsiImplUtil.getContainerNodeOf((PsiElement) inner);
		if (container == null) return null;
		for (Node containerNode : container.components())
			if (isOverridden(inner, containerNode))
				return (TaraNode) containerNode;
		return null;
	}

	private boolean isOverridden(Node node) {
		return getOverriddenNode(node) != null;
	}

	private boolean isOverridden(Node node, Node parentNode) {
		return parentNode.type().equals(node.type()) && ((parentNode.name() == null && node.name() == null) || (parentNode.name() != null && parentNode.name().equals(node.name())));
	}
}