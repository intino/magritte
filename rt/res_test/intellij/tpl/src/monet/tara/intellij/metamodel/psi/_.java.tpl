package monet.::projectName::.intellij.metamodel.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::FileImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public interface Definition extends Navigatable, Iconable, ::projectProperName::PsiElement {

	::projectProperName::FileImpl getFile() throws PsiInvalidElementAccessException;

	\@Nullable
	String getDocCommentText();

	PsiElement getPsiElement();

	PsiElement getIdentifierNode();

	\@Nullable
	Body getBody();

	\@Nullable
	Doc getDoc();

	\@NotNull
	Signature getSignature();

	boolean isBase();

	boolean isCase();

	Definition[] getCases();

	boolean isExtensible();

	boolean isExtension();

	\@Nullable
	Annotations getAnnotations();

	\@Override
	Icon getIcon(\@IconFlags int i);

	PsiElement setName(String newName);

	String getName();

	String getType();
}

