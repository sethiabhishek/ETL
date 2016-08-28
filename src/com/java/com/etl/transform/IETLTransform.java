package com.etl.transform;

import com.etl.entity.ETLDocumentEntity;
import com.etl.entity.ETLTransformedEntity;
import com.etl.exceptions.UnsupportedETLOperationException;

/**
 * interface to denote all ETL related operations
 * @author aseth7
 *
 */
public interface IETLTransform {
 
	public ETLTransformedEntity performTransformation(ETLDocumentEntity etlDocumentEntity) throws UnsupportedETLOperationException;
}
