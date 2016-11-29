package tara.intellij.lang.psi.resolve;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.Checker;
import tara.intellij.lang.LanguageManager;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Facet;
import tara.lang.model.Node;
import tara.lang.model.Parameter;
import tara.lang.model.Tag;
import tara.lang.model.rules.variable.ReferenceRule;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.constraints.parameter.ReferenceParameter;
import tara.lang.semantics.errorcollector.SemanticFatalException;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerByType;
import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getContainerNodeOf;

class VariantsManager {

	private final Set<Node> variants = new LinkedHashSet<>();
	private final Identifier myElement;
	private final List<Identifier> context;

	VariantsManager(Identifier myElement) {
		this.myElement = myElement;
		this.context = solveIdentifierContext();
	}

	Set<Node> resolveVariants() {
		if (hasContext()) addContextVariants();
		else {
			addInModelVariants();
			addImportVariants();
		}
		final Node node = TaraPsiImplUtil.getContainerNodeOf(myElement);
		if (node == null || node.type() == null) return variants;
		if (isParentReference((IdentifierReference) myElement.getParent()))
			variants.removeAll(collectUnacceptableNodes(singletonList(node.type())));
		else if (isParameterReference(myElement))
			variants.removeAll(collectUnacceptableNodes(filterTypes(myElement)));
		return variants;
	}

	private List<String> filterTypes(PsiElement element) {
		final Node node = getContainerNodeOf(element);
		check(node);
		final List<Constraint> constraints = TaraUtil.getConstraintsOf(node);
		final Parameter parameter = getContainerByType(element, Parameter.class);
		if (constraints == null || parameter == null || parameter.name() == null) return emptyList();
		Constraint.Parameter constraint = findParameter(constraints, parameter);
		if (constraint == null && !node.facets().isEmpty()) constraint = searchInFacets(node.facets(), constraints, parameter);
		if (constraint == null || !(constraint.rule() instanceof ReferenceRule)) return emptyList();
		return ((ReferenceRule) constraint.rule()).allowedReferences();
	}

	private Constraint.Parameter searchInFacets(List<Facet> facets, List<Constraint> constraints, Parameter parameter) {
		for (Constraint c : constraints)
			if (c instanceof Constraint.Facet && facetOf((Constraint.Facet) c, facets) != null)
				return findParameter(((Constraint.Facet) c).constraints(), parameter);
		return null;
	}

	private Facet facetOf(Constraint.Facet c, List<Facet> facets) {
		for (Facet facet : facets)
			if (facet.type().equals(c.type())) return facet;
		return null;
	}

	private Constraint.Parameter findParameter(List<Constraint> constraints, Parameter parameter) {
		return (Constraint.Parameter) constraints.stream().
			filter(c -> c instanceof ReferenceParameter && isConstraintOf(parameter, c)).findFirst().orElse(null);
	}

	private boolean isConstraintOf(Parameter parameter, Constraint constraint) {
		final ReferenceParameter c = (ReferenceParameter) constraint;
		return c.name().equals(parameter.name()) || c.isConstraintOf(parameter);
	}

	private void check(Node node) {
		Checker checker = new Checker(LanguageManager.getLanguage(myElement.getContainingFile()));
		try {
			checker.check(node);
		} catch (SemanticFatalException ignored) {
		}
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
		TaraUtil.getAllNodesOfFile(model).stream().
			filter(node -> !variants.contains(node) && !node.is(Tag.Component) && !node.is(Tag.Feature)).
			forEach(node -> resolvePathFor(node, context));
	}

	private void resolvePathFor(Node node, List<Identifier> path) {
		List<Node> childrenOf = TaraPsiImplUtil.getComponentsOf(node);
		if (node == null || node.type() == null) return;
		if (path.isEmpty()) variants.add(node);
		else if (path.get(0).getText().equals(node.name()))
			for (Node child : childrenOf)
				resolvePathFor(child, path.subList(1, path.size()));
	}

	private List<Identifier> solveIdentifierContext() {
		List<? extends Identifier> list = ((IdentifierReference) myElement.getParent()).getIdentifierList();
		return (List<Identifier>) list.subList(0, list.size() - 1);
	}

	private boolean isParentReference(IdentifierReference reference) {
		return reference.getParent() instanceof Signature;
	}
}
