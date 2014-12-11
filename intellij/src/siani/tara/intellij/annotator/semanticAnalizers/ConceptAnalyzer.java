package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.annotator.fix.AddAddressFix;
import siani.tara.intellij.annotator.fix.RemoveConceptFix;
import siani.tara.intellij.lang.psi.Annotation;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.Variable;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static siani.tara.intellij.MessageProvider.message;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.WARNING;
import static siani.tara.lang.Annotation.*;

public class ConceptAnalyzer extends TaraAnalyzer {

	private Concept concept;

	public ConceptAnalyzer(Concept concept) {
		this.concept = concept;
	}

	@Override
	public void analyze() {
		if (isRootSub())
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, message("sub.bad.position")));
		else if (isDuplicated())
			results.put(concept.getSignature(), addError(message("duplicate.concept")));
		else if (!analyzeIfExtendedFromSameType(concept)) {
			results.put(concept.getSignature().getParentReference(), addError(message("invalid.extension.concept")));
			return;
		}
		Node node = getMetaConcept(concept);
		if (node == null) return;
		analyzeMetaAnnotationConstrains(node);
		if (!hasErrors()) analyzeAddressAdded(node);
		if (!hasErrors()) analyzeConceptName(concept, TaraUtil.isTerminalBox(concept.getFile()));
		analyzeJavaClassCreation(node, concept);
	}

	private AnnotateAndFix addError(String message) {
		return new AnnotateAndFix(ERROR, message, new RemoveConceptFix(concept));
	}


	private boolean isRootSub() {
		return concept.isSub() && TaraPsiImplUtil.getConceptContainerOf(concept) == null;
	}

	private void analyzeConceptName(Concept concept, boolean terminal) {
		if (concept.getName() != null && Character.isLowerCase(concept.getName().charAt(0)) && !terminal)
			results.put(concept.getIdentifierNode(), new AnnotateAndFix(WARNING, "Concept should be in camelCase"));
	}

	private boolean isDuplicated() {
		return concept.getIdentifierNode() != null && findDuplicates() > 1;
	}

	private boolean analyzeIfExtendedFromSameType(Concept concept) {
		return concept.getSignature().getParentConcept() == null
			|| concept.getType() == null
			|| concept.getType().equals(concept.getSignature().getParentConcept().getType());
	}

	public int findDuplicates() {
		if (concept.getName() == null) return 1;
		Concept parent = TaraPsiImplUtil.getConceptContainerOf(concept);
		if (parent != null) return analyzeChildDuplicates(parent);
		else {
			PsiElement inFacetTarget = TaraPsiImplUtil.getContextOf(concept);
			if (inFacetTarget != null && inFacetTarget instanceof TaraFacetTarget) {
				List<Concept> innerConceptsInBody = TaraPsiImplUtil.getInnerConceptsInBody(((TaraFacetTarget) inFacetTarget).getBody());
				return countDuplicates(innerConceptsInBody, concept.getName());
			}
		}
		return searchConceptInFile(concept).size();
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

	private List<Concept> searchConceptInFile(Concept concept) {
		List<Concept> list = new ArrayList<>();
		for (Concept aConcept : TaraUtil.getRootConceptsOfFile(concept.getFile()))
			//noinspection ConstantConditions
			if (concept.getName().equals(aConcept.getName())) list.add(aConcept);
		return list;
	}

	private void analyzeJavaClassCreation(Node node, Concept concept) {
		if ((concept.isIntention()) && !javaClassCreated(concept))
			results.put(concept.getSignature(), new AnnotateAndFix(WARNING, message("no.java.generated.class")));
		if ((concept.isFacet() && isIntention(node)) && !javaClassCreated(concept))
			results.put(concept.getSignature(), new AnnotateAndFix(WARNING, message("no.java.generated.class")));
	}

	private void analyzeMetaAnnotationConstrains(Node node) {
		if (!analyzeAsComponent(node))
			results.put(concept.getIdentifierNode(), new AnnotateAndFix(ERROR, "Component cannot be declared as root"));
		else if (!isFacet(node))
			results.put(concept.getIdentifierNode(), new AnnotateAndFix(ERROR, "Facets are no instantiable"));
		else if (!analyzeAsNamed(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Name required", new AddAddressFix(concept)));
		else if (!analyzeAsProperty(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Name required", new AddAddressFix(concept)));
		else if (!analyzeAddressAdded(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Address required", new AddAddressFix(concept)));
		else if (concept.isIntention() && !analyzeAsIntention())
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, message("intention.with.children")));
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

	private boolean isIntention(Node node) {
		return node.getObject().is(INTENTION);
	}

	private boolean javaClassCreated(Concept concept) {
		return ReferenceManager.resolve(concept.getIdentifierNode()) != null;
	}
}
