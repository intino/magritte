package siani.tara.compiler.parser;

import org.junit.Test;
import siani.tara.compiler.core.CompilerConfiguration;

import java.io.File;

public class ParserTest {

	@Test
	public void parse() throws Exception {
		Parser parser = new Parser(new File("tara_runtime/res_test/monet.m"), new CompilerConfiguration());
		parser.parse();
	}
}
