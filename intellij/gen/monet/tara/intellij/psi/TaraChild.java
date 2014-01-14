// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface TaraChild extends PsiElement {

	@Nullable
	TaraChildAnnotation getChildAnnotation();

	@Nullable
	TaraConceptBody getConceptBody();

	@Nullable
	TaraDoc getDoc();

	@NotNull
	List<TaraIdentifier> getIdentifierList();

	@Nullable
	TaraModifier getModifier();

}
