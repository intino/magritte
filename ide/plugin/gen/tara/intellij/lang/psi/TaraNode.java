// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import com.intellij.openapi.util.Iconable;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.lang.model.Node;

public interface TaraNode extends TaraPsiElement, Node, Iconable, Navigatable {

  @Nullable
  TaraBody getBody();

  @Nullable
  TaraDoc getDoc();

  @NotNull
  TaraSignature getSignature();

  void setShortType(String type);

}
