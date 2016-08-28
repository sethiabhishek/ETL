package com.etl.factory;

import com.etl.load.IETLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class LoaderFactoryTest {

    @Mock
    private IETLoader ietLoader;
    @InjectMocks
    private LoaderFactory loaderFactory;

    @Test
    public void shouldGetFileLoaderFactory() {
        Optional<IETLoader> loader = loaderFactory.getLoader("file");
        assertNotNull(loader.get());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldGetNoSuchElementExceptionForUnKnownLoaderFactory() {
        Optional<IETLoader> loader = loaderFactory.getLoader("");
        assertNotNull(loader.get());
    }


}