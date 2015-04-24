package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import siani.tara.intellij.lang.psi.TaraFlag;
import siani.tara.intellij.lang.psi.TaraFlags;

import java.util.ArrayList;
import java.util.List;

public class FlagsMixin extends ASTWrapperPsiElement {

	public FlagsMixin(ASTNode node) {
		super(node);
	}

	public String[] asStringArray() {
		List<String> names = new ArrayList<>();
		for (TaraFlag flag : ((TaraFlags) this).getFlagList())
			names.add(flag.getText());
		return names.toArray(new String[names.size()]);
	}
}
