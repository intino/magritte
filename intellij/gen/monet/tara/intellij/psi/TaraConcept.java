// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TaraConcept extends TaraNamedElement {

	@Nullable
	TaraConceptBody getConceptBody();

	@NotNull
	TaraConceptSignature getConceptSignature();

	String getName();

	PsiElement setName(String newName);

	PsiElement getNameIdentifier();

}
