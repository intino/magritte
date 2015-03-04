package siani.tara.intellij.lang.psi.impl;

import com.intellij.psi.PsiElement;
import siani.tara.intellij.lang.psi.TaraModel;
import siani.tara.intellij.project.module.ModuleProvider;

import java.util.Set;

public class FileVariantsManager {

	private final Set<TaraModel> variants;
	private final PsiElement myElement;

	public FileVariantsManager(Set<TaraModel> variants, PsiElement myElement) {
		this.variants = variants;
		this.myElement = myElement;
	}

	public void resolveVariants() {
		for (TaraModelImpl taraBoxFile : TaraUtil.getTaraFilesOfModule(ModuleProvider.getModuleOf(myElement.getContainingFile().getOriginalFile()))) {
			variants.add(taraBoxFile);
		}
	}
}
