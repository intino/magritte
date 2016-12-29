// This is a generated file. Not intended for manual editing.
package io.intino.tara.plugin.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  io.intino.tara.lang.model.Facet;
import  com.intellij.pom.Navigatable;

public interface TaraFacetApply extends TaraPsiElement, Facet, Navigatable {

  @NotNull
  TaraMetaIdentifier getMetaIdentifier();

  @Nullable
  TaraParameters getParameters();

}
