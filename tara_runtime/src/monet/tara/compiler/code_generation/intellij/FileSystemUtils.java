package monet.tara.compiler.code_generation.intellij;

import java.io.*;
import java.nio.file.FileSystemException;
import java.util.ArrayList;

public class FileSystemUtils {

	protected FileSystemUtils() {
	}

	public static String[] listDir(String sDirname) {
		FilenameFilter oFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return !name.startsWith(".");
			}
		};

		return new File(sDirname).list(oFilter);
	}

	public static String[] listFiles(String sDirname) {
		File[] aFiles;
		ArrayList<String> alResult = new ArrayList<>();
		String[] aResult;
		FilenameFilter oFilter;
		Integer iPos;

		oFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return !name.startsWith(".");
			}
		};

		aFiles = new File(sDirname).listFiles(oFilter);
		for (iPos = 0; iPos < aFiles.length; iPos++) {
			if (aFiles[iPos].isDirectory()) continue;
			alResult.add(aFiles[iPos].getName());
		}

		aResult = new String[alResult.size()];
		return alResult.toArray(aResult);
	}

	public static Boolean createDir(String sDirname) {
		return forceDir(sDirname);
	}

	public static Boolean renameDir(String sSource, String sDestination) {
		File oDestination = new File(sDestination);
		return new File(sSource).renameTo(oDestination);
	}

	public static Boolean removeDir(String sDirname) {
		File oFile = new File(sDirname);
		return removeDir(oFile);
	}

	public static Boolean removeDir(File oFile) {
		if (oFile.exists()) {
			File[] aFiles = oFile.listFiles();
			for (int iPos = 0; iPos < aFiles.length; iPos++) {
				if (aFiles[iPos].isDirectory()) {
					removeDir(aFiles[iPos].getAbsolutePath());
				} else {
					aFiles[iPos].delete();
				}
			}
		} else {
			return true;
		}

		return (oFile.delete());
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
					if (!oDestination.exists()) {
						oDestination.mkdir();
					}

					String[] children = oSource.list();
					for (String aChildren : children) {
						copyDir(new File(oSource, aChildren),
							new File(oDestination, aChildren));
					}
				} else {

					InputStream in = new FileInputStream(oSource);
					OutputStream out = new FileOutputStream(oDestination);

					// Copy the bits from instream to outstream
					byte[] buf = new byte[1024];
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
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

	public static Boolean moveDir(File oSource, File oDestination) {
		if (oDestination.exists()) {
			boolean result = true;
			String[] children = oSource.list();
			for (String aChildren : children) {
				File sourceChild = new File(oSource, aChildren);
				File destinationChild = new File(oDestination, aChildren);
				if (sourceChild.isDirectory()) {
					if (!moveDir(sourceChild, destinationChild)) result = false;
				} else {
					if (destinationChild.exists()) destinationChild.delete();
					if (!sourceChild.renameTo(destinationChild)) result = false;
				}
			}
			return result;
		} else {
			return oSource.renameTo(oDestination);
		}
	}

	public static Boolean forceDir(String sDirname) {
		return new File(sDirname).mkdirs();
	}

	public static Boolean existFile(String sFilename) {
		return new File(sFilename).exists();
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

			// Copy the bits from instream to outstream
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (IOException ex) {
			return false;
		}

		return true;
	}


	public static Boolean renameFile(String sSource, String sDestination) {
		File oDestination = new File(sDestination);
		return new File(sSource).renameTo(oDestination);
	}

	public static Boolean removeFile(String sFilename) {
		return new File(sFilename).delete();
	}

	public static Reader getReader(String sFilename) throws FileSystemException {
		InputStreamReader oReader = null;

		try {
			oReader = new InputStreamReader(new FileInputStream(sFilename), "UTF-8");
		} catch (IOException oException) {
			throw new FileSystemException("Could not read file", sFilename, oException.getMessage());
		}

		return oReader;
	}

	public static InputStream getInputStream(String sFilename) throws FileSystemException {
		FileInputStream oReader = null;

		try {
			oReader = new FileInputStream(sFilename);
		} catch (IOException oException) {
			throw new FileSystemException("Could not read file", sFilename, oException.getMessage());
		}

		return oReader;
	}

	public static byte[] getBytesFromFile(String sFilename) throws FileSystemException {
		File oFile = new File(sFilename);
		InputStream oStream = null;
		long lLength;
		byte[] aBytes;
		int iOffset, iNumRead;

		try {
			oStream = new FileInputStream(oFile);

			lLength = oFile.length();
			if (lLength > Integer.MAX_VALUE) {
				throw new FileSystemException("File is too large", sFilename, "");
			}

			aBytes = new byte[(int) lLength];

			iOffset = 0;
			while (iOffset < aBytes.length && (iNumRead = oStream.read(aBytes, iOffset, aBytes.length - iOffset)) >= 0) {
				iOffset += iNumRead;
			}

			if (iOffset < aBytes.length) {
				throw new FileSystemException("Could not completely read file", sFilename, "");
			}

		} catch (IOException oException) {
			throw new FileSystemException("Could not get bytes from file", sFilename, oException.getMessage());
		}

		return aBytes;
	}

	public static String readFile(String sFilename, String Mode) throws FileSystemException {
		char[] sContent = null;
		try {
			File oFile = new File(sFilename);
			InputStreamReader oInput = new InputStreamReader(new FileInputStream(oFile), "UTF-8");
			oInput.read(sContent);
			oInput.close();
		} catch (IOException oException) {
			throw new FileSystemException("Could not read file", sFilename, oException.getMessage());
		}

		return new String(sContent);
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

	public static String readFile(String sFilename) throws FileSystemException {
		StringBuilder oContent = new StringBuilder();
		InputStreamReader oInputStreamReader;
		BufferedReader oBufferedReader;
		String sLine;
		try {
			oInputStreamReader = new InputStreamReader(new FileInputStream(sFilename), "UTF-8");
			oBufferedReader = new BufferedReader(oInputStreamReader);
			while ((sLine = oBufferedReader.readLine()) != null) {
				oContent.append(sLine).append("\n");
			}
			oInputStreamReader.close();
		} catch (IOException oException) {
			throw new FileSystemException("Could not read file", sFilename, oException.getMessage());
		}

		return oContent.toString();
	}

	public static String getReaderContent(Reader oReader) throws FileSystemException {
		StringBuilder sbContent = new StringBuilder();
		BufferedReader oBufferedReader;
		String sLine;

		try {
			oBufferedReader = new BufferedReader(oReader);
			while ((sLine = oBufferedReader.readLine()) != null) {
				sbContent.append(sLine).append("\n");
			}
		} catch (IOException oException) {
			throw new FileSystemException("Could not get content from reader", null, oException.getMessage());
		}

		return sbContent.toString();
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

	public static Boolean writeFile(File file, InputStream content) throws FileSystemException {
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(file);
			copyStream(content, outputStream);

		} catch (IOException oException) {
			throw new FileSystemException("Could not write file", file.getName(), oException.getMessage());
		}

		return true;
	}

	public static Writer getWriter(String sFilename) throws FileSystemException {
		OutputStreamWriter oWriter;
		try {
			oWriter = new OutputStreamWriter(new FileOutputStream(sFilename), "UTF-8");
		} catch (IOException oException) {
			throw new FileSystemException("Could not read file", sFilename, oException.getMessage());
		}

		return oWriter;
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

	public static Boolean appendToFile(String sFilename, String sContent) throws FileSystemException {

		try {
			FileWriter oFileWriter = new FileWriter(sFilename, true);
			oFileWriter.write(sContent);
			oFileWriter.close();
		} catch (IOException oException) {
			throw new FileSystemException("Could not write file", sFilename, oException.getMessage());
		}

		return true;
	}

	public static void copyStream(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[4096];
		int readed;
		while ((readed = input.read(buffer)) > 0) {
			output.write(buffer, 0, readed);
		}
	}


	public static File[] listFiles(String path, FileFilter fileFilter) {
		ArrayList<File> javaFiles = new ArrayList<>();
		listFilesRecursive(new File(path), javaFiles, fileFilter);
		return javaFiles.toArray(new File[javaFiles.size()]);

	}

	private static void listFilesRecursive(File path, ArrayList<File> javaFiles, FileFilter filter) {
		for (File file : path.listFiles(filter))
			if (file.isDirectory()) listFilesRecursive(file, javaFiles, filter);
			else javaFiles.add(file);
	}
}
