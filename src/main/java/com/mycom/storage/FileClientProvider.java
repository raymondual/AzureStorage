package com.mycom.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.Properties;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.file.CloudFileClient;

/**
 * Manages the storage file client
 * 
 * @author Neusoft
 */
class FileClientProvider {

    /**
     * Validates the connection string and returns the storage file client.
     * The connection string must be in the Azure connection string format.
     *
     * @return The newly created CloudFileClient object
     *
     */
    static CloudFileClient getFileClientReference() throws RuntimeException, IOException, URISyntaxException, InvalidKeyException {

        // Retrieve the connection string
        Properties prop = new Properties();
        try {
            InputStream propertyStream = FileBasics.class.getClassLoader().getResourceAsStream("config.properties");
            if (propertyStream != null) {
                prop.load(propertyStream);
            } else {
                throw new RuntimeException();
            }
        } catch (RuntimeException|IOException e) {
            PrintHelper.printInfo("\nFailed to load config.properties file.");
            throw e;
        }

        CloudStorageAccount storageAccount;
        try {
            storageAccount = CloudStorageAccount.parse(prop.getProperty("StorageConnectionString"));
        } catch (IllegalArgumentException|URISyntaxException e) {
            PrintHelper.printInfo("\nConnection string specifies an invalid URI.");
            PrintHelper.printInfo("Please confirm the connection string is in the Azure connection string format.");
            throw e;
        } catch (InvalidKeyException e) {
            PrintHelper.printInfo("\nConnection string specifies an invalid key.");
            PrintHelper.printInfo("Please confirm the AccountName and AccountKey in the connection string are valid.");
            throw e;
        }

        return storageAccount.createCloudFileClient();
    }
}