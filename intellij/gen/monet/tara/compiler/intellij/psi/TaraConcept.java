// This is a generated file. Not intended for manual editing.
package monet.tara.compiler.intellij.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
