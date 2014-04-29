package monet.::projectName::.intellij.metamodel.psi;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public interface ::projectProperName::File extends PsiFile{

	\@NotNull
	PsiFile getContainingFile();

	\@NotNull
	Definition getDefinition();

	Definition findDefinitionByKey(\@NotNull \@NonNls String key);

	\@NotNull
	PsiElement addDefinition(\@NotNull Definition definition) throws IncorrectOperationException;

	Definition addDefinition(String identifier);

	Import addImport(String reference);

	String getName();

	VirtualFile getVirtualFile();

	PsiDirectory getParent();

	Project getProject();

	String getText();

	::projectProperName::Packet getPackage();

	Import[] getImports();
}
