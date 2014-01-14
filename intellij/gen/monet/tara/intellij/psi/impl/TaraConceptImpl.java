// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.TokenType;
import com.intellij.util.IncorrectOperationException;
import monet.tara.intellij.psi.TaraConcept;
import monet.tara.intellij.psi.TaraConceptBody;
import monet.tara.intellij.psi.TaraConceptSignature;
import monet.tara.intellij.psi.TaraVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraConceptImpl extends TaraNamedElementImpl implements TaraConcept {

	public TaraConceptImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitConcept(this);
		else super.accept(visitor);
	}

	@Override
	@Nullable
	public TaraConceptBody getConceptBody() {
		return findChildByClass(TaraConceptBody.class);
	}

	@Override
	@NotNull
	public TaraConceptSignature getConceptSignature() {
		return findNotNullChildByClass(TaraConceptSignature.class);
	}

	@NotNull
	public String getName() {
		return TaraPsiImplUtil.getIdentifier(this);
	}

	@NotNull
	public PsiElement setName(String newName) {
		return TaraPsiImplUtil.setName(this.getConceptSignature(), newName);
	}

	@NotNull
	public PsiElement getNameIdentifier() {
		return TaraPsiImplUtil.getIdentifier(this.getConceptSignature());
	}

	public void delete() throws IncorrectOperationException {
		final ASTNode parentNode = getParent().getNode();
		assert parentNode != null;

		ASTNode node = getNode();
		ASTNode prev = node.getTreePrev();
		ASTNode next = node.getTreeNext();
		parentNode.removeChild(node);
		if ((prev == null || prev.getElementType() == TokenType.WHITE_SPACE) && next != null &&
			next.getElementType() == TokenType.WHITE_SPACE) {
			parentNode.removeChild(next);
		}
	}
}
