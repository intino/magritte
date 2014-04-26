package monet.tara.intellij.metamodel.psi.api;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiSubstitutor;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TaraResolveResult extends ResolveResult {
	TaraResolveResult EMPTY_RESULT = new TaraResolveResult() {
		@Override
		public boolean isAccessible() {
			return false;
		}

		@Override
		public boolean isStaticsOK() {
			return false;
		}

		@Override
		public boolean isApplicable() {
			return false;
		}

		@Nullable
		@Override
		public PsiElement getCurrentFileResolveContext() {
			return null;
		}

		@NotNull
		@Override
		public PsiSubstitutor getSubstitutor() {
			return PsiSubstitutor.EMPTY;
		}

		@Override
		public boolean isInvokedOnProperty() {
			return false;
		}

		@Nullable
		@Override
		public PsiElement getElement() {
			return null;
		}

		@Override
		public boolean isValidResult() {
			return false;
		}
	};

	boolean isAccessible();

	boolean isStaticsOK();

	boolean isApplicable();

	@Nullable
	PsiElement getCurrentFileResolveContext();

	@NotNull
	PsiSubstitutor getSubstitutor();

	boolean isInvokedOnProperty();

}