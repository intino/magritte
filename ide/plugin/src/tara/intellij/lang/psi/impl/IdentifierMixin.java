package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.resolve.*;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Node;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;
import tara.lang.semantics.Constraint;

import javax.swing.*;

import static tara.lang.model.Primitive.REFERENCE;
import static tara.lang.model.Primitive.WORD;

public class IdentifierMixin extends ASTWrapperPsiElement {

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
		PsiElement element = (PsiElement) asParameterReference();
		if (element != null) return createResolverForParameter((Parameter) element);
		else if (isContract()) return createOutDefinedResolver();
		else if (isWordDefaultValue()) return null;
		else if (isFileReference()) return createFileResolver();
		else if (isTableReference()) return createTableResolver();
		else if (isNodeReference()) return createNodeResolver();
		else return null;
	}

	private Parameter asParameterReference() {
		PsiElement parent = this.getParent();
		while (!PsiFile.class.isInstance(parent)) {
			if (parent instanceof Parameter) return (Parameter) parent;
			parent = parent.getParent();
		}
		return null;
	}

	private boolean isContract() {
		PsiElement parent = this.getParent();
		while (!PsiFile.class.isInstance(parent))
			if (parent instanceof Rule) return true;
			else parent = parent.getParent();
		return false;
	}

	private boolean isWordDefaultValue() {
		PsiElement parent = this.getParent();
		while (!PsiFile.class.isInstance(parent))
			if (parent instanceof Variable && WORD.equals(((Variable) parent).type())) return true;
			else parent = parent.getParent();
		return false;
	}

	private PsiReference createTableResolver() {
		return new TaraTableReferenceSolver(this, getRange());
	}

	private PsiReference createOutDefinedResolver() {
		final Module module = ModuleProvider.getModuleOf(this);
		final TaraFacet facet = TaraFacet.of(module);
		if (facet == null) return null;
		final String outputDsl = facet.getConfiguration().outputDsl();
		return new OutDefinedReferenceSolver((Identifier) this, module, outputDsl.isEmpty() ? module.getName() : outputDsl);
	}

	private PsiReference createFileResolver() {
		return new TaraFileReferenceSolver((HeaderReference) this.getParent(), getRange());
	}

	private PsiReference createNodeResolver() {
		return new TaraNodeReferenceSolver(this, getRange());
	}

	private PsiReference createResolverForParameter(Parameter parameter) {
		Node container = TaraPsiImplUtil.getContainerNodeOf(this);
		Constraint.Parameter parameterAllow = TaraUtil.getConstraint(container, parameter);
		if (parameterAllow == null) return null;
		if (parameterAllow.type().equals(REFERENCE))
			return new TaraNodeReferenceSolver(this, getRange());
		if (parameterAllow.type().equals(WORD) || !Primitive.isPrimitive(parameterAllow.type().getName()))
			return new TaraWordReferenceSolver(this, getRange(), parameterAllow);
		return null;
	}

	private TextRange getRange() {
		return new TextRange(0, getIdentifier().length());
	}

	public PsiElement setName(String name) {
		Identifier identifier = TaraElementFactoryImpl.getInstance(this.getProject()).createNameIdentifier(name);
		ASTNode node = identifier.getNode();
		this.getParent().getNode().replaceChild(getNode(), node);
		return identifier;
	}

	public boolean isReferringTarget() {
		final IdentifierReference containerByType = TaraPsiImplUtil.getContainerByType(this, IdentifierReference.class);
		return containerByType != null && (this.getNode().getTreePrev().getElementType().equals(TaraTypes.COLON));
	}

	public boolean isFileReference() {
		return this.getParent() instanceof TaraHeaderReference;
	}

	public boolean isTableReference() {
		return TaraPsiImplUtil.getContainerByType(this, TaraWithTable.class) != null;
	}

	@Override
	public Icon getIcon(@IconFlags int i) {
		return TaraIcons.NODE;
	}

	@Override
	public String getName() {
		return this.getText();
	}

	public String toString() {
		return this.getName();
	}


	@Nullable
	public PsiElement getNameIdentifier() {
		return this;
	}

	public boolean isNodeReference() {
		return this.getParent() instanceof TaraIdentifierReference;
	}
}
