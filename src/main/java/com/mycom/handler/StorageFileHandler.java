package com.mycom.handler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.microsoft.azure.storage.StorageException;
import com.mycom.service.StorageFileService;
import com.mycom.storage.FileBasics;

@Controller
public class StorageFileHandler {
    
    @Autowired
    private StorageFileService storageFileService;
    @Autowired
    private HttpServletResponse response;
    
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String storageShare = "firstshare";
        String storageParentDir = "level1dir";
        String storageChildDir = "level2dir";        
        
        try {
            FileBasics.uploadStorageFile(file.getInputStream(), file.getSize(), fileName, storageShare, storageParentDir, storageChildDir);
            System.out.println("File upload successfully.");
            long id = storageFileService.insertStorageFile(fileName, storageShare, storageParentDir, storageChildDir);
            if(id <= 0) {
                System.out.println("Insert db failure");
            } else {
                System.out.println("Insert db successfully, id is " + id);
            }
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @RequestMapping(value = "/downloadFile")
    @ResponseBody
    public String download(@RequestParam("fileName" ) String fileName) {
        String storageShare = "firstshare";
        String storageParentDir = "level1dir";
        String storageChildDir = "level2dir";
        String markword = "This is sample";
               
        try {
            FileBasics.downloadStorageFile(response.getOutputStream(), markword, fileName, storageShare, storageParentDir, storageChildDir);
            System.out.println("File download successfully.");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @RequestMapping(value = "/deleteFile")
    @ResponseBody
    public String delete(@RequestParam("fileName" ) String fileName,@RequestParam("id" ) long id) {
        String storageShare = "firstshare";
        String storageParentDir = "level1dir";
        String storageChildDir = "level2dir";
               
        try {
            FileBasics.deleteStorageFile(fileName, storageShare, storageParentDir, storageChildDir);
            long size = storageFileService.deleteStorageFile(id);
            if(size <= 0) {
                System.out.println("Delete db failure");
            } else {
                System.out.println("Delete db successfully, id is " + id);
            }
            System.out.println("File delete successfully.");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    
    @RequestMapping(value = "/listFile")
    @ResponseBody
    public String list() {
        String storageShare = "firstshare";
               
        try {
            FileBasics.listStorageDirectory(storageShare);
            System.out.println("File listed successfully.");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}