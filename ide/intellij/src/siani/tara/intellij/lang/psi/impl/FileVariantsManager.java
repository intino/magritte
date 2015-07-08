package siani.tara.intellij.lang.psi.impl;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.Set;
import java.util.stream.Collectors;

public class FileVariantsManager {

	private final Set<TaraModel> variants;
	private final PsiElement myElement;

	public FileVariantsManager(Set<TaraModel> variants, PsiElement myElement) {
		this.variants = variants;
		this.myElement = myElement;
	}

	public void resolveVariants() {
		variants.addAll(TaraUtil.getTaraFilesOfModule(ModuleProvider.getModuleOf(myElement.getContainingFile().getOriginalFile())).stream().collect(Collectors.toList()));
	}
}
