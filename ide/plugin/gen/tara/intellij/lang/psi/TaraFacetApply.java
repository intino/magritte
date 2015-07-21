// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  tara.language.model.Facet;
import  com.intellij.pom.Navigatable;

public interface TaraFacetApply extends TaraPsiElement, Facet, Navigatable {

  @Nullable
  TaraBody getBody();

  @NotNull
  List<TaraMetaIdentifier> getMetaIdentifierList();

  @Nullable
  TaraParameters getParameters();

}
