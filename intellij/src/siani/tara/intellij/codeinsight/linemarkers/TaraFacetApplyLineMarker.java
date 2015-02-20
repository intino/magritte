package siani.tara.intellij.codeinsight.linemarkers;

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
import com.intellij.util.Function;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.siani.itrules.formatter.Inflector;
import org.siani.itrules.formatter.InflectorFactory;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static siani.tara.intellij.lang.psi.impl.ReferenceManager.resolveJavaClassReference;

public class TaraFacetApplyLineMarker extends JavaLineMarkerProvider {

	private static final String FACETS_PATH = "extensions";
	private final MarkerType OVERRIDDEN_PROPERTY_TYPE = new MarkerType(new Function<PsiElement, String>() {
		@Nullable
		@Override
		public String fun(PsiElement element) {
			if (!Concept.class.isInstance(element)) return null;
			Concept concept = (Concept) element;
			List<PsiElement> references = getFacetClasses(concept);
			String start = (references.size() == 1 ? "Facet" : "Facets") + " declared in ";
			@NonNls String pattern = "";
			if (references.isEmpty()) return "";
			for (PsiElement reference : references)
				pattern += ", " + reference.getNavigationElement().getContainingFile().getName();
			pattern = pattern.substring(2);
			return GutterIconTooltipHelper.composeText(references.toArray(new PsiElement[references.size()]), start, pattern);
		}
	}, new LineMarkerNavigator() {
		@Override
		public void browse(MouseEvent e, PsiElement element) {
			if (!(element instanceof Concept)) return;
			Concept concept = (Concept) element;
			if (DumbService.isDumb(element.getProject())) {
				DumbService.getInstance(element.getProject()).showDumbModeNotification("Navigation to implementation classes is not possible during index update");
				return;
			}
			List<PsiElement> facetClasses = getFacetClasses(concept);
			if (facetClasses.isEmpty()) return;
			String title = MessageProvider.message("facet.class.chooser", concept.getName(), facetClasses.size());
			ClassCellRenderer renderer = new ClassCellRenderer(null);
			PsiElementListNavigator.openTargets(e, facetClasses.toArray(toNavigatable(facetClasses)), title,
				"Facet implementations of " + (concept.getName()), renderer);
		}
	}
	);

	private NavigatablePsiElement[] toNavigatable(List<PsiElement> facetClasses) {
		List<NavigatablePsiElement> navigatables = new ArrayList<>();
		for (PsiElement facetClass : facetClasses) navigatables.add((NavigatablePsiElement) facetClass);
		return navigatables.toArray(new NavigatablePsiElement[navigatables.size()]);
	}

	private List<PsiElement> getFacetClasses(Concept concept) {
		List<PsiElement> references = new ArrayList<>();
		for (FacetApply apply : concept.getFacetApplies()) {
			PsiElement reference = resolveExternal(concept, apply);
			if (reference != null)
				references.add(reference);
		}
		return references;
	}


	public TaraFacetApplyLineMarker(DaemonCodeAnalyzerSettings daemonSettings, EditorColorsManager colorsManager) {
		super(daemonSettings, colorsManager);
	}

	@Override
	public LineMarkerInfo getLineMarkerInfo(@NotNull final PsiElement element) {
		if (!(element instanceof Concept)) return super.getLineMarkerInfo(element);
		Concept concept = (Concept) element;
		if (concept.getFacetApplies().length == 0) return null;
		PsiElement reference = null;
		for (FacetApply facetApply : concept.getFacetApplies()) {
			reference = resolveExternal(concept, facetApply);
			if (reference != null) break;
		}
		if (reference != null) {
			final Icon icon = AllIcons.Gutter.ImplementedMethod;
			final MarkerType type = OVERRIDDEN_PROPERTY_TYPE;
			return new LineMarkerInfo(element, element.getTextRange(), icon, Pass.UPDATE_ALL, type.getTooltip(),
				type.getNavigationHandler(), GutterIconRenderer.Alignment.LEFT);
		} else return super.getLineMarkerInfo(element);
	}

	private PsiElement resolveExternal(Concept concept, FacetApply apply) {
		return resolveJavaClassReference(concept.getProject(), getFacetApplyPackage(concept, apply) + "." + concept.getName() + apply.getFacetName());
	}

	private String getFacetApplyPackage(Concept concept, FacetApply apply) {
		Inflector inflector = getInflector(apply);
		if (inflector == null) return "";
		return (getFacetPackage(concept) + "." + inflector.plural(apply.getFacetName())).toLowerCase();
	}

	private Inflector getInflector(FacetApply apply) {
		return InflectorFactory.getInflector(ModuleConfiguration.getInstance(ModuleProvider.getModuleOf(apply)).getLanguage());
	}

	private String getFacetPackage(Concept concept) {
		return (concept.getProject().getName() + "." + FACETS_PATH).toLowerCase();
	}
}
