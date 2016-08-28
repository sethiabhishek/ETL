package com.etl.load;

import com.etl.beans.ETLProcessingInputBean;
import com.etl.entity.ETLDocumentEntity;
import com.etl.exceptions.UnsupportedETLOperationException;

/**
 * loaders meant for ETL operation only
 * @author aseth7
 *
 */
public interface IETLoader extends ILoader{
  
	public ETLDocumentEntity performLoading(ETLProcessingInputBean etlProcessingInputBean) throws UnsupportedETLOperationException;
}
