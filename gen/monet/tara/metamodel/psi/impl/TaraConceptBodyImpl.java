// This is a generated file. Not intended for manual editing.
package monet.tara.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.metamodel.psi.TaraConceptBody;
import monet.tara.metamodel.psi.TaraStatement;
import monet.tara.metamodel.psi.TaraVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TaraConceptBodyImpl extends ASTWrapperPsiElement implements TaraConceptBody {

	public TaraConceptBodyImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitConceptBody(this);
		else super.accept(visitor);
	}

	@Override
	@NotNull
	public List<TaraStatement> getStatementList() {
		return PsiTreeUtil.getChildrenOfTypeAsList(this, TaraStatement.class);
	}

}
