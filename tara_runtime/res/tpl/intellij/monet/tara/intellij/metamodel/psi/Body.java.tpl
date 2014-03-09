package monet.::projectName::.intellij.metamodel.psi;

import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::FileImpl;

public interface Body extends Navigatable, PsiElement {

	::projectProperName::FileImpl getFile() throws PsiInvalidElementAccessException;

}

