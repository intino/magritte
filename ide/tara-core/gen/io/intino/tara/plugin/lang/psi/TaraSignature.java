// This is a generated file. Not intended for manual editing.
package io.intino.tara.plugin.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraSignature extends Signature {

  @Nullable
  TaraAnchor getAnchor();

  @Nullable
  TaraFacetTarget getFacetTarget();

  @Nullable
  TaraFacets getFacets();

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

  @Nullable
  TaraWithTable getWithTable();

}
