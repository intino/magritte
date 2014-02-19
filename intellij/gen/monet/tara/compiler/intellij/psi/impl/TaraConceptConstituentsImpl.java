// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.compiler.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraConceptConstituentsImpl extends ASTWrapperPsiElement implements TaraConceptConstituents {

	public TaraConceptConstituentsImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitConceptConstituents(this);
		else super.accept(visitor);
	}

	@Override
	@Nullable
	public TaraAttribute getAttribute() {
		return findChildByClass(TaraAttribute.class);
	}

	@Override
	@Nullable
	public TaraAttributeList getAttributeList() {
		return findChildByClass(TaraAttributeList.class);
	}

	@Override
	@Nullable
	public TaraComponent getComponent() {
		return findChildByClass(TaraComponent.class);
	}

	@Override
	@Nullable
	public TaraFrom getFrom() {
		return findChildByClass(TaraFrom.class);
	}

	@Override
	@Nullable
	public TaraReferenceStatement getReferenceStatement() {
		return findChildByClass(TaraReferenceStatement.class);
	}

	@Override
	@Nullable
	public TaraWord getWord() {
		return findChildByClass(TaraWord.class);
	}

}
