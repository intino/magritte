package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Annotations;

import java.util.ArrayList;
import java.util.List;

public class AnnotationsMixin extends ASTWrapperPsiElement {
	public AnnotationsMixin(@NotNull ASTNode node) {
		super(node);
	}

	public PsiElement[] getAnnotations() {
		return TaraPsiImplUtil.getAnnotations((Annotations) this);
	}

	public String[] getAnnotationsAsString() {
		List<String> arrayList = new ArrayList();
		for (PsiElement element : getAnnotations()) arrayList.add(element.getText());
		return arrayList.toArray(new String[arrayList.size()]);
	}

}
