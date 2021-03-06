package io.intino.magritte.lang.model.rules.variable;

import io.intino.magritte.lang.model.rules.CustomRule;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class NativeCustomWordRule extends NativeRule implements CustomRule {

	private List<String> words;
	private String externalWordClass;
	private File classFile;

	public NativeCustomWordRule(List<String> words, String externalWordClass) {
		super(externalWordClass, "", Collections.emptyList());
		this.words = words;
		this.externalWordClass = externalWordClass;
	}

	public List<String> words() {
		return words;
	}


	public String source() {
		return externalWordClass;
	}

	@Override
	public String toString() {
		return "NativeCustomWordRule{" + String.join(",", words) + '}';
	}

	@Override
	public Class<?> loadedClass() {
		return null;
	}

	@Override
	public void setLoadedClass(Class<?> loadedClass) {

	}

	@Override
	public void classFile(File file) {
		this.classFile = file;
	}

	@Override
	public File classFile() {
		return this.classFile;
	}

	@Override
	public String externalClass() {
		return externalWordClass;
	}
}
