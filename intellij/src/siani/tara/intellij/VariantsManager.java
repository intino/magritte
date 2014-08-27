package siani.tara.intellij;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import java.util.Collection;
import java.util.List;

public class VariantsManager {

	private final Collection<Concept> variants;
	private final PsiElement myElement;
	private final List<Identifier> context;

	public VariantsManager(Collection<Concept> variants, PsiElement myElement) {
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
		Concept contextOf = TaraPsiImplUtil.getContextOf(TaraPsiImplUtil.getContextOf(myElement));
		if (contextOf == null) return;
		for (Concept concept : TaraPsiImplUtil.getChildrenOf(contextOf))
			resolvePathFor(concept, context);
	}

	private void addInBoxVariants() {
		List<? extends Identifier> boxPath = ((TaraBoxFile) myElement.getContainingFile()).getBoxPath();
		TaraBoxFile box = (TaraBoxFile) ReferenceManager.resolve(boxPath.get(boxPath.size() - 1), false);
		if (box == null) return;
		for (Concept concept : box.getConcepts()) {
			if (concept.equals(TaraPsiImplUtil.getContextOf(myElement))) continue;
			resolvePathFor(concept, context);
		}
	}

	private void addImportVariants() {
		Import[] imports = ((TaraBoxFile) myElement.getContainingFile()).getImports();
		if (imports == null) return;
		for (Import anImport : imports) {
			List<TaraIdentifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
			PsiElement resolve = ReferenceManager.resolve(importIdentifiers.get(importIdentifiers.size() - 1), false);
			if (resolve == null || !TaraBoxFile.class.isInstance(resolve)) continue;
			for (Concept concept : ((TaraBoxFile) resolve).getConcepts()) {
				if (concept.equals(TaraPsiImplUtil.getContextOf(myElement))) continue;
				resolvePathFor(concept, context);
			}
		}
	}

	private void resolvePathFor(Concept concept, List<Identifier> path) {
		List<Concept> childrenOf = TaraPsiImplUtil.getChildrenOf(concept);
		if (concept == null || concept.getType() == null) return;
		if (path.isEmpty()) {
			variants.add(concept);
		} else if (path.get(0).getText().equals(concept.getName()))
			for (Concept child : childrenOf)
				resolvePathFor(child, path.subList(1, path.size()));
	}

	public List<Identifier> solveIdentifierContext() {
		if (myElement.getParent() instanceof IdentifierReference) {
			IdentifierReference element = (IdentifierReference) myElement.getParent();
			List<? extends Identifier> list = element.getIdentifierList();
			return (List<Identifier>) list.subList(0, list.size() - 1);
		}
		if (myElement.getParent() instanceof TaraHeaderReference) {
			TaraHeaderReference element = (TaraHeaderReference) myElement.getParent();
			List<? extends Identifier> list = element.getIdentifierList();
			return (List<Identifier>) list.subList(0, list.size() - 1);
		}
		return null;
	}
}
