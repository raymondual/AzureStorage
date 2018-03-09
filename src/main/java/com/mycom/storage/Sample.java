package com.mycom.storage;

import java.io.File;
import java.util.Scanner;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;
import com.microsoft.azure.storage.file.CloudFile;
import com.microsoft.azure.storage.file.CloudFileClient;
import com.microsoft.azure.storage.file.CloudFileDirectory;
import com.microsoft.azure.storage.file.CloudFileShare;

/**************************************************************************************************************************
* Summary: This application demonstrates how to use the Blob Storage service.
* It does so by creating a container, creating a file, then uploading that file, listing all files in a container, 
* and downloading the file. Then it deletes all the resources it created
* 
* Documentation References:
* Associated Article - https://docs.microsoft.com/en-us/azure/storage/blobs/storage-quickstart-blobs-java
* What is a Storage Account - http://azure.microsoft.com/en-us/documentation/articles/storage-whatis-account/
* Getting Started with Blobs - http://azure.microsoft.com/en-us/documentation/articles/storage-dotnet-how-to-use-blobs/
* Blob Service Concepts - http://msdn.microsoft.com/en-us/library/dd179376.aspx 
* Blob Service REST API - http://msdn.microsoft.com/en-us/library/dd135733.aspx
* *************************************************************************************************************************
* @author Neusoft
*/
public class Sample {
	/******************************************************************************
	 * ******************************************** Instructions: Update the
	 * storageConnectionString variable with your AccountName and Key and then run
	 * the sample.
	 * *****************************************************************************
	 * ********************************************
	 */
	public static final String STORAGE_BLOB_CONNECTION_STRING = "";

	public static final String STORAGE_FILE_CONNECTION_STRING = "";
	
	public static void main(String[] args) {
		new Sample().uploadStorageFile();		
	}

	private void uploadStorageFile() {	
		
		System.out.println("Azure file share storage quick start sample");

		CloudStorageAccount storageAccount;
		CloudFileClient fileClient;
		CloudFileShare share;
		
		try {
			
			/***
			 * Create an Azure File share
			 */
			// Parse the connection string and create a blob client to interact with Blob
			// storage
			storageAccount = CloudStorageAccount.parse(STORAGE_FILE_CONNECTION_STRING);
			// Create the Azure Files client.
			fileClient = storageAccount.createCloudFileClient();
			
			// Get a reference to the file share
			share = fileClient.getShareReference("firstshare");
			
			//set max capacity, max 5T
			share.getProperties().setShareQuota(5*1024);

			if (share.createIfNotExists()) {
			    System.out.println("New share created");
			}
			
			/***
			 * Create directory
			 */
			//Get a reference to the root directory for the share.
			CloudFileDirectory rootDir = share.getRootDirectoryReference();

			//Get a reference to the sample dir directory
			CloudFileDirectory level1Dir = rootDir.getDirectoryReference("level1dir");

			if (level1Dir.createIfNotExists()) {
			    System.out.println("level 1 dir created");
			} else {
			    System.out.println("level 1 dir already exists");
			}
			
			//Get a reference to the sample dir directory
			CloudFileDirectory level2Dir = level1Dir.getDirectoryReference("level2dir");

			if (level2Dir.createIfNotExists()) {
			    System.out.println("level 2 dir created");
			} else {
			    System.out.println("level 2 dir already exists");
			} 
			
			// Define the path to a local file.
	        final String filePath = "D:/Jobs.pdf";
	        
	        CloudFile cloudFile1 = level1Dir.getFileReference("Jobs.pdf");
	        cloudFile1.uploadFromFile(filePath);

	        CloudFile cloudFile2 = level2Dir.getFileReference("Jobs.pdf");
	        cloudFile2.uploadFromFile(filePath);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("Done!");
	}
	
	private void uploadBlobFile() {
		File sourceFile = null, downloadedFile = null;
		System.out.println("Azure Blob storage quick start sample");

		CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container = null;

		try {
			// Parse the connection string and create a blob client to interact with Blob
			// storage
			storageAccount = CloudStorageAccount.parse(STORAGE_BLOB_CONNECTION_STRING);
			blobClient = storageAccount.createCloudBlobClient();
			container = blobClient.getContainerReference("mysecondcontainer");

			// Create the container if it does not exist with public access.
			System.out.println("Creating container: " + container.getName());
			container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(),
					new OperationContext());

			String dest = "D:/MyWork/nginx-1.13.9/html/My.pdf";
			
			// a sample file
			// sourceFile = File.createTempFile("sampleFile", ".txt");
			sourceFile = new File(dest);
						
			System.out.println("Read a sample PDF file at: " + sourceFile.toString());
			// Writer output = new BufferedWriter(new FileWriter(sourceFile));
			// output.write("Hello Azure!");
			// output.close();

			// Getting a blob reference
			CloudBlockBlob blob = container.getBlockBlobReference(sourceFile.getName());

			// Creating blob and uploading file to it
			System.out.println("Uploading the sample PDF file ");
			blob.uploadFromFile(sourceFile.getAbsolutePath());

			// Listing contents of container
			for (ListBlobItem blobItem : container.listBlobs()) {
				System.out.println("URI of blob is: " + blobItem.getUri());
			}

			// Download blob. In most cases, you would have to retrieve the reference
			// to cloudBlockBlob here. However, we created that reference earlier, and
			// haven't changed the blob we're interested in, so we can reuse it.
			// Here we are creating a new file to download to. Alternatively you can also
			// pass in the path as a string into downloadToFile method:
			// blob.downloadToFile("/path/to/new/file").
			downloadedFile = new File(sourceFile.getParentFile(), sourceFile.getName());
			blob.downloadToFile(downloadedFile.getAbsolutePath());
		} catch (StorageException ex) {
			System.out.println(String.format("Error returned from the service. Http code: %d and error code: %s",
					ex.getHttpStatusCode(), ex.getErrorCode()));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			System.out.println("The program has completed successfully.");
			System.out.println(
					"Press the 'Enter' key while in the console to delete the sample files, example container, and exit the application.");

			// Pausing for input
			Scanner sc = new Scanner(System.in);
			sc.nextLine();

			System.out.println("Deleting the container");
			try {
				if (container != null) {
					container.deleteIfExists();
				}
			} catch (StorageException ex) {
				System.out.println(String.format("Service error. Http code: %d and error code: %s",
						ex.getHttpStatusCode(), ex.getErrorCode()));
			}

			System.out.println("Deleting the source, and downloaded files");

			if (downloadedFile != null) {
				downloadedFile.deleteOnExit();
			}

			if (sourceFile != null) {
				sourceFile.deleteOnExit();
			}

			// Closing scanner
			sc.close();
		}
	}
}
