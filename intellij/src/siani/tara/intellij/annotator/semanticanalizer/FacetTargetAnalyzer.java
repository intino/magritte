package siani.tara.intellij.annotator.semanticanalizer;

import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.TaraIdentifier;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.Node;

import java.util.List;

import static com.intellij.psi.JavaPsiFacade.getInstance;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.WARNING;
import static siani.tara.intellij.lang.psi.impl.TaraUtil.getInflector;
import static siani.tara.lang.Annotation.META_FACET;

public class FacetTargetAnalyzer extends TaraAnalyzer {

	private static final String INTENTIONS = "intentions";
	private static final String INTENTION = "Intention";
	private final TaraFacetTarget target;

	public FacetTargetAnalyzer(TaraFacetTarget target) {
		this.target = target;
	}

	@Override
	public void analyze() {
		Concept container = TaraPsiImplUtil.getConceptContainerOf(target);
		if (container == null) return;
		PsiElement resolve = ReferenceManager.resolve(target.getIdentifierReference());
		if (resolve == null) return;
		Concept contextOf = TaraPsiImplUtil.getConceptContainerOf(resolve);
		if (contextOf != null && contextOf.equals(container))
			results.put(target, new AnnotateAndFix(ERROR, MessageProvider.message("target.not.allowed")));
		if (hasErrors()) return;
		Node metaConcept = findMetaConcept(container);
		if (!container.isFacet() && (metaConcept != null && !metaConcept.is(META_FACET)))
			results.put(target, new AnnotateAndFix(ERROR, MessageProvider.message("target.not.allowed")));
		if (hasErrors()) return;
		if (metaConcept != null)
			if (!container.isSub() && !container.isFacet() && !metaConcept.getObject().is(META_FACET))
				results.put(target, new AnnotateAndFix(ERROR, MessageProvider.message("target.in.nofacet.concept")));
		if (hasErrors()) return;
		if (isDuplicated(container))
			results.put(target, new AnnotateAndFix(ERROR, MessageProvider.message("duplicated.target.in.facet")));
		if (container.isIntention())
			analyzeCreatedClasses(container);
	}

	private boolean isDuplicated(Concept container) {
		int count = 0;
		Concept destiny = ReferenceManager.resolveToConcept(target.getIdentifierReference());
		if (destiny == null) return false;
		for (TaraFacetTarget facetTarget : container.getFacetTargets())
			if (destiny.equals(ReferenceManager.resolveToConcept(facetTarget.getIdentifierReference())))
				count++;
		return count > 1;
	}

	private void analyzeCreatedClasses(Concept parent) {
		Module module = ModuleProvider.getModuleOf(parent);
		String qn = getPackage(parent, module) + composeClassName(parent);
		PsiClass aClass = getInstance(parent.getProject()).findClass(qn, GlobalSearchScope.moduleWithLibrariesScope(module));
		if (aClass == null)
			results.put(target, new AnnotateAndFix(WARNING, MessageProvider.message("intention.class.not.created")));
	}

	private String composeClassName(Concept parent) {
		List<TaraIdentifier> route = target.getIdentifierReference().getIdentifierList();
		String name = "";
		for (TaraIdentifier identifier : route)
			name += "." + identifier + parent.getName() + INTENTION;
		return name;

	}

	private String getPackage(Concept parent, Module module) {
		String projectName = parent.getProject().getName();
		String basePath = projectName.toLowerCase() + "." + INTENTIONS;
		return basePath + "." + getInflector(module).plural(parent.getName()).toLowerCase();
	}
}
