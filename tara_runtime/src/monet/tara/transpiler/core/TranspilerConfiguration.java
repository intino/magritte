package monet.tara.transpiler.core;

import org.codehaus.groovy.control.io.NullWriter;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class TranspilerConfiguration {
	private static final String JDK5_CLASSNAME_CHECK = "java.lang.annotation.Annotation";
	public static final TranspilerConfiguration DEFAULT = new TranspilerConfiguration();
	private int warningLevel;
	private String sourceEncoding;
	private PrintWriter output;
	private File targetDirectory;
	private LinkedList<String> classpath;
	private boolean verbose;
	private boolean debug;
	private int tolerance;
	private String scriptBaseClass;
	private String defaultScriptExtension;
	private boolean recompileGroovySource;
	private int minimumRecompilationInterval;
	private Map<String, Object> jointCompilationOptions;
	private Map<String, Boolean> optimizationOptions;
	private LinkedList compilationCustomizers = new LinkedList();

	public TranspilerConfiguration() {
		setWarningLevel(1);
		setOutput(null);
		setTargetDirectory((File) null);
		setClasspath("");
		setVerbose(false);
		setDebug(false);
		setTolerance(10);
		setScriptBaseClass(null);
		setRecompileGroovySource(false);
		setMinimumRecompilationInterval(100);
		String tmpDefaultScriptExtension = null;
		try {
			tmpDefaultScriptExtension = System.getProperty("tara.default.scriptExtension");
		} catch (Exception ignored) {
		}
		if (tmpDefaultScriptExtension != null)
			setDefaultScriptExtension(tmpDefaultScriptExtension);
		else {
			setDefaultScriptExtension(".m2");
		}

		String encoding = null;
		try {
			encoding = System.getProperty("file.encoding", "UTF8");
		} catch (Exception e) {
		}
		try {
			encoding = System.getProperty("tara.source.encoding", encoding);
		} catch (Exception e) {
		}
		setSourceEncoding(encoding);
		try {
			setOutput(new PrintWriter(System.err));
		} catch (Exception e) {
		}
		try {
			String target = System.getProperty("tara.target.directory");
			if (target != null) {
				setTargetDirectory(target);
			}
		} catch (Exception e) {
		}
		setOptimizationOptions(new HashMap(2));

	}

	public int getWarningLevel() {
		return this.warningLevel;
	}

	public void setWarningLevel(int level) {
		if ((level < 0) || (level > 3)) {
			this.warningLevel = 1;
		} else
			this.warningLevel = level;
	}

	public String getSourceEncoding() {
		return this.sourceEncoding;
	}

	public void setSourceEncoding(String encoding) {
		if (encoding == null) encoding = "UTF8";
		this.sourceEncoding = encoding;
	}

	public PrintWriter getOutput() {
		return this.output;
	}

	public void setOutput(PrintWriter output) {
		if (output == null) {
			this.output = new PrintWriter(NullWriter.DEFAULT);
		} else
			this.output = output;
	}

	public File getTargetDirectory() {
		return this.targetDirectory;
	}

	public void setTargetDirectory(String directory) {
		if ((directory != null) && (directory.length() > 0))
			this.targetDirectory = new File(directory);
		else
			this.targetDirectory = null;
	}

	public void setTargetDirectory(File directory) {
		this.targetDirectory = directory;
	}

	public List<String> getClasspath() {
		return this.classpath;
	}

	public void setClasspath(String classpath) {
		this.classpath = new LinkedList();
		StringTokenizer tokenizer = new StringTokenizer(classpath, File.pathSeparator);
		while (tokenizer.hasMoreTokens())
			this.classpath.add(tokenizer.nextToken());
	}

	public void setClasspathList(List<String> parts) {
		this.classpath = new LinkedList(parts);
	}

	public boolean getVerbose() {
		return this.verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public boolean getDebug() {
		return this.debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public int getTolerance() {
		return this.tolerance;
	}

	public void setTolerance(int tolerance) {
		this.tolerance = tolerance;
	}

	public String getScriptBaseClass() {
		return this.scriptBaseClass;
	}

	public void setScriptBaseClass(String scriptBaseClass) {
		this.scriptBaseClass = scriptBaseClass;
	}

	public String getDefaultScriptExtension() {
		return this.defaultScriptExtension;
	}

	public void setDefaultScriptExtension(String defaultScriptExtension) {
		this.defaultScriptExtension = defaultScriptExtension;
	}

	public void setRecompileGroovySource(boolean recompile) {
		this.recompileGroovySource = recompile;
	}

	public boolean getRecompileGroovySource() {
		return this.recompileGroovySource;
	}

	public void setMinimumRecompilationInterval(int time) {
		this.minimumRecompilationInterval = Math.max(0, time);
	}

	public int getMinimumRecompilationInterval() {
		return this.minimumRecompilationInterval;
	}

	public Map<String, Object> getJointCompilationOptions() {
		return this.jointCompilationOptions;
	}

	public void setJointCompilationOptions(Map<String, Object> options) {
		this.jointCompilationOptions = options;
	}

	public Map<String, Boolean> getOptimizationOptions() {
		return this.optimizationOptions;
	}

	public void setOptimizationOptions(Map<String, Boolean> options) {
		if (options == null) throw new IllegalArgumentException("provided option map must not be null");
		this.optimizationOptions = options;
	}

	public LinkedList getCompilationCustomizers() {
		return this.compilationCustomizers;
	}

}
