package siani.tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.Body;
import siani.tara.intellij.lang.psi.IdentifierReference;
import siani.tara.semantic.model.FacetTarget;
import siani.tara.semantic.model.Node;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageFacetTarget extends LanguageElement implements FacetTarget {

	siani.tara.intellij.lang.psi.FacetTarget target;

	public LanguageFacetTarget(siani.tara.intellij.lang.psi.FacetTarget target) {
		this.target = target;
	}

	@Override
	public String target() {
		IdentifierReference reference = target.getIdentifierReference();
		return reference != null ? reference.getText() : null;
	}

	@Override
	public List<String> constraints() {
		return target.getConstraints();
	}

	@Override
	public List<Node> includes() {
		Body body = target.getBody();
		if (body == null) return Collections.emptyList();
		List<siani.tara.intellij.lang.psi.Node> nodeList = (List<siani.tara.intellij.lang.psi.Node>) body.getNodeList();
		return Collections.unmodifiableList(nodeList.stream().map(LanguageNode::new).collect(Collectors.toList()));
	}

	@Override
	public String type() {
		return target();
	}

	@Override
	public PsiElement element() {
		return target;
	}
}
