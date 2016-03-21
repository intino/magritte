package tara.intellij.codeinsight.spelling;

import com.intellij.spellchecker.BundledDictionaryProvider;

public class TaraDictionaryProvider implements BundledDictionaryProvider {
	@Override
	public String[] getBundledDictionaries() {
		return new String[]{"es.dic"};
	}


}
