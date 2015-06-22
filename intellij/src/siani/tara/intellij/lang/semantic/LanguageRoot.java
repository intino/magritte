package siani.tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.semantic.model.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

public class LanguageRoot extends LanguageElement implements Node {

	TaraModel model;

	public LanguageRoot(TaraModel model) {
		this.model = model;
	}

	@Override
	public Node context() {
		return null;
	}

	@Override
	public String type() {
		return "";
	}

	@Override
	public boolean isReference() {
		return false;
	}

	@Override
	public siani.tara.semantic.model.Node destinyOfReference() {
		return this;
	}

	@Override
	public void type(String type) {
	}

	@Override
	public List<String> secondaryTypes() {
		return emptyList();
	}

	@Override
	public List<String> types() {
		return emptyList();
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
		return null;
	}

	@Override
	public List<String> annotations() {
		return emptyList();
	}

	@Override
	public List<String> flags() {
		return emptyList();
	}

	@Override
	public void flags(String... flags) {

	}

	@Override
	public void annotations(String... annotations) {

	}

	@Override
	public void moveToTheTop() {
	}

	@Override
	public List<Facet> facets() {
		return emptyList();
	}

	@Override
	public List<FacetTarget> facetTargets() {
		return emptyList();
	}

	@Override
	public List<Parameter> parameters() {
		return emptyList();
	}

	@Override
	public List<Node> includes() {
		return unmodifiableList(model.getIncludes().stream().map(LanguageNode::new).collect(Collectors.toList()));
	}

	@Override
	public List<Variable> variables() {
		return emptyList();
	}

	@Override
	public PsiElement element() {
		return model;
	}
}
