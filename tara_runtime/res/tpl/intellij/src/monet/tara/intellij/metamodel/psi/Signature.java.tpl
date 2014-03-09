package monet.::projectName::.intellij.metamodel.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::FileImpl;

public interface Signature extends PsiElement {

	::projectProperName::FileImpl getFile() throws PsiInvalidElementAccessException;

	PsiElement getPsiElement();

	PsiElement getIdentifierNode();

}

