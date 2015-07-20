package tara.compiler.core.operation.model;

import tara.builder.MetadataEnricher;
import tara.builder.MetadataEnricherProvider;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.model.Node;
import tara.compiler.model.Parameter;
import tara.compiler.model.impl.Model;

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
		for (Node component : node.getIncludedNodes()) {
			for (Parameter parameter : component.getParameters()) {
				final MetadataEnricher.Metadata metadata = enricher.get(getQualifiedName(component, parameter));
				if (metadata == null) continue;
				parameter.setName(metadata.name);
				parameter.setInferredType(metadata.type.name().toLowerCase());
			}
			component.getIncludedNodes().forEach(this::enrich);
		}
	}

	private String getQualifiedName(Node component, Parameter parameter) {
		return clean(component.getQualifiedName() + DOT + (parameter.getName() == null ? parameter.getPosition() : parameter.getName()));
	}

	private static String clean(String qualifiedName) {
		return qualifiedName.replace(Node.ANNONYMOUS, "").replace("[", "").replace("]", "");
	}
}
