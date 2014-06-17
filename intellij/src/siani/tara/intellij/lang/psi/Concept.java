package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import siani.tara.intellij.lang.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public interface Concept extends Navigatable, Iconable, TaraPsiElement {

	TaraFileImpl getFile() throws PsiInvalidElementAccessException;

	@Nullable
	String getDocCommentText();

	PsiElement getPsiElement();

	PsiElement getIdentifierNode();

	@Nullable
	Body getBody();

	@Nullable
	Doc getDoc();

	@NotNull
	Signature getSignature();

	boolean isBase();

	boolean isCase();

	Concept[] getCases();

	boolean isIntention();

	@Nullable
	Annotations getAnnotations();

	@Override
	Icon getIcon(@IconFlags int i);

	PsiElement setName(String newName);

	String getName();

	String getQualifiedName();

	@Nullable
	String getType();

	@Nullable
	MetaIdentifier getMetaIdentifier();
}

