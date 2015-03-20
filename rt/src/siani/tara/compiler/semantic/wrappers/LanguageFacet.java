package siani.tara.compiler.semantic.wrappers;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.lang.psi.Parameters;
import siani.tara.semantic.model.Facet;
import siani.tara.semantic.model.Node;
import siani.tara.semantic.model.Parameter;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil.getInnerNodesInBody;

public class LanguageFacet extends LanguageElement implements Facet {

	FacetApply facetApply;

	public LanguageFacet(FacetApply facetApply) {
		this.facetApply = facetApply;
	}

	@Override
	public String type() {
		return facetApply.getFacetName();
	}

	@Override
	public Parameter[] parameters() {
		return wrapParameters(facetApply.getParameters());
	}

	@Override
	public Node[] includes() {
		List<Node> nodes = new ArrayList<>();
		for (siani.tara.intellij.lang.psi.Node inner : getInnerNodesInBody(facetApply.getBody()))
			nodes.add(new LanguageNode(inner));
		return nodes.toArray(new Node[nodes.size()]);
	}

	private Parameter[] wrapParameters(Parameters toWrap) {
		if (toWrap == null) return new Parameter[0];
		List<Parameter> parameters = new ArrayList<>();
		for (siani.tara.intellij.lang.psi.Parameter parameter : toWrap.getParameters())
			parameters.add(new LanguageParameter(parameter));
		return parameters.toArray(new Parameter[parameters.size()]);
	}

	@Override
	public PsiElement element() {
		return facetApply;
	}
}
