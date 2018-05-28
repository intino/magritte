package io.intino.tara.plugin.annotator.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import io.intino.tara.lang.model.Facet;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class AddRequiredFacetFix implements IntentionAction {

	private final Node node;

	public AddRequiredFacetFix(PsiElement element) {
		this.node = element instanceof Node ? (Node) element : (Node) TaraPsiImplUtil.getContainerOf(element);
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return "Add required facet";
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return getText();
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return file.isValid();
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
		List<Constraint.Facet> requires = findConstraints().stream().
				filter(constraint -> constraint instanceof Constraint.Facet && ((Constraint.Facet) constraint).isRequired()).
				map(constraint -> (Constraint.Facet) constraint).collect(Collectors.toList());
		filterPresentFacets(requires);
		for (Constraint.Facet require : requires) {
			node.addFacet(require.type());
		}
		PsiDocumentManager.getInstance(file.getProject()).doPostponedOperationsAndUnblockDocument(editor.getDocument());
	}

	private List<Constraint> findConstraints() {
		final List<Constraint> constraintsOf = new ArrayList<>(Objects.requireNonNull(TaraUtil.getConstraintsOf(node)));
		List<Constraint> facetConstraints = new ArrayList<>();
		final List<String> facets = facetTypes(node);
		constraintsOf.stream().
				filter(c -> c instanceof Constraint.Facet && facets.contains(((Constraint.Facet) c).type())).
				forEach(c -> facetConstraints.addAll(((Constraint.Facet) c).constraints()));
		constraintsOf.addAll(facetConstraints);
		return constraintsOf;
	}

	private List<String> facetTypes(Node node) {
		return node.facets().stream().map(Facet::type).collect(Collectors.toList());
	}

	private void filterPresentFacets(List<Constraint.Facet> requires) {
		for (Facet facet : node.facets()) {
			Constraint.Facet require = findInConstraints(requires, facet.type());
			if (require != null) requires.remove(require);
		}
	}

	@Nullable
	private Constraint.Facet findInConstraints(List<Constraint.Facet> constraints, String name) {
		for (Constraint.Facet require : constraints) if (require.type().equals(name)) return require;
		return null;
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}
}
