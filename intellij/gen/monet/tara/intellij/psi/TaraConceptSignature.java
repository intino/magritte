// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface TaraConceptSignature extends PsiElement {

	@Nullable
	TaraConceptAnnotation getConceptAnnotation();

	@NotNull
	List<TaraIdentifier> getIdentifierList();

	@Nullable
	TaraModifier getModifier();

}
