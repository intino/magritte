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
import com.intellij.util.Function;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.MeasureType;
import siani.tara.intellij.lang.psi.TaraMeasureType;

import javax.swing.*;
import java.awt.event.MouseEvent;

import static siani.tara.intellij.lang.psi.impl.ReferenceManager.resolveMeasure;

public class TaraMetricLineMarker extends JavaLineMarkerProvider {

	private final MarkerType markerType = new MarkerType(new Function<PsiElement, String>() {
		@Nullable
		@Override
		public String fun(PsiElement element) {
			if (!MeasureType.class.isInstance(element)) return null;
			PsiElement reference = resolveMeasure((MeasureType) element);
			String start = "Metric declared in ";
			@NonNls String pattern;
			if (reference == null) return null;
			pattern = reference.getNavigationElement().getContainingFile().getName();
			return GutterIconTooltipHelper.composeText(new PsiElement[]{reference}, start, pattern);
		}
	}, new LineMarkerNavigator() {
		@Override
		public void browse(MouseEvent e, PsiElement element) {
			if (!(element instanceof TaraMeasureType)) return;
			if (DumbService.isDumb(element.getProject())) {
				DumbService.getInstance(element.getProject()).showDumbModeNotification("Navigation to implementation classes is not possible during index update");
				return;
			}
			NavigatablePsiElement reference = (NavigatablePsiElement) resolveMeasure((MeasureType) element);
			if (reference == null) return;
			String title = DaemonBundle.message("navigation.title.overrider.method", element.getText(), 1);
			MethodCellRenderer renderer = new MethodCellRenderer(false);
			PsiElementListNavigator.openTargets(e, new NavigatablePsiElement[]{reference}, title, "Overriding Methods of " + (reference.getName()), renderer);
		}
	}
	);


	public TaraMetricLineMarker(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		super(daemonSettings, colorsManager);
	}

	@Override
	public LineMarkerInfo getLineMarkerInfo(@NotNull final PsiElement element) {
		if (!(element instanceof MeasureType)) return super.getLineMarkerInfo(element);
		MeasureType measureType = (MeasureType) element;
		PsiElement reference = resolveMeasure(measureType);
		if (reference != null) {
			final Icon icon = AllIcons.Gutter.ImplementedMethod;
			final MarkerType type = markerType;
			return new LineMarkerInfo(element, element.getTextRange(), icon, Pass.UPDATE_ALL, type.getTooltip(),
				type.getNavigationHandler(), GutterIconRenderer.Alignment.LEFT);
		} else return super.getLineMarkerInfo(element);
	}
}
