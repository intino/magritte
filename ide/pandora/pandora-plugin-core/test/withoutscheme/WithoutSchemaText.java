package withoutscheme;

import pandora.PandoraApplication;
import pandora.codegeneration.FullRenderer;
import tara.magritte.Graph;
import pandora.codegeneration.accessor.rest.RESTAccessorRenderer;
import pandora.codegeneration.server.rest.RESTServiceRenderer;
import pandora.rest.RESTService;

import java.io.File;

public class WithoutSchemaText {

	public static void main(String[] args) {
		File genFolder = new File("test-gen", "withoutschema");
        new FullRenderer(Graph.load("WithoutSchema").wrap(PandoraApplication.class), genFolder, genFolder, "withoutschema").execute();
	}
}
