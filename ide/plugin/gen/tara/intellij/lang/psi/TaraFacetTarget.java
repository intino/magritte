// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.Nullable;
import tara.lang.model.FacetTarget;

public interface TaraFacetTarget extends TaraPsiElement, FacetTarget, Navigatable {

  @Nullable
  TaraConstraint getConstraint();

  @Nullable
  TaraIdentifierReference getIdentifierReference();

}
