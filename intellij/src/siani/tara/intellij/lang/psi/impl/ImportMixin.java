package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.TaraTypes;

public class ImportMixin extends ASTWrapperPsiElement {
	public ImportMixin(@NotNull ASTNode node) {
		super(node);
	}


	public boolean isMetamodelImport() {
		return this.findChildByType(TaraTypes.IDENTIFIER_KEY) != null;
	}
}
