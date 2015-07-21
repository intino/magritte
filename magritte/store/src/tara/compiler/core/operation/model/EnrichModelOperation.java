package tara.compiler.core.operation.model;

import tara.builder.MetadataEnricher;
import tara.builder.MetadataEnricherProvider;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.model.Model;
import tara.language.model.*;

import java.io.File;

public class EnrichModelOperation extends ModelOperation {

	private static final String DOT = ".";
	private final Model model;
	private final MetadataEnricher enricher;


	public EnrichModelOperation(CompilationUnit compilationUnit) {
		super();
		model = compilationUnit.getModel();
		enricher = MetadataEnricherProvider.get(new File(compilationUnit.getConfiguration().getRootFolder()));
	}

	@Override
	public void call(Model model) throws CompilationFailedException {
		enrich(model);
	}

	private void enrich(Node node) {
		for (Node component : node.components()) {
			for (Parameter parameter : component.parameters()) {
				final MetadataEnricher.Metadata metadata = enricher.get(getQualifiedName(component, parameter));
				if (metadata == null) continue;
				parameter.name(metadata.name);
				parameter.inferredType(metadata.type.name().toLowerCase());
			}
			component.components().forEach(this::enrich);
		}
	}

	private String getQualifiedName(Node component, Parameter parameter) {
		return clean(component.qualifiedName() + DOT + (parameter.name() == null ? parameter.position() : parameter.name()));
	}

	private static String clean(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}
}
