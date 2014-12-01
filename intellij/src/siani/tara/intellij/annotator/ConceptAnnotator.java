package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.fix.AddAddressFix;
import siani.tara.intellij.annotator.fix.RemoveConceptFix;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.lang.Model;
import siani.tara.lang.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static siani.tara.lang.Annotations.Annotation.*;

public class ConceptAnnotator extends TaraAnnotator {


	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!Concept.class.isInstance(element)) return;
		this.holder = holder;
		Concept concept = (Concept) element;
		if (isRootSub(concept)) {
			annotateAndFix(element, new RemoveConceptFix(concept), MessageProvider.message("concept.position.key.error.message"));
			return;
		}
		Model model = TaraLanguage.getMetaModel(concept.getContainingFile());
		addRootAnnotation(concept);
		checkIfDuplicated(concept);
		checkIfExtendedFromDifferentType(concept);
		checkConceptName(concept, isTerminal(concept.getFile()));
		if (model != null) {
			Node node = findNode(concept, model);
			if (node == null) return;
			checkJavaClassCreation(node, concept);
			checkAsComponent(node, concept);
			checkAsFacet(node, concept);
			checkAddressAdded(node, concept);
			checkAsNamed(node, concept);
		}
	}

	private void checkConceptName(Concept concept, boolean terminal) {
		if (concept.getName() != null && Character.isLowerCase(concept.getName().charAt(0)) && !terminal)
			holder.createWarningAnnotation(concept.getIdentifierNode(), "Concept should be in camel-case");
	}

	private void checkAsNamed(Node node, Concept concept) {
		if (node != null && node.getObject().is(NAMED) && concept.getName() == null)
			annotateAndFix(concept.getSignature(), new AddAddressFix(concept), "Name needed");
	}


	private void checkAddressAdded(Node node, Concept concept) {
		if (node != null && node.getObject().is(ADDRESSED) && concept.getAddress() == null)
			annotateAndFix(concept.getSignature(), new AddAddressFix(concept), "Address needed");
	}

	private void checkAsFacet(Node node, Concept concept) {
		if (node == null) return;
		if (node.getObject().is(FACET))
			holder.createErrorAnnotation(concept.getIdentifierNode(), "Facets are no instantiable");
	}

	private void checkJavaClassCreation(Node node, Concept concept) {
		if ((concept.isIntention()) && !javaClassCreated(concept))
			holder.createWarningAnnotation(concept.getSignature().getNode(), MessageProvider.message("no.java.generated.class.error.message"));
		if ((concept.isFacet() && isIntention(node)) && !javaClassCreated(concept))
			holder.createWarningAnnotation(concept.getSignature().getNode(), MessageProvider.message("no.java.generated.class.error.message"));
	}

	private boolean isIntention(Node node) {
		return node.getObject().is(INTENTION);
	}

	private boolean javaClassCreated(Concept concept) {
		return ReferenceManager.resolve(concept.getIdentifierNode()) != null;
	}

	private void checkAsComponent(Node node, Concept concept) {
		if (node.getObject().is(COMPONENT)) {
			Collection<Concept> rootConcepts = TaraUtil.getRootConceptsOfFile(concept.getFile());
			if (rootConcepts.contains(concept) && concept.getIdentifierNode() != null && !isTerminal(concept.getFile()))
				holder.createErrorAnnotation(concept.getIdentifierNode(), "Component cannot be declared as root");
		}
	}

	@SuppressWarnings("deprecation")
	private void addRootAnnotation(Concept concept) {
		Collection<Concept> rootConcepts = TaraUtil.getRootConceptsOfFile(concept.getFile());
		if (rootConcepts.contains(concept) && concept.getIdentifierNode() != null) {
			TextAttributesKey root = createTextAttributesKey("CONCEPT_ROOT", new TextAttributes(null, null, null, null, Font.BOLD));
			holder.createInfoAnnotation(concept.getIdentifierNode(), "Root").setTextAttributes(root);
		}
	}

	private void checkIfExtendedFromDifferentType(Concept concept) {
		if (concept.getSignature().getParentConcept() == null || concept.getType() == null) return;
		if (!concept.getType().equals(concept.getSignature().getParentConcept().getType()))
			annotateAndFix(concept.getSignature().getParentReference(), new RemoveConceptFix(concept), MessageProvider.message("invalid.extension.concept.key.error.message"));
	}

	private boolean isRootSub(Concept element) {
		return (element.isSub() && TaraPsiImplUtil.getConceptContainerOf(element) == null);
	}

	private void checkIfDuplicated(Concept concept) {
		if (concept.getIdentifierNode() != null && findDuplicates(concept) > 1)
			annotateAndFix(concept.getSignature(), new RemoveConceptFix(concept), MessageProvider.message("duplicate.concept.key.error.message"));
	}

	public int findDuplicates(Concept concept) {
		if (concept.getName() == null) return 1;
		Concept parent = TaraPsiImplUtil.getConceptContainerOf(concept);
		if (parent != null) return checkChildDuplicates(concept, parent);
		else {
			PsiElement inFacetTarget = TaraPsiImplUtil.getContextOf(concept);
			if (inFacetTarget != null && inFacetTarget instanceof TaraFacetTarget) {
				List<Concept> innerConceptsInBody = TaraPsiImplUtil.getInnerConceptsInBody(((TaraFacetTarget) inFacetTarget).getBody());
				return countDuplicates(innerConceptsInBody, concept.getName());
			}
		}
		return searchConceptInFile(concept).size();
	}

	private int checkChildDuplicates(Concept concept, Concept parent) {
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
		for (Concept aConcept : getRootConcepts(concept.getFile()))
			//noinspection ConstantConditions
			if (concept.getName().equals(aConcept.getName())) list.add(aConcept);
		return list;
	}

	private Collection<Concept> getRootConcepts(TaraBoxFileImpl file) {
		List<Concept> list = new ArrayList<>();
		Concept[] concepts = PsiTreeUtil.getChildrenOfType(file, Concept.class);
		if (concepts != null)
			for (Concept concept : concepts) {
				list.add(concept);
				list.addAll(concept.getSubConcepts());
			}
		return list;
	}

	private boolean isTerminal(TaraBoxFileImpl boxFile) {
		return ModuleConfiguration.getInstance(TaraUtil.getModuleOfFile(boxFile)).isTerminal();
	}
}
