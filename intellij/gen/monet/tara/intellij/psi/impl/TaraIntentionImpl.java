// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.intellij.psi.TaraIdentifier;
import monet.tara.intellij.psi.TaraIntention;
import monet.tara.intellij.psi.TaraVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TaraIntentionImpl extends ASTWrapperPsiElement implements TaraIntention {

	public TaraIntentionImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitIntention(this);
		else super.accept(visitor);
	}

	@Override
	@NotNull
	public List<TaraIdentifier> getIdentifierList() {
		return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraIdentifier.class);
	}

}
