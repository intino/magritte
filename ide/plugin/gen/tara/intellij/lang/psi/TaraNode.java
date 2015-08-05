// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  tara.language.model.Node;
import  com.intellij.openapi.util.Iconable;
import  com.intellij.pom.Navigatable;

public interface TaraNode extends TaraPsiElement, Node, Iconable, Navigatable {

  @Nullable
  TaraBody getBody();

  @NotNull
  TaraSignature getSignature();

}
