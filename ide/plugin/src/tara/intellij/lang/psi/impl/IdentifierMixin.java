package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.psi.HeaderReference;
import tara.intellij.lang.psi.Identifier;
import tara.intellij.lang.psi.TaraHeaderReference;
import tara.intellij.lang.psi.resolve.TaraFileReferenceSolver;
import tara.intellij.lang.psi.resolve.TaraNodeReferenceSolver;
import tara.intellij.lang.psi.resolve.TaraWordReferenceSolver;
import tara.language.model.Node;
import tara.language.model.Parameter;
import tara.language.model.Primitives;
import tara.language.model.Variable;
import tara.language.semantics.Allow;

import javax.swing.*;

import static tara.language.model.Primitives.WORD;
import static tara.language.model.Primitives.isPrimitive;

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
		PsiElement element = (PsiElement) asParameterReference();
		if (element != null) return createResolverForParameter((Parameter) element);
		if (isWordDefaultValue()) return null;
		else if (isFileReference()) return creteFileResolver();
		else return createNodeResolver();
	}

	private boolean isWordDefaultValue() {
		PsiElement parent = this.getParent();
		while (!PsiFile.class.isInstance(parent))
			if (parent instanceof Variable && Primitives.WORD.equals(((Variable) parent).type())) return true;
			else parent = parent.getParent();
		return false;
	}

	private PsiReference creteFileResolver() {
		return new TaraFileReferenceSolver((HeaderReference) this.getParent(), getRange());
	}

	private PsiReference createNodeResolver() {
		return new TaraNodeReferenceSolver(this, getRange());
	}

	private PsiReference createResolverForParameter(Parameter parameter) {
		Node container = TaraPsiImplUtil.getContainerNodeOf(this);
		Allow.Parameter parameterAllow = TaraUtil.getCorrespondingAllow(container, parameter);
		if (parameterAllow == null) return null;
		if (parameterAllow.type().equalsIgnoreCase(REFERENCE))
			return new TaraNodeReferenceSolver(this, getRange());
		if (parameterAllow.type().equalsIgnoreCase(WORD) || !isPrimitive(parameterAllow.type()))
			return new TaraWordReferenceSolver(this, getRange(), parameterAllow);
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
		return TaraIcons.NODE;
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

	public boolean isFileReference() {
		return this.getParent() instanceof TaraHeaderReference;
	}

	@Nullable
	public PsiElement getNameIdentifier() {
		return this;
	}


}
