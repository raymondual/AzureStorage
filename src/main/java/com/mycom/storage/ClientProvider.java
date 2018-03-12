package com.mycom.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.Properties;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.file.CloudFileClient;

/**
 * Manages the storage file or blob client
 * 
 * @author Neusoft
 */
class ClientProvider {
    
    private static final String AZURE_CONFIG  = "azureConfig.properties";
    private static final String FILE_CONN_STR = "file.connection.string";
    private static final String BLOB_CONN_STR = "blob.connection.string";
    
    private ClientProvider() {        
    }

    /**
     * Validates the connection string and returns the storage file client.
     * The connection string must be in the Azure connection string format.
     *
     * @return The newly created CloudFileClient object
     *
     */
    static CloudFileClient getFileClient() throws RuntimeException, IOException, URISyntaxException, InvalidKeyException {

        // Retrieve the connection string
        Properties prop = new Properties();
        try {
            InputStream propertyStream = ClientProvider.class.getClassLoader().getResourceAsStream(AZURE_CONFIG);
            if (propertyStream != null) {
                prop.load(propertyStream);
            } else {
                throw new RuntimeException("Failed to load azureConfig.properties file.");
            }
        } catch (RuntimeException|IOException e) {
            PrintHelper.printInfo("\nFailed to load azureConfig.properties file.");
            throw e;
        }

        CloudStorageAccount storageAccount;
        try {
            storageAccount = CloudStorageAccount.parse(prop.getProperty(FILE_CONN_STR));
        } catch (IllegalArgumentException|URISyntaxException e) {
            PrintHelper.printInfo("\nConnection string specifies an invalid URI.");
            throw e;
        } catch (InvalidKeyException e) {
            PrintHelper.printInfo("\nConnection string specifies an invalid key.");
            throw e;
        }

        return storageAccount.createCloudFileClient();
    }
    
    
    /**
     * Validates the connection string and returns the storage blob client.
     * The connection string must be in the Azure connection string format.
     *
     * @return The newly created CloudFileClient object
     *
     */
    static CloudBlobClient getBlobClient() throws RuntimeException, IOException, URISyntaxException, InvalidKeyException {

        // Retrieve the connection string
        Properties prop = new Properties();
        try {
            InputStream propertyStream = ClientProvider.class.getClassLoader().getResourceAsStream(AZURE_CONFIG);
            if (propertyStream != null) {
                prop.load(propertyStream);
            } else {
                throw new RuntimeException("Failed to load azureConfig.properties file.");
            }
        } catch (RuntimeException|IOException e) {
            PrintHelper.printInfo("\nFailed to load azureConfig.properties file.");
            throw e;
        }

        CloudStorageAccount storageAccount;
        try {
            storageAccount = CloudStorageAccount.parse(prop.getProperty(BLOB_CONN_STR));
        } catch (IllegalArgumentException|URISyntaxException e) {
            PrintHelper.printInfo("\nConnection string specifies an invalid URI.");
            throw e;
        } catch (InvalidKeyException e) {
            PrintHelper.printInfo("\nConnection string specifies an invalid key.");
            throw e;
        }

        return storageAccount.createCloudBlobClient();
    }

}