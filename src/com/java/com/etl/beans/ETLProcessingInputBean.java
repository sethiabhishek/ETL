package com.etl.beans;

/**
 * Bean for communication
 * 
 * @author aseth7
 *
 */
public class ETLProcessingInputBean {

	private String transformation;
	private String inputFilePath;
	private String outputFilePath;
	private String loaderType;
	private String writerCategory;

	public String getWriterCategory() {
		return writerCategory;
	}

	public void setWriterCategory(String writerCategory) {
		this.writerCategory = writerCategory;
	}

	public String getLoaderType() {
		return loaderType;
	}

	public void setLoaderType(String loaderType) {
		this.loaderType = loaderType;
	}

	public void setTransformation(String transformation) {
		this.transformation = transformation;
	}

	public String getInputFilePath() {
		return inputFilePath;
	}

	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	public String getOutputFilePath() {
		return outputFilePath;
	}

	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

	public String getTransformation() {
		return transformation;
	}
}
