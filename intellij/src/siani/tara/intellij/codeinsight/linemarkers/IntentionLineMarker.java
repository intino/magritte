package siani.tara.intellij.codeinsight.linemarkers;

import com.intellij.codeInsight.daemon.DaemonBundle;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzerSettings;
import com.intellij.codeInsight.daemon.impl.*;
import com.intellij.ide.util.MethodCellRenderer;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.util.Function;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;
import org.siani.itrules.formatter.Inflector;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;

import java.awt.event.MouseEvent;

public abstract class IntentionLineMarker extends JavaLineMarkerProvider {

	protected static final String DOT = ".";

	protected final MarkerType markerType = new MarkerType(new Function<PsiElement, String>() {
		@Nullable
		@Override
		public String fun(PsiElement element) {
			if (!canBeMarked(element)) return null;
			PsiElement reference;
			reference = element instanceof Concept ? resolveExternal((Concept) element) : resolveExternal((TaraFacetTarget) element);
			String start = "Intention declared in ";
			@NonNls String pattern = null;
			if (reference != null) pattern = reference.getNavigationElement().getContainingFile().getName();
			return GutterIconTooltipHelper.composeText(new PsiElement[]{reference}, start, pattern);
		}
	}, getNavigator());

	public IntentionLineMarker(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		super(daemonSettings, colorsManager);
	}

	protected PsiElement resolveExternal(Concept concept) {
		return ReferenceManager.resolveJavaClassReference(concept.getProject(), buildDestinyClassQN(concept, concept.getProject()));
	}

	protected LineMarkerNavigator getNavigator() {
		return new LineMarkerNavigator() {
			@Override
			public void browse(MouseEvent e, PsiElement element) {
				if (!canBeMarked(element)) return;
				if (DumbService.isDumb(element.getProject())) {
					DumbService.getInstance(element.getProject()).showDumbModeNotification("Navigation to implementation classes is not possible during index update");
					return;
				}
				NavigatablePsiElement reference = (NavigatablePsiElement) (element instanceof Concept ? resolveExternal((Concept) element) : resolveExternal((TaraFacetTarget) element));
				if (reference == null) return;
				String title = DaemonBundle.message("navigation.title.overrider.method", element.getText(), 1);
				MethodCellRenderer renderer = new MethodCellRenderer(false);
				PsiElementListNavigator.openTargets(e, new NavigatablePsiElement[]{reference}, title, "Overriding Methods of " + (reference.getName()), renderer);
			}
		};
	}

	protected abstract PsiElement resolveExternal(TaraFacetTarget element);

	protected abstract String buildDestinyClassQN(Concept concept, Project project);

	protected abstract String composeClassName(TaraFacetTarget target, String conceptName);

	protected abstract boolean canBeMarked(PsiElement element);

	protected abstract String getFacetPackage(Concept concept, Inflector inflector);

}
