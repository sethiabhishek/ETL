package com.etl.load.loaderImpl;

import com.etl.beans.ETLProcessingInputBean;
import com.etl.commons.ETLUtitlity;
import com.etl.entity.ETLDocumentEntity;
import com.etl.entity.ETLFile;
import com.etl.exceptions.UnsupportedETLOperationException;
import com.etl.factory.FileFactory;
import com.etl.load.IETLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * File loader implementation
 * @author aseth7
 *
 */
@Component
public class FileLoader implements IETLoader {

    @Autowired
    private FileFactory fileFactory;
    @Autowired
    private ETLUtitlity etlUtitlity;

    @Override
    public ETLDocumentEntity performLoading(ETLProcessingInputBean etlProcessingInputBean)
            throws UnsupportedETLOperationException {

        ETLDocumentEntity etlDocumentEntity = new ETLDocumentEntity();
        etlDocumentEntity.setDirectoryName(etlProcessingInputBean.getInputFilePath());
        File file = fileFactory.getFile(etlProcessingInputBean.getInputFilePath());
        List<ETLFile> etlFiles = listFilesForFolder(file, etlProcessingInputBean.getInputFilePath());
        etlDocumentEntity.setEtlFiles(etlFiles);
        return etlDocumentEntity;
    }

    private List<ETLFile> listFilesForFolder(final File folder, String parentDirectory)
            throws UnsupportedETLOperationException {

        List<ETLFile> etlFiles = new ArrayList<>();
        ETLFile etlFile = null;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                System.out.println("Will not process the sub-directory for now--" + fileEntry);
            } else {
                etlFile = etlUtitlity.mapFileToETLFile(fileEntry.getName(), parentDirectory);
                etlFiles.add(etlFile);
            }
        }
        return etlFiles;
    }
}
