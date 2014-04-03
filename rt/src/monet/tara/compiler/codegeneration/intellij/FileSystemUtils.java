package monet.tara.compiler.codegeneration.intellij;

import java.io.*;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileSystemUtils {

	private FileSystemUtils() {
	}

	public static Boolean createDir(String sDirname) {
		return forceDir(sDirname);
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

	public static String readStream(InputStream is) throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1)
					writer.write(buffer, 0, n);
			} finally {
				is.close();
			}
			return writer.toString();
		} else
			return "";
	}

	public static Boolean copyDir(File oSource, File oDestination) throws FileSystemException {
		try {
			if (oSource.exists()) {
				if (oSource.isDirectory()) {
					if (!oDestination.exists())
						oDestination.mkdir();
					String[] children = oSource.list();
					for (String aChildren : children)
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
		} catch (IOException oException) {
			throw new FileSystemException(oException.getMessage(), oSource.getName(), oException.getMessage());
		}
		return false;
	}

	public static Boolean forceDir(String sDirname) {
		return new File(sDirname).mkdirs();
	}

	public static Boolean createFile(String sFilename) {
		try {
			new File(sFilename).createNewFile();
		} catch (IOException ex) {
			return false;
		}
		return true;
	}

	public static Boolean copyFile(String source, String destination) throws FileSystemException {
		try {
			return copyFile(new FileInputStream(new File(source)), new File(destination));
		} catch (FileNotFoundException | FileSystemException e) {
			e.printStackTrace();
			throw new FileSystemException("Could not copy the file");
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
			e.printStackTrace();
			throw new FileSystemException("Could not copy the file");
		}

		return true;
	}

	public static InputStream getInputStream(String sFilename) throws FileSystemException {
		FileInputStream oReader;
		try {
			oReader = new FileInputStream(sFilename);
		} catch (IOException oException) {
			throw new FileSystemException("Could not read file", sFilename, oException.getMessage());
		}
		return oReader;
	}

	public static String readFile(String sFilename) throws FileSystemException {
		StringBuilder oContent = new StringBuilder();
		InputStreamReader oInputStreamReader;
		BufferedReader oBufferedReader;
		String sLine;
		try {
			oInputStreamReader = new InputStreamReader(new FileInputStream(sFilename), "UTF-8");
			oBufferedReader = new BufferedReader(oInputStreamReader);
			while ((sLine = oBufferedReader.readLine()) != null)
				oContent.append(sLine).append("\n");
			oInputStreamReader.close();
		} catch (IOException oException) {
			throw new FileSystemException("Could not read file", sFilename, oException.getMessage());
		}
		return oContent.toString();
	}

	public static Boolean writeFile(String sFilename, String sContent) throws FileSystemException {

		try {
			OutputStreamWriter oWriter = new OutputStreamWriter(new FileOutputStream(sFilename), "UTF-8");
			oWriter.write(sContent);
			oWriter.close();
		} catch (IOException oException) {
			throw new FileSystemException("Could not write file", sFilename, oException.getMessage());
		}
		return true;
	}

	public static OutputStream getOutputStream(String sFilename) throws FileSystemException {
		FileOutputStream oStream;
		try {
			oStream = new FileOutputStream(sFilename);
		} catch (IOException oException) {
			throw new FileSystemException("Could not read file", sFilename, oException.getMessage());
		}
		return oStream;
	}

	public static File[] listFiles(String path, FileFilter fileFilter) {
		List<File> javaFiles = new ArrayList<>();
		listFilesRecursive(new File(path), javaFiles, fileFilter);
		return javaFiles.toArray(new File[javaFiles.size()]);
	}

	private static void listFilesRecursive(File path, List<File> javaFiles, FileFilter filter) {
		for (File file : path.listFiles(filter))
			if (file.isDirectory()) listFilesRecursive(file, javaFiles, filter);
			else javaFiles.add(file);
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
			fileList.add(file);
			if (file.isDirectory())
				getAllFiles(file, fileList);
		}
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
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1, file.getCanonicalPath().length());
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
		for (File file : new File(directory).listFiles())
			add(directory, file, target);
		target.close();
	}

	private static void add(String directory, File source, JarOutputStream target) throws IOException {
		BufferedInputStream in = null;
		try {
			if (source.isDirectory()) {
				String name;
				try {
					name = source.getCanonicalPath().substring(new File(directory).getCanonicalPath().length() + 1, source.getCanonicalPath().length());
					name = name.replace("\\", "/");
				} catch (Exception e) {
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
				for (File nestedFile : source.listFiles())
					add(directory, nestedFile, target);
				return;
			}
			String name = source.getCanonicalPath().substring(new File(directory).getCanonicalPath().length() + 1, source.getCanonicalPath().length());
			JarEntry entry = new JarEntry(name.replace("\\", "/"));
			entry.setTime(source.lastModified());
			target.putNextEntry(entry);
			in = new BufferedInputStream(new FileInputStream(source));

			byte[] buffer = new byte[1024];
			while (true) {
				int count = in.read(buffer);
				if (count == -1) break;
				target.write(buffer, 0, count);
			}
			target.closeEntry();
		} finally {
			if (in != null) in.close();
		}
	}
}
