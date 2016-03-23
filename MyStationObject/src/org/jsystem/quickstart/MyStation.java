package org.jsystem.quickstart;

import java.io.File;
import systemobject.terminal.Prompt;
import com.aqua.sysobj.conn.CliCommand;
import com.aqua.sysobj.conn.CliConnectionImpl;
import com.aqua.sysobj.conn.LinuxDefaultCliConnection;

import jsystem.framework.system.SystemObjectImpl;

public class MyStation extends SystemObjectImpl {
	private String host;
    private String userName;
    private String password;
    private String ccView;
    private String tftpRoot;
    private String stripOk;
    private String codeDir;
    private String file1Name;
    private String file2Name;
    private String promptString;
    private CliConnectionImpl connection;
	
	public void init() throws Exception {
        super.init();
        report.report("In init method");
        connection = new LinuxDefaultCliConnection(getHost(),getUserName(),getPassword());
        Prompt p = new Prompt("~]$",false);
        p.setCommandEnd(true);
        connection.addPrompts(new Prompt[]{p});
        connection.init();
        
    }

     public void close(){
         super.close();
         report.report("In close method");
     }
     
	public void ping(String destination,int packetSize) 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("ping " + destination + " -s " + packetSize + " -c 3");
	        connection.handleCliCommand("Ping performed", command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void rawCommand(String rawCommand, String prompt) 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand(rawCommand);
		    command.setPromptString(prompt);
	        connection.handleCliCommand(rawCommand, command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void rawCommandNoTimeOut(String rawCommand, String prompt) 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand(rawCommand);
		    command.setPromptString(prompt);
		    command.setTimeout(2*3600*1000);
	        connection.handleCliCommand(rawCommand, command);
	        setTestAgainstObject(command.getResult());
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
    
    public String getCcView() {
        return ccView;
    }

    public void setCcView(String ccView) {
        this.ccView = ccView;
    }
    
    public String getTftpRoot() {
        return tftpRoot;
    }

    public void setTftpRoot(String tftpRoot) {
        this.tftpRoot = tftpRoot;
    }
    
    public String getStripOk() {
        return stripOk;
    }

    public void setStripOk(String stripOk) {
        this.stripOk = stripOk;
    }

    public void setCodeDir(String codeDir) {
        this.codeDir = codeDir;
    }
    
    public String getCodeDir() {
        return codeDir;
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
    
    public String getPromptString() {
        return promptString;
    }

    public void setPromptString(String promptString) {
        this.promptString = promptString;
    }
}
