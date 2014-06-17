// This is a generated file. Not intended for manual editing.
package siani.tara.intellij.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraSignature extends Signature {

  @Nullable
  TaraIdentifier getIdentifier();

  @Nullable
  TaraIdentifierReference getIdentifierReference();

  @Nullable
  TaraMetaIdentifier getMetaIdentifier();

  @Nullable
  TaraModifier getModifier();

  @Nullable
  TaraParameters getParameters();

}
