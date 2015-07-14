package tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import tara.intellij.lang.psi.Annotation;
import tara.intellij.lang.psi.FacetApply;
import tara.intellij.lang.psi.Flag;
import tara.intellij.lang.psi.NodeReference;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.language.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LanguageNodeReference extends LanguageNode implements Node {

	private final NodeReference reference;
	private tara.intellij.lang.psi.Node destiny;

	public LanguageNodeReference(NodeReference reference) {
		super(null);
		this.reference = reference;
		destiny = ReferenceManager.resolveToNode(reference.getIdentifierReference());
	}

	@Override
	public PsiElement element() {
		return reference;
	}

	@Override
	public Node context() {
		return new LanguageNode(TaraPsiImplUtil.getContainerNodeOf(reference));
	}

	@Override
	public String type() {
		return badReference() ? null : destiny.resolve().getFullType();
	}

	@Override
	public boolean isReference() {
		return true;
	}

	@Override
	public Node destinyOfReference() {
		return new LanguageNode(destiny);
	}

	@Override
	public void type(String type) {
	}

	@Override
	public List<String> secondaryTypes() {
		if (badReference()) return Collections.emptyList();
		return Collections.unmodifiableList(destiny.getFacetApplies().stream().map(FacetApply::getType).collect(Collectors.toList()));
	}

	private boolean badReference() {
		return destiny == null;
	}

	@Override
	public String name() {
		return "";
	}

	@Override
	public Node parent() {
		return null;
	}

	@Override
	public boolean hasSubs() {
		return false;
	}

	@Override
	public String plate() {
		return "";
	}

	@Override
	public List<String> annotations() {
		return reference.getAnnotations().stream().map(Annotation::getText).collect(Collectors.toList());
	}

	@Override
	public List<String> flags() {
		Set<String> names = reference.getFlags().stream().map(Flag::getText).collect(Collectors.toSet());
		names.addAll(reference.getInheritedFlags());
		return Collections.unmodifiableList(new ArrayList<>(names));
	}

	@Override
	public void flags(String... flags) {
		reference.addInheritedFlags(flags);
	}

	@Override
	public void annotations(String... annotations) {
//		TODO nodeReference.addInheritedAnnotations(annotations);
	}

	@Override
	public List<Facet> facets() {
		return Collections.emptyList();
	}

	@Override
	public List<FacetTarget> facetTargets() {
		return Collections.emptyList();
	}

	@Override
	public List<Parameter> parameters() {
		return Collections.emptyList();
	}

	@Override
	public List<Variable> variables() {
		return Collections.emptyList();
	}

	@Override
	public List<Node> components() {
		return Collections.emptyList();
	}

	@Override
	public String toString() {
		return "reference " + destiny.getType();
	}
}
