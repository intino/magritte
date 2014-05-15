package monet.::projectName::.intellij.lang.psi;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ::projectProperName::File extends PsiFile {

	\@NotNull
	PsiFile getContainingFile();

	Definition getDefinition();

	\@NotNull
	PsiElement addDefinition(\@NotNull Definition definition) throws IncorrectOperationException;

	Definition addDefinition(String identifier);

	Import addImport(String reference);

	\@NotNull
	String getName();

	VirtualFile getVirtualFile();

	PsiDirectory getParent();

	\@NotNull
	Project getProject();

	String getText();

	::projectProperName::Packet getPackage();

	List<? extends Identifier> getPackageRoute();

	Import[] getImports();
}
