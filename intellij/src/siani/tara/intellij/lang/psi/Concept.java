package siani.tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;

import javax.swing.*;
import java.util.Collection;

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

	Collection<Concept> getSubConcepts();

	boolean isIntention();

	boolean isFacet();

	@NotNull
	PsiElement[] getAnnotations();

	@Override
	Icon getIcon(@IconFlags int i);

	PsiElement setName(String newName);

	Concept getParentConcept();

	@Nullable
	String getName();

	String getQualifiedName();

	@Nullable
	String getType();

	Parameter[] getParameters();

	Collection<Concept> getConceptSiblings();

	Collection<Concept> getInnerConcepts();

	Collection<Variable> getVariables();

	TaraConceptReference[] getConceptLinks();

	TaraFacetApply[] getFacetApplies();

	Collection<TaraFacetTarget> getFacetTargets();

	@Nullable
	String getParentConceptName();

	@Nullable
	MetaIdentifier getMetaIdentifier();

	String toString();

	boolean equals(Object obj);

	public int hashCode();
}

