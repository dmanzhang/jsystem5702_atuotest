package org.jsystem.automationProj;

import jsystem.framework.system.SystemObjectImpl;

public class MyTestEnvironment extends SystemObjectImpl {
    private String environmentName;
    private int switchNumber;
    private String switchList;
    private String tftpServer;
    private String imagePash;
	
	public void init() throws Exception {
        super.init();
        report.report("In init method");
    }

     public void close(){
         super.close();
         report.report("In close method");
     }
     
     public String getEnvironmentName() {
         return environmentName;
     }

     public void setEnvironmentName(String environmentName) {
         this.environmentName = environmentName;
     }
     
     public int getSwitchNumber() {
         return switchNumber;
     }

     public void setSwitchNumber(int switchNumber) {
         this.switchNumber = switchNumber;
     }
     
     public String getSwitchList() {
         return switchList;
     }

     public void setSwitchList(String switchList) {
         this.switchList = switchList;
     }
     
     public String getTftpServer() {
         return tftpServer;
     }

     public void setTftpServer(String tftpServer) {
         this.tftpServer = tftpServer;
     }
     
     public String getImagePash() {
         return imagePash;
     }

     public void setImagePash(String imagePash) {
         this.imagePash = imagePash;
     }
}
