package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.resolve.TaraNodeReferenceSolver;
import siani.tara.intellij.lang.psi.resolve.TaraFileReferenceSolver;
import siani.tara.intellij.lang.psi.resolve.TaraParameterReferenceSolver;
import siani.tara.semantic.Allow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static siani.tara.intellij.lang.lexer.TaraPrimitives.WORD;
import static siani.tara.intellij.lang.lexer.TaraPrimitives.isPrimitive;

public class IdentifierMixin extends ASTWrapperPsiElement {

	private static final String REFERENCE = "reference";

	public IdentifierMixin(@NotNull ASTNode node) {
		super(node);
	}

	public String getIdentifier() {
		return TaraPsiImplUtil.getIdentifier((Identifier) this);
	}

	@NotNull
	@Override
	public PsiReference[] getReferences() {
		PsiReference reference = getReference();
		return reference == null ? new PsiReference[0] : new PsiReference[]{reference};
	}

	@Nullable
	@Override
	public PsiReference getReference() {
		Parameter parameter;
		if ((parameter = isParameterReference()) != null)
			return createResolverForParameter(parameter);
		else if (isFileReference()) return creteFileResolver();
		else return createConceptResolver();
	}

	private PsiReference creteFileResolver() {
		return new TaraFileReferenceSolver((HeaderReference) this.getParent(), getRange());
	}

	private PsiReference createConceptResolver() {
		Node container = TaraPsiImplUtil.getContainerNodeOf(this);
		return new TaraNodeReferenceSolver(this, getRange(), container);
	}

	private PsiReference createResolverForParameter(Parameter parameter) {
		Node container = TaraPsiImplUtil.getContainerNodeOf(this);
		Allow.Parameter parameterAllow = getCorrespondingAllow(container, parameter);
		if (parameterAllow == null) return null;
		if (parameterAllow.type().equals(WORD) || !isPrimitive(parameterAllow.type()))
			return new TaraParameterReferenceSolver(this, getRange(), container);
		if (parameterAllow.type().equals(REFERENCE))
			return new TaraNodeReferenceSolver(this, getRange(), container);
		return null;
	}

	private Allow.Parameter getCorrespondingAllow(Node container, Parameter parameter) {
		FacetApply facetApply = areFacetParameters(parameter);
		Collection<Allow> allowsOf = facetApply != null ? getAllows(container, facetApply.getType()) : TaraUtil.getAllowsOf(container);
		if (allowsOf == null) return null;
		List<Allow.Parameter> parametersAllowed = parametersAllowed(allowsOf);
		if (parametersAllowed.isEmpty() || parametersAllowed.size() <= parameter.getIndexInParent()) return null;
		return parameter.isExplicit() ? findParameter(parametersAllowed, parameter.getExplicitName()) : parametersAllowed.get(parameter.getIndexInParent());
	}

	private Collection<Allow> getAllows(Node container, String facetApply) {
		Collection<Allow> allowsOf = TaraUtil.getAllowsOf(container);
		if (allowsOf == null) return Collections.EMPTY_LIST;
		for (Allow allow : allowsOf)
			if (allow instanceof Allow.Facet && ((Allow.Facet) allow).type().equals(facetApply))
				return TaraUtil.getAllowsOf(container, ((Allow.Facet) allow).type());
		return Collections.EMPTY_LIST;
	}

	private FacetApply areFacetParameters(Parameter parameter) {
		PsiElement contextOf = TaraPsiImplUtil.getContextOf(parameter);
		return contextOf instanceof FacetApply ? (FacetApply) contextOf : null;
	}

	private List<Allow.Parameter> parametersAllowed(Collection<Allow> allowsOf) {
		List<Allow.Parameter> parameters = new ArrayList<>();
		for (Allow allow : allowsOf)
			if (allow instanceof Allow.Parameter)
				parameters.add((Allow.Parameter) allow);
		return parameters;
	}

	private TextRange getRange() {
		return new TextRange(0, getIdentifier().length());
	}

	@NotNull
	public PsiElement setName(String name) {
		Identifier identifier = TaraElementFactoryImpl.getInstance(this.getProject()).createNameIdentifier(name);
		ASTNode node = identifier.getNode();
		this.getParent().getNode().replaceChild(getNode(), node);
		return identifier;
	}

	@Override
	public Icon getIcon(@IconFlags int i) {
		return TaraIcons.getIcon(TaraIcons.CONCEPT);
	}

	@Override
	public String getName() {
		return this.getText();
	}

	public String toString() {
		return this.getName();
	}

	public Parameter isParameterReference() {
		PsiElement parent = this.getParent();
		while (!PsiFile.class.isInstance(parent)) {
			if (parent instanceof Parameter) return (Parameter) parent;
			parent = parent.getParent();
		}

		return null;
	}

	private Allow.Parameter findParameter(List<Allow.Parameter> parameters, String name) {
		for (Allow.Parameter variable : parameters)
			if (variable.name().equals(name))
				return variable;
		return null;
	}

	public boolean isFileReference() {
		return this.getParent() instanceof TaraHeaderReference;
	}

}
