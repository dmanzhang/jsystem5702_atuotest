package org.jsystem.automationProj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import jsystem.framework.system.SystemObjectImpl;

public class MySftpclient extends SystemObjectImpl {
    private String userName;
    private String password;
    private String tftpRoot;
    private String tftpServer;
    private String localPath;
    private String codeSrc;
    private String file1Name;
    private String file2Name;
    protected com.jcraft.jsch.JSch sftpClient;
    protected ChannelSftp sftp = null;

	public void init() throws Exception {
        super.init();
        report.report("In init method");
        sftpClient = new JSch();
        
    }

    public void close(){
         super.close();
         report.report("In close method");
     }
    
    public void connect(String host, int port, String username,
    		String password) {
    	try {
    	sftpClient.getSession(username, host, port);
    	Session sshSession = sftpClient.getSession(username, host, port);
    	System.out.println("Session created.");
    	sshSession.setPassword(password);
    	Properties sshConfig = new Properties();
    	sshConfig.put("StrictHostKeyChecking", "no");
    	sshSession.setConfig(sshConfig);
    	sshSession.connect();
    	System.out.println("Session connected.");
    	System.out.println("Opening Channel.");
    	Channel channel = sshSession.openChannel("sftp");
    	channel.connect();
    	sftp = (ChannelSftp) channel;
    	System.out.println("Connected to " + host + ".");
    	} catch (Exception e) {
            
    	}
    }

    public void disconnect() {
    	try {
    		sftp.disconnect();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    public void upload(String directory, String uploadFile) {
    	try {
    		System.out.println("enter " + directory);
    		sftp.cd(directory);
    		File file=new File(uploadFile);
    		sftp.put(new FileInputStream(file), file.getName());
    		} catch (Exception e) {
    		e.printStackTrace();
    		}
    }


    public void download(String directory, String downloadFile,String saveFile) {
    	try {
    		sftp.cd(directory);
    		File file=new File(saveFile);
    		sftp.get(downloadFile, new FileOutputStream(file));
    		} catch (Exception e) {
    		e.printStackTrace();
    		}
    }

    public void delete(String directory, String deleteFile) {
    	try {
    		sftp.cd(directory);
    		sftp.rm(deleteFile);
    		} catch (Exception e) {
    		e.printStackTrace();
    		}
    }

    public void uploadDirectory(String tftpDirectory, String LocalDirectory) {
    	try {
    		System.out.println("enter " + tftpDirectory);
    		sftp.cd(tftpDirectory);
    		File file= new File(LocalDirectory);
    		File[] files = file.listFiles();
    		if (files != null) {
    			for (File f:files) {
    				sftp.put(new FileInputStream(f), f.getName());
    			}
    		}
    		} catch (Exception e) {
    		    e.printStackTrace();
    		}
    }


    public void downloadDirectory(String tftpDirectory, String LocalDirectory) {
    	try {
    		Vector<String> nameList;
    		sftp.cd(tftpDirectory);
    		nameList = listFiles(tftpDirectory);
    		    for(int i = 0;i < nameList.size();i++){ 
    			    System.out.println(nameList.get(i)); 
    			    File file=new File(LocalDirectory + nameList.get(i));
    			    sftp.get(nameList.get(i), new FileOutputStream(file));
    		    }
    		} catch (Exception e) {
    		e.printStackTrace();
    		}
    }
    
    public void deleteDirectory(String tftpDirectory) {
    	try {
    		Vector<String> nameList;
    		sftp.cd(tftpDirectory);
    		nameList = listFiles(tftpDirectory);
		    for(int i = 0;i < nameList.size();i++){ 
			    System.out.println(nameList.get(i)); 
			    sftp.rm(nameList.get(i));
		    }
    		} catch (Exception e) {
    		e.printStackTrace();
    		}
    }
    
    @SuppressWarnings("unchecked")
	public Vector<String> listFiles(String directory) throws SftpException{
    		return sftp.ls(directory);
    }

    public void mkdirDirectory(String directory) throws SftpException{
		sftp.mkdir(directory);
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
    
    public void setCodeSrc(String codeSrc) {
        this.codeSrc = codeSrc;
    }
    
    public String getCodeSrc() {
        return codeSrc;
    }

    public void setFile1Name(String file1Name) {
        this.file1Name = file1Name;
    }
    
    public String getFile1Name() {
        return file1Name;
    }

    public void setFile2Name(String file2Name) {
        this.file2Name = file2Name;
    }
    
    public String getFile2Name() {
        return file2Name;
    } 
}
