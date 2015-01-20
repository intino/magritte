package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiElement;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.annotator.fix.AddAddressFix;
import siani.tara.intellij.annotator.fix.RemoveConceptFix;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static siani.tara.intellij.MessageProvider.message;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.WARNING;
import static siani.tara.lang.Annotation.*;

public class ConceptAnalyzer extends TaraAnalyzer {

	private static final String FACET_PATH = "extensions";
	private static final String INTENTION_PATH = "intentions";
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
		if (hasErrors()) return;
		analyzeAnnotationsConstrains();
		Node node = getMetaConcept(concept);
		if (node != null) {
			if (!hasErrors()) analyzeMetaAnnotationConstrains(node);
			if (!hasErrors()) analyzeMetaMetaAnnotationConstrains(node);
			if (!hasErrors()) analyzeAddressAdded(node);
			if (!hasErrors()) analyzeConceptName(concept, TaraUtil.isTerminalBox(concept.getFile()));
		}
		if (!hasErrors()) analyzeJavaClassCreation(node, concept);
	}

	private void analyzeAnnotationsConstrains() {
		if (concept.isFacet() && concept.getFacetTargets().isEmpty())
			results.put(concept.getSignature(), addError(message("facet.target.missed")));
		else if (concept.isSub() && !concept.getFacetTargets().isEmpty())
			results.put(concept.getSignature(), addError(message("facets.not.allowed")));
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
		Module moduleOfFile = ModuleProvider.getModuleOf(concept.getContainingFile());
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
			results.put(concept.getSignature(), new AnnotateAndFix(WARNING, message("no.java.generated.class")));
		if (shouldHaveClass(node, concept) && !isFacetClassCreated(concept))
			results.put(concept.getSignature(), new AnnotateAndFix(WARNING, message("no.facet.java.generated.class")));
	}

	private boolean shouldHaveClass(Node node, Concept concept) {
		if (concept == null || node == null) return false;
		return (concept.isFacet() || node.is(META_FACET)) && node.is(INTENTION);
	}

	private void analyzeMetaAnnotationConstrains(Node node) {
		if (!analyzeAsComponent(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Component cannot be declared as root"));
		else if (!isFacet(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Facets are no instantiable"));
		else if (!analyzeAsNamed(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Name required", new AddAddressFix(concept)));
		else if (!analyzeAsProperty(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Name required", new AddAddressFix(concept)));
		else if (!analyzeAddressAdded(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Address required", new AddAddressFix(concept)));
		else if (concept.isIntention() && !analyzeAsIntention())
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, message("intention.with.children")));
	}

	private void analyzeMetaMetaAnnotationConstrains(Node node) {
		if (node.getObject().is(META_FACET)) {
			if (concept.getSubConcepts().isEmpty() && concept.getFacetTargets().isEmpty())
				results.put(concept.getSignature(), addError(message("facet.target.missed")));
			else if (!concept.getSubConcepts().isEmpty() && !concept.getFacetTargets().isEmpty())
				results.put(concept.getSignature(), addError(message("abstract.facet.has.no.targets")));
		}
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
		return ReferenceManager.resolveJavaClassReference(concept.getProject(), INTENTION_PATH + "." + concept.getName() + "Intention") != null;
	}

	private boolean isFacetClassCreated(Concept concept) {
		return ReferenceManager.resolveJavaClassReference(concept.getProject(), getPackage(concept) + "." + concept.getName() + concept.getType()) != null;
	}

	private String getPackage(Concept concept) {
		return concept.getProject().getName() + "." + FACET_PATH;
	}
}
