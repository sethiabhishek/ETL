package com.etl.adapter;

import com.etl.beans.ETLProcessingInputBean;
import com.etl.exceptions.UnsupportedETLOperationException;

/**
 * Adapter for talking to various components
 * @author aseth7
 *
 */
public interface IETLAdapter {

    void processETLOperation(ETLProcessingInputBean etlProcessingInputBean) throws UnsupportedETLOperationException;
}
