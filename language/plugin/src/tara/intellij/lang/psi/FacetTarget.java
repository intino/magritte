package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.impl.TaraFacetTargetImpl;
import tara.intellij.lang.psi.resolve.ReferenceManager;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface FacetTarget extends tara.language.model.FacetTarget, NodeContainer, Navigatable {

	@Nullable
	TaraBody getBody();

	@Nullable
	TaraIdentifierReference getIdentifierReference();

	default List<String> constraints() {
		TaraConstraint with = ((TaraFacetTargetImpl) this).getConstraint();
		if (with == null) return Collections.EMPTY_LIST;
		return with.getIdentifierReferenceList().stream().map(IdentifierReference::getText).collect(Collectors.toList());
	}

	@NotNull
	default List<Variable> variables() {
		Body body = this.getBody();
		return (body == null) ? Collections.EMPTY_LIST : Collections.unmodifiableList(body.getVariableList());
	}

	default String qualifiedName() {
		final String target = target();
		return container().qualifiedName() + "." + container().name() + "_" + (target.contains(".") ? target.substring(0, target.lastIndexOf(".")) : target);
	}

	default String target() {
		TaraIdentifierReference identifierReference = ((TaraFacetTargetImpl) this).getIdentifierReference();
		return identifierReference == null ? "" : identifierReference.getText();
	}

	default Node targetNode() {
		return ReferenceManager.resolveToNode(((TaraFacetTargetImpl) this).getIdentifierReference());
	}

	default <T extends tara.language.model.Node> void targetNode(T destiny) {
	}

	default void target(String destiny) {
	}

	default void constraints(List<String> constraints) {
	}


	default String type() {
		return target();
	}


}
