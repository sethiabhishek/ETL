package com.etl.load.loaderImpl;

import com.etl.beans.ETLProcessingInputBean;
import com.etl.commons.ETLUtitlity;
import com.etl.entity.ETLDocumentEntity;
import com.etl.entity.ETLFile;
import com.etl.exceptions.UnsupportedETLOperationException;
import com.etl.factory.FileFactory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileLoaderTest {

    @Mock
    private FileFactory fileFactory;
    @Mock
    private File file;
    @Mock
    private ETLUtitlity etlUtitlity;

    @InjectMocks
    private FileLoader loader;

    @Test
    public void shouldNotReturnFileForDir() throws UnsupportedETLOperationException {
        ETLProcessingInputBean inputBean = new ETLProcessingInputBean();
        inputBean.setInputFilePath("inputDir");
        inputBean.setLoaderType("loader");
        inputBean.setOutputFilePath("outputDir");
        inputBean.setTransformation("transformer");

        when(fileFactory.getFile(inputBean.getInputFilePath())).thenReturn(file);
        when(file.listFiles()).thenReturn(new File[]{file});
        when(file.isDirectory()).thenReturn(true);

        ETLDocumentEntity etlDocumentEntity = loader.performLoading(inputBean);

        Assert.assertEquals(etlDocumentEntity.getEtlFiles().size(), 0);


    }

    @Test
    public void shouldReturnFileForFilePath() throws UnsupportedETLOperationException {
        ETLProcessingInputBean inputBean = new ETLProcessingInputBean();
        inputBean.setInputFilePath("inputDir");
        inputBean.setLoaderType("loader");
        inputBean.setOutputFilePath("outputDir");
        inputBean.setTransformation("transformer");
        String inputFile = "data.txt";

        when(fileFactory.getFile(inputBean.getInputFilePath())).thenReturn(file);
        when(file.listFiles()).thenReturn(new File[]{file});
        when(file.isDirectory()).thenReturn(false);
        when(file.getName()).thenReturn(inputFile);
        when(etlUtitlity.mapFileToETLFile(inputFile, inputBean.getInputFilePath())).thenReturn(new ETLFile());

        ETLDocumentEntity etlDocumentEntity = loader.performLoading(inputBean);

        Assert.assertEquals(etlDocumentEntity.getEtlFiles().size(), 1);


    }

}