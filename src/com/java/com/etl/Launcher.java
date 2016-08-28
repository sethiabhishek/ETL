package com.etl;

import com.etl.adapter.IETLAdapter;
import com.etl.beans.ETLProcessingInputBean;
import com.etl.exceptions.UnsupportedETLOperationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.Scanner;

import static com.etl.enums.Type.uppercase;
import static com.etl.enums.Type.wordcount;

/**
 * For now I have considered FileLoader will be the case if in future DBLoader
 * needs to be presented some of the code needs to be changed in the launcher
 * for presentation purpose, so that the user can choose a valid input and thus
 * processing can be performed although no code corresponding to ETL processing
 * need to changed * 
 * @author aseth7
 */
public class Launcher {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dependency.xml");
		System.out.println("About initiate the ETL process");
		Scanner in = new Scanner(System.in);
		System.out.println("select one of the below \n" + "1 for File loading \n");
		if (in.nextInt() == 1) {
			System.out.println("please enter the inputFilePath to transform");
			String inputFilePath = in.next();

			System.out.println("Enter 1 for WordCount and 2 for UpperCase Transformation");
			String transformationOption = in.next();

			System.out.println("Please enter the outputFilePath");
			String outputFilePath = in.next();

			System.out.println("file to be transformed is---" + inputFilePath);

			if (!checkFileExistence(inputFilePath)) {
				System.out.println("file does not exist, please re-run and enter the correct path");
			} else {
				executeETLProcess(applicationContext, inputFilePath, transformationOption, outputFilePath);
			}

		} else {
			System.out.println("other options not yet supported");
		}
		in.close();

	}

	private static void executeETLProcess(ApplicationContext applicationContext, String inputFilePath,
			String transformationOption, String outputFilePath) {
		ETLProcessingInputBean etlProcessingInputBean;
		IETLAdapter etlAdapter = (IETLAdapter) applicationContext.getBean("ETLAdapterImpl");

		String transformationType = transformationTypeFor(transformationOption);
		etlProcessingInputBean = populateETLProcessingInputBean(inputFilePath, outputFilePath, transformationType);

		try {
			etlAdapter.processETLOperation(etlProcessingInputBean);
			System.out.println("processing complete, please check the output file");
		} catch (UnsupportedETLOperationException e) {
			System.out.println("ETL process unsuccessful--" + e.getErrorMessage());
		}
	}

	private static String transformationTypeFor(String transformationOption) {
		return "1".equalsIgnoreCase(transformationOption) ? wordcount.name() : uppercase.name();
	}

	private static ETLProcessingInputBean populateETLProcessingInputBean(String inputFilePath, String outputFilePath,
			String transformationType) {
		ETLProcessingInputBean etlProcessingInputBean;
		etlProcessingInputBean = new ETLProcessingInputBean();
		etlProcessingInputBean.setInputFilePath(inputFilePath);
		etlProcessingInputBean.setOutputFilePath(outputFilePath);
		etlProcessingInputBean.setTransformation(transformationType);
		return etlProcessingInputBean;
	}

	private static boolean checkFileExistence(String filepath) {
		File file = new File(filepath);
		boolean fileExistFlag = file.exists();
		return fileExistFlag;
	}

}
