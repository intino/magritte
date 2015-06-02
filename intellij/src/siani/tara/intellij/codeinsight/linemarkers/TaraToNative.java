package siani.tara.intellij.codeinsight.linemarkers;

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
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Contract;

import javax.swing.*;
import java.awt.event.MouseEvent;

import static siani.tara.intellij.lang.psi.impl.ReferenceManager.resolveContract;

public class TaraToNative extends JavaLineMarkerProvider {

	public TaraToNative(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		super(daemonSettings, colorsManager);
	}

	private final MarkerType markerType = new MarkerType(element -> {
		if (!Contract.class.isInstance(element)) return null;
		PsiElement reference = resolveContract((Contract) element);
		String start = "Native code declared in ";
		@NonNls String pattern;
		if (reference == null) return null;
		pattern = reference.getNavigationElement().getContainingFile().getName();
		return GutterIconTooltipHelper.composeText(new PsiElement[]{reference}, start, pattern);
	}, new LineMarkerNavigator() {
		@Override
		public void browse(MouseEvent e, PsiElement element) {
			if (!Contract.class.isInstance(element)) return;
			if (DumbService.isDumb(element.getProject())) {
				DumbService.getInstance(element.getProject()).showDumbModeNotification("Navigation to implementation classes is not possible during index update");
				return;
			}
			NavigatablePsiElement reference = (NavigatablePsiElement) resolveContract((Contract) element);
			if (reference == null) return;
			String title = DaemonBundle.message("navigation.title.overrider.method", element.getText(), 1);
			MethodCellRenderer renderer = new MethodCellRenderer(false);
			PsiElementListNavigator.openTargets(e, new NavigatablePsiElement[]{reference}, title, "Overriding Methods of " + (reference.getName()), renderer);
		}
	}
	);

	@Override
	public LineMarkerInfo getLineMarkerInfo(@NotNull final PsiElement element) {
		if (!(element instanceof Contract)) return super.getLineMarkerInfo(element);
		Contract contract = (Contract) element;
		PsiElement reference = resolveContract(contract);
		if (reference != null) {
			final Icon icon = AllIcons.Gutter.ImplementedMethod;
			final MarkerType type = markerType;
			return new LineMarkerInfo(element, element.getTextRange(), icon, Pass.UPDATE_ALL, type.getTooltip(),
				type.getNavigationHandler(), GutterIconRenderer.Alignment.LEFT);
		} else return super.getLineMarkerInfo(element);
	}
}
