package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public interface Concept extends Navigatable, Iconable, TaraPsiElement {

	TaraBoxFileImpl getFile() throws PsiInvalidElementAccessException;

	@Nullable
	String getDocCommentText();

	PsiElement getPsiElement();

	Identifier getIdentifierNode();

	@Nullable
	Body getBody();

	@Nullable
	Doc getDoc();

	@NotNull
	Signature getSignature();

	boolean isSub();

	Concept[] getSubConcepts();

	boolean isIntention();

	@NotNull
	PsiElement[] getAnnotations();

	@Override
	Icon getIcon(@IconFlags int i);

	PsiElement setName(String newName);

	@Nullable
	String getName();

	String getQualifiedName();

	@Nullable
	String getType();

	Concept[] getConceptSiblings();

	Concept[] getConceptChildren();

	TaraConceptReference[] getConceptLinks();

	TaraFacetApply[] getFacetApplies();

	TaraFacetTarget[] getFacetTargets();

	@Nullable
	String getParentConcept();

	@Nullable
	MetaIdentifier getMetaIdentifier();

	String toString();

	boolean equals(Object obj);

	public int hashCode();
}

