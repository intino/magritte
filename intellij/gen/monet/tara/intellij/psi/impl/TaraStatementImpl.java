// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraStatementImpl extends ASTWrapperPsiElement implements TaraStatement {

	public TaraStatementImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitStatement(this);
		else super.accept(visitor);
	}

	@Override
	@Nullable
	public TaraChild getChild() {
		return findChildByClass(TaraChild.class);
	}

	@Override
	@Nullable
	public TaraIntention getIntention() {
		return findChildByClass(TaraIntention.class);
	}

	@Override
	@Nullable
	public TaraProperty getProperty() {
		return findChildByClass(TaraProperty.class);
	}

	@Override
	@Nullable
	public TaraReferenceStatement getReferenceStatement() {
		return findChildByClass(TaraReferenceStatement.class);
	}

}
