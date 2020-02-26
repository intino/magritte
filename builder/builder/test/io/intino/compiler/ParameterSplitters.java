package io.intino.compiler;

import io.intino.magritte.compiler.codegeneration.Format;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Ignore
public class ParameterSplitters {


	@Test
	public void testSplitOneParameter() throws Exception {
		String parameters = "String value";
		assertEquals("value", Format.nativeParameterWithoutType().format(parameters));
	}

	@Test
	public void testSplitTwoParameters() throws Exception {
		String parameters = "String value, String value";
		assertEquals("value, value", Format.nativeParameterWithoutType().format(parameters));

	}

	@Test
	public void testSplitThreeParameters() throws Exception {
		String parameters = "String value, String value, String value";
		assertEquals("value, value, value", Format.nativeParameterWithoutType().format(parameters));
	}


	@Test
	public void testSplitList() throws Exception {
		String parameters = "String value, List<String> values";
		assertEquals("value, values", Format.nativeParameterWithoutType().format(parameters));
	}

	@Test
	public void testSplitListAndOther() throws Exception {
		String parameters = "List<String> values, String value";
		assertEquals("values, value", Format.nativeParameterWithoutType().format(parameters));
	}

	@Test
	public void testSplitMap() throws Exception {
		String parameters = "Map<String, String> values";
		assertEquals("values", Format.nativeParameterWithoutType().format(parameters));
	}

	@Test
	public void testSplitMapAndOther() throws Exception {
		String parameters = "Map<String, String> values, String value";
		assertEquals("values, value", Format.nativeParameterWithoutType().format(parameters));
	}

	@Test
	public void testSplitMapAndOthers() throws Exception {
		String parameters = "Map<String, String> values, String value, List<String> list";
		assertEquals("values, value, list", Format.nativeParameterWithoutType().format(parameters));
	}

	@Test
	public void testSplitMapAndOtherThree() throws Exception {
		String parameters = "Map<String, String> values, String value, List<String> list,Map<String, String> map";
		assertEquals("values, value, list, map", Format.nativeParameterWithoutType().format(parameters));
	}
}
