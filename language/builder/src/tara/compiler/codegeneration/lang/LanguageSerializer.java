package tara.compiler.codegeneration.lang;

import tara.compiler.codegeneration.FileSystemUtils;
import tara.compiler.codegeneration.Format;
import tara.compiler.codegeneration.JavaCompiler;
import tara.compiler.core.CompilerConfiguration;
import tara.compiler.core.errorcollection.TaraException;
import tara.compiler.model.Model;
import tara.dsl.Proteo;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.Context;

import java.io.*;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.io.File.separator;

public class LanguageSerializer {
	private static final Logger LOG = Logger.getLogger(LanguageSerializer.class.getName());
	private static final String JAVA = ".java";

	CompilerConfiguration conf;

	public LanguageSerializer(CompilerConfiguration conf) {
		this.conf = conf;
	}

	public void serialize(Model model) throws TaraException {
		try {
			new File(conf.getLanguageDirectory()).mkdirs();
			File file = new File(conf.getLanguageDirectory(), conf.getGeneratedLanguage() + ".reload");
			if (!file.exists()) file.createNewFile();
			LanguageCreator creator = new LanguageCreator(conf, model);
			serialize(creator.create(), getDslDestiny(), collectRules(model));
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			throw new TaraException("Error saving model: " + e.getMessage(), e);
		}
	}

	public List<Class<?>> collectRules(Model model) {
		Set<Class<?>> classes = new HashSet<>(model.getRules().values());
		classes.addAll(collectLanguageRules());
		return new ArrayList<>(classes);
	}

	private List<Class<?>> collectLanguageRules() {
		List<Class<?>> classes = new ArrayList<>();
		for (Context context : conf.getLanguage().catalog().values())
			classes.addAll(getRulesOfNode(context));
		return classes;

	}

	private List<Class<?>> getRulesOfNode(Context context) {
		return context.constraints().stream().
			filter(constraint -> constraint instanceof Constraint.Parameter &&
				((Constraint.Parameter) constraint).rule() != null &&
				!((Constraint.Parameter) constraint).rule().getClass().getName().startsWith("tara.lang")).
			map(constraint -> (((Constraint.Parameter) constraint).rule()).getClass()).collect(Collectors.toList());
	}

	private File getDslDestiny() {
		final File file = new File(conf.getLanguageDirectory() + separator + conf.getGeneratedLanguage());
		file.mkdirs();
		return new File(file, Format.firstUpperCase().format(conf.getGeneratedLanguage()) + JAVA);
	}

	private boolean serialize(String content, File destiny, List<Class<?>> rules) throws TaraException {
		try {
			destiny.getParentFile().mkdirs();
//			destiny.deleteOnExit();
			FileWriter writer = new FileWriter(destiny);
			writer.write(content);
			writer.close();
			JavaCompiler.compile(destiny, String.join(File.pathSeparator, collectClassPath(rules)), getDslDestiny().getParentFile());
			jar(destiny.getParentFile(), rules.stream().filter(v -> !v.getName().startsWith("tara.lang")).collect(Collectors.toList()));
			return true;
		} catch (IOException e) {
			throw new TaraException("Error saving language: " + e.getMessage(), e);
		} catch (InterruptedException e) {
			throw new TaraException("Error compiling language: " + e.getMessage(), e);
		}
	}

	private Collection<String> collectClassPath(Collection<Class<?>> values) {
		Set<String> dependencies = new HashSet<>();
		dependencies.add(conf.getSemanticRulesLib().getAbsolutePath());
		if (!(conf.getLanguage() instanceof Proteo))
			dependencies.add(conf.getLanguage().getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
		dependencies.addAll(values.stream().filter(v -> !v.getName().startsWith("tara.lang")).map(value -> value.getProtectionDomain().getCodeSource().getLocation().getPath()).collect(Collectors.toList()));
		return dependencies;
	}

	private void jar(File dslDir, List<Class<?>> rules) throws IOException {
		Manifest manifest = new Manifest();
		manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
		JarOutputStream target = new JarOutputStream(new FileOutputStream(new File(dslDir, conf.getGeneratedLanguage() + ".jar")), manifest);
		final File src = new File(dslDir, "tara");
		add(dslDir, src, target);
		addRules(rules, target);
		addInheritedRules(target);
		target.close();
		FileSystemUtils.removeDir(src);
	}

	private void addInheritedRules(JarOutputStream target) throws IOException {
		if (conf.getLanguage() instanceof Proteo) return;
		final File tempDirectory = conf.getTempDirectory();
		conf.cleanTemp();
		FileSystemUtils.extractJar(conf.getLanguage().getClass().getProtectionDomain().getCodeSource().getLocation().getPath(), conf.getTempDirectory());
		final File file = new File(tempDirectory, conf.getLanguage().languageName().toLowerCase());
		List<File> rules = new ArrayList<>();
		FileSystemUtils.getAllFiles(file, rules);
		rules.stream().filter(f -> f.getName().endsWith(".class")).forEach(r -> {
			try {
				add(conf.getTempDirectory(), r, target);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
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

	@SuppressWarnings("Duplicates")
	private void add(File base, File source, JarOutputStream target) throws IOException {
		BufferedInputStream in = null;
		try {
			if (source.isDirectory()) {
				String name = getRelativePath(base, source).replace("\\", "/");
				if (!name.isEmpty()) {
					if (!name.endsWith("/")) name += "/";
					JarEntry entry = new JarEntry(name);
					entry.setTime(source.lastModified());
					target.putNextEntry(entry);
					target.closeEntry();
				}
				for (File nestedFile : source.listFiles())
					add(base, nestedFile, target);
				return;
			}

			JarEntry entry = new JarEntry(getRelativePath(base, source).replace("\\", "/"));
			entry.setTime(source.lastModified());
			target.putNextEntry(entry);
			in = new BufferedInputStream(new FileInputStream(source));

			byte[] buffer = new byte[1024];
			while (true) {
				int count = in.read(buffer);
				if (count == -1)
					break;
				target.write(buffer, 0, count);
			}
			target.closeEntry();
		} finally {
			if (in != null)
				in.close();
		}
	}

	private String getRelativePath(File base, File source) {
		return source.getPath().replace(base.getAbsolutePath() + File.separator, "");
	}

}
