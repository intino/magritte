package org.jetbrains.jps.incremental.tara.model.impl;

import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.BuildRootDescriptor;

import java.io.File;
import java.io.FileFilter;

public class TaraRootDescriptor extends BuildRootDescriptor {
	private final TaraTarget myTarget;
	private final TaraConfiguration myConfig;
	private final File myFile;
	private final String myId;

	public TaraRootDescriptor(@NotNull TaraTarget target, TaraConfiguration config) {
		myTarget = target;
		myConfig = config;
		final String path = FileUtil.toCanonicalPath(config.directory);
		myFile = new File(path);
		myId = path;
	}

	public TaraConfiguration getConfiguration() {
		return myConfig;
	}

	@Override
	public String getRootId() {
		return myId;
	}

	@Override
	public File getRootFile() {
		return myFile;
	}

	@Override
	public TaraTarget getTarget() {
		return myTarget;
	}

	@NotNull
	@Override
	public FileFilter createFileFilter() {
		return null;//TODO
	}

	@Override
	public boolean canUseFileCache() {
		return true;
	}
}

