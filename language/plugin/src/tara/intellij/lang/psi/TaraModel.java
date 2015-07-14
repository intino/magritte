package tara.intellij.lang.psi;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TaraModel extends NodeContainer, PsiFile {

	@NotNull
	PsiFile getContainingFile();

	@NotNull
	List<Node> components();

	@NotNull
	PsiElement addNode(@NotNull Node node) throws IncorrectOperationException;

	Node addNode(String identifier);

	Import addImport(String reference);

	@NotNull
	String getName();

	@NotNull
	String getPresentableName();

	VirtualFile getVirtualFile();

	PsiDirectory getParent();

	String getDSL();

	@NotNull
	Project getProject();

	String getText();

	@NotNull
	List<Import> getImports();

	TaraDslDeclaration getDSLDeclaration();

	void updateDSL();
}
