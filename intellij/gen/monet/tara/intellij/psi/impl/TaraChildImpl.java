// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.intellij.psi.TaraChild;
import monet.tara.intellij.psi.TaraConceptBody;
import monet.tara.intellij.psi.TaraIdentifier;
import monet.tara.intellij.psi.TaraVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TaraChildImpl extends ASTWrapperPsiElement implements TaraChild {

	public TaraChildImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitChild(this);
		else super.accept(visitor);
	}

	@Override
	@NotNull
	public List<TaraConceptBody> getConceptBodyList() {
		return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraConceptBody.class);
	}

	@Override
	@NotNull
	public TaraIdentifier getIdentifier() {
		return findNotNullChildByClass(TaraIdentifier.class);
	}

}
