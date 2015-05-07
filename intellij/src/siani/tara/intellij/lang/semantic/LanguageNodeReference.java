package siani.tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.Annotation;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.lang.psi.Flag;
import siani.tara.intellij.lang.psi.NodeReference;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.semantic.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LanguageNodeReference extends LanguageNode implements Node {

	private final NodeReference nodeReference;
	private siani.tara.intellij.lang.psi.Node destiny;

	public LanguageNodeReference(NodeReference nodeReference) {
		super(null);
		this.nodeReference = nodeReference;
		destiny = ReferenceManager.resolveToNode(nodeReference.getIdentifierReference());
	}

	@Override
	public PsiElement element() {
		return nodeReference;
	}

	@Override
	public Node context() {
		return new LanguageNode(TaraPsiImplUtil.getContainerNodeOf(nodeReference));
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
	public void type(String type) {
	}

	@Override
	public String[] secondaryTypes() {
		if (badReference()) return new String[0];
		List<String> types = new ArrayList<>();
		for (FacetApply facetApply : destiny.getFacetApplies())
			types.add(facetApply.getType());
		return types.toArray(new String[types.size()]);
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
	public Long address() {
		return Long.MIN_VALUE;
	}

	@Override
	public String[] annotations() {
		List<String> names = new ArrayList<>();
		for (Annotation annotation : nodeReference.getAnnotations()) names.add(annotation.getText());
		return names.toArray(new String[names.size()]);
	}

	@Override
	public String[] flags() {
		Set<String> names = new HashSet<>();
		for (Flag flags : nodeReference.getFlags()) names.add(flags.getText());
		names.addAll(nodeReference.getInheritedFlags());
		return names.toArray(new String[names.size()]);
	}

	@Override
	public void flags(String... flags) {
		nodeReference.addInheritedFlags(flags);
	}

	@Override
	public void annotations(String... annotations) {
//		TODO nodeReference.addInheritedAnnotations(annotations);
	}

	@Override
	public Facet[] facets() {
		return new Facet[0];
	}

	@Override
	public FacetTarget[] facetTargets() {
		return new FacetTarget[0];
	}

	@Override
	public Parameter[] parameters() {
		return new Parameter[0];
	}

	@Override
	public Variable[] variables() {
		return new Variable[0];
	}

	@Override
	public Node[] includes() {
		return new Node[0];
	}

	@Override
	public String toString() {
		return "reference " + destiny.getType();
	}
}
