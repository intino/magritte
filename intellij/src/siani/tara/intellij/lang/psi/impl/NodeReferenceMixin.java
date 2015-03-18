package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import siani.tara.intellij.lang.psi.Annotation;
import siani.tara.intellij.lang.psi.Annotations;
import siani.tara.intellij.lang.psi.NodeReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class NodeReferenceMixin extends ASTWrapperPsiElement {
	public NodeReferenceMixin(ASTNode node) {
		super(node);
	}

	private List<String> inheritedAnnotations = new ArrayList<>();

	public boolean isAggregated() {
		NodeReference reference = (NodeReference) this;
		Annotations annotations = reference.getAnnotations();
		if (annotations == null) return false;
		for (Annotation annotation : annotations.getAnnotationList())
			if (siani.tara.intellij.lang.lexer.Annotation.AGGREGATED.getName().equals(annotation.getText())) return true;
		return false;
	}


	public void addInheritedAnnotations(String... annotations) {
		Collections.addAll(inheritedAnnotations, annotations);
	}

	public List<String> getInheritedAnnotations() {
		return inheritedAnnotations;
	}
}
