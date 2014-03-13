package monet.tara.intellij;

import com.intellij.testFramework.ParsingTestCase;
import monet.tara.intellij.metamodel.parser.TaraParserDefinition;

public class TaraParsingTest extends ParsingTestCase {
	public TaraParsingTest() {
		super("", "m2", new TaraParserDefinition());
	}

	public void testParsingTestData() {
		doTest(true);
	}

	@Override
	protected String getTestDataPath() {
		return "./res_test/parsing/";
	}

	@Override
	protected boolean skipSpaces() {
		return false;
	}

	@Override
	protected boolean includeRanges() {
		return true;
	}
}