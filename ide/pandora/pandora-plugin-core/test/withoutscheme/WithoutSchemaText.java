package withoutscheme;

import pandora.PandoraApplication;
import tara.magritte.Graph;
import pandora.codegeneration.accessor.rest.RESTAccessorRenderer;
import pandora.codegeneration.server.rest.RESTServiceRenderer;
import pandora.rest.RESTService;

import java.io.File;

public class WithoutSchemaText {

	public static void main(String[] args) {
		Graph withoutSchema = Graph.load("WithoutSchema").wrap(PandoraApplication.class);
		File genFolder = new File("test-gen", "withoutSchema");
		new RESTServiceRenderer(withoutSchema, genFolder, "withoutSchema").execute();
		withoutSchema.find(RESTService.class).forEach(rs -> new RESTAccessorRenderer(rs, new File("test-gen/" + rs.name(), "withoutSchema"), "withoutSchema").execute());
	}
}
