package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.TaraAnnotation;
import siani.tara.intellij.lang.psi.TaraAnnotations;

import java.util.ArrayList;
import java.util.List;

public class AnnotationsMixin extends ASTWrapperPsiElement {
	public AnnotationsMixin(@NotNull ASTNode node) {
		super(node);
	}


	public String[] asStringArray() {
		List<String> names = new ArrayList<>();
		List<TaraAnnotation> annotationList = ((TaraAnnotations) this).getAnnotationList();
		for (TaraAnnotation taraAnnotation : annotationList)
			names.add(taraAnnotation.getText());
		return names.toArray(new String[names.size()]);
	}

}
