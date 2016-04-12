package org.jsystem.automationProj;

import jsystem.framework.system.SystemObjectImpl;

public class BroadcomSDKshellParam extends SystemObjectImpl {
	private String host;
	private int telnetPort;
    private String userName;
    private String password;
    private String tftpRoot;
    private String serialPort;
    private int serialBaudRate;
    private String promptString;
    private String connectedPrompt;
    private String connectMode;
    
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getTelnetPort() {
		return telnetPort;
	}
	public void setTelnetPort(int telnetPort) {
		this.telnetPort = telnetPort;
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
	public String getSerialPort() {
		return serialPort;
	}
	public void setSerialPort(String serialPort) {
		this.serialPort = serialPort;
	}
	public int getSerialBaudRate() {
		return serialBaudRate;
	}
	public void setSerialBaudRate(int serialBaudRate) {
		this.serialBaudRate = serialBaudRate;
	}
	public String getPromptString() {
		return promptString;
	}
	public void setPromptString(String promptString) {
		this.promptString = promptString;
	}
	public String getConnectedPrompt() {
		return connectedPrompt;
	}
	public void setConnectedPrompt(String connectedPrompt) {
		this.connectedPrompt = connectedPrompt;
	}
	public String getConnectMode() {
		return connectMode;
	}
	public void setConnectMode(String connectMode) {
		this.connectMode = connectMode;
	}
}
