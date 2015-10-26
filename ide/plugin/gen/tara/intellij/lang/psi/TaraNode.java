// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  tara.lang.model.Node;
import  com.intellij.openapi.util.Iconable;
import  com.intellij.pom.Navigatable;

public interface TaraNode extends TaraPsiElement, Node, Iconable, Navigatable {

  @Nullable
  TaraBody getBody();

  @Nullable
  TaraDoc getDoc();

  @NotNull
  TaraSignature getSignature();

}
