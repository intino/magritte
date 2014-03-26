package monet.tara.compiler.codegeneration.intellij;

import java.io.*;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;
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

	public static Boolean copyFile(String source, String destination) {
		return copyFile(new File(source), new File(destination));
	}

	public static Boolean copyFile(File source, File destination) {
		forceDir(destination.getParentFile().getAbsolutePath());
		try {
			InputStream in = new FileInputStream(source);
			OutputStream out = new FileOutputStream(destination);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0)
				out.write(buf, 0, len);
			in.close();
			out.close();
		} catch (IOException ex) {
			return false;
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

	public static void zipDir(String name, String directory) throws IOException {
		File directoryToZip = new File(directory);
		List<File> fileList = new ArrayList<>();
		getAllFiles(directoryToZip, fileList);
		writeZipFile(name, directoryToZip, fileList);
	}


	public static void writeInputStream(InputStream in, File outFile) throws IOException {
		byte[] buffer = new byte[1024];
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
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
			file.getCanonicalPath().length());
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0)
			zos.write(bytes, 0, length);
		zos.closeEntry();
		fis.close();
	}
}
