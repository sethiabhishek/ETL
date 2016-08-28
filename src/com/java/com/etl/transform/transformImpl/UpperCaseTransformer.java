package com.etl.transform.transformImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.etl.entity.ETLDocumentEntity;
import com.etl.entity.ETLFile;
import com.etl.entity.ETLTransformedEntity;
import com.etl.exceptions.UnsupportedETLOperationException;
import com.etl.transform.IETLTransform;

/**
 * transformer to convert text of Document
 * 
 * @author aseth7
 *
 */
@Component
public class UpperCaseTransformer implements IETLTransform {

	@Override
	public ETLTransformedEntity performTransformation(ETLDocumentEntity etlDocumentEntity)
			throws UnsupportedETLOperationException {
		ETLTransformedEntity etlTransformedEntity = new ETLTransformedEntity();
		etlTransformedEntity.setDirectoryName(etlDocumentEntity.getDirectoryName());
		transformDocument(etlTransformedEntity,etlDocumentEntity);
		return etlTransformedEntity;
	}

	private void transformDocument(ETLTransformedEntity etlTransformedEntity, ETLDocumentEntity etlDocumentEntity) {
		   List<ETLFile> etlFiles = etlDocumentEntity.getEtlFiles();
		   List<ETLFile>  transformedETLFiles = etlTransformedEntity.getEtlFiles();
		   ETLFile transformedETLFile = null;
		   for (ETLFile etlFile : etlFiles) {
			 transformedETLFile = new ETLFile();
			 transformedETLFile.setFileName(etlFile.getFileName());
			 List<String> content = etlFile.getLines();
			 List<String> transformedContent = content.stream()
			            .map(String::toUpperCase)
			            .collect(Collectors.toList()); 	
			 transformedETLFile.setLines(transformedContent);
			 transformedETLFiles.add(transformedETLFile);
		}
		
	}

}
