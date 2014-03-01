// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import monet.tara.intellij.metamodel.psi.TaraAnnotations;
import monet.tara.intellij.metamodel.psi.TaraVisitor;
import org.jetbrains.annotations.NotNull;

public class TaraAnnotationsImpl extends ASTWrapperPsiElement implements TaraAnnotations {

	public static final String[] ROOT_ANNOTATIONS = new String[]{"root", "extensible", "singleton", "has-code"};
	public static final String[] CHILD_ANNOTATIONS = new String[]{"extensible", "singleton", "has-code", "optional", "multiple"};
	public static final String[] MORPH_ANNOTATIONS = new String[]{"extensible", "singleton", "has-code"};

	public TaraAnnotationsImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitAnnotations(this);
		else super.accept(visitor);
	}

	public PsiElement[] getAnnotations() {
		return TaraPsiImplUtil.getAnnotations(this);
	}

}
