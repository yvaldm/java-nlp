package ru.yvaldm.data.preprocess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yvaldm.data.preprocess.xml.ProductCatalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import java.io.File;

public class CatalogLoader {

    private static final Logger logger = LoggerFactory.getLogger(CatalogLoader.class);
    private static JAXBContext catalogContext;
    private static XMLInputFactory xmlInputFactory;

    static {
        try {
            catalogContext = JAXBContext.newInstance(ProductCatalog.class);
            xmlInputFactory = XMLInputFactory.newFactory();
            xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Unmarshaller catalogUnmarshaller;

    public CatalogLoader() {
        try {
            catalogUnmarshaller = catalogContext.createUnmarshaller();
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }

    public ProductCatalog loadCatalog(String fileName) {

        try {
            return (ProductCatalog) catalogUnmarshaller.unmarshal(new File(fileName));
        } catch (JAXBException ex) {
            logger.error("file: {}", fileName, ex);
        }

        return null;
    }
}
