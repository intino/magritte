package siani.tara.intellij.lang.psi.impl;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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
		addContextVariants((Identifier) myElement);
		addInModelVariants();
		addImportVariants();

	}

	private void addContextVariants(Identifier identifier) {
		Node container = TaraPsiImplUtil.getContainerNodeOf(identifier);
		if (container != null && !isExtendsReference((IdentifierReference) identifier.getParent()) &&
			namesAreEqual(identifier, container))
			variants.add(container);
		while (container != null) {
			for (Node sibling : container.getNodeSiblings())
				variants.add(sibling);
			container = container.container();
		}
	}

	private static boolean isExtendsReference(IdentifierReference reference) {
		return reference.getParent() instanceof Signature;
	}

	private static boolean namesAreEqual(Identifier identifier, Node node) {
		return identifier.getText().equals(node.getName());
	}

	private void addInModelVariants() {
		TaraModel model = (TaraModel) myElement.getContainingFile();
		if (model == null) return;
		for (Node node : model.getRootNodes())
			if (!node.equals(TaraPsiImplUtil.getContainerNodeOf(myElement)))
				resolvePathFor(node, context);
		addAggregatedConcepts(model);
	}

	private void addImportVariants() {
		Collection<Import> imports = ((TaraModel) myElement.getContainingFile()).getImports();
		for (Import anImport : imports) {
			PsiElement resolve = resolveImport(anImport);
			if (resolve == null || !TaraModel.class.isInstance(resolve)) continue;
			for (Node node : ((TaraModel) resolve).getRootNodes())
				if (!node.equals(TaraPsiImplUtil.getContainerNodeOf(myElement)))
					resolvePathFor(node, context);
			addAggregatedConcepts((TaraModel) resolve);
		}
	}

	private PsiElement resolveImport(Import anImport) {
		List<TaraIdentifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
		return ReferenceManager.resolve(importIdentifiers.get(importIdentifiers.size() - 1));
	}

	private void addAggregatedConcepts(TaraModel model) {
		for (Node node : TaraUtil.getAllNodesOfFile(model))
			if (!variants.contains(node) && node.isAggregated())
				resolvePathFor(node, context);
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
}
