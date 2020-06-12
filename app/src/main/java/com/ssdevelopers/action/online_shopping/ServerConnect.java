package com.ssdevelopers.action.online_shopping;

import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

public class ServerConnect {
    String hostIP = "103.133.214.89";
    Boolean status = false;
    String username = "citybask";
    String password = "citybaskweb123";
    FTPClient client;



    public void connectToFTPServer(File file){
        client = new FTPClient();
        client.setConnectTimeout(10*1000);
        try {
            client.connect(InetAddress.getByName(hostIP));
            status = client.login(username,password);
            client.setFileType(FTP.BINARY_FILE_TYPE);
            client.enterLocalPassiveMode();
            Log.e("isFTPConnected", String.valueOf(status));
            if (FTPReply.isPositiveCompletion(client.getReplyCode())) {
                client.setFileType(FTP.ASCII_FILE_TYPE);
                client.enterLocalPassiveMode();
                FTPFile[] mFileArray = client.listFiles();
                Log.e("Size", String.valueOf(mFileArray.length));
            }

            client.changeWorkingDirectory("/public_html/");
            Log.e("Working Path",client.printWorkingDirectory());
            InputStream input = new FileInputStream(file);

         //   boolean state = client.storeFile(client.printWorkingDirectory(),input);
        //    if(!state){
         //       Log.e("Error :",String.valueOf(state));
         //   }
        } catch (IOException e) {
          Log.e("Errorssss :",e.getMessage());
        }
    }

    public void uploadFile(File file){
        try {
            FileInputStream srcFileStream = new FileInputStream(file);
            boolean status = client.storeUniqueFile(srcFileStream);
            Log.e("Status", String.valueOf(status));
            srcFileStream.close();
        } catch (Exception e) {
            Log.e("Errorsss:",e.getMessage());
        }
    }
}
