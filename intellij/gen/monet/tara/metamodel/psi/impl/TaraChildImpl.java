// This is a generated file. Not intended for manual editing.
package monet.tara.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.metamodel.psi.TaraChild;
import monet.tara.metamodel.psi.TaraConceptBody;
import monet.tara.metamodel.psi.TaraIdentifier;
import monet.tara.metamodel.psi.TaraVisitor;
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
