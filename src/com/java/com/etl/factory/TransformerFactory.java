package com.etl.factory;

import com.etl.transform.IETLTransform;
import com.etl.transform.transformImpl.UpperCaseTransformer;
import com.etl.transform.transformImpl.WordCountTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.etl.enums.Type.uppercase;
import static com.etl.enums.Type.wordcount;

/**
 * Factory for transformers in the system
 * @author aseth7
 *
 */
@Component
public class TransformerFactory {

    @Autowired
    @Qualifier("upperCaseTransformer")
    private UpperCaseTransformer upperCaseTransformer;

    @Autowired
    @Qualifier("wordCountTransformer")
    private WordCountTransformer wordCountTransformer;

    public Optional<IETLTransform> getTransformer(String transformerType) {
        Optional<IETLTransform> etlTransform = Optional.empty();
        if (uppercase.name().equals(transformerType)) {
            etlTransform = Optional.of(upperCaseTransformer);
        } else if (wordcount.name().equals(transformerType)) {
            etlTransform = Optional.of(wordCountTransformer);
        }
        return etlTransform;
    }
}
