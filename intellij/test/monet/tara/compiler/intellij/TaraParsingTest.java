package monet.tara.compiler.intellij;

import com.intellij.testFramework.ParsingTestCase;
import monet.tara.intellij.metamodel.TaraParserDefinition;

/**
 * Created by oroncal on 03/01/14.
 */
public class TaraParsingTest extends ParsingTestCase {
	public TaraParsingTest() {
		super("", "m2", new TaraParserDefinition());
	}

	public void testParsingTestData() {
		doTest(true);
	}

	@Override
	protected String getTestDataPath() {
		return "./testData/parsing/";
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