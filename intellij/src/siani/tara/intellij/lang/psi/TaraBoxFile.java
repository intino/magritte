package siani.tara.intellij.lang.psi;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface TaraBoxFile extends PsiFile {

	@NotNull
	PsiFile getContainingFile();

	@NotNull
	Concept[] getConcepts();

	@NotNull
	PsiElement addConcept(@NotNull Concept concept) throws IncorrectOperationException;

	Concept addConcept(String identifier);

	Import addImport(String reference);

	@NotNull
	String getName();

	@NotNull
	String getPresentableName();

	VirtualFile getVirtualFile();

	PsiDirectory getParent();

	String getParentModel();

	@NotNull
	Project getProject();

	String getText();

	TaraBox getBoxReference();

	List<? extends Identifier> getBoxPath();

	@Nullable
	Import[] getImports();

	void setBox(String path);
}