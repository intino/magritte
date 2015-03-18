package siani.tara.intellij.codeinsight.linemarkers;

import com.intellij.codeHighlighting.Pass;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzerSettings;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.formatter.Inflector;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.TaraIdentifier;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;

import javax.swing.*;

public final class TaraIntentionLineMarker extends IntentionLineMarker {

	private static final String INTENTIONS_PATH = "intentions";
	private static final String INTENTION = "Intention";

	public TaraIntentionLineMarker(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		super(daemonSettings, colorsManager);
	}

	@Override
	public LineMarkerInfo getLineMarkerInfo(@NotNull final PsiElement element) {
		if (canBeMarked(element)) {
			PsiElement reference = element instanceof Node ? resolveExternal((Node) element) : resolveExternal((TaraFacetTarget) element);
			if (reference != null) {
				final Icon icon = AllIcons.Gutter.ImplementedMethod;
				return new LineMarkerInfo(element, element.getTextRange(), icon, Pass.UPDATE_ALL, markerType.getTooltip(),
					markerType.getNavigationHandler(), GutterIconRenderer.Alignment.LEFT);
			}
		}
		return super.getLineMarkerInfo(element);
	}

	protected boolean canBeMarked(PsiElement element) {
		return (Node.class.isInstance(element) && ((Node) element).isIntention()) ||
			TaraFacetTarget.class.isInstance(element) && TaraPsiImplUtil.getContainerNodeOf(element).isIntention();
	}

	protected String buildDestinyClassQN(Node node, Project project) {
		return project.getName().toLowerCase() + DOT + INTENTIONS_PATH + DOT + node.getName() + INTENTION;
	}

	protected String getFacetPackage(Node node, Inflector inflector) {
		return node.getProject().getName().toLowerCase() + DOT + INTENTIONS_PATH + DOT + inflector.plural(node.getName()).toLowerCase();
	}

	protected String composeClassName(TaraFacetTarget target, String conceptName) {
		String name = "";
		if (target.getIdentifierReference() == null) return "";
		for (TaraIdentifier identifier : target.getIdentifierReference().getIdentifierList())
			name += DOT + identifier.getName() + conceptName + INTENTION;
		return name;
	}

	protected PsiElement resolveExternal(TaraFacetTarget target) {
		Node node = TaraPsiImplUtil.getContainerNodeOf(target);
		if (node == null || node.getName() == null) return null;
		String facetPackage = getFacetPackage(node, TaraUtil.getInflector(ModuleProvider.getModuleOf(target)));
		return ReferenceManager.resolveJavaClassReference(target.getProject(), facetPackage + composeClassName(target, node.getName()));
	}
}
