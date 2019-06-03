package io.intino.tara.compiler.codegeneration.lang;

import io.intino.tara.compiler.codegeneration.FileSystemUtils;
import io.intino.tara.compiler.codegeneration.JavaCompiler;
import io.intino.tara.compiler.core.CompilerConfiguration;
import io.intino.tara.compiler.core.errorcollection.TaraException;
import io.intino.tara.compiler.model.Model;
import io.intino.tara.compiler.shared.TaraBuildConstants;
import io.intino.tara.dsl.Meta;
import io.intino.tara.dsl.Proteo;
import io.intino.tara.lang.semantics.Constraint;
import io.intino.tara.lang.semantics.Context;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static io.intino.tara.compiler.codegeneration.Format.firstUpperCase;
import static io.intino.tara.compiler.codegeneration.Format.reference;
import static io.intino.tara.compiler.core.CompilerConfiguration.REPOSITORY;
import static java.io.File.separator;

public class LanguageSerializer {
	private static final Logger LOG = Logger.getGlobal();
	private static final String JAVA = ".java";
	private static final String JAR = ".jar";
	private static final String TARA_LANG_PACKAGE = "tara.lang";

	private final CompilerConfiguration conf;
	private final Collection<Model> models;

	public LanguageSerializer(CompilerConfiguration conf, Collection<Model> models) {
		this.conf = conf;
		this.models = models;
	}

	public void write() throws TaraException {
		conf.getTaraProjectDirectory().mkdirs();
		write(new LanguageCreator(conf, models).create(), getDslDestiny(), collectRules(models));
	}

	private List<Class<?>> collectRules(Collection<Model> models) {
		Set<Class<?>> classes = new HashSet<>();
		for (Model model : models) {
			classes.addAll(model.getRules().values());
			classes.addAll(collectLanguageRules(model));
		}
		return new ArrayList<>(classes);
	}

	private List<Class<?>> collectLanguageRules(Model model) {
		List<Class<?>> classes = new ArrayList<>();
		for (Context context : model.language().catalog().values())
			classes.addAll(getRulesOfNode(context));
		return classes;
	}

	private List<Class<?>> getRulesOfNode(Context context) {
		return context.constraints().stream().
				filter(constraint -> constraint instanceof Constraint.Parameter &&
						((Constraint.Parameter) constraint).rule() != null &&
						!((Constraint.Parameter) constraint).rule().getClass().getName().startsWith(TARA_LANG_PACKAGE)).
				map(constraint -> (((Constraint.Parameter) constraint).rule()).getClass()).collect(Collectors.toList());
	}

	private File getDslDestiny() {
		final File file = new File(conf.getTaraDirectory(), REPOSITORY + separator + conf.dslGroupId().replace(".", separator) + separator +
				conf.outDSL().toLowerCase() + separator + conf.version());
		file.mkdirs();
		return new File(file, reference().format(firstUpperCase().format(conf.outDSL())) + JAVA);
	}

	private void write(String content, File javaFile, List<Class<?>> rules) throws TaraException {
		try {
			final File dslDestiny = javaFile.getParentFile();
			if (dslDestiny.exists()) FileSystemUtils.removeDir(dslDestiny);
			dslDestiny.mkdirs();
			Files.write(javaFile.toPath(), content.getBytes());
			JavaCompiler.compile(javaFile, String.join(File.pathSeparator, collectClassPath(rules)), getDslDestiny().getParentFile());
			jar(dslDestiny, rules.stream().filter(v -> !v.getName().startsWith(TARA_LANG_PACKAGE)).collect(Collectors.toList()));
		} catch (IOException e) {
			throw new TaraException("Error creating languageName: " + e.getMessage(), e);
		}
	}

	private Collection<String> collectClassPath(Collection<Class<?>> values) throws IOException {
		Set<String> dependencies = new HashSet<>();
		dependencies.add(conf.getSemanticRulesLib().getAbsolutePath());
		for (Model model : models)
			if (!(model.language() instanceof Proteo) && !(model.language() instanceof Meta)) try {
				dependencies.add(Paths.get(model.language().getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).toFile().getCanonicalPath().replaceAll("%20", " "));
			} catch (URISyntaxException ignored) {
			}
		dependencies.addAll(values.stream().filter(v -> !v.getName().startsWith(TARA_LANG_PACKAGE)).map(value -> value.getProtectionDomain().getCodeSource().getLocation().getPath()).collect(Collectors.toList()));
		return dependencies;
	}

	private void jar(File dslDir, List<Class<?>> rules) throws IOException {
		Manifest manifest = new Manifest();
		manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
		manifest.getMainAttributes().put(Attributes.Name.IMPLEMENTATION_VERSION, conf.version());
		frameworkParameters(manifest);
		manifest.getEntries().put("tara", createTaraProperties());
		JarOutputStream target = new JarOutputStream(new FileOutputStream(new File(dslDir, conf.outDSL() + "-" + conf.version() + JAR)), manifest);
		final File src = new File(dslDir, "tara");
		add(dslDir, src, target);
		addRules(rules, target);
		addInheritedRules(target);
		target.close();
		FileSystemUtils.removeDir(src);
	}

	private void frameworkParameters(Manifest manifest) {
		if (conf.packageParameters() == null) return;
		final Attributes frameworkAttributes = new Attributes();
		for (Map.Entry<String, String> entry : conf.packageParameters().entrySet())
			if (!entry.getKey().isEmpty())
				frameworkAttributes.put(new Attributes.Name(entry.getKey()), entry.getValue());
		manifest.getEntries().put("framework", frameworkAttributes);
	}

	private Attributes createTaraProperties() {
		final Attributes taraAttributes = new Attributes();
		taraAttributes.put(new Attributes.Name(TaraBuildConstants.GROUP_ID), conf.groupId());
		taraAttributes.put(new Attributes.Name(TaraBuildConstants.ARTIFACT_ID), conf.artifactId());
		taraAttributes.put(new Attributes.Name(TaraBuildConstants.VERSION), conf.version());
		taraAttributes.put(new Attributes.Name(TaraBuildConstants.OUT_DSL.replace(".", "-")), conf.outDSL());
		taraAttributes.put(new Attributes.Name(TaraBuildConstants.LEVEL), conf.level().name());
		taraAttributes.put(new Attributes.Name(TaraBuildConstants.TARA_FRAMEWORK), conf.groupId() + ":" + conf.artifactId() + ":" + conf.version());
		taraAttributes.put(new Attributes.Name(TaraBuildConstants.WORKING_PACKAGE.replace(".", "-")), conf.workingPackage());
		return taraAttributes;
	}

	private void addInheritedRules(JarOutputStream target) {
		for (Model model : models) {
			if (model.language() instanceof Proteo || model.language() instanceof Meta) return;
			final File tempDirectory = conf.getTempDirectory();
			conf.cleanTemp();
			FileSystemUtils.extractJar(model.language().getClass().getProtectionDomain().getCodeSource().getLocation().getPath(), tempDirectory);
			final File[] languageDirectories = languageDirectory(tempDirectory);
			List<File> rules = new ArrayList<>();
			for (File d : languageDirectories) FileSystemUtils.getAllFiles(d, rules);
			rules.stream().filter(f -> f.getName().endsWith(".class")).forEach(r -> {
				try {
					add(conf.getTempDirectory(), r, target);
				} catch (IOException e) {
					LOG.log(Level.SEVERE, e.getMessage(), e);
				}
			});
		}
	}

	private File[] languageDirectory(File tempDirectory) {
		return tempDirectory.listFiles(file -> file.isDirectory() && !file.getName().equals("META-INF") && !file.getName().equals("tara"));
	}

	private void addRules(List<Class<?>> rules, JarOutputStream target) throws IOException {
		HashMap<File, String> ruleFiles = new HashMap<>();
		for (Class<?> rule : rules) {
			final String base = rule.getProtectionDomain().getCodeSource().getLocation().getPath();
			List<File> files = new ArrayList<>();
			FileSystemUtils.getAllFiles(new File(base), files);
			for (File file : files) ruleFiles.put(file, base);
		}
		for (Map.Entry<File, String> entry : ruleFiles.entrySet())
			add(new File(entry.getValue()), entry.getKey(), target);
	}

	private void add(File base, File source, JarOutputStream target) throws IOException {
		BufferedInputStream in = null;
		try {
			if (source.isDirectory()) {
				createDirectory(base, source, target);
				return;
			}
			JarEntry entry = new JarEntry(getRelativePath(base, source).replace("\\", "/"));
			entry.setTime(source.lastModified());
			target.putNextEntry(entry);
			in = writeEntry(source, target);
			target.closeEntry();
		} finally {
			if (in != null) in.close();
		}
	}

	private BufferedInputStream writeEntry(File source, JarOutputStream target) throws IOException {
		BufferedInputStream in;
		in = new BufferedInputStream(new FileInputStream(source));
		byte[] buffer = new byte[1024];
		while (true) {
			int count = in.read(buffer);
			if (count == -1) break;
			target.write(buffer, 0, count);
		}
		return in;
	}

	private void createDirectory(File base, File source, JarOutputStream target) throws IOException {
		String name = getRelativePath(base, source).replace("\\", "/");
		if (!name.isEmpty()) createEntry(source, target, name);
		for (File nestedFile : source.listFiles())
			add(base, nestedFile, target);
	}

	private void createEntry(File source, JarOutputStream target, String name) throws IOException {
		if (!name.endsWith("/")) name += "/";
		JarEntry entry = new JarEntry(name);
		entry.setTime(source.lastModified());
		target.putNextEntry(entry);
		target.closeEntry();
	}

	private String getRelativePath(File base, File source) {
		return source.getPath().replace(base.getAbsolutePath() + separator, "");
	}

}
