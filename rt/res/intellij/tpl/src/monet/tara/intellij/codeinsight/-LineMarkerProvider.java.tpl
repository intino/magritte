package monet.::projectName::.intellij.codeinsight;

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
import monet.::projectName::.intellij.lang.psi.Identifier;
import monet.::projectName::.intellij.lang.psi.::projectProperName::Intention;
import monet.::projectName::.intellij.lang.psi.impl.ReferenceManager;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::PsiImplUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class ::projectProperName::LineMarkerProvider extends JavaLineMarkerProvider {

	private final MarkerType OVERRIDDEN_PROPERTY_TYPE = new MarkerType(new Function<PsiElement, String>() {
		\@Nullable
		\@Override
		public String fun(PsiElement element) {
			if (!(element instanceof ::projectProperName::Intention)) return null;
			PsiElement reference = ReferenceManager.resolve((Identifier) ::projectProperName::PsiImplUtil.getContextOf(element).getIdentifierNode(), true);
			String start = "Definition extended in ";
			\@NonNls String pattern = null;
			if (reference != null) pattern = reference.getNavigationElement().getContainingFile().getName();
			return GutterIconTooltipHelper.composeText(new PsiElement[]{reference}, start, pattern);
		}
	}, new LineMarkerNavigator() {
		\@Override
		public void browse(MouseEvent e, PsiElement element) {
			PsiElement parent = element.getParent();
			if (!(element instanceof Identifier)) return;
			if (DumbService.isDumb(element.getProject())) {
				DumbService.getInstance(element.getProject()).showDumbModeNotification("Navigation to overriding classes is not possible during index update");
				return;
			}
			if (!(parent instanceof ::projectProperName::Intention)) return;
			NavigatablePsiElement reference = (NavigatablePsiElement) ReferenceManager.resolve((Identifier) element, true);
			String title = DaemonBundle.message("navigation.title.overrider.method", element.getText(), 1);
			MethodCellRenderer renderer = new MethodCellRenderer(false);
			PsiElementListNavigator.
				openTargets(e, new NavigatablePsiElement[]{reference}, title, "Overriding Methods of " + (reference != null ? reference.getName() \: null), renderer);
		}
	}
	);


	public ::projectProperName::LineMarkerProvider(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		super(daemonSettings, colorsManager);
	}

	\@Override
	public LineMarkerInfo getLineMarkerInfo(\@NotNull final PsiElement element) {
		if (element instanceof ::projectProperName::Intention) {
			final Icon icon = AllIcons.Gutter.ImplementedMethod;
			final MarkerType type = OVERRIDDEN_PROPERTY_TYPE;
			return new LineMarkerInfo<>(element, element.getTextRange(), icon, Pass.UPDATE_ALL, type.getTooltip(),
				type.getNavigationHandler(), GutterIconRenderer.Alignment.LEFT);
		}
		return super.getLineMarkerInfo(element);
	}
}
