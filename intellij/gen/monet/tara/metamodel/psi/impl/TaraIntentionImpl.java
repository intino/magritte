// This is a generated file. Not intended for manual editing.
package monet.tara.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.metamodel.psi.TaraIdentifier;
import monet.tara.metamodel.psi.TaraIntention;
import monet.tara.metamodel.psi.TaraVisitor;
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
