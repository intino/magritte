// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.lang.model.Facet;

import java.util.List;

public interface TaraFacetApply extends TaraPsiElement, Facet, Navigatable {

  @NotNull
  List<TaraMetaIdentifier> getMetaIdentifierList();

  @Nullable
  TaraParameters getParameters();

}
