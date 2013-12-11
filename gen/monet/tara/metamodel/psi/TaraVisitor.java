// This is a generated file. Not intended for manual editing.
package monet.tara.metamodel.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class TaraVisitor extends PsiElementVisitor {

  public void visitAnnotation(@NotNull TaraAnnotation o) {
    visitPsiElement(o);
  }

  public void visitChildrenBody(@NotNull TaraChildrenBody o) {
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

  public void visitPrimitive(@NotNull TaraPrimitive o) {
    visitPsiElement(o);
  }

  public void visitPrimitiveType(@NotNull TaraPrimitiveType o) {
    visitPsiElement(o);
  }

  public void visitPropertiesBody(@NotNull TaraPropertiesBody o) {
    visitPsiElement(o);
  }

  public void visitReferencesBody(@NotNull TaraReferencesBody o) {
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
