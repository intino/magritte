package tara.compiler.core.operation;

import tara.compiler.codegeneration.FileSystemUtils;
import tara.compiler.codegeneration.magritte.stash.StashCreator;
import tara.compiler.codegeneration.magritte.stash.StaticStashCreator;
import tara.compiler.constants.TaraBuildConstants;
import tara.compiler.core.CompilationUnit;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.CompilationFailedException;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.core.operation.model.ModelOperation;
import tara.compiler.model.Model;
import tara.io.Stash;
import tara.io.StashSerializer;
import tara.language.model.Node;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StashGenerationOperation extends ModelOperation {
	private static final Logger LOG = Logger.getLogger(StashGenerationOperation.class.getName());
	private static final String STASH = ".stash";
	private static final String DSL = ".dsl";
	private final CompilationUnit compilationUnit;
	private final CompilerConfiguration conf;
	private final String genLanguage;

	public StashGenerationOperation(CompilationUnit compilationUnit) {
		super();
		this.compilationUnit = compilationUnit;
		this.conf = compilationUnit.getConfiguration();
		this.genLanguage = conf.getGeneratedLanguage() != null ? conf.getGeneratedLanguage() : conf.getModule();
	}

	@Override
	public void call(Model model) {
		try {
			System.out.println(TaraBuildConstants.PRESENTABLE_MESSAGE + "Generating Stashes...");
			writeStashCollection(writeStashes(createStashes(model.language(), pack(model))));
		} catch (TaraException e) {
			LOG.log(Level.SEVERE, "Error during stash generation: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private Map<String, Stash> createStashes(String language, List<List<Node>> groupByBox) throws TaraException {
		Map<String, Stash> map = new HashMap();
		for (List<Node> nodes : groupByBox)
			map.put(nodes.get(0).file(), buildStash(language, nodes));
		return map;
	}

	private Stash buildStash(String language, List<Node> nodes) throws TaraException {
		return conf.isStashGeneration() ?
			new StaticStashCreator(nodes, nodes.get(0).uses(), language, conf.getResourcesDirectory(), conf.getStashPath()).create() :
			new StashCreator(nodes, nodes.get(0).uses(), language, genLanguage, conf.getResourcesDirectory()).create();

	}

	private List<String> writeStashes(Map<String, Stash> stashes) {
		if (isStaticStashGeneration())
			FileSystemUtils.removeDir(getStashFolder(conf.getResourcesDirectory(), genLanguage.toLowerCase()));
		return stashes.entrySet().stream().map(entry -> writeStash(new File(entry.getKey()), entry.getValue())).collect(Collectors.toList());
	}

	private boolean isStaticStashGeneration() {
		return genLanguage != null;
	}

	private String writeStash(File taraFile, Stash stash) {
		final byte[] content = StashSerializer.serialize(stash);
		final File file = createStashDestiny(taraFile);
		file.getParentFile().mkdirs();
		try (FileOutputStream stream = new FileOutputStream(file)) {
			stream.write(content);
			stream.close();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Error writing stashes: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
		return file.getPath();
	}

	private void writeStashCollection(List<String> stashes) {
		if (stashes.isEmpty()) return;
		final File file = new File(conf.getResourcesDirectory(), genLanguage + DSL);
		try (FileOutputStream stream = new FileOutputStream(file)) {
			for (String stash : stashes)
				stream.write(new File(stash).getPath().substring(conf.getResourcesDirectory().getAbsolutePath().length()).replace("\\", "/").concat("\n").getBytes());
			stream.close();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Error writing stash collection: " + e.getMessage(), e);
			throw new CompilationFailedException(compilationUnit.getPhase(), compilationUnit, e);
		}
	}

	private File createStashDestiny(File taraFile) {
		final File destiny = getStashFolder(conf.getResourcesDirectory(), genLanguage);
		destiny.mkdirs();
		return new File(destiny, getPresentableName(taraFile.getName()) + STASH);
	}

	private File getStashFolder(File resDirectory, String genLanguage) {
		return genLanguage == null ? resDirectory : new File(resDirectory, genLanguage);
	}

	private static String getPresentableName(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}

	private List<List<Node>> pack(Model model) {
		Map<String, List<Node>> nodes = new HashMap();
		for (Node node : model.components()) {
			if (!nodes.containsKey(node.file()))
				nodes.put(node.file(), new ArrayList<>());
			nodes.get(node.file()).add(node);
		}
		return pack(nodes);
	}

	private List<List<Node>> pack(Map<String, List<Node>> nodes) {
		return nodes.values().stream().collect(Collectors.toList());
	}
}
