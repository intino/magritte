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
import com.intellij.openapi.project.Project;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.util.Function;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.Identifier;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class TaraIntentionLineMarkerProvider extends JavaLineMarkerProvider {

	private static final String INTENTIONS_PATH = "intentions";
	private static final String INTENTION = "Intention";

	private final MarkerType OVERRIDDEN_PROPERTY_TYPE = new MarkerType(new Function<PsiElement, String>() {
		@Nullable
		@Override
		public String fun(PsiElement element) {
			if (!((element instanceof Concept && ((Concept) element).isIntention()))) return null;
			PsiElement reference = resolveExternal((Concept) element);
			String start = "Intention declared in ";
			@NonNls String pattern = null;
			if (reference != null) pattern = reference.getNavigationElement().getContainingFile().getName();
			return GutterIconTooltipHelper.composeText(new PsiElement[]{reference}, start, pattern);
		}
	}, new LineMarkerNavigator() {
		@Override
		public void browse(MouseEvent e, PsiElement element) {
			if (!(element instanceof Concept)) return;
			if (DumbService.isDumb(element.getProject())) {
				DumbService.getInstance(element.getProject()).showDumbModeNotification("Navigation to implementation classes is not possible during index update");
				return;
			}
			Identifier identifierNode = ((Concept) element).getIdentifierNode();
			if (identifierNode == null) return;
			NavigatablePsiElement reference = (NavigatablePsiElement) resolveExternal(((Concept) element));
			if (reference == null) return;
			String title = DaemonBundle.message("navigation.title.overrider.method", element.getText(), 1);
			MethodCellRenderer renderer = new MethodCellRenderer(false);
			PsiElementListNavigator.openTargets(e, new NavigatablePsiElement[]{reference}, title, "Overriding Methods of " + (reference.getName()), renderer);
		}
	}
	);

	public TaraIntentionLineMarkerProvider(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		super(daemonSettings, colorsManager);
	}

	@Override
	public LineMarkerInfo getLineMarkerInfo(@NotNull final PsiElement element) {
		if (element instanceof Concept && ((Concept) element).isIntention()) {
			PsiElement reference = resolveExternal((Concept) element);
			if (reference != null) {
				final Icon icon = AllIcons.Gutter.ImplementedMethod;
				final MarkerType type = OVERRIDDEN_PROPERTY_TYPE;
				return new LineMarkerInfo(element, element.getTextRange(), icon, Pass.UPDATE_ALL, type.getTooltip(),
					type.getNavigationHandler(), GutterIconRenderer.Alignment.LEFT);
			}
		}
		return super.getLineMarkerInfo(element);
	}

	private PsiElement resolveExternal(Concept concept) {
		Project project = concept.getProject();
		return ReferenceManager.resolveJavaClassReference(project, INTENTIONS_PATH + "." + concept.getName() + INTENTION);
	}
}
