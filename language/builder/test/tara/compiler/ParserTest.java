/*
 * Copyright 2014 SIANI - ULPGC
 * Octavio Roncal Andrés
 * José Juan Hernández Cabrera
 * José Évora Gomez
 *
 * This File is Part of itrules Project
 *
 * itrules Project is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * itrules Project is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with itrules Library.  If not, see <http://www.gnu.org/licenses/>.
 */

package tara.compiler;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;
import tara.compiler.parser.antlr.ModelGenerator;
import tara.compiler.parser.antlr.TaraErrorStrategy;
import tara.language.grammar.TaraGrammar;
import tara.language.grammar.TaraLexer;

public class ParserTest {

	private static final String WITH_DOCS = "Agent Person is main\n" +
		"\tvar Place... services = empty !! asdasdasd\n" +
		"\n" +
		"\n" +
		"!! asdasdasd\n" +
		"!! asdasdasd\n" +
		"!! asdasdasd\n" +
		"Entity Vehicle is main !! aaaa\n" +
		"\t!! asdasdasd\n" +
		"\t!! asdasdasd\n" +
		"\t!! asdasdasd\n" +
		"\tvar VehicleModel model is final !! asdasdasd\n" +
		"\n" +
		"\tvar double[2] coordinates = 0.0 0.0\n" +
		"\tvar double speed = 0\n" +
		"\tvar native:MoveTo moveTo =''\n" +
		"\t!! asdasdasd\n" +
		"\t!! asdasdasd\n" +
		"\t!! asdasdasd\n" +
		"\tsub Car\n" +
		"\tsub Bus";

	@Test
	public void test1() {
		TaraGrammar parser = init(WITH_DOCS);
		try {
			Assert.assertTrue(parse(parser));
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}


	private TaraGrammar init(String query) {
		CharStream stream = new ANTLRInputStream(query);
		TaraLexer lexer = new TaraLexer(stream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		TaraGrammar parser = new TaraGrammar(tokens);
		parser.setTrace(true);
		parser.setErrorHandler(new TaraErrorStrategy());
		return parser;
	}

	private boolean parse(TaraGrammar parser) throws Exception {
		try {
			TaraGrammar.RootContext root = parser.root();
			ParseTreeWalker walker = new ParseTreeWalker();
			ModelGenerator extractor = new ModelGenerator("./");
			walker.walk(extractor, root);
			return true;
		} catch (RecognitionException e) {
			Token token = ((Parser) e.getRecognizer()).getCurrentToken();
			System.err.println("Syntax error in line" + token.getLine() + "at" + token.getCharPositionInLine());
			return false;
		}
	}
}