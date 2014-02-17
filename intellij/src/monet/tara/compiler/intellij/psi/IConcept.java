package monet.tara.compiler.intellij.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.PsiNamedElement;
import monet.tara.compiler.intellij.metamodel.file.TaraFile;
import org.jetbrains.annotations.Nullable;

public interface IConcept extends Navigatable, Iconable, PsiNamedElement {

	TaraFile getTaraFile() throws PsiInvalidElementAccessException;

	@Nullable
	String getDocCommentText();

	PsiElement getPsiElement();
}

