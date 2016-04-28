// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  tara.intellij.lang.psi.TaraPsiElement;

public interface TaraRule extends Rule, TaraPsiElement {

  @Nullable
  TaraClassTypeValue getClassTypeValue();

  @NotNull
  List<TaraIdentifier> getIdentifierList();

  @Nullable
  TaraIdentifierReference getIdentifierReference();

  @Nullable
  TaraMetric getMetric();

  @Nullable
  TaraRange getRange();

  @Nullable
  TaraStringValue getStringValue();

}
