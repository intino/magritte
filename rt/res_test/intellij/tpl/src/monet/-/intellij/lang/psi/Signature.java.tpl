package monet.::projectName::.intellij.lang.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::FileImpl;
import org.jetbrains.annotations.Nullable;

public interface Signature extends ::projectProperName::PsiElement {

	::projectProperName::FileImpl getFile() throws PsiInvalidElementAccessException;

	PsiElement getPsiElement();

	\@Nullable
	::projectProperName::Identifier getIdentifier();

	\@Nullable
	::projectProperName::Modifier getModifier();

	boolean isCase();

	boolean isBase();

	\@Nullable
	::projectProperName::IdentifierReference getIdentifierReference();

	\@Nullable
	MetaIdentifier getType();

	Parameters getParameters();
}

