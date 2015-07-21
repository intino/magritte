package tara.compiler.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.junit.Assert;
import org.junit.Test;
import tara.language.antlr.TaraLexer;

import java.util.ArrayList;

public class ParserTest {
	public static String[] ruleNamesList;

	String TEST1 =
		"\t\t--\n" +
			"\t\tnumeroChip().setValue(\"X-ll210s0\");\n" +
			"\t\tAnimal animal = new Animal();\n" +
			"\t\tfor (Morph morph : animal.referees()) {\n" +
			"\t\t\tfor (Morph morph1 : morph.referees()) {\n" +
			"\t\t\t\tSystem.out.println(\"morph1.toString()\");" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\t--";

	String TEST2 =
		"Operation(\"Cambiar chip\")\n" +
			"\taction1 =\"setValue(\\\"Toby\\\");\"";

	String TEST3 = "dsl Proteo\n" +
		"\n" +
		"use Query\n" +
		"\n" +
		"Concept Dashboard is terminal main\n" +
		"\tvar string label\n" +
		"//\tvar string layout = \"\"\n" +
		"//\n" +
		"//\tConcept Timeline is single final\n" +
		"//\t\tvar word:[Year Quarter Season Month Week Day Hour Minute Second]... scales\n" +
		"\tConcept Chart is final\n" +
		"\t\tvar Indicator selection\n" +
		"//\n" +
		"//\t\thas Chart\n" +
		"//\t\tConcept Display";

	public static String[] lexerTest(String query) {
		try {
			String receivedToken;
			ArrayList<String> receivedTypes = new ArrayList<>();
			CharStream stream = new ANTLRInputStream(query);
			TaraLexer lexer = new TaraLexer(stream);
			lexer.reset();
			setRulesNameList(lexer.getRuleNames());
			Token currentToken = lexer.nextToken();
			while (currentToken.getType() != Token.EOF) {
				receivedToken = getRulesNameList(currentToken.getType() - 1);
				receivedTypes.add(receivedToken);
				currentToken = lexer.nextToken();
			}
			return receivedTypes.toArray(new String[receivedTypes.size()]);
		} catch (RecognitionException error) {
			System.err.println("Error on query: " + query);
			return (new String[0]);
		}
	}

	public static void setRulesNameList(String[] list) {
		ruleNamesList = list;
	}

	public static String getRulesNameList(int index) {
		return ruleNamesList[index];
	}

	@Test
	public void accept_multi_line_strings() {
		String[] expectedTypes = new String[]{};
		String[] receivedTypes = lexerTest(TEST1);
		Assert.assertArrayEquals(expectedTypes, receivedTypes);
	}

	@Test
	public void accept_line_comments() {
		String[] expectedTypes = new String[]{};
		String[] receivedTypes = lexerTest(TEST3);
		Assert.assertArrayEquals(expectedTypes, receivedTypes);
	}

	@Test
	public void accept_quotes_in_strings() {
		String[] expectedTypes = new String[]{};
		String[] receivedTypes = lexerTest(TEST2);
		Assert.assertArrayEquals(expectedTypes, receivedTypes);
	}
}
