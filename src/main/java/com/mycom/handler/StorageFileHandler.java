package com.mycom.handler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import javax.servlet.http.HttpServletRequest;
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
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String storageShare = "firstshare";
        String storageParentDir = "level1dir";
        String storageChildDir = "level2dir";        
        
        int id = storageFileService.insertStorageFile(fileName, storageShare, storageParentDir, storageChildDir);
        System.out.println("File id is " + id);
        
        try {
            FileBasics.uploadStorageFile(file.getInputStream(), file.getSize(), fileName, storageShare, storageParentDir, storageChildDir);
            System.out.println("File upload successfully.");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "upload";
    }
    
    @RequestMapping(value = "/downloadFile")
    @ResponseBody
    public String download(@RequestParam("fileName" ) String fileName) {
        String storageShare = "firstshare";
        String storageParentDir = "level1dir";
        String storageChildDir = "level2dir";    
               
        try {
            FileBasics.downloadStorageFile(response.getOutputStream(), "Liu Leigang", fileName, storageShare, storageParentDir, storageChildDir);
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
        
        return "upload";
    }
}