package com.etl.commons;

import com.etl.entity.ETLTransformedEntity;
import com.etl.exceptions.UnsupportedETLOperationException;

/**
 * ETL type writer for writing data specifically to file
 * @author aseth7
 *
 */
public interface IETLFileWriter extends IWriter {

    void writeDocumentToFile(String outDir, ETLTransformedEntity etlTransformedEntity) throws UnsupportedETLOperationException;
}
