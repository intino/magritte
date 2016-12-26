package io.intino.tara.plugin.codeinsight.linemarkers;

import com.intellij.codeHighlighting.Pass;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzerSettings;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.impl.*;
import com.intellij.icons.AllIcons;
import com.intellij.ide.util.PsiElementListCellRenderer;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.project.DumbService;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import io.intino.tara.plugin.lang.psi.TaraModel;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.NodeContainer;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NodeFragments extends JavaLineMarkerProvider {

	private final MarkerType markerType = new MarkerType("", element -> {
		if (!Node.class.isInstance(element)) return null;
		List<NavigatablePsiElement> references = getFragmentNodes((Node) element);
		@NonNls String pattern;
		if (references.isEmpty()) return null;
		pattern = references.get(0).getNavigationElement().getContainingFile().getName();
		return GutterIconTooltipHelper.composeText(references.toArray(new NavigatablePsiElement[references.size()]), "fragment ", pattern);
	}, new LineMarkerNavigator() {
		@Override
		public void browse(MouseEvent e, PsiElement element) {
			if (!Node.class.isInstance(element)) return;
			if (DumbService.isDumb(element.getProject())) {
				DumbService.getInstance(element.getProject()).showDumbModeNotification("Navigation to elements is not possible during index update");
				return;
			}
			List<NavigatablePsiElement> references = getFragmentNodes((Node) element);
			references.remove(element);
			if (references.isEmpty()) return;

			DefaultPsiElementListCellRenderer renderer = new DefaultPsiElementListCellRenderer();
			PsiElementListNavigator.openTargets(e, references.toArray(new NavigatablePsiElement[references.size()]), "Fragment of " + (((Node) element).name()), "Fragment of " + (((Node) element).name()), renderer);
		}
	});

	public NodeFragments(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		super(daemonSettings, colorsManager);
	}

	@Override
	public LineMarkerInfo getLineMarkerInfo(@NotNull final PsiElement element) {
		if (!Node.class.isInstance(element)) return super.getLineMarkerInfo(element);
		Node node = (Node) element;
		final List<NavigatablePsiElement> fragmentNodes = getFragmentNodes(node);
		if (fragmentNodes.size() > 1) {
			final Icon icon = AllIcons.Gutter.Unique;
			final MarkerType type = markerType;
			return new LineMarkerInfo(element, element.getTextRange(), icon, Pass.UPDATE_ALL, type.getTooltip(),
				type.getNavigationHandler(), GutterIconRenderer.Alignment.LEFT);
		} else return super.getLineMarkerInfo(element);
	}

	private List<NavigatablePsiElement> getFragmentNodes(Node node) {
		if (node.isAnonymous()) return Collections.emptyList();
		List<NavigatablePsiElement> fragments = new ArrayList<>();
		NodeContainer container = node.container();
		if (container == null) return Collections.emptyList();
		fragments.addAll(container.component(node.name()).stream().filter(c -> !c.isReference()).map(c -> (NavigatablePsiElement) c).collect(Collectors.toList()));
		return fragments;
	}

	private static class DefaultPsiElementListCellRenderer extends PsiElementListCellRenderer {
		@Override
		public String getElementText(final PsiElement element) {
			if (element instanceof PsiNamedElement) {
				String name = ((PsiNamedElement) element).getName();
				if (name != null) {
					return name;
				}
			}
			return ((TaraModel) element.getContainingFile()).getPresentableName();
		}

		@Override
		protected String getContainerText(final PsiElement element, final String name) {
			if (element instanceof NavigationItem) {
				final ItemPresentation presentation = ((NavigationItem) element).getPresentation();
				return presentation != null ? presentation.getLocationString() : null;
			}

			return null;
		}

		@Override
		protected int getIconFlags() {
			return 0;
		}
	}


}
