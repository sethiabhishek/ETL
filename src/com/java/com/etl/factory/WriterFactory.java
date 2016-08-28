package com.etl.factory;

import com.etl.commons.IETLFileWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.etl.enums.Type.file;

/**
 * Factory for writers configured in the system
 * @author aseth7
 *
 */
@Component
public class WriterFactory {

    @Autowired
    private IETLFileWriter ietlFileWriter;

    public Optional<IETLFileWriter> getWriter(String writerType) {
        Optional<IETLFileWriter> writer = Optional.empty();
        if (file.name().equals(writerType)) {
            writer = Optional.of(ietlFileWriter);
        }
        return writer;
    }
}
