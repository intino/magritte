package monet.tara.intellij.metamodel.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.Nullable;

public interface Signature extends TaraPsiElement {

	TaraFileImpl getFile() throws PsiInvalidElementAccessException;

	PsiElement getPsiElement();

	@Nullable
	TaraIdentifier getIdentifier();

	@Nullable
	TaraModifier getModifier();

	boolean isCase();

	boolean isBase();

	@Nullable
	TaraIdentifierReference getIdentifierReference();

}

