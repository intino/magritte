// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.psi.IConcept;

public interface TaraConcept extends IConcept {

  @Nullable
  TaraConceptAnnotations getConceptAnnotations();

  @Nullable
  TaraConceptBody getConceptBody();

  @NotNull
  TaraConceptSignature getConceptSignature();

  @Nullable
  TaraDoc getDoc();

  String getIdentifier();

}
