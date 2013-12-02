// This is a generated file. Not intended for manual editing.
package monet.tara.metamodelplugin.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraConceptDefinition extends TaraNamedElement {

  @NotNull
  List<TaraANNOTATION> getANNOTATIONList();

  @Nullable
  TaraConceptBody getConceptBody();

  String getIdentifier();

  String getName();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

}
