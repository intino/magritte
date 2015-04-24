package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.lexer.Tag;
import siani.tara.intellij.lang.psi.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class NodeReferenceMixin extends ASTWrapperPsiElement {
	public NodeReferenceMixin(ASTNode node) {
		super(node);
	}

	private List<String> inheritedFlags = new ArrayList<>();

	public boolean isAggregated() {
		NodeReference reference = (NodeReference) this;
		for (Annotation annotation : reference.getAnnotations())
			if (Tag.AGGREGATED.getName().equals(annotation.getText())) return true;
		return false;
	}

	public boolean isAssociated() {
		NodeReference reference = (NodeReference) this;
		for (Annotation annotation : reference.getAnnotations())
			if (Tag.ASSOCIATED.getName().equals(annotation.getText()))
				return true;
		return false;
	}

	@NotNull
	public List<TaraAnnotation> getAnnotations() {
		TaraTags tags = ((TaraNodeReference) this).getTags();
		if (tags == null || tags.getAnnotations() == null) return Collections.EMPTY_LIST;
		return tags.getAnnotations().getAnnotationList();
	}

	@NotNull
	public List<TaraFlag> getFlags() {
		TaraTags tags = ((TaraNodeReference) this).getTags();
		if (tags == null || tags.getFlags() == null) return Collections.EMPTY_LIST;
		return tags.getFlags().getFlagList();
	}

	public void addInheritedFlags(String... flags) {
		Collections.addAll(inheritedFlags, flags);
	}

	public List<String> getInheritedFlags() {
		return inheritedFlags;
	}
}
