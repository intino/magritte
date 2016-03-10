package tara.intellij.refactoring.rename;

import com.intellij.openapi.module.Module;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.listeners.RefactoringElementListener;
import com.intellij.refactoring.rename.RenamePsiElementProcessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.codeinsight.languageinjection.imports.Imports;
import tara.intellij.lang.psi.Identifier;
import tara.intellij.lang.psi.Signature;
import tara.intellij.lang.psi.TaraIdentifier;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.NodeContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaraRenamePsiElementProcessor extends RenamePsiElementProcessor {

	@Override
	public boolean canProcessElement(@NotNull PsiElement element) {
		return element instanceof TaraModel || element instanceof Identifier || element instanceof Module;
	}

	@Nullable
	@Override
	public Runnable getPostRenameCallback(PsiElement element, String newName, RefactoringElementListener elementListener) {
		if (!(element instanceof TaraIdentifier) || element.getParent() == null || !(element.getParent() instanceof Signature)) return null;
		final String old = oldQn(element);
		final String module = ModuleProvider.getModuleOf(element.getContainingFile()).getName();
		return () -> {
			Imports imports = new Imports(element.getProject());
			imports.refactor(module, old, newQn(old, newName));
		};
	}

	private String newQn(String oldQn, String newName) {
		final String[] split = oldQn.split("\\.");
		final String[] ts = Arrays.copyOf(split, split.length - 1);
		final List<String> elements = new ArrayList<>(Arrays.asList(ts));
		elements.add(newName);
		return String.join(".", elements);
	}

	private String oldQn(PsiElement element) {
		final NodeContainer containerByType = getContainerByType(element.getOriginalElement(), NodeContainer.class);
		return containerByType == null ? "" : containerByType.qualifiedName();
	}

	public static <T> T getContainerByType(PsiElement element, Class<T> tClass) {
		PsiElement parent = element;
		while (parent != null)
			if (tClass.isInstance(parent)) return (T) parent;
			else parent = parent.getContext();
		return null;
	}
}
