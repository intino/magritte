package monet.tara.intellij.metamodel.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.Nullable;

public interface Signature extends TaraPsiElement {

	TaraFileImpl getFile() throws PsiInvalidElementAccessException;

	PsiElement getPsiElement();

	PsiElement getIdentifierNode();

	@Nullable
	TaraIdentifier getIdentifier();

	@Nullable
	TaraModifier getModifier();

	@Nullable
	boolean isCase();

	@Nullable
	boolean isBase();

	@Nullable
	TaraIdentifierReference getIdentifierReference();

}

