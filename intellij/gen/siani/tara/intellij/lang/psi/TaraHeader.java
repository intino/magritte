// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraHeader extends TaraPsiElement {

  @NotNull
  List<TaraImportStatement> getImportStatementList();

  @NotNull
  TaraNamespace getNamespace();

  @Nullable
  TaraPacket getPacket();

}
