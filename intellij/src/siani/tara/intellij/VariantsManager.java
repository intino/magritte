package siani.tara.intellij;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class VariantsManager {

	private final Set<Concept> variants;
	private final PsiElement myElement;
	private final List<Identifier> context;

	public VariantsManager(Set<Concept> variants, PsiElement myElement) {
		this.variants = variants;
		this.myElement = myElement;
		this.context = solveIdentifierContext();
	}

	public void resolveVariants() {
		addContextVariants();
		addInBoxVariants();
		addImportVariants();
	}

	private void addContextVariants() {
		Concept contextOf = TaraPsiImplUtil.getConceptContainerOf(TaraPsiImplUtil.getConceptContainerOf(myElement));
		if (contextOf == null) return;
		for (Concept concept : TaraPsiImplUtil.getInnerConceptsOf(contextOf))
			resolvePathFor(concept, context);
	}

	private void addInBoxVariants() {
		TaraBoxFile box = ((TaraBoxFile) myElement.getContainingFile());
		if (box == null) return;
		for (Concept concept : box.getConcepts())
			if (!concept.equals(TaraPsiImplUtil.getConceptContainerOf(myElement)))
				resolvePathFor(concept, context);
		addAggregatedConcepts(box);
	}

	private void addImportVariants() {
		Collection<Import> imports = ((TaraBoxFile) myElement.getContainingFile()).getImports();
		for (Import anImport : imports) {
			PsiElement resolve = resolveImport(anImport);
			if (resolve == null || !TaraBoxFile.class.isInstance(resolve)) continue;
			for (Concept concept : ((TaraBoxFile) resolve).getConcepts())
				if (!concept.equals(TaraPsiImplUtil.getConceptContainerOf(myElement)))
					resolvePathFor(concept, context);
			addAggregatedConcepts((TaraBoxFile) resolve);
		}
	}

	private PsiElement resolveImport(Import anImport) {
		List<TaraIdentifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
		return ReferenceManager.resolve(importIdentifiers.get(importIdentifiers.size() - 1));
	}

	private void addAggregatedConcepts(TaraBoxFile box) {
		for (Concept concept : TaraUtil.getAllConceptsOfFile(box))
			if (!variants.contains(concept) && concept.isAggregated())
				resolvePathFor(concept, context);
	}

	private void resolvePathFor(Concept concept, List<Identifier> path) {
		List<Concept> childrenOf = TaraPsiImplUtil.getInnerConceptsOf(concept);
		if (concept == null || concept.getType() == null) return;
		if (path.isEmpty()) variants.add(concept);
		else if (path.get(0).getText().equals(concept.getName()))
			for (Concept child : childrenOf)
				resolvePathFor(child, path.subList(1, path.size()));
	}

	public List<Identifier> solveIdentifierContext() {
		if (myElement.getParent() instanceof IdentifierReference) {
			List<? extends Identifier> list = ((IdentifierReference) myElement.getParent()).getIdentifierList();
			return (List<Identifier>) list.subList(0, list.size() - 1);
		}
		if (myElement.getParent() instanceof TaraHeaderReference) {
			List<? extends Identifier> list = ((TaraHeaderReference) myElement.getParent()).getIdentifierList();
			return (List<Identifier>) list.subList(0, list.size() - 1);
		}
		return null;
	}
}
