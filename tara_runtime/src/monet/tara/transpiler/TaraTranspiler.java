package monet.tara.transpiler;

import monet.tara.transpiler.core.TranspilationUnit;
import monet.tara.transpiler.core.TranspilerMessage;

import java.util.List;

public class TaraTranspiler {
	public TaraTranspiler(List<TranspilerMessage> transpilerMessages) {
	}

	public List<OutputItem> compile(TranspilationUnit unit) {
		return null;
	}


	public static class OutputItem {
		private final String myOutputPath;
		private final String mySourceFileName;

		public OutputItem(String outputPath, String sourceFileName) {
			myOutputPath = outputPath;
			mySourceFileName = sourceFileName;
		}

		public String getOutputPath() {
			return myOutputPath;
		}

		public String getSourceFile() {
			return mySourceFileName;
		}
	}
}
