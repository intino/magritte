// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;

public interface TaraFacetApply extends FacetApply {

  @Nullable
  TaraBody getBody();

  @NotNull
  List<TaraMetaIdentifier> getMetaIdentifierList();

  @Nullable
  TaraParameters getParameters();

}
