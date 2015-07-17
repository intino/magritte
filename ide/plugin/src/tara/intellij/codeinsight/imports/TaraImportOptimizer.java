package tara.intellij.codeinsight.imports;

import com.intellij.lang.ImportOptimizer;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.IdentifierReference;
import tara.intellij.lang.psi.Import;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.resolve.ReferenceManager;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TaraImportOptimizer implements ImportOptimizer {
	@Override
	public boolean supports(PsiFile file) {
		return file instanceof TaraModel;
	}

	@NotNull
	@Override
	public Runnable processFile(final PsiFile file) {
		return new Runnable() {
			@Override
			public void run() {
				if (file instanceof TaraModel) new ImportsOptimizer((TaraModel) file).run();
			}
		};
	}

	private static class ImportsOptimizer {
		private final TaraModel file;
		private final Collection<Import> myImportBlock;

		private ImportsOptimizer(TaraModel file) {
			this.file = file;
			myImportBlock = this.file.getImports();
		}

		public void run() {
			deleteDuplicates();
			deleteUnusedImportStatement();
		}


		private void deleteUnusedImportStatement() {
			Collection<IdentifierReference> identifierReferences = PsiTreeUtil.collectElementsOfType(file, IdentifierReference.class);
			Set<String> neededReferences = new HashSet<>();
			for (IdentifierReference reference : identifierReferences) {
				PsiElement resolve = ReferenceManager.resolve(reference);
				if (resolve == null) continue;
				neededReferences.add(resolve.getContainingFile().getName());
			}
			for (Import anImport : myImportBlock)
				if (!neededReferences.contains(anImport.getHeaderReference().getText()))
					anImport.delete();
		}

		private void deleteDuplicates() {
			Set<Import> set = new HashSet<>();
			for (Import anImport : myImportBlock)
				if (isInSet(set, anImport))
					anImport.delete();
		}

		private boolean isInSet(Set<Import> set, Import anImport) {
			for (Import anImport1 : set) if (anImport1.getText().equals(anImport.getText())) return true;
			set.add(anImport);
			return false;
		}

	}
}
