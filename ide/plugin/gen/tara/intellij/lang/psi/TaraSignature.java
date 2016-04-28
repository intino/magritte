// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraSignature extends Signature {

  @Nullable
  TaraAnchor getAnchor();

  @NotNull
  List<TaraFacetApply> getFacetApplyList();

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

  @Nullable
  TaraWithTable getWithTable();

}
