package siani.tara.intellij.lang.psi.impl;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.*;

import java.util.*;
import java.util.stream.Collectors;

public class VariantsManager {

	private final Set<Node> variants;
	private final PsiElement myElement;
	private final List<Identifier> context;

	public VariantsManager(Set<Node> variants, PsiElement myElement) {
		this.variants = variants;
		this.myElement = myElement;
		this.context = solveIdentifierContext();
	}

	public void resolveVariants() {
		if (hasContext()) addContextVariants();
		else {
			addInModelVariants();
			addImportVariants();
		}
		if (isExtendsReference((IdentifierReference) myElement.getParent()))
			variants.removeAll(collectUnacceptableNodes());

	}

	private List<Node> collectUnacceptableNodes() {
		List<Node> unacceptables = new ArrayList<>();
		final Node containerNodeOf = TaraPsiImplUtil.getContainerNodeOf(myElement);
		if (containerNodeOf == null) return Collections.emptyList();
		unacceptables.addAll(variants.stream().
			filter(variant -> variant.getType() != null && !variant.getType().equals(containerNodeOf.getType())).
			collect(Collectors.toList()));
		return unacceptables;

	}

	private boolean hasContext() {
		return getContext().indexOf((Identifier) myElement) != 0;
	}

	@NotNull
	private List<? extends Identifier> getContext() {
		return ((IdentifierReference) myElement.getParent()).getIdentifierList();
	}

	private void addContextVariants() {
		final List<Identifier> context = (List<Identifier>) getContext();
		final PsiElement resolve = ReferenceManager.resolve(context.get(context.size() - 2));
		if (resolve == null) return;
		final Node containerNodeOf = TaraPsiImplUtil.getContainerNodeOf(resolve);
		if (containerNodeOf == null) return;
		variants.addAll(containerNodeOf.getIncludes());
	}

	private void addInModelVariants() {
		TaraModel model = (TaraModel) myElement.getContainingFile();
		if (model == null) return;
		model.getIncludes().stream().
			filter(node -> !node.equals(TaraPsiImplUtil.getContainerNodeOf(myElement))).
			forEach(node -> resolvePathFor(node, context));
		addMainConcepts(model);
	}

	private void addImportVariants() {
		Collection<Import> imports = ((TaraModel) myElement.getContainingFile()).getImports();
		for (Import anImport : imports) {
			PsiElement resolve = resolveImport(anImport);
			if (resolve == null || !TaraModel.class.isInstance(resolve)) continue;
			((TaraModel) resolve).getIncludes().stream().filter(node -> !node.equals(TaraPsiImplUtil.getContainerNodeOf(myElement))).forEach(node -> resolvePathFor(node, context));
			addMainConcepts((TaraModel) resolve);
		}
	}

	private PsiElement resolveImport(Import anImport) {
		List<TaraIdentifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
		return ReferenceManager.resolve(importIdentifiers.get(importIdentifiers.size() - 1));
	}

	private void addMainConcepts(TaraModel model) {
		TaraUtil.getAllNodesOfFile(model).stream().filter(node -> !variants.contains(node) && node.isMain()).forEach(node -> resolvePathFor(node, context));
	}

	private void resolvePathFor(Node node, List<Identifier> path) {
		List<Node> childrenOf = TaraPsiImplUtil.getInnerNodesOf(node);
		if (node == null || node.getType() == null) return;
		if (path.isEmpty()) variants.add(node);
		else if (path.get(0).getText().equals(node.getName()))
			for (Node child : childrenOf)
				resolvePathFor(child, path.subList(1, path.size()));
	}

	public final List<Identifier> solveIdentifierContext() {
		List<? extends Identifier> list = ((IdentifierReference) myElement.getParent()).getIdentifierList();
		return (List<Identifier>) list.subList(0, list.size() - 1);
	}

	private boolean isExtendsReference(IdentifierReference reference) {
		return reference.getParent() instanceof Signature;
	}
}
