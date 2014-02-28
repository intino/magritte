package monet.tara.intellij.metamodel.psi;

import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;

public interface Body extends Navigatable, PsiElement {

	TaraFileImpl getFile() throws PsiInvalidElementAccessException;

}

