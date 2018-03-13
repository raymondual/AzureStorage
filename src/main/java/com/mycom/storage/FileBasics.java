package com.mycom.storage;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.file.CloudFile;
import com.microsoft.azure.storage.file.CloudFileClient;
import com.microsoft.azure.storage.file.CloudFileDirectory;
import com.microsoft.azure.storage.file.CloudFileShare;

/**
 * This basic usage of the Azure file storage service.
 * 
 * @author Neusoft
 */
public class FileBasics {
    
    private FileBasics() {
        //
    }
        
    public static void uploadStorageFile(InputStream inStream,long length,String fileName,String shareName,String ...directoryName) throws StorageException, URISyntaxException, IOException, InvalidKeyException {
        CloudFileShare share = getFileShare(ClientProvider.getFileClient(), shareName);        
        CloudFileDirectory[] storedDirRef = new CloudFileDirectory[directoryName.length];
        CloudFileDirectory fileDir;
        for(int i = 0; i < directoryName.length; i++) {
            if(i == 0) {
                fileDir = getFileDirectory(share.getRootDirectoryReference(),directoryName[i]);
            } else {
                fileDir = getFileDirectory(storedDirRef[i-1],directoryName[i]);
            }
            storedDirRef[i] = fileDir;
        }
        CloudFile cloudFile = storedDirRef[directoryName.length-1].getFileReference(fileName);
        cloudFile.upload(inStream,length);
        System.out.println("URI of " + fileName + " is: " + cloudFile.getUri());
        PrintHelper.printInfo("upload successfully.");
    }
    
    public static void downloadStorageFile(OutputStream outputStream,String markword,String fileName,String shareName,String ...directoryName) throws StorageException, URISyntaxException, IOException, InvalidKeyException {
        CloudFileShare share = getFileShare(ClientProvider.getFileClient(), shareName);        
        CloudFileDirectory[] storedDirRef = new CloudFileDirectory[directoryName.length];
        CloudFileDirectory fileDir;
        for(int i = 0; i < directoryName.length; i++) {
            if(i == 0) {
                fileDir = getFileDirectory(share.getRootDirectoryReference(),directoryName[i]);
            } else {
                fileDir = getFileDirectory(storedDirRef[i-1],directoryName[i]);
            }
            storedDirRef[i] = fileDir;
        }
        //Get a reference to the file you want to download
        CloudFile file = storedDirRef[directoryName.length-1].getFileReference(fileName);
        DataInputStream inputStream = new DataInputStream(file.openRead());
        if(fileName.toLowerCase().endsWith(".pdf")) {
            new Watermark().manipulatePdf(inputStream, outputStream, markword);
        } else {
            new Watermark().manipulateImg(inputStream, outputStream, markword);
        }
        PrintHelper.printInfo("download successfully.");
    }
    
    public static void deleteStorageFile(String fileName,String shareName,String ...directoryName) throws StorageException, URISyntaxException, IOException, InvalidKeyException {
        CloudFileShare share = getFileShare(ClientProvider.getFileClient(), shareName);        
        CloudFileDirectory[] storedDirRef = new CloudFileDirectory[directoryName.length];
        CloudFileDirectory fileDir;
        for(int i = 0; i < directoryName.length; i++) {
            if(i == 0) {
                fileDir = getFileDirectory(share.getRootDirectoryReference(),directoryName[i]);
            } else {
                fileDir = getFileDirectory(storedDirRef[i-1],directoryName[i]);
            }
            storedDirRef[i] = fileDir;
        }
        //Get a reference to the file you want to download
        CloudFile file = storedDirRef[directoryName.length-1].getFileReference(fileName);
        if (file.deleteIfExists()) {
            PrintHelper.printInfo(fileName + " was deleted");
        }
    }

    /**
     * get file share, if it's not exist, creates and returns a file share for the application to use.
     *
     * @param fileShareName Name of the file share to create
     * @return The newly created CloudFileShare object
     *
     * @throws StorageException
     * @throws URISyntaxException
     */
    private static CloudFileShare getFileShare(CloudFileClient fileClient, String fileShareName) throws StorageException, URISyntaxException {
        CloudFileShare fileShare;
        try {
            // Create a new file share if it is not exist
            fileShare = fileClient.getShareReference(fileShareName);
            if(fileShare.createIfNotExists()) {
                PrintHelper.printInfo("Created file share " + fileShareName);
            } else {
                PrintHelper.printInfo("File share " + fileShareName + " has been created already");
            }
        } catch (URISyntaxException e) {
            PrintHelper.printException(e);
            throw e;
        } catch (StorageException s) {
            PrintHelper.printException(s);
            throw s;
        }
        return fileShare;
    }
    
    private static CloudFileDirectory getFileDirectory(CloudFileDirectory parentDir, String fileDirName) throws StorageException, URISyntaxException {        
        CloudFileDirectory fileDirectory;
        try {
            // Create a new file directory if it is not exist
            fileDirectory = parentDir.getDirectoryReference(fileDirName);
            if(fileDirectory.createIfNotExists()) {
                PrintHelper.printInfo("Created file directory " + fileDirName);
            } else {
                PrintHelper.printInfo("File directory " + fileDirName + " has been created already");
            }
        } catch (URISyntaxException e) {
            PrintHelper.printException(e);
            throw e;
        } catch (StorageException s) {
            PrintHelper.printException(s);
            throw s;
        }
        return fileDirectory;
    }

}
