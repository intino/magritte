package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import tara.intellij.lang.psi.TaraFlag;
import tara.intellij.lang.psi.TaraFlags;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FlagsMixin extends ASTWrapperPsiElement {

	public FlagsMixin(ASTNode node) {
		super(node);
	}

	public List<String> asStringList() {
		return Collections.unmodifiableList(((TaraFlags) this).getFlagList().stream().map(TaraFlag::getText).collect(Collectors.toList()));
	}
}
