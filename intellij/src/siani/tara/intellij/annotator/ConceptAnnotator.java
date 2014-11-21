package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.fix.AddAddressFix;
import siani.tara.intellij.annotator.fix.RemoveConceptFix;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFacetTarget;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;
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
		checkJavaClassCreation(model, concept);
		if (model != null) {
			checkAsComponent(model, concept);
			checkAsFacet(model, concept);
			checkAddressAdded(model, concept);
			checkAsNamed(model, concept);
		}
	}

	private void checkAsNamed(Model model, Concept concept) {
		Node node = findNode(concept, model);
		if (node != null && node.getObject().is(NAMED) && concept.getName() == null)
			annotateAndFix(concept.getSignature(), new AddAddressFix(concept), "Name needed");
	}

	private void checkAddressAdded(Model model, Concept concept) {
		Node node = findNode(concept, model);
		if (node != null && node.getObject().is(ADDRESSED) && concept.getAddress() == null)
			annotateAndFix(concept.getSignature(), new AddAddressFix(concept), "Address needed");
	}

	private void checkAsFacet(Model model, Concept concept) {
		Node node = findNode(concept, model);
		if (node == null) return;
		if (node.getObject().is(FACET))
			holder.createErrorAnnotation(concept.getIdentifierNode(), "Facets are no instantiable");
	}

	private void checkJavaClassCreation(Model model, Concept concept) {
		if ((concept.isIntention()) && !javaClassCreated(concept))
			holder.createWarningAnnotation(concept.getSignature().getNode(), MessageProvider.message("no.java.generated.class.error.message"));
		if ((concept.isFacet() && isIntentionInstance(model, concept)) && !javaClassCreated(concept))
			holder.createWarningAnnotation(concept.getSignature().getNode(), MessageProvider.message("no.java.generated.class.error.message"));
	}

	private boolean isIntentionInstance(Model model, Concept concept) {
		if(model == null) return false;
		Node node = findNode(concept, model);
		return node != null && node.getObject().is(INTENTION);
	}

	private boolean javaClassCreated(Concept concept) {
		return ReferenceManager.resolve(concept.getIdentifierNode()) != null;
	}

	private void checkAsComponent(Model model, Concept concept) {
		Node node = findNode(concept, model);
		if (node == null) return;
		if (node.getObject().is(COMPONENT)) {
			Collection<Concept> rootConcepts = TaraUtil.getRootConceptsOfFile(concept.getFile());
			if (rootConcepts.contains(concept) && concept.getIdentifierNode() != null && !isTerminalModule(concept))
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
		if (parent != null)
			return checkChildDuplicates(concept, parent);
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
		for (Concept aConcept : concept.getFile().getConcepts())
			if (concept.getName().equals(aConcept.getName())) list.add(aConcept);
		return list;
	}

	public boolean isTerminalModule(Concept concept) {
		return ModuleConfiguration.getInstance(ModuleProvider.getModuleOfDocument(concept.getFile())).isTerminal();
	}
}
