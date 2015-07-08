package siani.tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.semantic.model.Facet;
import siani.tara.semantic.model.Node;
import siani.tara.semantic.model.Parameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getInnerNodesInBody;

public class LanguageFacet extends LanguageElement implements Facet {

	FacetApply facetApply;

	public LanguageFacet(FacetApply facetApply) {
		this.facetApply = facetApply;
	}

	@Override
	public String type() {
		return facetApply.getType();
	}

	@Override
	public List<String> nodeTypes() {
		List<String> types = new ArrayList<>();
		final siani.tara.intellij.lang.psi.Node container = facetApply.getContainer();
		types.add(container.getType());
		types.addAll(container.getFacetApplies().stream().map(FacetApply::getType).collect(Collectors.toList()));
		return types;
	}

	@Override
	public List<Parameter> parameters() {
		return wrapParameters(facetApply.getParameterList());
	}

	@Override
	public List<Node> includes() {
		return Collections.unmodifiableList(getInnerNodesInBody(facetApply.getBody()).stream().map(LanguageNode::new).collect(Collectors.toList()));
	}

	private List<siani.tara.semantic.model.Parameter> wrapParameters(List<siani.tara.intellij.lang.psi.Parameter> toWrap) {
		if (toWrap == null) return Collections.EMPTY_LIST;
		return Collections.unmodifiableList(toWrap.stream().map(LanguageParameter::new).collect(Collectors.toList()));
	}

	@Override
	public PsiElement element() {
		return facetApply;
	}
}
