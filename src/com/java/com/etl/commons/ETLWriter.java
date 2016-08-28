package com.etl.commons;

import com.etl.entity.ETLFile;
import com.etl.entity.ETLTransformedEntity;
import com.etl.exceptions.UnsupportedETLOperationException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * ETL file writer
 * @author aseth7
 *
 */
@Component
public class ETLWriter implements IETLFileWriter {

	private static final String OUTPUT = "./Output";

	private static final String SEPARATOR = "/";

	@Override
	public void writeDocumentToFile(String outDir, ETLTransformedEntity etlTransformEntity)
			throws UnsupportedETLOperationException {
		try {
			boolean checkDirectoryExist = checkDirectoryPath(outDir);
			if(!checkDirectoryExist){
				outDir = OUTPUT;
			}
			mapDocumentToFiles(outDir, etlTransformEntity.getEtlFiles());
		} catch (IOException e) {
			System.err.println("Cannot write data to file");
			throw new UnsupportedETLOperationException();
		}
	}

	private boolean checkDirectoryPath(String outDir) {
		boolean directoryExist = false;
		File directory = new File(outDir);
		directoryExist = directory.exists();
		return directoryExist;
	}

	private void mapDocumentToFiles(String directoryPath, List<ETLFile> etlFiles)
			throws UnsupportedETLOperationException, IOException {
		File file = null;
		for (ETLFile etlFile : etlFiles) {
			file = new File(directoryPath + SEPARATOR + etlFile.getFileName());
			file.createNewFile();
			mapETLContentToFile(file, etlFile);
		}
	}

	private void mapETLContentToFile(File file, ETLFile etlFile) throws FileNotFoundException {
		List<String> contentList = etlFile.getLines();
		PrintWriter pw = new PrintWriter(file);
		if (!contentList.isEmpty()) {
			for (String content : contentList) {
				pw.println(content);
			}
		}
		pw.flush();
		pw.close();
	}

}
