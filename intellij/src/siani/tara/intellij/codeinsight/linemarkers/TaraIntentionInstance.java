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
import siani.tara.semantic.Assumption;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

public final class TaraIntentionInstance extends Intention {

	private static final String EXTENSIONS = "extensions";

	public TaraIntentionInstance(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
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

	protected final boolean canBeMarked(PsiElement element) {
		if (!(element instanceof Node) && !(element instanceof TaraFacetTarget)) return false;
		Node node = element instanceof Node ? (Node) element : TaraPsiImplUtil.getContainerNodeOf(element);
		Collection<Assumption> assumptions = TaraUtil.getAssumptionsOf(node);
		if (assumptions == null) return false;
		for (Assumption assumption : assumptions)
			if (assumption instanceof Assumption.FacetInstance) return true;
		return false;
	}

	protected final String buildDestinyClassQN(Node node, Project project) {
		return project.getName().toLowerCase() + DOT + EXTENSIONS + DOT + node.getName() + node.getType();
	}

	protected final String getFacetPackage(Node node, Inflector inflector) {
		return node.getProject().getName().toLowerCase() + DOT + EXTENSIONS + DOT +
			inflector.plural(node.getType()).toLowerCase() + DOT + inflector.plural(node.getName()).toLowerCase();
	}

	protected String composeClassName(TaraFacetTarget target, String intention) {
		if (target.getIdentifierReference() == null) return "";
		List<TaraIdentifier> identifiers = target.getIdentifierReference().getIdentifierList();
		return DOT + identifiers.get(identifiers.size() - 1).getName() + intention;
	}

	protected PsiElement resolveExternal(TaraFacetTarget target) {
		Node node = TaraPsiImplUtil.getContainerNodeOf(target);
		if (node == null || node.getName() == null) return null;
		String facetPackage = getFacetPackage(node, TaraUtil.getInflector(ModuleProvider.getModuleOf(target)));
		return ReferenceManager.resolveJavaClassReference(target.getProject(), facetPackage + composeClassName(target, node.getType()));
	}
}
