package tara.intellij.codeinsight.linemarkers;

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
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.engine.formatters.PluralFormatter;
import org.siani.itrules.engine.formatters.PluralInflector;
import tara.intellij.MessageProvider;
import tara.intellij.lang.psi.TaraNode;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.language.model.Facet;
import tara.language.model.Node;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static tara.intellij.lang.psi.resolve.ReferenceManager.resolveJavaClassReference;

public class FacetApplyMarker extends JavaLineMarkerProvider {

	private static final String FACETS_PATH = "extensions";
	private static final String DOT = ".";
	private final MarkerType markerType = new MarkerType(element -> {
		if (!Node.class.isInstance(element)) return null;
		Node node = (Node) element;
		List<PsiElement> references = getFacetClasses(node);
		String start = (references.size() == 1 ? "Facet" : "Facets") + " declared in ";
		@NonNls String pattern = "";
		if (references.isEmpty()) return "";
		for (PsiElement reference : references)
			pattern += ", " + reference.getNavigationElement().getContainingFile().getName();
		pattern = pattern.substring(2);
		return GutterIconTooltipHelper.composeText(references.toArray(new PsiElement[references.size()]), start, pattern);
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
		List<NavigatablePsiElement> navigatables = facetClasses.stream().map(facetClass -> (NavigatablePsiElement) facetClass).collect(Collectors.toList());
		return navigatables.toArray(new NavigatablePsiElement[navigatables.size()]);
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
			return new LineMarkerInfo(element, element.getTextRange(), icon, Pass.UPDATE_ALL, markerType.getTooltip(),
				markerType.getNavigationHandler(), GutterIconRenderer.Alignment.LEFT);
		} else return super.getLineMarkerInfo(element);
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
		TaraFacet facet = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf((PsiElement) apply));
		if (facet == null) return null;
		return new PluralFormatter(Locale.ENGLISH).getInflector();
	}

	private String getFacetPackage(Node node) {
		return (((TaraNode) node).getProject().getName() + DOT + FACETS_PATH).toLowerCase();
	}
}
