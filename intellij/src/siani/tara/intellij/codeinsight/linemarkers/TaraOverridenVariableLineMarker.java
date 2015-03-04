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
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Variable;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class TaraOverridenVariableLineMarker extends JavaLineMarkerProvider {

	public TaraOverridenVariableLineMarker(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		super(daemonSettings, colorsManager);
	}


	private final MarkerType markerType = new MarkerType(new Function<PsiElement, String>() {
		@Nullable
		@Override
		public String fun(PsiElement element) {
			if (!Variable.class.isInstance(element)) return null;
			PsiElement reference = getOverridenVariable((Variable) element);
			String start = "Variable overried in ";
			@NonNls String pattern;
			if (reference == null) return null;
			pattern = reference.getNavigationElement().getContainingFile().getName();
			return GutterIconTooltipHelper.composeText(new PsiElement[]{reference}, start, pattern);
		}
	}, new LineMarkerNavigator() {
		@Override
		public void browse(MouseEvent e, PsiElement element) {
			if (!Variable.class.isInstance(element)) return;
			if (DumbService.isDumb(element.getProject())) {
				DumbService.getInstance(element.getProject()).showDumbModeNotification("Navigation to implementation classes is not possible during index update");
				return;
			}
			NavigatablePsiElement reference = (NavigatablePsiElement) getOverridenVariable((Variable) element);
			if (reference == null) return;
			String title = DaemonBundle.message("navigation.title.overrider.method", element.getText(), 1);
			MethodCellRenderer renderer = new MethodCellRenderer(false);
			PsiElementListNavigator.openTargets(e, new NavigatablePsiElement[]{reference}, title, "Overrided Variable of " + (reference.getName()), renderer);
		}
	}
	);

	@Override
	public LineMarkerInfo getLineMarkerInfo(@NotNull final PsiElement element) {
		if (!Variable.class.isInstance(element)) return super.getLineMarkerInfo(element);
		Variable variable = (Variable) element;
		if (getOverridenVariable(variable) != null) {
			final Icon icon = AllIcons.Gutter.OverridenMethod;
			final MarkerType type = markerType;
			return new LineMarkerInfo(element, element.getTextRange(), icon, Pass.UPDATE_ALL, type.getTooltip(),
				type.getNavigationHandler(), GutterIconRenderer.Alignment.LEFT);
		} else return super.getLineMarkerInfo(element);
	}

	private Variable getOverridenVariable(Variable variable) {
		Concept concept = TaraPsiImplUtil.getConceptContainerOf(variable);
		if (concept == null) return null;
		Concept parent = concept.getParentConcept();
		while (parent != null) {
			for (Variable parentVar : parent.getVariables())
				if (isOverriden(variable, parentVar))
					return parentVar;
			parent = parent.getParentConcept();
		}
		return null;
	}

	private boolean isOverriden(Variable variable, Variable parentVar) {
		return parentVar.getType().equals(variable.getType()) && parentVar.getName() != null && parentVar.getName().equals(variable.getName());
	}
}
