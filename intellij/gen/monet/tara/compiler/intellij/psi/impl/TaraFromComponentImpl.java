// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.compiler.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraFromComponentImpl extends TaraFromComponentMixin implements TaraFromComponent {

	public TaraFromComponentImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitFromComponent(this);
		else super.accept(visitor);
	}

	@Override
	@Nullable
	public TaraConceptBody getConceptBody() {
		return findChildByClass(TaraConceptBody.class);
	}

	@Override
	@Nullable
	public TaraConceptSignature getConceptSignature() {
		return findChildByClass(TaraConceptSignature.class);
	}

	@Override
	@Nullable
	public TaraDoc getDoc() {
		return findChildByClass(TaraDoc.class);
	}

	@Override
	@Nullable
	public TaraExtendedConcept getExtendedConcept() {
		return findChildByClass(TaraExtendedConcept.class);
	}

	@Override
	@Nullable
	public TaraFromComponentAnnotations getFromComponentAnnotations() {
		return findChildByClass(TaraFromComponentAnnotations.class);
	}

	public String getIdentifier() {
		return TaraPsiImplUtil.getIdentifier(this);
	}

}
