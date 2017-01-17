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
import io.intino.tara.plugin.lang.psi.Rule;
import io.intino.tara.plugin.lang.psi.resolve.ReferenceManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class TaraToNative extends JavaLineMarkerProvider {

	private final MarkerType markerType = new MarkerType("", element -> {
		if (!Rule.class.isInstance(element)) return null;
		PsiElement reference = ReferenceManager.resolveRule((Rule) element);
		String start = "Native code declared in ";
		@NonNls String pattern;
		if (reference == null) return null;
		pattern = reference.getNavigationElement().getContainingFile().getName();
		return GutterIconTooltipHelper.composeText(new PsiElement[]{reference}, start, pattern);
	}, new LineMarkerNavigator() {
		@Override
		public void browse(MouseEvent e, PsiElement element) {
			if (!Rule.class.isInstance(element)) return;
			if (DumbService.isDumb(element.getProject())) {
				DumbService.getInstance(element.getProject()).showDumbModeNotification("Navigation to implementation classes is not possible during index update");
				return;
			}
			NavigatablePsiElement reference = (NavigatablePsiElement) ReferenceManager.resolveRule((Rule) element);
			if (reference == null) return;
			String title = DaemonBundle.message("navigation.title.overrider.method", element.getText(), 1);
			MethodCellRenderer renderer = new MethodCellRenderer(false);
			PsiElementListNavigator.openTargets(e, new NavigatablePsiElement[]{reference}, title, "Overriding Methods of " + (reference.getName()), renderer);
		}
	}
	);

	public TaraToNative(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		super(daemonSettings, colorsManager);
	}

	@Override
	public LineMarkerInfo getLineMarkerInfo(@NotNull final PsiElement element) {
		if (!(element instanceof Rule))
			return super.getLineMarkerInfo(element);
		Rule rule = (Rule) element;
		PsiElement reference = ReferenceManager.resolveRule(rule);
		if (reference != null) {
			final Icon icon = AllIcons.Gutter.ImplementingMethod;
			final MarkerType type = markerType;
			return new LineMarkerInfo(element, element.getTextRange(), icon, Pass.UPDATE_ALL, type.getTooltip(),
				type.getNavigationHandler(), GutterIconRenderer.Alignment.LEFT);
		} else return super.getLineMarkerInfo(element);
	}

}