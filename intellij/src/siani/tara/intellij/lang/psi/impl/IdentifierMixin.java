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
import siani.tara.intellij.lang.lexer.TaraPrimitives;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.resolve.TaraFileReferenceSolver;
import siani.tara.intellij.lang.psi.resolve.TaraNativeReferenceSolver;
import siani.tara.intellij.lang.psi.resolve.TaraNodeReferenceSolver;
import siani.tara.intellij.lang.psi.resolve.TaraWordReferenceSolver;
import siani.tara.semantic.Allow;

import javax.swing.*;

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
		PsiElement element = asParameterReference();
		if (element != null) return createResolverForParameter((Parameter) element);
		if ((element = asVarInitReference()) != null) return createResolverForVarInit((VarInit) element);
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
		Allow.Parameter parameterAllow = TaraUtil.getCorrespondingAllow(container, parameter);
		if (parameterAllow == null) return null;
		if (parameterAllow.type().equalsIgnoreCase(REFERENCE))
			return new TaraNodeReferenceSolver(this, getRange(), container);
		if (parameterAllow.type().equalsIgnoreCase(WORD) || !isPrimitive(parameterAllow.type()))
			return new TaraWordReferenceSolver(this, getRange(), parameterAllow);
		if (parameterAllow.type().equalsIgnoreCase(TaraPrimitives.NATIVE) || !isPrimitive(parameterAllow.type()))
			return new TaraNativeReferenceSolver(this, getRange(), parameterAllow);
		return null;
	}

	private PsiReference createResolverForVarInit(VarInit varInit) {
		Node container = TaraPsiImplUtil.getContainerNodeOf(this);
		Allow.Parameter parameterAllow = TaraUtil.getCorrespondingAllow(container, varInit);
		if (parameterAllow == null) return null;
		if (parameterAllow.type().equalsIgnoreCase(REFERENCE))
			return new TaraNodeReferenceSolver(this, getRange(), container);
		if (parameterAllow.type().equalsIgnoreCase(WORD) || !isPrimitive(parameterAllow.type()))
			return new TaraWordReferenceSolver(this, getRange(), parameterAllow);
		if (parameterAllow.type().equalsIgnoreCase(TaraPrimitives.NATIVE) || !isPrimitive(parameterAllow.type()))
			return new TaraNativeReferenceSolver(this, getRange(), parameterAllow);
		return null;
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
		return TaraIcons.CONCEPT;
	}

	@Override
	public String getName() {
		return this.getText();
	}

	public String toString() {
		return this.getName();
	}

	public Parameter asParameterReference() {
		PsiElement parent = this.getParent();
		while (!PsiFile.class.isInstance(parent)) {
			if (parent instanceof Parameter) return (Parameter) parent;
			parent = parent.getParent();
		}
		return null;
	}

	public VarInit asVarInitReference() {
		PsiElement parent = this.getParent();
		while (!PsiFile.class.isInstance(parent)) {
			if (parent instanceof VarInit) return (VarInit) parent;
			parent = parent.getParent();
		}
		return null;
	}

	public boolean isFileReference() {
		return this.getParent() instanceof TaraHeaderReference;
	}

	@Nullable
	public PsiElement getNameIdentifier() {
		return this;
	}
}
