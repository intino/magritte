// This is a generated file. Not intended for manual editing.
package monet.tara.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.metamodel.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraPropertyImpl extends ASTWrapperPsiElement implements TaraProperty {

	public TaraPropertyImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitProperty(this);
		else super.accept(visitor);
	}

	@Override
	@NotNull
	public TaraIdentifier getIdentifier() {
		return findNotNullChildByClass(TaraIdentifier.class);
	}

	@Override
	@Nullable
	public TaraPrimitive getPrimitive() {
		return findChildByClass(TaraPrimitive.class);
	}

	@Override
	@NotNull
	public TaraPrimitiveType getPrimitiveType() {
		return findNotNullChildByClass(TaraPrimitiveType.class);
	}

}
