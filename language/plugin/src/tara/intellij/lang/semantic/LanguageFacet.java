package tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import tara.intellij.lang.psi.FacetApply;
import tara.language.model.Facet;
import tara.language.model.Node;
import tara.language.model.Parameter;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
		final tara.intellij.lang.psi.Node container = facetApply.container();
		types.add(container.getType());
		types.addAll(container.getFacetApplies().stream().map(FacetApply::getType).collect(Collectors.toList()));
		return types;
	}

	@Override
	public List<Parameter> parameters() {
		return wrapParameters(facetApply.getParameterList());
	}

	@Override
	public List<Node> components() {
		return Collections.unmodifiableList(TaraPsiImplUtil.getInnerNodesInBody(facetApply.getBody()).stream().map(LanguageNode::new).collect(Collectors.toList()));
	}

	private List<Parameter> wrapParameters(List<tara.intellij.lang.psi.Parameter> toWrap) {
		if (toWrap == null) return Collections.EMPTY_LIST;
		return Collections.unmodifiableList(toWrap.stream().map(LanguageParameter::new).collect(Collectors.toList()));
	}

	@Override
	public PsiElement element() {
		return facetApply;
	}
}
