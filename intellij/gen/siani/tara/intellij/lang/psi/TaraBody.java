// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraBody extends Body {

  @NotNull
  List<TaraConcept> getConceptList();

  @NotNull
  List<TaraConceptReference> getConceptReferenceList();

  @NotNull
  List<TaraDoc> getDocList();

  @NotNull
  List<TaraFacetApply> getFacetApplyList();

  @NotNull
  List<TaraFacetTarget> getFacetTargetList();

  @NotNull
  List<TaraVarInit> getVarInitList();

  @NotNull
  List<TaraVariable> getVariableList();

}
