// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraBody extends Body {

  @NotNull
  List<TaraAnnotationsAndFacets> getAnnotationsAndFacetsList();

  @NotNull
  List<TaraAttribute> getAttributeList();

  @NotNull
  List<TaraConcept> getConceptList();

  @NotNull
  List<TaraFacetTarget> getFacetTargetList();

  @NotNull
  List<TaraVarInit> getVarInitList();

}
