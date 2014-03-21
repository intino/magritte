// This is a generated file. Not intended for manual editing.
package monet.tara.intellij.metamodel.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface TaraBody extends Body {

  @NotNull
  List<TaraAttribute> getAttributeList();

  @NotNull
  List<TaraConcept> getConceptList();

  @NotNull
  List<TaraConceptInjection> getConceptInjectionList();

  @NotNull
  List<TaraReferenceStatement> getReferenceStatementList();

  @NotNull
  List<TaraWord> getWordList();

}