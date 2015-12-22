// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface TaraSignature extends Signature {

  @Nullable
  TaraAnchor getAnchor();

  @Nullable
  TaraFacetTarget getFacetTarget();

  @Nullable
  TaraIdentifier getIdentifier();

  @Nullable
  TaraIdentifierReference getIdentifierReference();

  @Nullable
  TaraMetaIdentifier getMetaIdentifier();

  @Nullable
  TaraParameters getParameters();

  @NotNull
  List<TaraRuleContainer> getRuleContainerList();

  @Nullable
  TaraTags getTags();

}
