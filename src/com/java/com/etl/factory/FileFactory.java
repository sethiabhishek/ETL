package com.etl.factory;

import org.springframework.stereotype.Component;

import java.io.File;

/**
 * File factory for creation of new file
 * @author aseth7
 *
 */
@Component
public class FileFactory {
    public File getFile(String fileName) {
        return new File(fileName);
    }
}
