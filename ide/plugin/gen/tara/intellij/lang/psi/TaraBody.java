// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  tara.intellij.lang.psi.TaraPsiElement;

public interface TaraBody extends Body, TaraPsiElement {

  @NotNull
  List<TaraDoc> getDocList();

  @NotNull
  List<TaraFacetApply> getFacetApplyList();

  @NotNull
  List<TaraFacetTarget> getFacetTargetList();

  @NotNull
  List<TaraNode> getNodeList();

  @NotNull
  List<TaraNodeReference> getNodeReferenceList();

  @NotNull
  List<TaraVarInit> getVarInitList();

  @NotNull
  List<TaraVariable> getVariableList();

}
