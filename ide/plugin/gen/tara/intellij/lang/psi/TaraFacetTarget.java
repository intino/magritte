// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  tara.lang.model.FacetTarget;
import  com.intellij.pom.Navigatable;

public interface TaraFacetTarget extends TaraPsiElement, FacetTarget, Navigatable {

  @Nullable
  TaraBody getBody();

  @Nullable
  TaraConstraint getConstraint();

  @Nullable
  TaraDoc getDoc();

  @Nullable
  TaraIdentifierReference getIdentifierReference();

}
