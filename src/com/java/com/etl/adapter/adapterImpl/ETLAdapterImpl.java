package com.etl.adapter.adapterImpl;

import com.etl.adapter.IETLAdapter;
import com.etl.beans.ETLProcessingInputBean;
import com.etl.commons.IETLFileWriter;
import com.etl.exceptions.UnsupportedETLOperationException;
import com.etl.factory.LoaderFactory;
import com.etl.factory.TransformerFactory;
import com.etl.factory.WriterFactory;
import com.etl.load.IETLoader;
import com.etl.transform.IETLTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adapter implementation
 * @author aseth7
 *
 */
@Component
public class ETLAdapterImpl implements IETLAdapter {

	@Value("${loader}")
	private String loaderType;

	@Value("${writeto}")
	private String writeTo;

	@Autowired
	private LoaderFactory loaderFactory;

	@Autowired
	private TransformerFactory transformerFactory;

	@Autowired
	private WriterFactory writerFactory;

	@Override
	public void processETLOperation(ETLProcessingInputBean etlProcessingInputBean)
			throws UnsupportedETLOperationException {

		populateETLProcessingInputBean(etlProcessingInputBean);

		Optional<IETLoader> loader = loaderFactory.getLoader(loaderType);
		Optional<IETLTransform> transformer = transformerFactory
				.getTransformer(etlProcessingInputBean.getTransformation());
		Optional<IETLFileWriter> writer = writerFactory.getWriter(writeTo);

		boolean checkProcessing = loader.isPresent() && transformer.isPresent() && writer.isPresent();

		if (checkProcessing) {
			writer.get().writeDocumentToFile(etlProcessingInputBean.getOutputFilePath(),
					transformer.get().performTransformation(loader.get().performLoading(etlProcessingInputBean)));
		}
	}

	private void populateETLProcessingInputBean(ETLProcessingInputBean etlProcessingInputBean) {
		etlProcessingInputBean.setTransformation(etlProcessingInputBean.getTransformation());
		etlProcessingInputBean.setLoaderType(loaderType);
		etlProcessingInputBean.setWriterCategory(writeTo);
	}

}
