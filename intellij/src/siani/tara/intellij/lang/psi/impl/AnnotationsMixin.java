package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Annotation;
import siani.tara.intellij.lang.psi.Annotations;

import java.util.ArrayList;
import java.util.List;

public class AnnotationsMixin extends ASTWrapperPsiElement {
	public AnnotationsMixin(@NotNull ASTNode node) {
		super(node);
	}


	@NotNull
	public List<Annotation> getMetaAnnotations() {
		List<Annotation> annotations = new ArrayList<>();
		for (Annotation annotation : ((Annotations) this).getAnnotationList())
			if (annotation.isMetaAnnotation())
				annotations.add(annotation);
		return annotations;
	}

	@NotNull
	public List<Annotation> getNormalAnnotations() {
		List<Annotation> annotations = new ArrayList<>();
		for (Annotation annotation : ((Annotations) this).getAnnotationList())
			if (!annotation.isMetaAnnotation())
				annotations.add(annotation);
		return annotations;
	}
}
