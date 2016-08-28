package com.etl.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Transformed content of file will be represented by this bean
 * 
 * @author aseth7
 *
 */
public class ETLTransformedEntity {
	private String directoryName;

	private List<ETLFile> etlFiles = new ArrayList<>();

	public String getDirectoryName() {
		return directoryName;
	}

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public List<ETLFile> getEtlFiles() {
		return etlFiles;
	}

	public void setEtlFiles(List<ETLFile> etlFiles) {
		this.etlFiles = etlFiles;
	}

}
