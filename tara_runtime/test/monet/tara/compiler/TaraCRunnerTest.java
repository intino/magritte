package monet.tara.compiler;

import monet.tara.TaracRunner;
import org.junit.Test;

public class TaraCRunnerTest {

	@Test
	public void compilerTest(){
		String[] args = new String[]{"","/Users/oroncal/workspace/tara/tara_runtime/res_test/argsFile.txt" };
		TaracRunner.main(args);
	}

}
