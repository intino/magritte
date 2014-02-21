// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.psi.IConcept;

public class TaraVisitor extends PsiElementVisitor {

  public void visitAttribute(@NotNull TaraAttribute o) {
    visitPsiElement(o);
  }

  public void visitBooleanAssign(@NotNull TaraBooleanAssign o) {
    visitPsiElement(o);
  }

  public void visitBooleanListAssign(@NotNull TaraBooleanListAssign o) {
    visitPsiElement(o);
  }

  public void visitComponent(@NotNull TaraComponent o) {
    visitIConcept(o);
  }

  public void visitComponentAnnotations(@NotNull TaraComponentAnnotations o) {
    visitPsiElement(o);
  }

  public void visitConcept(@NotNull TaraConcept o) {
    visitIConcept(o);
  }

  public void visitConceptAnnotations(@NotNull TaraConceptAnnotations o) {
    visitPsiElement(o);
  }

  public void visitConceptBody(@NotNull TaraConceptBody o) {
    visitPsiElement(o);
  }

  public void visitConceptConstituents(@NotNull TaraConceptConstituents o) {
    visitPsiElement(o);
  }

  public void visitConceptSignature(@NotNull TaraConceptSignature o) {
    visitPsiElement(o);
  }

  public void visitDoc(@NotNull TaraDoc o) {
    visitPsiElement(o);
  }

  public void visitDoubleAssign(@NotNull TaraDoubleAssign o) {
    visitPsiElement(o);
  }

  public void visitDoubleListAssign(@NotNull TaraDoubleListAssign o) {
    visitPsiElement(o);
  }

  public void visitExtendedConcept(@NotNull TaraExtendedConcept o) {
    visitPsiElement(o);
  }

  public void visitFrom(@NotNull TaraFrom o) {
    visitPsiElement(o);
  }

  public void visitFromAnnotations(@NotNull TaraFromAnnotations o) {
    visitPsiElement(o);
  }

  public void visitFromBody(@NotNull TaraFromBody o) {
    visitPsiElement(o);
  }

  public void visitFromComponent(@NotNull TaraFromComponent o) {
    visitIConcept(o);
  }

  public void visitFromComponentAnnotations(@NotNull TaraFromComponentAnnotations o) {
    visitPsiElement(o);
  }

  public void visitIdentifier(@NotNull TaraIdentifier o) {
    visitPsiElement(o);
  }

  public void visitIntegerAssign(@NotNull TaraIntegerAssign o) {
    visitPsiElement(o);
  }

  public void visitIntegerListAssign(@NotNull TaraIntegerListAssign o) {
    visitPsiElement(o);
  }

  public void visitIntegerValue(@NotNull TaraIntegerValue o) {
    visitPsiElement(o);
  }

  public void visitModifier(@NotNull TaraModifier o) {
    visitPsiElement(o);
  }

  public void visitNaturalAssign(@NotNull TaraNaturalAssign o) {
    visitPsiElement(o);
  }

  public void visitNaturalListAssign(@NotNull TaraNaturalListAssign o) {
    visitPsiElement(o);
  }

  public void visitReferenceStatement(@NotNull TaraReferenceStatement o) {
    visitPsiElement(o);
  }

  public void visitStringAssign(@NotNull TaraStringAssign o) {
    visitPsiElement(o);
  }

  public void visitStringListAssign(@NotNull TaraStringListAssign o) {
    visitPsiElement(o);
  }

  public void visitWord(@NotNull TaraWord o) {
    visitPsiElement(o);
  }

  public void visitIConcept(@NotNull IConcept o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
