package com.etl.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * entity class representing a single file
 * @author aseth7
 *
 */
public class ETLFile {
	
	private static final String LINESEPARATOR = " ";
	
	private String fileName;

	private List<String> lines = new ArrayList<>();

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (String conntent : lines) {
			stringBuilder.append(conntent).append(LINESEPARATOR);
		}
		return stringBuilder.toString();
	}
}
