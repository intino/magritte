// This is a generated file. Not intended for manual editing.
package monet.tara.metamodelplugin.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class TaraVisitor extends PsiElementVisitor {

  public void visitANNOTATION(@NotNull TaraANNOTATION o) {
    visitPsiElement(o);
  }

  public void visitPRIMITIVE(@NotNull TaraPRIMITIVE o) {
    visitPsiElement(o);
  }

  public void visitPRIMITIVETYPE(@NotNull TaraPRIMITIVETYPE o) {
    visitPsiElement(o);
  }

  public void visitAttrSiblings(@NotNull TaraAttrSiblings o) {
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

  public void visitPropertiesBody(@NotNull TaraPropertiesBody o) {
    visitPsiElement(o);
  }

  public void visitReferencesBody(@NotNull TaraReferencesBody o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull TaraNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
