package monet.tara.intellij.metamodel.psi;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public interface TaraFile extends PsiFile{

	@NotNull
	PsiFile getContainingFile();

	@NotNull
	Concept getConcept();

	Concept findConceptByKey(@NotNull @NonNls String key);

	@NotNull
	PsiElement addConcept(@NotNull Concept concept) throws IncorrectOperationException;

	Concept addConcept(String identifier);

	Import addImport(String reference);

	String getName();

	VirtualFile getVirtualFile();

	PsiDirectory getParent();

	Project getProject();

	String getText();

	TaraPacket getPackage();

	Import[] getImports();
}
