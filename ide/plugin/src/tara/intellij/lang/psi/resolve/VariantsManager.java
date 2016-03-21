package tara.intellij.lang.psi.resolve;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.Parameter;
import tara.lang.model.Tag;
import tara.lang.model.rules.variable.ReferenceRule;
import tara.lang.semantics.Constraint;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerByType;
import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;

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
		final Node node = TaraPsiImplUtil.getContainerNodeOf(myElement);
		if (node == null || node.type() == null) return;
		if (isParentReference((IdentifierReference) myElement.getParent()))
			variants.removeAll(collectUnacceptableNodes(singletonList(node.type())));
		else if (isParameterReference(myElement))
			variants.removeAll(collectUnacceptableNodes(getExpectedType(myElement)));
	}

	private List<String> getExpectedType(PsiElement element) {
		final List<Constraint> constraints = TaraUtil.getConstraintsOf(getContainerNodeOf(element));
		final Parameter parameter = getContainerByType(element, Parameter.class);
		if (constraints == null || parameter == null || parameter.name() == null) return emptyList();
		final Constraint.Parameter constraint = (Constraint.Parameter) constraints.stream().
			filter(c -> c instanceof Constraint.Parameter && ((Constraint.Parameter) c).name().equals(parameter.name())).findFirst().orElse(null);
		if (constraint == null || !(constraint.rule() instanceof ReferenceRule)) return emptyList();
		return ((ReferenceRule) constraint.rule()).allowedReferences();
	}

	private boolean isParameterReference(PsiElement element) {
		return getContainerByType(element, Parameter.class) != null;
	}

	private List<Node> collectUnacceptableNodes(List<String> expectedTypes) {
		if (expectedTypes.isEmpty()) return emptyList();
		List<Node> unacceptable = new ArrayList<>();
		unacceptable.addAll(variants.stream().
			filter(variant -> variant.type() != null && !expectedTypes.contains(variant.type())).
			collect(Collectors.toList()));
		return unacceptable;
	}

	private boolean hasContext() {
		return getContext().indexOf(myElement) != 0;
	}

	@NotNull
	private List<? extends Identifier> getContext() {
		return ((IdentifierReference) myElement.getParent()).getIdentifierList();
	}

	private void addContextVariants() {
		final List<Identifier> aContext = (List<Identifier>) getContext();
		final List<PsiElement> resolve = ReferenceManager.resolve(aContext.get(aContext.size() - 2));
		if (resolve.isEmpty()) return;
		final Node containerNodeOf = TaraPsiImplUtil.getContainerNodeOf(resolve.get(0));
		if (containerNodeOf == null) return;
		variants.addAll(containerNodeOf.components());
		for (Facet facet : containerNodeOf.facets())
			variants.addAll(facet.components());
	}

	private void addInModelVariants() {
		TaraModel model = (TaraModel) myElement.getContainingFile();
		if (model == null) return;
		model.components().stream().
			filter(node -> !node.equals(TaraPsiImplUtil.getContainerNodeOf(myElement))).
			forEach(node -> resolvePathFor(node, context));
		addMainConcepts(model);
	}

	private void addImportVariants() {
		Collection<Import> imports = ((TaraModel) myElement.getContainingFile()).getImports();
		for (Import anImport : imports) {
			PsiElement resolve = resolveImport(anImport);
			if (resolve == null || !TaraModel.class.isInstance(resolve)) continue;
			((TaraModel) resolve).components().stream().filter(node -> !node.equals(TaraPsiImplUtil.getContainerNodeOf(myElement))).forEach(node -> resolvePathFor(node, context));
			addMainConcepts((TaraModel) resolve);
		}
	}

	private PsiElement resolveImport(Import anImport) {
		List<TaraIdentifier> importIdentifiers = anImport.getHeaderReference().getIdentifierList();
		return ReferenceManager.resolve(importIdentifiers.get(importIdentifiers.size() - 1)).get(0);
	}

	private void addMainConcepts(TaraModel model) {
		TaraUtil.getAllNodesOfFile(model).stream().filter(node -> !variants.contains(node) && node.is(Tag.Component)).forEach(node -> resolvePathFor(node, context));
	}

	private void resolvePathFor(Node node, List<Identifier> path) {
		List<Node> childrenOf = TaraPsiImplUtil.getComponentsOf(node);
		if (node == null || node.type() == null) return;
		if (path.isEmpty()) variants.add(node);
		else if (path.get(0).getText().equals(node.name()))
			for (Node child : childrenOf)
				resolvePathFor(child, path.subList(1, path.size()));
	}

	public final List<Identifier> solveIdentifierContext() {
		List<? extends Identifier> list = ((IdentifierReference) myElement.getParent()).getIdentifierList();
		return (List<Identifier>) list.subList(0, list.size() - 1);
	}

	private boolean isParentReference(IdentifierReference reference) {
		return reference.getParent() instanceof Signature;
	}
}
