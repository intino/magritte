package io.intino.tara.plugin.codeinsight.linemarkers;

import com.intellij.codeHighlighting.Pass;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzerSettings;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.impl.*;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.project.DumbService;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.ui.ClassCellRenderer;
import io.intino.itrules.formatters.StringFormatters.PluralInflector;
import io.intino.itrules.formatters.inflectors.EnglishPluralInflector;
import io.intino.tara.lang.model.Facet;
import io.intino.tara.lang.model.Node;
import io.intino.tara.plugin.lang.psi.TaraNode;
import io.intino.tara.plugin.messages.MessageProvider;
import io.intino.tara.plugin.project.IntinoModuleType;
import io.intino.tara.plugin.project.module.ModuleProvider;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static io.intino.tara.plugin.lang.psi.resolve.ReferenceManager.resolveJavaClassReference;

public class FacetApplyMarker extends JavaLineMarkerProvider {

	private static final String FACETS_PATH = "extensions";
	private static final String DOT = ".";
	private final MarkerType markerType = new MarkerType("Unknown", element -> {
		if (!(element instanceof Node)) return null;
		Node node = (Node) element;
		List<PsiElement> references = getFacetClasses(node);
		String start = (references.size() == 1 ? "Facet" : "Facets") + " declared in ";
		@NonNls StringBuilder pattern = new StringBuilder();
		if (references.isEmpty()) return "";
		for (PsiElement reference : references)
			pattern.append(", ").append(reference.getNavigationElement().getContainingFile().getName());
		pattern = new StringBuilder(pattern.substring(2));
		return GutterIconTooltipHelper.composeText(references.toArray(new PsiElement[0]), start, pattern.toString());
	}, new LineMarkerNavigator() {
		@Override
		public void browse(MouseEvent e, PsiElement element) {
			if (!(element instanceof Node)) return;
			Node node = (Node) element;
			if (DumbService.isDumb(element.getProject())) {
				DumbService.getInstance(element.getProject()).showDumbModeNotification("Navigation to implementation classes is not possible during index update");
				return;
			}
			List<PsiElement> facetClasses = getFacetClasses(node);
			if (facetClasses.isEmpty()) return;
			String title = MessageProvider.message("facet.class.chooser", node.name(), facetClasses.size());
			ClassCellRenderer renderer = new ClassCellRenderer(null);
			PsiElementListNavigator.openTargets(e, facetClasses.toArray(toNavigatable(facetClasses)), title,
					"Facet implementations of " + (node.name()), renderer);
		}
	}
	);

	public FacetApplyMarker(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		super(daemonSettings, colorsManager);
	}

	private NavigatablePsiElement[] toNavigatable(List<PsiElement> facetClasses) {
		return facetClasses.stream().map(facetClass -> (NavigatablePsiElement) facetClass).toArray(NavigatablePsiElement[]::new);
	}

	private List<PsiElement> getFacetClasses(Node node) {
		List<PsiElement> references = new ArrayList<>();
		for (Facet apply : node.facets()) {
			PsiElement reference = resolveExternal(node, apply);
			if (reference != null)
				references.add(reference);
		}
		return references;
	}

	@Override
	public LineMarkerInfo getLineMarkerInfo(@NotNull final PsiElement element) {
		if (!(element instanceof Node)) return super.getLineMarkerInfo(element);
		Node node = (Node) element;
		if (node.facets().isEmpty()) return null;
		PsiElement reference = null;
		for (Facet facetApply : node.facets()) {
			reference = resolveExternal(node, facetApply);
			if (reference != null) break;
		}
		if (reference != null) {
			final Icon icon = AllIcons.Gutter.ImplementedMethod;
			return new LineMarkerInfo(leafOf(element), element.getTextRange(), icon, Pass.UPDATE_ALL, markerType.getTooltip(),
					markerType.getNavigationHandler(), GutterIconRenderer.Alignment.LEFT);
		} else return super.getLineMarkerInfo(element);
	}

	private PsiElement leafOf(@NotNull PsiElement element) {
		PsiElement leaf = element;
		while (leaf.getFirstChild() != null) leaf = leaf.getFirstChild();
		return leaf;
	}

	private PsiElement resolveExternal(Node node, Facet apply) {
		return resolveJavaClassReference(((TaraNode) node).getProject(), getFacetApplyPackage(node, apply) + DOT + node.name() + apply.type());
	}

	private String getFacetApplyPackage(Node node, Facet apply) {
		PluralInflector inflector = getInflector(apply);
		if (inflector == null) return "";
		return (getFacetPackage(node) + DOT + inflector.plural(apply.type())).toLowerCase();
	}

	private PluralInflector getInflector(Facet apply) {
		return !IntinoModuleType.isIntino(ModuleProvider.moduleOf((PsiElement) apply)) ? null : new EnglishPluralInflector();
	}

	private String getFacetPackage(Node node) {
		return (((TaraNode) node).getProject().getName() + DOT + FACETS_PATH).toLowerCase();
	}
}
