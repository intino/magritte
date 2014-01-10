// This is a generated file. Not intended for manual editing.
package monet.tara.metamodel.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class TaraVisitor extends PsiElementVisitor {

	public void visitChild(@NotNull TaraChild o) {
		visitPsiElement(o);
	}

	public void visitConceptBody(@NotNull TaraConceptBody o) {
		visitPsiElement(o);
	}

	public void visitConceptDefinition(@NotNull TaraConceptDefinition o) {
		visitNamedElement(o);
	}

	public void visitConceptSignature(@NotNull TaraConceptSignature o) {
		visitPsiElement(o);
	}

	public void visitIdentifier(@NotNull TaraIdentifier o) {
		visitPsiElement(o);
	}

	public void visitIntention(@NotNull TaraIntention o) {
		visitPsiElement(o);
	}

	public void visitPrimitive(@NotNull TaraPrimitive o) {
		visitPsiElement(o);
	}

	public void visitPrimitiveType(@NotNull TaraPrimitiveType o) {
		visitPsiElement(o);
	}

	public void visitProperty(@NotNull TaraProperty o) {
		visitPsiElement(o);
	}

	public void visitReferenceStatement(@NotNull TaraReferenceStatement o) {
		visitPsiElement(o);
	}

	public void visitStatement(@NotNull TaraStatement o) {
		visitPsiElement(o);
	}

	public void visitNamedElement(@NotNull TaraNamedElement o) {
		visitPsiElement(o);
	}

	public void visitPsiElement(@NotNull PsiElement o) {
		visitElement(o);
	}

}
