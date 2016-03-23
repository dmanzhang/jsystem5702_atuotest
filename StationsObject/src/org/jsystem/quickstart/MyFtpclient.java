package org.jsystem.quickstart;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPReply;

import jsystem.framework.system.SystemObjectImpl;

public class MyFtpclient extends SystemObjectImpl {
	private String host;
    private String userName;
    private String password;
    private String tftpRoot;
    private String tftpServer;
    private String localPath;
    protected org.apache.commons.net.ftp.FTPClient ftpClient; 

	public void init() throws Exception {
        super.init();
        report.report("In init method");
        ftpClient = new org.apache.commons.net.ftp.FTPClient();
        
    }

    public void close(){
         super.close();
         report.report("In close method");
    }

	public void ftpConnectBntcc() throws SocketException, IOException {
		boolean isConnectionSuccess = true; 
		report.report("Connect to " + getHost());
		ftpClient.connect(getHost(), 21);
		int reply = ftpClient.getReplyCode();
		if(!FTPReply.isPositiveCompletion(reply)){
			ftpClient.disconnect(); 
			isConnectionSuccess = false;
		}
		if (!isConnectionSuccess)
		{
		    ftpClient.login(getUserName(), getPassword());
		}
	}

	public void ftpConnectTftp() throws SocketException, IOException {
		ftpClient.connect(getTftpServer(), 21);
		ftpClient.login("anonymous", "anonymous");
	}
	
	public void ftpConnect(String ftpServer, String userName, String password) throws SocketException, IOException {
		ftpClient.connect(ftpServer, 21);
		ftpClient.login(userName, password);
	}
	
	public void ftpDisconnect() throws Exception {
		ftpClient.disconnect();
	}
	
	public void ftpChangeToBinary() throws IOException {
		ftpClient.setFileType(org.apache.commons.net.ftp.FTPClient.BINARY_FILE_TYPE);
	}

	public void ftpChangeToAscii() throws IOException {
		ftpClient.setFileType(org.apache.commons.net.ftp.FTPClient.ASCII_FILE_TYPE);
	}

	public void ftpEnterLocalActiveMode() throws Exception {
		ftpClient.enterLocalPassiveMode();
	}
	
	/**
	 * Fetches file from remote url (of the ftp server directory) and copies it
	 * to destination folder. 
	 * 
	 * @param remoteFileName
	 * @param localName
	 * @throws IOException
	 */

	public void ftpGetFile(String remoteFileName, String localName) throws IOException {
		report.report("Get " + remoteFileName + " to " + localName);
		FileOutputStream fos = new FileOutputStream(localName);
		try {
			ftpClient.retrieveFile(remoteFileName, fos);
		} finally {
			fos.close();
		}
	}

	/**
	 * Puts file source to url (The url of the ftp server) the directory of the
	 * ftp server. 
	 * 
	 * @param localfileName
	 * @param remoteName
	 * @throws IOException
	 */

	public void ftpPutFile(String localfileName, String remoteName) throws IOException {
		report.report("Put " + localfileName + " in " + remoteName);
		FileInputStream fis = new FileInputStream(localfileName);
		try {
			ftpClient.storeFile(remoteName, fis);
		} catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP client error", e);
        } finally {
        	if (fis != null) {
                try {
                	fis.close();
                } catch (IOException e) {
                	
                }
            } 
		}
	}

	/**
	 * Rename a file in remote machine. The path of the file, relates to the ftp
	 * server directory. url - The url of the ftp server directory - The
	 * directory of the ftp server.
	 * 
	 * @param from
	 * @param to
	 * @throws IOException
	 */

	public void ftpMoveFile(String from, String to) throws IOException {
		report.report("Move " + from + " to");
		ftpClient.rename(from, to);
	}

	/**
	 * Deletes a file from remote machine. The path of the file, relates to the
	 * ftp server directory. url - The url of the ftp server directory - The
	 * directory of the ftp server.
	 * 
	 * @param pathname
	 * @throws IOException
	 */

	public void ftpDeleteFile(String pathname) throws IOException {
		report.report("Delete From FTP: " + pathname);
		ftpClient.deleteFile(pathname);
	}

	/**
	 * change directory url (The url of the ftp server).
	 * 
	 * @param path
	 * @throws Exception
	 */
	public void ftpChangeDirectory(String path) throws Exception {
		report.report("Change directory " + path);
		ftpClient.changeWorkingDirectory(path);
	}
	
	/**
	 * makes a new directory url (The url of the ftp server).
	 * 
	 * @param newDir
	 * @throws Exception
	 */
	public void ftpMakeDirectory(String newDir) throws Exception {
		report.report("Make directory " + newDir);
		ftpClient.makeDirectory(newDir);
	}
	
	public void deployPatch(File patchPath) throws Exception {
	     report.report("In deploy patch operation");
	}
	public void updateHostsTable(String entryKey,String entryValue) throws Exception {
	     report.report("In update hosts file");
	}
		
	public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getTftpRoot() {
        return tftpRoot;
    }

    public void setTftpRoot(String tftpRoot) {
        this.tftpRoot = tftpRoot;
    }
    
    public String getTftpServer() {
        return tftpServer;
    }

    public void setTftpServer(String tftpServer) {
        this.tftpServer = tftpServer;
    }
    
    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
