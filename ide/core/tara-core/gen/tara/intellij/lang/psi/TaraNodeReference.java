// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import  tara.lang.model.Node;
import  com.intellij.pom.Navigatable;

public interface TaraNodeReference extends TaraPsiElement, Node, Navigatable {

  @Nullable
  TaraIdentifierReference getIdentifierReference();

  @NotNull
  List<TaraRuleContainer> getRuleContainerList();

  @Nullable
  TaraTags getTags();

}