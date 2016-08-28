package com.etl.transform.transformImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.etl.entity.ETLDocumentEntity;
import com.etl.entity.ETLFile;
import com.etl.entity.ETLTransformedEntity;
import com.etl.exceptions.UnsupportedETLOperationException;
import com.etl.transform.IETLTransform;

/**
 * Transformer to count number of unique words in Document
 * 
 * @author aseth7
 *
 */

@Component
public class WordCountTransformer implements IETLTransform {

	private static final String SEPARATOR = " ";

	private static final String CONTENTSEPARATOR = ">";

	@Override
	public ETLTransformedEntity performTransformation(ETLDocumentEntity etlDocumentEntity)
			throws UnsupportedETLOperationException {
		ETLTransformedEntity etlTransformedEntity = new ETLTransformedEntity();
		etlTransformedEntity.setDirectoryName(etlDocumentEntity.getDirectoryName());
		transformETLcontent(etlDocumentEntity, etlTransformedEntity);
		return etlTransformedEntity;
	}

	private void transformETLcontent(ETLDocumentEntity etlDocumentEntity, ETLTransformedEntity etlTransformedEntity) {
		ETLFile transformedETLFile = null;
		List<ETLFile> transformedETLFiles = etlTransformedEntity.getEtlFiles();
		String originalContent = "";
		int count = 0;
		HashMap<String, Integer> contentMap = new HashMap<>();
		for (ETLFile etlFile : etlDocumentEntity.getEtlFiles()) {
			transformedETLFile = new ETLFile();
			List<String> transformedETLFileContentList = transformedETLFile.getLines();
			transformedETLFile.setFileName(etlFile.getFileName());
			originalContent = etlFile.toString();
			String[] words = originalContent.replaceAll("\\s+", " ").split(" ");
			for (String content : words) {
				if (contentMap.containsKey(content)) {
					count = contentMap.get(content);
					contentMap.put(content, count + 1);
				} else {
					count = 1;
					contentMap.put(content, count);
				}
			}
			String transformedContent = getTransformedText(contentMap);
			transformedETLFileContentList.add(transformedContent);
			transformedETLFiles.add(transformedETLFile);
		}
	}

	private String getTransformedText(HashMap<String, Integer> contentMap) {
		String transformedText = "";
		if (null != contentMap && !contentMap.isEmpty()) {
			StringBuilder stringBuilder = new StringBuilder();
			Set<Entry<String, Integer>> contentEntrySet = contentMap.entrySet();
			for (Entry<String, Integer> entry : contentEntrySet) {
				stringBuilder.append(entry.getKey()).append(CONTENTSEPARATOR).append(entry.getValue())
						.append(SEPARATOR);
			}
			transformedText = stringBuilder.toString();
		}
		return transformedText;
	}

}
