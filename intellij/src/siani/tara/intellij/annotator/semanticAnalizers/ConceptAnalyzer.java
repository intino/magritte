package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiElement;
import org.siani.itrules.formatter.Inflector;
import org.siani.itrules.formatter.InflectorFactory;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.annotator.fix.AddAddressFix;
import siani.tara.intellij.annotator.fix.LinkToJavaFix;
import siani.tara.intellij.annotator.fix.RemoveConceptFix;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.FacetTarget;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static siani.tara.intellij.MessageProvider.message;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.WARNING;
import static siani.tara.intellij.lang.psi.impl.ReferenceManager.resolveJavaClassReference;
import static siani.tara.lang.Annotation.*;

public class ConceptAnalyzer extends TaraAnalyzer {

	private static final String FACET_PATH = "extensions";
	private static final String INTENTIONS = "intentions";
	private Concept concept;

	public ConceptAnalyzer(Concept concept) {
		this.concept = concept;
	}

	@Override
	public void analyze() {
		if (isRootSub()) results.put(concept.getSignature(), new AnnotateAndFix(ERROR, message("sub.bad.position")));
		else if (isDuplicated()) results.put(concept.getSignature(), addError(message("duplicate.concept")));
		else if (!analyzeIfExtendedFromSameType(concept))
			results.put(concept.getSignature().getParentReference(), addError(message("invalid.extension.concept")));
		analyzeFacetConstrains();
		if (hasErrors()) return;
		Node node = findMetaConcept(concept);
		if (node != null) {
			if (!hasErrors()) analyzeMetaAnnotationConstrains(node);
			if (!hasErrors()) analyzeFacetConstrains(node);
			if (!hasErrors()) analyzeAddressAdded(node);
			if (!hasErrors()) analyzeConceptName(concept, TaraUtil.isTerminalBox(concept.getFile()));
		}
		if (!hasErrors()) analyzeJavaClassCreation(node, concept);
	}

	private void analyzeFacetConstrains(Node node) {
		if ((node.is(META_FACET)) && !concept.getSubConcepts().isEmpty() && !concept.getFacetTargets().isEmpty())
			results.put(concept.getSignature(), addError(message("abstract.facet.has.no.targets")));
		Concept parent = concept.getParentConcept();
		if (concept.isSub() && concept.getFacetTargets().isEmpty() && findMetaConcept(parent).is(META_FACET))
			results.put(concept.getSignature(), addError(message("facet.target.missed")));
	}

	private void analyzeFacetConstrains() {
		if (concept.isFacet() && !concept.getSubConcepts().isEmpty() && !concept.getFacetTargets().isEmpty())
			results.put(concept.getSignature(), addError(message("abstract.facet.has.no.targets")));
		Concept parent = concept.getParentConcept();
		if (concept.isSub() && concept.getFacetTargets().isEmpty() && parent.isFacet())
			results.put(concept.getSignature(), addError(message("facet.target.missed")));
	}

	private AnnotateAndFix addError(String message) {
		return new AnnotateAndFix(ERROR, message, new RemoveConceptFix(concept));
	}

	private boolean isRootSub() {
		return concept.isSub() && TaraPsiImplUtil.getConceptContainerOf(concept) == null;
	}

	private void analyzeConceptName(Concept concept, boolean terminal) {
		if (concept.getName() == null) return;
		if (Character.isLowerCase(concept.getName().charAt(0)) && !terminal)
			results.put(concept.getIdentifierNode(), new AnnotateAndFix(WARNING, "Concept should be in camelCase"));
		Model metamodel = getMetamodel(concept);
		if (metamodel != null && metamodel.getIdentifiers().contains(concept.getName()))
			results.put(concept.getIdentifierNode(), new AnnotateAndFix(ERROR, "Concept name not allowed"));
	}

	private boolean isDuplicated() {
		return concept.getIdentifierNode() != null && findDuplicates();
	}

	private boolean analyzeIfExtendedFromSameType(Concept concept) {
		return concept.getSignature().getParentConcept() == null
			|| concept.getType() == null
			|| concept.getType().equals(concept.getSignature().getParentConcept().getType());
	}

	public boolean findDuplicates() {
		if (concept.getName() == null) return false;
		Concept parent = TaraPsiImplUtil.getConceptContainerOf(concept);
		if (parent != null) return analyzeChildDuplicates(parent) > 1;
		else {
			PsiElement inFacetTarget = TaraPsiImplUtil.getContextOf(concept);
			if (inFacetTarget != null && inFacetTarget instanceof TaraFacetTarget) {
				List<Concept> innerConceptsInBody = TaraPsiImplUtil.getInnerConceptsInBody(((TaraFacetTarget) inFacetTarget).getBody());
				return countDuplicates(innerConceptsInBody, concept.getName()) > 1;
			}
		}
		return searchDuplicatesInAllModule(concept);
	}

	private boolean searchDuplicatesInAllModule(Concept concept) {
		Module moduleOfFile = ModuleProvider.getModuleOf(concept);
		int size = 0;
		for (TaraBoxFileImpl file : TaraUtil.getTaraFilesOfModule(moduleOfFile))
			size += searchConceptInFile(concept, file).size();
		return size > 1;
	}


	private int analyzeChildDuplicates(Concept parent) {
		Collection<Concept> innerConceptsOf = concept.isSub() ? parent.getSubConcepts() : TaraPsiImplUtil.getInnerConceptsOf(parent);
		String name = concept.getName();
		return countDuplicates(innerConceptsOf, name);
	}

	private int countDuplicates(Collection<Concept> innerConceptsOf, String name) {
		int duplicates = 0;
		for (Concept taraConcept : innerConceptsOf)
			if (taraConcept.getName() != null && taraConcept.getName().equals(name))
				duplicates++;
		return duplicates;
	}

	private List<Concept> searchConceptInFile(Concept concept, TaraBoxFile containingFile) {
		List<Concept> list = new ArrayList<>();
		for (Concept aConcept : TaraUtil.getRootConceptsOfFile(containingFile))
			//noinspection ConstantConditions
			if (concept.getName().equals(aConcept.getName())) list.add(aConcept);
		return list;
	}

	private void analyzeJavaClassCreation(Node node, Concept concept) {
		if ((concept.isIntention()) && !isIntentionClassCreated(concept))
			results.put(concept.getSignature(), new AnnotateAndFix(WARNING, message("no.java.generated.class"), new LinkToJavaFix(concept)));
		else if (node != null && node.is(INTENTION) && !isFacetClassCreated(concept))
			results.put(concept.getSignature(), new AnnotateAndFix(WARNING, message("no.java.generated.class"), new LinkToJavaFix(concept)));
		else if (shouldHaveFacetTargetClass(node, concept) && !isFacetClassCreated(concept))
			results.put(concept.getSignature(), new AnnotateAndFix(WARNING, message("no.facet.java.generated.class"), new LinkToJavaFix(concept)));
		for (FacetApply facetApply : concept.getFacetApplies())
			if (node != null && isFacetIntentionImplementation(node, facetApply) && !isFacetApplyClassCreated(concept, facetApply.getFacetName()))
				results.put(concept.getSignature(), new AnnotateAndFix(WARNING, message("no.facet.intention.java.generated.class", facetApply.getFacetName()), new LinkToJavaFix(concept)));
	}

	private boolean isFacetIntentionImplementation(Node node, FacetApply facetApply) {
		List<FacetTarget> facetTargets = node.getObject().getAllowedFacets().get(facetApply.getFacetName());
		if (facetTargets == null) return false;
		FacetTarget target = null;
		for (FacetTarget facetTarget : facetTargets)
			if (facetTarget.getDestiny().equals(node.getObject()))
				target = facetTarget;
		return target != null && target.isIntention();
	}

	private boolean shouldHaveFacetTargetClass(Node node, Concept concept) {
		return concept != null && node != null
			&& (concept.isFacet() || node.is(META_FACET))
			&& node.is(siani.tara.lang.Annotation.INTENTION);
	}

	private void analyzeMetaAnnotationConstrains(Node node) {
		if (!analyzeAsComponent(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Component Cannot be Declared as Primary"));
		else if (!isFacet(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Facets are no instantiable"));
		else if (!analyzeAsNamed(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Name required"));
		else if (!analyzeAsProperty(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Name required"));
		else if (!analyzeAddressAdded(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Address required", new AddAddressFix(concept)));
		else if (concept.isIntention() && !analyzeAsIntention())
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, message("intention.with.children")));
		else if ((node.is(INTENTION)) && concept.isAnonymous())
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Name Required in Instances of an Intention"));
		else if (concept.isAnonymous() && hasFacetIntention(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Name Required with Intention Facets"));
		else if (concept.isAnonymous() && hasNamedFacet(node, concept))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Name Required with Named Facets"));
	}

	private boolean hasNamedFacet(Node node, Concept concept) {
		Map<String, List<FacetTarget>> allowedFacets = node.getObject().getAllowedFacets();
		for (FacetApply facetApply : concept.getFacetApplies()) {
			if (allowedFacets.containsKey(facetApply.getFacetName()) && isNamed(getMetamodel(facetApply), allowedFacets.get(facetApply.getFacetName())))
				return true;
		}
		return false;
	}

	private boolean isNamed(Model metamodel, List<FacetTarget> facetTargets) {
		for (FacetTarget facetTarget : facetTargets) {
			Node facetNode = metamodel.get(facetTarget.getFacetQN());
			if (facetNode != null && facetNode.is(NAMED)) return true;
		}
		return false;
	}

	private boolean hasFacetIntention(Node node) {
		for (FacetApply apply : concept.getFacetApplies())
			for (FacetTarget facetTarget : node.getObject().getAllowedFacets().get(apply.getFacetName()))
				if (facetTarget.isIntention()) return true;
		return false;
	}

	private boolean analyzeAsProperty(Node node) {
		if (concept.isProperty()) checkContainsTerminal(concept);
		if (results.size() > 0) return false;
		if (!node.getObject().is(PROPERTY)) return true;
		if (concept.getName() != null && !concept.getName().isEmpty())
			results.put(this.concept.getFirstChild(), new AnnotateAndFix(ERROR, "Properties are unnamed"));
		if (areMultiple())
			results.put(this.concept.getFirstChild(), new AnnotateAndFix(ERROR, "Properties are single"));
		return true;
	}

	private void checkContainsTerminal(Concept concept) {
		if ((concept.isComponent() && concept.isProperty()) && concept.isAggregated())
			results.put(this.concept, new AnnotateAndFix(ERROR, "An aggregated concept cannot be component or property"));
		for (Variable variable : concept.getVariables())
			if (isTerminal(variable)) {
				results.put(this.concept, new AnnotateAndFix(ERROR, "Property concept cannot have terminal variables"));
				return;
			}
		for (Concept inner : concept.getInnerConcepts())
			checkContainsTerminal(inner);
	}

	private boolean isTerminal(Variable variable) {
		if (variable.getAnnotations() == null) return false;
		for (Annotation annotation : variable.getAnnotations().getNormalAnnotations())
			if (annotation.getText().contains(TERMINAL.getName())) return true;
		return false;
	}

	private boolean areMultiple() {
		List<Concept> concepts = new ArrayList<>();
		if (concept.getType() == null) return false;
		for (Concept inner : TaraUtil.getInnerConceptsOf(TaraPsiImplUtil.getConceptContainerOf(concept)))
			if (concept.getType().equals(inner.getType()))
				concepts.add(inner);
		return concepts.size() > 1;
	}

	private boolean analyzeAsComponent(Node node) {
		if (node.getObject().is(COMPONENT)) {
			Collection<Concept> rootConcepts = TaraUtil.getRootConceptsOfFile(concept.getFile());
			if (rootConcepts.contains(concept) && concept.getIdentifierNode() != null && !TaraUtil.isTerminalBox(concept.getFile()))
				return false;
		}
		return true;
	}

	private boolean isFacet(Node node) {
		return !node.getObject().is(FACET);
	}

	private boolean analyzeAsIntention() {
		return !(hasLinks(concept) || hasComponents(concept));
	}

	private boolean analyzeAddressAdded(Node node) {
		return !(node.getObject().is(ADDRESSED) && concept.getAddress() == null);
	}

	private boolean hasComponents(Concept concept) {
		return !concept.getInnerConcepts().isEmpty();
	}

	private boolean hasLinks(Concept concept) {
		return concept.getConceptLinks().length > 0;
	}

	private boolean analyzeAsNamed(Node node) {
		return !(node.getObject().is(NAMED) && concept.getName() == null);
	}

	private boolean isIntentionClassCreated(Concept concept) {
		String projectName = concept.getProject().getName().toLowerCase();
		return resolveJavaClassReference(concept.getProject(), projectName + "." + INTENTIONS + "." + concept.getName() + "Intention") != null;
	}

	private boolean isFacetClassCreated(Concept concept) {
		return resolveJavaClassReference(concept.getProject(), getFacetPackage(concept) + "." + concept.getName() + concept.getType()) != null;
	}

	private boolean isFacetApplyClassCreated(Concept concept, String facetName) {
		return resolveJavaClassReference(concept.getProject(), buildFacetApplyClassQN(concept, facetName)) != null;
	}

	private String buildFacetApplyClassQN(Concept facetedConcept, String facetName) {
		String interfaceName = "";
		for (Concept concept : TaraUtil.buildConceptCompositionPathOf(facetedConcept))
			interfaceName += "." + (hasFacet(concept, facetName) ?
				concept.getName() + facetName :
				concept.getType());
		return getFacetApplyPackage(facetedConcept, facetName) + interfaceName;
	}

	private boolean hasFacet(Concept concept, String facetName) {
		for (FacetApply apply : concept.getFacetApplies())
			if (apply.getFacetName().equals(facetName)) return true;
		return false;
	}

	private String getFacetApplyPackage(Concept concept, String facetName) {
		Inflector inflector = getInflector(concept);
		if (inflector == null) throw new RuntimeException("Inflector not found");
		return (getFacetPackage(concept) + "." + inflector.plural(facetName)).toLowerCase();
	}

	private Inflector getInflector(PsiElement concept) {
		return InflectorFactory.getInflector(ModuleConfiguration.getInstance(ModuleProvider.getModuleOf(concept)).getLanguage());
	}

	private String getFacetPackage(Concept concept) {
		return (concept.getProject().getName() + "." + FACET_PATH).toLowerCase();
	}
}
