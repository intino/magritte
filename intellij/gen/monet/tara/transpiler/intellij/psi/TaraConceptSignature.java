// This is a generated file. Not intended for manual editing.
package monet.tara.transpiler.intellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraConceptSignature extends PsiElement {

  @Nullable
  TaraConceptAnnotation getConceptAnnotation();

  @NotNull
  List<TaraIdentifier> getIdentifierList();

  @Nullable
  TaraModifier getModifier();

}
