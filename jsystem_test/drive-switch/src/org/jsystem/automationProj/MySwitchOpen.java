package org.jsystem.automationProj;

import java.io.File;

import systemobject.terminal.Prompt;
import com.aqua.sysobj.conn.CliCommand;
import com.aqua.sysobj.conn.CliConnectionImpl;
import com.aqua.sysobj.conn.WindowsDefaultCliConnection;

import jsystem.extensions.analyzers.text.FindText;
import jsystem.framework.system.SystemObjectImpl;

public class MySwitchOpen extends SystemObjectImpl {
	private String host;
    private String userName;
    private String password;
    private String promptString;
    private String tftpServer;
    private String managePort;
    private String protocalPort = "";
    private CliConnectionImpl connection;
    
    private String linkUpPort;
    private String SUTPort;
    private String SUTPort1;
	
	public void init() throws Exception {
        super.init();
        report.report("In init method");
        connection = new WindowsDefaultCliConnection(getHost(),getUserName(),getPassword());
        Prompt p = new Prompt("iscli):",false);
        p.setCommandEnd(true);
        connection.addPrompts(new Prompt[]{p});
        connection.init();
    }

     public void close(){
         super.close();
         report.report("In close method");
     }
     
	public void enterIscli() 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("iscli");
		    command.setPromptString(getPromptString() + ">");
	        connection.handleCliCommand("enter iscli", command);
	        setTestAgainstObject(command.getResult());
	}

	public void enable() 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("enable");
		    command.setPromptString(getPromptString() + "#");
	        connection.handleCliCommand("enable", command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void configureTerminal() 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("configure terminal");
		    command.setPromptString(getPromptString() + "(config)#");
	        connection.handleCliCommand("configure terminal", command);
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
	        
	public void enterInterfacePort(String port) 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("interface port " + port);
		    command.setPromptString(getPromptString() + "(config-if)#");
	        connection.handleCliCommand("interface port" + port, command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void exitInterface() 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("exit");
		    command.setPromptString(getPromptString() + "(config)#");
	        connection.handleCliCommand("exit", command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void setSwitchPortMode(String portMode) 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("switchport mode " + portMode);
		    command.setPromptString(getPromptString() + "(config-if)#");
	        connection.handleCliCommand("switchport mode" + portMode, command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void setSwitchPortAccessVlan(String vlan) 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("switchport access vlan " + vlan);
		    command.setPromptString(getPromptString() + "(config-if)#");
	        connection.handleCliCommand("switchport access vlan" + vlan, command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void setSwitchPortTrunkVlan(String action, String vlan) 
	        throws Exception {
		CliCommand command;
		if (action == " ") {
			command = 
			        new CliCommand("switchport trunk allowed vlan " + vlan);
		}
		else {
		    command = 
		        new CliCommand("switchport trunk allowed vlan " + action + " " + vlan);
		}
		    command.setPromptString(getPromptString() + "(config-if)#");
	        connection.handleCliCommand("switchport trunk allowed vlan", command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void showInterfacePort(String port) 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("show interface port " + port);
		    command.setPromptString(getPromptString() + "(config)#");
	        connection.handleCliCommand("show interface port" + port, command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void clearMacAddress() 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("clear mac-address-table");
		    command.setPromptString(getPromptString() + "(config)#");
	        connection.handleCliCommand("clear mac-address-table", command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void enterInterfaceIp(String ipIndex) 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("interface ip " + ipIndex);
		    command.setPromptString(getPromptString() + "(config-ip-if)#");
	        connection.handleCliCommand("interface port" + ipIndex, command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void setIpAddress(String ipAddress, String ipMask) 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("ip address " + ipAddress + " " + ipMask + " " + "enable");
		    command.setPromptString(getPromptString() + "(config-ip-if)#");
	        connection.handleCliCommand("ip address" + ipAddress, command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void setIpAddressVlan(String vlan) 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("vlan " + vlan);
		    command.setPromptString(getPromptString() + "(config-ip-if)#");
	        connection.handleCliCommand("vlan" + vlan, command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void noIpAddress(String ipIndex) 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("no interface ip " + ipIndex);
		    command.setPromptString(getPromptString() + "(config)#");
	        connection.handleCliCommand("no interface ip" + ipIndex, command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void showInterfaceIp(String ipIndex) 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("show interface ip " + ipIndex);
		    command.setPromptString(getPromptString() + "(config)#");
	        connection.handleCliCommand("show interface ip" + ipIndex, command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void showVlan() 
	        throws Exception {
		    CliCommand command = 
		        new CliCommand("show vlan");
		    command.setPromptString(getPromptString() + "(config-if)#");
	        connection.handleCliCommand("show vlan", command);
	        setTestAgainstObject(command.getResult());
	}
	
	public void UpdateBuildBoot(String tftpServer, String imagePash) throws Exception {
		report.report("UpdateBuildBoot");
		rawCommand("copy tftp boot-image address " + tftpServer + 
				" filename " + imagePash + "/" + "BladeBoot", "]:");
		rawCommand(managePort, "(y/n) ?");
		rawCommandNoTimeOut("y", "(config)#");
		analyze(new FindText("contains Software Version"));
	}
	
	public void UpdateBuildImage1(String tftpServer, String imagePash) throws Exception {
		report.report("UpdateBuildImage1");
		rawCommand("boot image image1", "(config)#");
		rawCommand("copy tftp image1 address " + tftpServer + 
				" filename " + imagePash + "/" + "BladeOS", "]:");
		rawCommand(managePort, "(y/n) ?");
		rawCommandNoTimeOut("y", "(config)#");
		analyze(new FindText("FLASH successful"));
	}
	
	public void UpdateBuildImage2(String tftpServer, String imagePash) throws Exception {
		report.report("UpdateBuildImage2");
		rawCommand("boot image image2", "(config)#");
		rawCommand("copy tftp image2 address " + tftpServer + 
				" filename " + imagePash + "/" +  "BladeOS", "]:");
		rawCommand(managePort, "(y/n) ?");
		rawCommandNoTimeOut("y", "(config)#");
		analyze(new FindText("FLASH successful"));
	}
	
	public void Reload() throws Exception {
		report.report("Reload");
		rawCommand("write", "(config)#");
		rawCommand("reload", "(y/n) ?");
		rawCommandNoTimeOut("y", "Resetting");
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
    
    public String getPromptString() {
        return promptString;
    }

    public void setPromptString(String promptString) {
        this.promptString = promptString;
    }
    
    public String getLinkUpPort() {
        return linkUpPort;
    }

    public void setLinkUpPort(String linkUpPort) {
        this.linkUpPort = linkUpPort;
    }
    
    public String getSUTPort() {
        return SUTPort;
    }

    public void setSUTPort(String SUTPort) {
        this.SUTPort = SUTPort;
    }
    
    public String getSUTPort1() {
        return SUTPort1;
    }

    public void setSUTPort1(String SUTPort1) {
        this.SUTPort1 = SUTPort1;
    }
    
    public String getTftpServer() {
        return tftpServer;
    }

    public void setTftpServer(String tftpServer) {
        this.tftpServer = tftpServer;
    }
    
    public String getManagePort() {
        return managePort;
    }

    public void setManagePort(String managePort) {
        this.managePort = managePort;
    }
    
    public String getProtocalPort() {
        return protocalPort;
    }

    public void setProtocalPort(String protocalPort) {
        this.protocalPort = protocalPort;
    }
}
