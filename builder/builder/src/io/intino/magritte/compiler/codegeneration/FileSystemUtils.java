package io.intino.magritte.compiler.codegeneration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.jar.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileSystemUtils {

	private static final Logger LOG = Logger.getGlobal();

	private FileSystemUtils() {
	}

	public static Boolean removeDir(String sDirname) {
		File oFile = new File(sDirname);
		return removeDir(oFile);
	}

	public static Boolean removeDir(File oFile) {
		if (oFile.exists()) {
			File[] aFiles = oFile.listFiles();
			assert aFiles != null;
			for (File aFile : aFiles)
				if (aFile.isDirectory()) removeDir(aFile.getAbsolutePath());
				else aFile.delete();
		} else
			return true;
		return oFile.delete();
	}

	public static Boolean copyDir(String sSource, String sDestination) throws FileSystemException {
		File oSource = new File(sSource);
		File oDestination = new File(sDestination);
		return copyDir(oSource, oDestination);
	}

	public static Boolean copyDir(File oSource, File oDestination) throws FileSystemException {
		try {
			if (oSource.exists()) {
				if (oSource.isDirectory()) {
					if (!oDestination.exists())
						oDestination.mkdir();
					String[] children = oSource.list();
					for (String aChildren : children != null ? children : new String[0])
						copyDir(new File(oSource, aChildren), new File(oDestination, aChildren));
				} else {
					InputStream in = new FileInputStream(oSource);
					OutputStream out = new FileOutputStream(oDestination);
					byte[] buf = new byte[1024];
					int len;
					while ((len = in.read(buf)) > 0)
						out.write(buf, 0, len);
					in.close();
					out.close();
				}
				return true;
			}
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			throw new FileSystemException(e.getMessage(), oSource.getName(), e.getMessage());
		}
		return false;
	}

	public static Boolean forceDir(String sDirname) {
		return new File(sDirname).mkdirs();
	}

	public static Boolean createFile(String sFilename) {
		try {
			new File(sFilename).createNewFile();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			return false;
		}
		return true;
	}

	public static Boolean copyFile(String source, String destination) {
		try {
			return copyFile(new FileInputStream(new File(source)), new File(destination));
		} catch (FileNotFoundException | FileSystemException e) {
			LOG.log(Level.SEVERE, "Could not copy the file: " + source + "\n" + e.getMessage(), e);
			return false;
		}
	}

	public static Boolean copyFile(InputStream source, File destination) throws FileSystemException {
		forceDir(destination.getParentFile().getAbsolutePath());
		try {
			OutputStream out = new FileOutputStream(destination);
			byte[] buf = new byte[1024];
			int len;
			while ((len = source.read(buf)) > 0)
				out.write(buf, 0, len);
			source.close();
			out.close();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			throw new FileSystemException("Could not copy the file");
		}

		return true;
	}

	public static Boolean writeFile(String sFilename, String sContent) throws FileSystemException {

		try {
			OutputStreamWriter oWriter = new OutputStreamWriter(new FileOutputStream(sFilename), StandardCharsets.UTF_8);
			oWriter.write(sContent);
			oWriter.close();
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
			throw new FileSystemException("Could not write file", sFilename, e.getMessage());
		}
		return true;
	}

	public static void writeInputStream(InputStream in, File outFile) throws IOException {
		byte[] buffer = new byte[8192];
		if (outFile.isDirectory()) outFile.mkdirs();
		OutputStream out = new FileOutputStream(outFile);
		int len;
		while ((len = in.read(buffer)) >= 0)
			out.write(buffer, 0, len);
		in.close();
		out.close();

	}

	public static void getAllFiles(File dir, List<File> fileList) {
		File[] files = dir.listFiles();
		for (File file : files != null ? files : new File[0]) {
			if (!file.isDirectory()) fileList.add(file);
			else getAllFiles(file, fileList);
		}
	}

	public static String getNameWithoutExtension(String name) {
		int i = name.lastIndexOf('.');
		if (i != -1) {
			name = name.substring(0, i);
		}
		return name;
	}

	public static void zipDir(String name, String directory) throws IOException {
		File directoryToZip = new File(directory);
		List<File> fileList = new ArrayList<>();
		getAllFiles(directoryToZip, fileList);
		writeZipFile(name, directoryToZip, fileList);
	}

	public static void writeZipFile(String name, File directoryToZip, List<File> fileList) throws IOException {
		FileOutputStream fos = new FileOutputStream(name);
		ZipOutputStream zos = new ZipOutputStream(fos);
		for (File file : fileList)
			if (!file.isDirectory())
				addToZip(directoryToZip, file, zos);
		zos.close();
		fos.close();
	}

	public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1);
		zipFilePath = zipFilePath.replace("\\", "/");
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zipEntry.setTime(file.lastModified());
		zos.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes, 0, 1024)) >= 0)
			zos.write(bytes, 0, length);
		zos.closeEntry();
		fis.close();
	}

	public static void jarDir(String name, String directory) throws IOException {
		Manifest manifest = new Manifest();
		manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
		JarOutputStream target = new JarOutputStream(new FileOutputStream(name), manifest);
		for (File file : Objects.requireNonNull(new File(directory).listFiles()))
			add(directory, file, target);
		target.close();
	}

	private static void add(String directory, File source, JarOutputStream target) throws IOException {
		BufferedInputStream in = null;
		try {
			if (source.isDirectory()) {
				String name;
				try {
					name = source.getCanonicalPath().substring(new File(directory).getCanonicalPath().length() + 1);
					name = name.replace("\\", "/");
				} catch (Exception e) {
					LOG.log(Level.INFO, e.getMessage(), e);
					name = "";
				}
				if (!name.isEmpty()) {
					if (!name.endsWith("/"))
						name += "/";
					JarEntry entry = new JarEntry(name);
					entry.setTime(source.lastModified());
					target.putNextEntry(entry);
					target.closeEntry();
				}
				for (File nestedFile : Objects.requireNonNull(source.listFiles()))
					add(directory, nestedFile, target);
				return;
			}
			String name = source.getCanonicalPath().substring(new File(directory).getCanonicalPath().length() + 1);
			JarEntry entry = new JarEntry(name.replace("\\", "/"));
			entry.setTime(source.lastModified());
			target.putNextEntry(entry);
			in = new BufferedInputStream(new FileInputStream(source));
			writeBuffer(in, target);
			target.closeEntry();
		} finally {
			if (in != null) in.close();
		}
	}

	public static void writeBuffer(InputStream in, OutputStream target) throws IOException {
		byte[] buffer = new byte[1024];
		while (true) {
			int count = in.read(buffer);
			if (count == -1) break;
			target.write(buffer, 0, count);
		}
	}

	public static void extractJar(String jarFile, File destDir) {
		try {
			JarFile jar = new JarFile(jarFile);
			Enumeration enumEntries = jar.entries();
			while (enumEntries.hasMoreElements()) {
				JarEntry file = (JarEntry) enumEntries.nextElement();
				java.io.File f = new java.io.File(destDir + java.io.File.separator + file.getName());
				if (file.isDirectory()) continue;
				f.getParentFile().mkdirs();
				InputStream is = jar.getInputStream(file);
				FileOutputStream fos = new FileOutputStream(f);
				while (is.available() > 0)
					fos.write(is.read());
				fos.close();
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
