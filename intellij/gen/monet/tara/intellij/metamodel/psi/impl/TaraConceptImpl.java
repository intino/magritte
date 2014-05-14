// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.intellij.metamodel.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraConceptImpl extends ConceptMixin implements TaraConcept {

	public TaraConceptImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitConcept(this);
		else super.accept(visitor);
	}

	@Override
	@Nullable
	public TaraAnnotations getAnnotations() {
		return findChildByClass(TaraAnnotations.class);
	}

	@Override
	@Nullable
	public TaraBody getBody() {
		return findChildByClass(TaraBody.class);
	}

	@Override
	@Nullable
	public TaraDoc getDoc() {
		return findChildByClass(TaraDoc.class);
	}

	@Override
	@NotNull
	public TaraSignature getSignature() {
		return findNotNullChildByClass(TaraSignature.class);
	}

}
