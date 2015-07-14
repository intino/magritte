package tara.intellij.lang.semantic;

import com.intellij.psi.PsiElement;
import tara.intellij.lang.psi.Body;
import tara.intellij.lang.psi.IdentifierReference;
import tara.language.model.FacetTarget;
import tara.language.model.Node;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageFacetTarget extends LanguageElement implements FacetTarget {

	tara.intellij.lang.psi.FacetTarget target;

	public LanguageFacetTarget(tara.intellij.lang.psi.FacetTarget target) {
		this.target = target;
	}

	@Override
	public String target() {
		IdentifierReference reference = target.getIdentifierReference();
		return reference != null ? reference.getText() : null;
	}

	@Override
	public List<String> constraints() {
		return target.constraints();
	}

	@Override
	public List<Node> components() {
		Body body = target.getBody();
		if (body == null) return Collections.emptyList();
		List<tara.intellij.lang.psi.Node> nodeList = (List<tara.intellij.lang.psi.Node>) body.getNodeList();
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
