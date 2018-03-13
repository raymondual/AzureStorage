package com.mycom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.dao.StorageFileMapper;
import com.mycom.entity.StorageFile;

@Service
public class StorageFileService {
	
	@Autowired
	private StorageFileMapper storageFileDao;
		
	public StorageFileService() {
		super();
	}
	
	public int insertStorageFile(String fileName,String storageShare,String storageParentDir,String storageChildDir) {
	    StorageFile record = new StorageFile();
	    record.setFileName(fileName);
	    record.setStorageShare(storageShare);
	    record.setStorageParentDir(storageParentDir);
	    record.setStorageChildDir(storageChildDir);
	    return storageFileDao.insert(record);
	}	

	/**
	 * @return the storageFileDao
	 */
	public StorageFileMapper getStorageFileDao() {
		return storageFileDao;
	}

	/**
	 * @param storageFileDao the storageFileDao to set
	 */
	public void setStorageFileDao(StorageFileMapper storageFileDao) {
		this.storageFileDao = storageFileDao;
	}

}