// This is a generated file. Not intended for manual editing.
package io.intino.tara.plugin.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  io.intino.tara.lang.model.Aspect;
import  com.intellij.pom.Navigatable;

public interface TaraAspectApply extends TaraPsiElement, Aspect, Navigatable {

  @NotNull
  TaraMetaIdentifier getMetaIdentifier();

  @Nullable
  TaraParameters getParameters();

}
