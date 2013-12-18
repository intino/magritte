// This is a generated file. Not intended for manual editing.
package monet.tara.metamodel.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TaraProperty extends PsiElement {

	@NotNull
	TaraIdentifier getIdentifier();

	@Nullable
	TaraPrimitive getPrimitive();

	@NotNull
	TaraPrimitiveType getPrimitiveType();

}
