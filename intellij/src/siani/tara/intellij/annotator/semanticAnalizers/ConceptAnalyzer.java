package siani.tara.intellij.annotator.semanticAnalizers;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.annotator.fix.AddAddressFix;
import siani.tara.intellij.annotator.fix.RemoveConceptFix;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static siani.tara.intellij.MessageProvider.message;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;
import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.WARNING;
import static siani.tara.lang.Annotations.Annotation.*;

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
			results.put(concept.getSignature(),
				addError(message("duplicate.concept")));
		else if (analyzeIfExtendedFromDifferentType(concept)) {
			results.put(concept.getSignature().getParentReference(), addError(message("invalid.extension.concept")));
			return;
		}

		Node node = getMetaConcept();
		if (node == null) return;

		analyzeAnnotationConstrains(node);
		if (!hasErrors()) analyzeAddressAdded(node);
		if (!hasErrors()) analyzeConceptName(concept, TaraUtil.isTerminalBox(concept.getFile()));
		analyzeJavaClassCreation(node, concept);
	}

	private AnnotateAndFix addError(String message) {
		return new AnnotateAndFix(ERROR, message, new RemoveConceptFix(concept));
	}

	private Node getMetaConcept() {
		Model model = getMetamodel(concept);
		if (model == null) return null;
		return TaraUtil.findNode(concept, model);
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

	private boolean analyzeIfExtendedFromDifferentType(Concept concept) {
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

	private void analyzeAnnotationConstrains(Node node) {
		if (!analyzeAsComponent(node))
			results.put(concept.getIdentifierNode(), new AnnotateAndFix(ERROR, "Component cannot be declared as root"));
		else if (!isFacet(node))
			results.put(concept.getIdentifierNode(), new AnnotateAndFix(ERROR, "Facets are no instantiable"));
		else if (analyzeAsNamed(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Name required", new AddAddressFix(concept)));
		else if (analyzeAddressAdded(node))
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, "Address required", new AddAddressFix(concept)));
		else if (analyzeAsIntention())
			results.put(concept.getSignature(), new AnnotateAndFix(ERROR, message("intention.with.children")));
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
		return (hasLinks(concept) || hasNoComponents(concept));
	}

	private boolean analyzeAddressAdded(Node node) {
		return !(node.getObject().is(ADDRESSED) && concept.getAddress() == null);
	}

	private boolean hasNoComponents(Concept concept) {
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
