/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server1 {
        
  public static void main(String[] args) throws IOException {

    
  
    ServerSocket serverSocket = null;

    try {
        serverSocket = new ServerSocket(6666);
    } catch (IOException ex) {
        System.out.println("Can't setup server on this port number. ");
    }
    
    Socket socket = null;
    try {
        socket = serverSocket.accept();
    } catch (IOException ex) {
        System.out.println("Can't accept client connection. ");
    }  
    
	
	
    
    File file = new File("/home/lehik/workspace/Server/myfile.cap");
    // Get the size of the file
    long length = file.length();
    if (length > Integer.MAX_VALUE) {
        System.out.println("File is too large.");
    }
    byte[] bytes = new byte[(int) length];
    FileInputStream fis = new FileInputStream(file);
    BufferedInputStream bis = new BufferedInputStream(fis);
    BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());

    int count;

    while ((count = bis.read(bytes)) > 0) {
        out.write(bytes, 0, count);
    }

    out.flush();
    out.close();
    fis.close();
    bis.close();
    socket.close();
    serverSocket.close();
    

}
}