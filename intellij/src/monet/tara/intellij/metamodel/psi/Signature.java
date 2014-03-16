package monet.tara.intellij.metamodel.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.Nullable;

public interface Signature extends PsiElement {

	TaraFileImpl getFile() throws PsiInvalidElementAccessException;

	PsiElement getPsiElement();

	PsiElement getIdentifierNode();

	@Nullable
	TaraIdentifier getIdentifier();

	@Nullable
	TaraModifier getModifier();

	@Nullable
	TaraMorph getMorph();

	@Nullable
	TaraPolymorphic getPolymorphic();

	@Nullable
	TaraReferenceIdentifier getReferenceIdentifier();

}

