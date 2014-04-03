package monet.tara.compiler;

import monet.tara.TaracRunner;
import org.junit.Test;

public class TaraCRunnerTest {

	@Test
	public void compilerTest(){
		String[] args = new String[]{"--gen-plugin","/Users/oroncal/workspace/tara/rt/res_test/argsFile.txt" };
		TaracRunner.main(args);
	}

	@Test
	public void compilerTestRuntime(){
		Runtime rt = Runtime.getRuntime();
//		try {
//			Process compileProcess = rt.exec();
//			fixTypes(configuration.getTempDirectory(), configuration.getProject());
//			printResult(compileProcess);
//		} catch (IOException | InterruptedException e) {
//			LOG.severe(e.getMessage());
//			throw new TaraException("Error during plugin generation");
//		}

	}

}
