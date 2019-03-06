package org.edhernandez.junit.jupiter.dataprovider.extension;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeforeTestNameLogger implements BeforeEachCallback {

    @Override public void beforeEach(ExtensionContext extensionContext) throws Exception {
        Logger logger = LoggerFactory.getLogger(extensionContext.getRequiredTestClass());
        logger.info("Executing test: " + extensionContext.getDisplayName());
    }
}
