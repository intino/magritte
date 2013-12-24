// This is a generated file. Not intended for manual editing.
package monet.tara.metamodel.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.metamodel.psi.TaraConceptBody;
import monet.tara.metamodel.psi.TaraConceptDefinition;
import monet.tara.metamodel.psi.TaraConceptSignature;
import monet.tara.metamodel.psi.TaraVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TaraConceptDefinitionImpl extends TaraNamedElementImpl implements TaraConceptDefinition {

	public TaraConceptDefinitionImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitConceptDefinition(this);
		else super.accept(visitor);
	}

	@Override
	@Nullable
	public TaraConceptBody getConceptBody() {
		return findChildByClass(TaraConceptBody.class);
	}

	@Override
	@NotNull
	public TaraConceptSignature getConceptSignature() {
		return findNotNullChildByClass(TaraConceptSignature.class);
	}

	@NotNull
	public String getName() {
		return TaraPsiImplUtil.getIdentifier(this);
	}

	@NotNull
	public PsiElement setName(String newName) {
		return TaraPsiImplUtil.setName(this.getConceptSignature(), newName);
	}

	@NotNull
	public PsiElement getNameIdentifier() {
		return TaraPsiImplUtil.getIdentifier(this.getConceptSignature());
	}

}