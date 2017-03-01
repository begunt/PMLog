package by.a1qa.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by alexei.khilchuk on 24/09/2016.
 */
public final class PropertiesResourceManager {
    private static final Logger LOG = LoggerFactory.getLogger(PropertiesResourceManager.class.getName());
    private Properties properties;

    public PropertiesResourceManager() {
        this.properties = new Properties();
        this.properties = new Properties();
    }

    public PropertiesResourceManager(String resourceName) {
        this.properties = new Properties();
        this.properties = this.appendFromResource(this.properties, resourceName);
    }

    public PropertiesResourceManager(String defaultResourceName, String resourceName) {
        this(defaultResourceName);
        this.properties = this.appendFromResource(new Properties(this.properties), resourceName);
    }

    private Properties appendFromResource(Properties objProperties, String resourceName) {
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
        if(inStream != null) {
            try {
                objProperties.load(inStream);
                inStream.close();
            } catch (IOException var5) {
                LOG.info("IOException was caught:",var5);
            }
        } else {
            LOG.error(String.format("Resource \"%1$s\" could not be found", new Object[]{resourceName}));
        }

        return objProperties;
    }

    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return this.properties.getProperty(key, defaultValue);
    }

    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }

}
