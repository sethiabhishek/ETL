package com.etl.factory;

import com.etl.enums.Type;
import com.etl.load.IETLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Factory for loaders in the system
 * @author aseth7
 *
 */
@Component
public class LoaderFactory {

    @Autowired
    private IETLoader fileLoader;

    public Optional<IETLoader> getLoader(String loaderType) {
        Optional<IETLoader> etLoader = Optional.empty();
        if (loaderType.equals(Type.file.name())) {
            etLoader = Optional.of(fileLoader);
        }
        return etLoader;
    }
}
