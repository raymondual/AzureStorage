package com.mycom.file;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.Iterator;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.file.CloudFile;
import com.microsoft.azure.storage.file.CloudFileClient;
import com.microsoft.azure.storage.file.CloudFileDirectory;
import com.microsoft.azure.storage.file.CloudFileShare;
import com.microsoft.azure.storage.file.CopyStatus;
import com.microsoft.azure.storage.file.ListFileItem;

/**
 * This basic usage of the Azure file storage service.
 * 
 * @author Neusoft
 */
public class FileBasics {
        
    public void uploadStorageFile(CloudFileShare share) throws StorageException, URISyntaxException, IOException {
        //Get a reference to the root directory for the share.
        CloudFileDirectory rootDir = share.getRootDirectoryReference();
        // Define the path to a local file.
        CloudFile cloudFile = rootDir.getFileReference("Readme.txt");
        cloudFile.uploadFromFile("C:/temp/Readme.txt");
    }
    
    public void downloadStorageFile(CloudFileShare share) throws StorageException, URISyntaxException, IOException {
        //Get a reference to the root directory for the share.
        CloudFileDirectory rootDir = share.getRootDirectoryReference();
        //Get a reference to the directory that contains the file
        CloudFileDirectory sampleDir = rootDir.getDirectoryReference("sampledir");
        //Get a reference to the file you want to download
        CloudFile file = sampleDir.getFileReference("SampleFile.txt");
        //Write the contents of the file to the console.
        PrintHelper.printInfo(file.downloadText());
    }
    
    public void deleteStorageFile(CloudFileShare share) throws StorageException, URISyntaxException {
        // Get a reference to the root directory for the share.
        CloudFileDirectory rootDir = share.getRootDirectoryReference();
        // Get a reference to the directory where the file to be deleted is in
        CloudFileDirectory containerDir = rootDir.getDirectoryReference("sampledir");
        String filename = "SampleFile.txt";
        CloudFile file;
        file = containerDir.getFileReference(filename);
        if ( file.deleteIfExists() ) {
            PrintHelper.printInfo(filename + " was deleted");
        }
    }

    /**
     * Azure Storage File Sample
     *
     * @throws Exception
     */
//    public void runSamples() throws Exception {
//
//    	PrintHelper.printInfo("Azure Storage File - Starting.");
//
//        CloudFileClient fileClient = null;
//        CloudFileShare fileShare1 = null;
//        CloudFileShare fileShare2 = null;
//        FileInputStream fileInputStream = null;
//
//        try {
//
//            // Create a file client for interacting with the file service
//            fileClient = FileClientProvider.getFileClientReference();
//
//            // Create sample file for upload demonstration
//            Random random = new Random();
//            PrintHelper.printInfo("\nCreating sample file between 128KB-256KB in size for upload demonstration.");
//            File tempFile1 = DataGenerator.createTempLocalFile("file-", ".tmp", (128 * 1024) + random.nextInt(256 * 1024));
//            PrintHelper.printInfo(String.format("\tSuccessfully created the file \"%s\".", tempFile1.getAbsolutePath()));
//
//            // Create file share with randomized name
//            PrintHelper.printInfo("\nCreate file share for the sample demonstration");
//            fileShare1 = createFileShare(fileClient, DataGenerator.createRandomName("filebasics-"));
//            PrintHelper.printInfo(String.format("\tSuccessfully created the file share \"%s\".", fileShare1.getName()));
//
//            // Get a reference to the root directory of the share.
//            CloudFileDirectory rootDir1 = fileShare1.getRootDirectoryReference();
//
//            // Upload a local file to the root directory
//            PrintHelper.printInfo("\nUpload the sample file to the root directory.");
//            CloudFile file1 = rootDir1.getFileReference(tempFile1.getName());
//            file1.uploadFromFile(tempFile1.getAbsolutePath());
//            PrintHelper.printInfo("\tSuccessfully uploaded the file.");
//
//            // Create a random directory under the root directory
//            PrintHelper.printInfo("\nCreate a random directory under the root directory");
//            CloudFileDirectory dir = rootDir1.getDirectoryReference(DataGenerator.createRandomName("dir-"));
//            if (dir.createIfNotExists()) {
//                PrintHelper.printInfo(String.format("\tSuccessfully created the directory \"%s\".", dir.getName()));
//            }
//            else {
//                throw new IllegalStateException(String.format("Directory with name \"%s\" already exists.", dir.getName()));
//            }
//
//            // Upload a local file to the newly created directory sparsely (Only upload certain ranges of the file)
//            PrintHelper.printInfo("\nUpload the sample file to the newly created directory partially in distinct ranges.");
//            CloudFile file1sparse = dir.getFileReference(tempFile1.getName());
//            file1sparse.create(tempFile1.length());
//            fileInputStream = new FileInputStream(tempFile1);
//            PrintHelper.printInfo("\t\tRange start: 0, length: 1024.");
//            file1sparse.uploadRange(fileInputStream, 0, 1024);
//            PrintHelper.printInfo("\t\tRange start: 4096, length: 1536.");
//            fileInputStream.getChannel().position(4096);
//            file1sparse.uploadRange(fileInputStream, 4096, 1536);
//            PrintHelper.printInfo("\t\tRange start: 8192, length: EOF.");
//            fileInputStream.getChannel().position(8192);
//            file1sparse.uploadRange(fileInputStream, 8192, tempFile1.length() - 8192);
//            fileInputStream.close();
//            PrintHelper.printInfo("\tSuccessfully uploaded the file sparsely.");
//
//            // Query the file ranges
//            PrintHelper.printInfo(String.format("\nQuery the file ranges for \"%s\".", file1sparse.getUri().toURL()));
//            ArrayList<FileRange> fileRanges = file1sparse.downloadFileRanges();
//            for (Iterator<FileRange> itr = fileRanges.iterator(); itr.hasNext(); ) {
//                FileRange fileRange = itr.next();
//                PrintHelper.printInfo(String.format("\tStart offset: %d, End offset: %d", fileRange.getStartOffset(), fileRange.getEndOffset()));
//            }
//
//            // Clear a range and re-query the file ranges
//            PrintHelper.printInfo(String.format("\nClearing the second range partially and then re-query the file ranges for \"%s\".", file1sparse.getUri().toURL()));
//            file1sparse.clearRange(4608, 512);
//            fileRanges = file1sparse.downloadFileRanges();
//            for (Iterator<FileRange> itr = fileRanges.iterator(); itr.hasNext(); ) {
//                FileRange fileRange = itr.next();
//                PrintHelper.printInfo(String.format("\tStart offset: %d, End offset: %d", fileRange.getStartOffset(), fileRange.getEndOffset()));
//            }
//
//            // Create another file share with randomized name
//            PrintHelper.printInfo("\nCreate another file share for the sample demonstration");
//            fileShare2 = createFileShare(fileClient, DataGenerator.createRandomName("filebasics-"));
//            PrintHelper.printInfo(String.format("\tSuccessfully created the file share \"%s\".", fileShare2.getName()));
//
//            // Get a reference to the root directory of the share.
//            CloudFileDirectory rootDir2 = fileShare2.getRootDirectoryReference();
//
//            // Create sample file for copy demonstration
//            PrintHelper.printInfo("\nCreating sample file between 10MB-15MB in size for copy demonstration.");
//            File tempFile2 = DataGenerator.createTempLocalFile("file-", ".tmp", (10 * 1024 * 1024) + random.nextInt(5 * 1024 * 1024));
//            PrintHelper.printInfo(String.format("\tSuccessfully created the file \"%s\".", tempFile2.getAbsolutePath()));
//
//            // Upload a local file to the root directory
//            PrintHelper.printInfo("\nUpload the sample file to the root directory.");
//            CloudFile file2 = rootDir1.getFileReference(tempFile2.getName());
//            file2.uploadFromFile(tempFile2.getAbsolutePath());
//            PrintHelper.printInfo("\tSuccessfully uploaded the file.");
//
//            // Copy the file between shares
//            PrintHelper.printInfo(String.format("\nCopying file \"%s\" from share \"%s\" into the share \"%s\".", file2.getName(), fileShare1.getName(), fileShare2.getName()));
//            CloudFile file2copy = rootDir2.getFileReference(file2.getName() + "-copy");
//            file2copy.startCopy(file2);
//            waitForCopyToComplete(file2copy);
//            PrintHelper.printInfo("\tSuccessfully copied the file.");
//
//            // Abort copying the file between shares
//            PrintHelper.printInfo(String.format("\nAbort when copying file \"%s\" from share \"%s\" into the share \"%s\".", file2.getName(), fileShare1.getName(), fileShare2.getName()));
//            PrintHelper.printInfo(String.format("\nAbort when copying file from the root directory \"%s\" into the directory we created \"%s\".", file2.getUri().toURL(), dir.getUri().toURL()));
//            CloudFile file2copyaborted = rootDir2.getFileReference(file2.getName() + "-copyaborted");
//            boolean copyAborted = true;
//            String copyId = file2copyaborted.startCopy(file2);
//            try {
//                file2copyaborted.abortCopy(copyId);
//            }
//            catch (StorageException ex) {
//                if (ex.getErrorCode().equals("NoPendingCopyOperation")) {
//                    copyAborted = false;
//                } else {
//                    throw ex;
//                }
//            }
//            if (copyAborted == true) {
//                PrintHelper.printInfo("\tSuccessfully aborted copying the file.");
//            } else {
//                PrintHelper.printInfo("\tFailed to abort copying the file because the copy finished before we could abort.");
//            }
//
//            // List all file shares and files/directories in each share
//            PrintHelper.printInfo("\nList all file shares and files/directories in each share.");
//            enumerateFileSharesAndContents(fileClient);
//
//            // Download the uploaded files
//            PrintHelper.printInfo("\nDownload the uploaded files.");
//            String downloadedFilePath = String.format("%s%s", System.getProperty("java.io.tmpdir"), file1.getName());
//            PrintHelper.printInfo(String.format("\tDownload the fully uploaded file from \"%s\" to \"%s\".", file1.getUri().toURL(), downloadedFilePath));
//            file1.downloadToFile(downloadedFilePath);
//            new File(downloadedFilePath).deleteOnExit();
//            downloadedFilePath = String.format("%s%s", System.getProperty("java.io.tmpdir"), file1sparse.getName());
//            PrintHelper.printInfo(String.format("\tDownload the sparsely uploaded file from \"%s\" to \"%s\".", file1sparse.getUri().toURL(), downloadedFilePath));
//            file1sparse.downloadToFile(downloadedFilePath);
//            new File(downloadedFilePath).deleteOnExit();
//            downloadedFilePath = String.format("%s%s", System.getProperty("java.io.tmpdir"), file2.getName());
//            PrintHelper.printInfo(String.format("\tDownload the copied file from \"%s\" to \"%s\".", file2.getUri().toURL(), downloadedFilePath));
//            file2.downloadToFile(downloadedFilePath);
//            new File(downloadedFilePath).deleteOnExit();
//            downloadedFilePath = String.format("%s%s", System.getProperty("java.io.tmpdir"), file2copy.getName());
//            PrintHelper.printInfo(String.format("\tDownload the copied file from \"%s\" to \"%s\".", file2copy.getUri().toURL(), downloadedFilePath));
//            file2copy.downloadToFile(downloadedFilePath);
//            new File(downloadedFilePath).deleteOnExit();
//            downloadedFilePath = String.format("%s%s", System.getProperty("java.io.tmpdir"), file2copyaborted.getName());
//            PrintHelper.printInfo(String.format("\tDownload the copied file from \"%s\" to \"%s\".", file2copyaborted.getUri().toURL(), downloadedFilePath));
//            file2copyaborted.downloadToFile(downloadedFilePath);
//            new File(downloadedFilePath).deleteOnExit();
//            PrintHelper.printInfo("\tSuccessfully downloaded the files.");
//
//            // Delete the files and directory
//            PrintHelper.printInfo("\nDelete the filess and directory. Press any key to continue...");
//
//            file1.delete();
//            file1sparse.delete();
//            file2.delete();
//            file2copy.delete();
//            file2copyaborted.delete();
//            PrintHelper.printInfo("\tSuccessfully deleted the files.");
//            dir.delete();
//            PrintHelper.printInfo("\tSuccessfully deleted the directory.");
//        }
//        catch (Throwable t) {
//            PrintHelper.printException(t);
//        }
//        finally {
//            // Delete any file shares that we created (If you do not want to delete the file share comment the line of code below)
//            System.out.print("\nDelete any file shares we created.");
//
//            if (fileShare1 != null && fileShare1.deleteIfExists() == true) {
//                PrintHelper.printInfo(String.format("\tSuccessfully deleted the file share: %s", fileShare1.getName()));
//            }
//
//            if (fileShare2 != null && fileShare2.deleteIfExists() == true) {
//                PrintHelper.printInfo(String.format("\tSuccessfully deleted the file share: %s", fileShare2.getName()));
//            }
//
//            // Close the file input stream of the local temporary file
//            if (fileInputStream != null) {
//                fileInputStream.close();
//            }
//        }
//
//        PrintHelper.printInfo("\nAzure Storage File sample - Completed.\n");
//    }

    /**
     * Creates and returns a file share for the sample application to use.
     *
     * @param fileShareName Name of the file share to create
     * @return The newly created CloudFileShare object
     *
     * @throws StorageException
     * @throws RuntimeException
     * @throws IOException
     * @throws URISyntaxException
     * @throws IllegalArgumentException
     * @throws InvalidKeyException
     * @throws IllegalStateException
     */
    private static CloudFileShare createFileShare(CloudFileClient fileClient, String fileShareName) throws StorageException, RuntimeException, IOException, InvalidKeyException, IllegalArgumentException, URISyntaxException, IllegalStateException {

        // Create a new file share
        CloudFileShare fileShare = fileClient.getShareReference(fileShareName);
        try {
            if (!fileShare.createIfNotExists()) {
                throw new IllegalStateException(String.format("File share with name \"%s\" already exists.", fileShareName));
            }
        }
        catch (StorageException s) {
            if (s.getCause() instanceof java.net.ConnectException) {
                PrintHelper.printInfo("Caught connection exception from the client. If running with the default configuration please make sure you have started the storage emulator.");
            }
            throw s;
        }

        return fileShare;
    }

    /**
     * Enumerates the contents of the file share.
     *
     * @param rootDir Root directory which needs to be enumerated
     *
     * @throws StorageException
     */
    private static void enumerateDirectoryContents(CloudFileDirectory rootDir) throws StorageException {

        Iterable<ListFileItem> results = rootDir.listFilesAndDirectories();
        for (Iterator<ListFileItem> itr = results.iterator(); itr.hasNext(); ) {
            ListFileItem item = itr.next();
            boolean isDirectory = item.getClass() == CloudFileDirectory.class;
            PrintHelper.printInfo(String.format("\t\t%s: %s", isDirectory ? "Directory " : "File      ", item.getUri().toString()));
            if (isDirectory == true) {
            	enumerateDirectoryContents((CloudFileDirectory) item);
            }
        }
    }

    /**
     * Enumerates the shares and contents of the file shares.
     *
     * @param fileClient CloudFileClient object
     *
     * @throws StorageException
     * @throws URISyntaxException
     */
    private static void enumerateFileSharesAndContents(CloudFileClient fileClient) throws StorageException, URISyntaxException {

        for (CloudFileShare share : fileClient.listShares("filebasics")) {
            PrintHelper.printInfo(String.format("\tFile Share: %s", share.getName()));
            enumerateDirectoryContents(share.getRootDirectoryReference());
        }
    }

    /**
     * Wait until the copy complete.
     *
     * @param file Target of the copy operation
     *
     * @throws InterruptedException
     * @throws StorageException
     */
    private static void waitForCopyToComplete(CloudFile file) throws InterruptedException, StorageException {
        CopyStatus copyStatus = CopyStatus.PENDING;
        while (copyStatus == CopyStatus.PENDING) {
            Thread.sleep(1000);
            copyStatus = file.getCopyState().getStatus();
        }
    }
}
