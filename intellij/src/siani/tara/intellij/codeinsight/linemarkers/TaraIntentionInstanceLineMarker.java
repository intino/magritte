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
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.TaraIdentifier;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.Node;

import javax.swing.*;
import java.util.List;

import static siani.tara.lang.Annotation.INTENTION;

public final class TaraIntentionInstanceLineMarker extends IntentionLineMarker {

	private static final String EXTENSIONS_PATH = "extensions";

	public TaraIntentionInstanceLineMarker(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		super(daemonSettings, colorsManager);
	}

	@Override
	public LineMarkerInfo getLineMarkerInfo(@NotNull final PsiElement element) {
		if (canBeMarked(element)) {
			PsiElement reference = element instanceof Concept ? resolveExternal((Concept) element) : resolveExternal((TaraFacetTarget) element);
			if (reference != null) {
				final Icon icon = AllIcons.Gutter.ImplementedMethod;
				return new LineMarkerInfo(element, element.getTextRange(), icon, Pass.UPDATE_ALL, markerType.getTooltip(),
					markerType.getNavigationHandler(), GutterIconRenderer.Alignment.LEFT);
			}
		}
		return super.getLineMarkerInfo(element);
	}

	protected final boolean canBeMarked(PsiElement element) {
		Node metaConcept;
		if (element instanceof Concept) {
			metaConcept = TaraUtil.getMetaConcept((Concept) element);
			return metaConcept != null && metaConcept.is(INTENTION);
		} else if (element instanceof TaraFacetTarget) {
			Concept concept = TaraPsiImplUtil.getConceptContainerOf(element);
			metaConcept = TaraUtil.getMetaConcept(concept);
			return metaConcept != null && metaConcept.is(INTENTION);
		}
		return false;
	}

	protected final String buildDestinyClassQN(Concept concept, Project project) {
		return project.getName().toLowerCase() + DOT + EXTENSIONS_PATH + DOT + concept.getName() + concept.getType();
	}

	protected final String getFacetPackage(Concept concept, Inflector inflector) {
		return concept.getProject().getName().toLowerCase() + DOT + EXTENSIONS_PATH + DOT +
			inflector.plural(concept.getType()).toLowerCase() + DOT + inflector.plural(concept.getName()).toLowerCase();
	}

	protected String composeClassName(TaraFacetTarget target, String intention) {
		if (target.getIdentifierReference() == null) return "";
		List<TaraIdentifier> identifiers = target.getIdentifierReference().getIdentifierList();
		return DOT + identifiers.get(identifiers.size() - 1).getName() + intention;//TODO Es asi o la jerarquia de la referencia
	}

	protected PsiElement resolveExternal(TaraFacetTarget target) {
		Concept concept = TaraPsiImplUtil.getConceptContainerOf(target);
		if (concept == null || concept.getName() == null) return null;
		String facetPackage = getFacetPackage(concept, TaraUtil.getInflector(ModuleProvider.getModuleOf(target)));
		return ReferenceManager.resolveJavaClassReference(target.getProject(), facetPackage + composeClassName(target, concept.getType()));
	}
}
