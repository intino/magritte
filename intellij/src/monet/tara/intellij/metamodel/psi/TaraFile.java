package monet.tara.intellij.metamodel.psi;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TaraFile {

	@NotNull
	PsiFile getContainingFile();

	@NotNull
	List<Concept> getConcepts();

	Concept findConceptByKey(@NotNull @NonNls String key);

	@NotNull
	PsiElement addConcept(@NotNull Concept concept) throws IncorrectOperationException;

	Concept addConcept(String identifier);

	String getName();

	VirtualFile getVirtualFile();

	PsiDirectory getParent();

	Project getProject();

	String getText();
}
