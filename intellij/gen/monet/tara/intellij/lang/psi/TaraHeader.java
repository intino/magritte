// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraHeader extends TaraPsiElement {

  @NotNull
  List<TaraImportStatement> getImportStatementList();

  @Nullable
  TaraPacket getPacket();

}
