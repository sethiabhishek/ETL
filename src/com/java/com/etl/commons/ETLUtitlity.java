package com.etl.commons;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Component;

import com.etl.entity.ETLFile;
import com.etl.exceptions.UnsupportedETLOperationException;

/**
 * 
 * @author aseth7
 *
 */
@Component
public class ETLUtitlity {

	public  ETLFile mapFileToETLFile(String fileName, String parentDirectory)
			throws UnsupportedETLOperationException {
		ETLFile etlFile = new ETLFile();
		etlFile.setFileName(fileName);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(parentDirectory).append("/").append(fileName);
		try (BufferedReader br = new BufferedReader(new FileReader(stringBuilder.toString()))) {
			String line;
			while ((line = br.readLine()) != null) {
				etlFile.getLines().add(line);
			}
		} catch (Exception e) {
			throw new UnsupportedETLOperationException();
		}
		return etlFile;
	}
}
