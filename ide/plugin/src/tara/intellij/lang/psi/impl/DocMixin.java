package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;

public class DocMixin extends ASTWrapperPsiElement {
	public DocMixin(@NotNull ASTNode node) {
		super(node);
	}


	public String getDocText() {
		return StringUtil.trimStart(getText(), "!!");
	}
}
