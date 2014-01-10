// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.intellij.psi.TaraIdentifier;
import monet.tara.intellij.psi.TaraReferenceStatement;
import monet.tara.intellij.psi.TaraVisitor;
import org.jetbrains.annotations.NotNull;

public class TaraReferenceStatementImpl extends ASTWrapperPsiElement implements TaraReferenceStatement {

	public TaraReferenceStatementImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitReferenceStatement(this);
		else super.accept(visitor);
	}

	@Override
	@NotNull
	public TaraIdentifier getIdentifier() {
		return findNotNullChildByClass(TaraIdentifier.class);
	}

}
