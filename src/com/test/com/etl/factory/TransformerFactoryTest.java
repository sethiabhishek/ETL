package com.etl.factory;

import com.etl.transform.IETLTransform;
import com.etl.transform.transformImpl.UpperCaseTransformer;
import com.etl.transform.transformImpl.WordCountTransformer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class TransformerFactoryTest {

    @Mock
    private UpperCaseTransformer upperCaseTransformer;
    @Mock
    private WordCountTransformer wordCountTransformer;

    @InjectMocks
    private TransformerFactory transformerFactory;

    @Test
    public void shouldReturnUpperCaseTransformer() {
        Optional<IETLTransform> uppercase = transformerFactory.getTransformer("uppercase");

        assertNotNull(uppercase.get());
    }

    @Test
    public void shouldReturnWordCaseTransformer() {
        Optional<IETLTransform> wordcount = transformerFactory.getTransformer("wordcount");

        assertNotNull(wordcount.get());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionForUnknowName() {
        Optional<IETLTransform> unKnown = transformerFactory.getTransformer("");
        unKnown.get();
    }
}