package monet.tara.intellij.metamodel.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;

public interface Signature extends PsiElement {

	TaraFileImpl getFile() throws PsiInvalidElementAccessException;

	PsiElement getPsiElement();

	PsiElement getIdentifierNode();

}

