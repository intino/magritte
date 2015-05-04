package siani.tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.FacetApply;
import siani.tara.intellij.lang.psi.Parameters;
import siani.tara.semantic.model.*;

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
		return facetApply.getType();
	}

	@Override
	public Parameter[] parameters() {
		return wrapParameters(facetApply.getParameters());
	}

	@Override
	public Node[] includes() {
		List<siani.tara.semantic.model.Node> nodes = new ArrayList<>();
		for (siani.tara.intellij.lang.psi.Node inner : getInnerNodesInBody(facetApply.getBody()))
			nodes.add(new LanguageNode(inner));
		return nodes.toArray(new siani.tara.semantic.model.Node[nodes.size()]);
	}

	private siani.tara.semantic.model.Parameter[] wrapParameters(Parameters toWrap) {
		if (toWrap == null) return new siani.tara.semantic.model.Parameter[0];
		List<Parameter> parameters = new ArrayList<>();
		for (siani.tara.intellij.lang.psi.Parameter parameter : toWrap.getParameters())
			parameters.add(new LanguageParameter(parameter));
		return parameters.toArray(new siani.tara.semantic.model.Parameter[parameters.size()]);
	}

	@Override
	public PsiElement element() {
		return facetApply;
	}
}