// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraChild extends PsiElement {

  @Nullable
  TaraChildAnnotation getChildAnnotation();

  @Nullable
  TaraConceptBody getConceptBody();

  @Nullable
  TaraDoc getDoc();

  @NotNull
  List<TaraIdentifier> getIdentifierList();

  @Nullable
  TaraModifier getModifier();

}
