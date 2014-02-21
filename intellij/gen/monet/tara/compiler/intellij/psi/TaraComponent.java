// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.psi.IConcept;

public interface TaraComponent extends IConcept {

  @Nullable
  TaraComponentAnnotations getComponentAnnotations();

  @Nullable
  TaraConceptBody getConceptBody();

  @Nullable
  TaraConceptSignature getConceptSignature();

  @Nullable
  TaraDoc getDoc();

  @Nullable
  TaraExtendedConcept getExtendedConcept();

  String getIdentifier();

}
