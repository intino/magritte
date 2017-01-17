package io.intino.tara.plugin.lang.file;

import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

public class TaraFileTypeFactory extends FileTypeFactory {
	@Override
	public void createFileTypes(@NotNull com.intellij.openapi.fileTypes.FileTypeConsumer fileTypeConsumer) {
		fileTypeConsumer.consume(TaraFileType.instance(), TaraFileType.instance().getDefaultExtension());
		fileTypeConsumer.consume(StashFileType.INSTANCE, StashFileType.INSTANCE.getDefaultExtension());
	}
}