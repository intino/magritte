package monet.::projectName::.intellij.metamodel.psi;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ::projectProperName::File {

	@NotNull
	PsiFile getContainingFile();

	@NotNull
	List<Definition> getDefinitions();

	Definition findDefinitionByKey(@NotNull @NonNls String key);

	@NotNull
	PsiElement addDefinition(@NotNull Definition definition) throws IncorrectOperationException;

	Definition addDefinition(String identifier);

	String getName();

	VirtualFile getVirtualFile();

	PsiDirectory getParent();

	Project getProject();

	String getText();
}
