// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraFacetApply extends FacetApply {

  @Nullable
  TaraBody getBody();

  @NotNull
  List<TaraMetaIdentifier> getMetaIdentifierList();

  @Nullable
  TaraParameters getParameters();

}
