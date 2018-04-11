package com.zju.javastudy.unrar;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.exception.RarException.RarExceptionType;
import com.github.junrar.rarfile.FileHeader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author Arthur
 * @Date 11/04/2018
 * @Decription:
 */


public class UnRarDemo {

    /**
     * @param rarFileName rar file name
     * @param outFilePath output file path
     * @return success Or Failed
     * @author zhuss
     * @throws Exception
     */
    public static boolean  unrar(String rarFileName, String outFilePath)  throws  Exception{

        try  {
            Archive archive = new  Archive(new File(rarFileName), new UnrarProcessMonitor(rarFileName));
            if(archive == null){
                throw new FileNotFoundException(rarFileName + " NOT FOUND!");
            }
            if(archive.isEncrypted()){
                throw new Exception(rarFileName + " IS ENCRYPTED!");
            }
            List<FileHeader> files =  archive.getFileHeaders();
            for (FileHeader fh : files) {
                if(fh.isEncrypted()){
                    throw new Exception(rarFileName + " IS ENCRYPTED!");
                }
                String fileName = fh.getFileNameW();
                if(fileName != null && fileName.trim().length() > 0){
                    String saveFileName = outFilePath+File.separator+fileName;
                    File saveFile = new File(saveFileName);
                    File parent =  saveFile.getParentFile();
                    if(!parent.exists()){
                        parent.mkdirs();
                    }
                    if(!saveFile.exists()){
                        saveFile.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(saveFile);
                    try {
                        archive.extractFile(fh, fos);
                        fos.flush();
                        fos.close();
                    } catch (RarException e) {
                        if(e.getType().equals(RarExceptionType.notImplementedYet)){
                        }
                    }finally{
                    }
                }
            }
            return true;
        } catch  (Exception e) {
            System.out.println("failed.");
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("unrar result:"+unrar("/Users/arthur/Desktop/Books/193.rar","/Users/arthur/Desktop/Books/"));
    }
}
