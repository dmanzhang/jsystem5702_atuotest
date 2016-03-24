package org.jsystem.automationProj;

import jsystem.framework.system.SystemObjectImpl;

public class ConfigureTestParam extends SystemObjectImpl {
	private String host;
    private String userName;
    private String password;
    private int protocalPort;
    
	public void init() throws Exception {
        super.init();
        report.report("In init method");
        
    }

    public void close(){
         super.close();
         report.report("In close method");
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
    
    public int getProtocalPort() {
        return protocalPort;
    }

    public void setProtocalPort(int protocalPort) {
        this.protocalPort = protocalPort;
    }
}
