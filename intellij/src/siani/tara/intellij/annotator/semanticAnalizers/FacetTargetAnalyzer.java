package siani.tara.intellij.annotator.semanticAnalizers;

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
import siani.tara.lang.Annotation;
import siani.tara.lang.Node;

import java.util.List;

import static com.intellij.psi.JavaPsiFacade.getInstance;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.WARNING;
import static siani.tara.intellij.lang.psi.impl.TaraUtil.getInflector;

public class FacetTargetAnalyzer extends TaraAnalyzer {

	private static final String INTENTIONS = "intentions";
	private static final String INTENTION = "Intention";
	private final TaraFacetTarget target;

	public FacetTargetAnalyzer(TaraFacetTarget target) {
		this.target = target;
	}

	@Override
	public void analyze() {
		Concept parent = TaraPsiImplUtil.getConceptContainerOf(target);
		if (parent == null) return;
		PsiElement resolve = ReferenceManager.resolve(target.getIdentifierReference());
		if (resolve == null) return;
		Concept contextOf = TaraPsiImplUtil.getConceptContainerOf(resolve);
		if (contextOf != null && contextOf.equals(parent))
			results.put(target, new AnnotateAndFix(ERROR, MessageProvider.message("target.not.allowed")));
		if (hasErrors()) return;
		Node metaConcept = findMetaConcept(parent);
		if (metaConcept != null)
			if (!parent.isSub() && !parent.isFacet() && !metaConcept.getObject().is(Annotation.META_FACET))
				results.put(target, new AnnotateAndFix(ERROR, MessageProvider.message("target.in.nofacet.concept")));
		if (hasErrors()) return;
		if (parent.isIntention())
			analyzeCreatedClasses(parent);
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
