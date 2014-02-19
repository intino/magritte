// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.compiler.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraComponentImpl extends TaraComponentMixin implements TaraComponent {

	public TaraComponentImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitComponent(this);
		else super.accept(visitor);
	}

	@Override
	@Nullable
	public TaraComponentAnnotations getComponentAnnotations() {
		return findChildByClass(TaraComponentAnnotations.class);
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

	public String getIdentifier() {
		return TaraPsiImplUtil.getIdentifier(this);
	}

}
