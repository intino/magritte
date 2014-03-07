// This is a generated file. Not intended for manual editing.
package monet.::projectName::.intellij.metamodel.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import monet.::projectName::.intellij.metamodel.psi.*;

public class ::projectProperName::WordImpl extends ASTWrapperPsiElement implements ::projectProperName::Word {

  public ::projectProperName::WordImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ::projectProperName::Visitor) ((::projectProperName::Visitor)visitor).visitWord(this);
    else super.accept(visitor);
  }

}
