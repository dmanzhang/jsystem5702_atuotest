package org.jsystem.automationProj;

import org.junit.Before;
import org.junit.Test;

import junit.framework.SystemTestCase4;

public class FtpclientSanityTest extends SystemTestCase4 {
    private MyFtpclient myFtpclient;
	
	@Before
	public void before() throws Exception{
		myFtpclient =  
	              (MyFtpclient)system.getSystemObject("my_ftpclient");  

	}
	
	@Test
	public void connectBntccServer() throws Exception {
		report.report("connectBntccServer " + myFtpclient.getHost());
		myFtpclient.ftpConnectBntcc();
	}
	
	@Test
	public void connectTftpServer() throws Exception {
		report.report("connectTftpServer " + myFtpclient.getTftpServer());
		myFtpclient.ftpConnectTftp();
	}
	
	@Test
	public void disconnectServer() throws Exception {
		report.report("connectServer " + myFtpclient.getHost());
		myFtpclient.ftpDisconnect();
	}
	
	@Test
	public void changeDir() throws Exception {
		report.report("changeDir " + myFtpclient.getTftpRoot());
		myFtpclient.ftpChangeDirectory(myFtpclient.getTftpRoot());
	}
	
	@Test
	public void DownImage() throws Exception {
		report.report("ftpDown " + myFtpclient.getHost());
		myFtpclient.ftpChangeToBinary();
		myFtpclient.ftpEnterLocalActiveMode();
		myFtpclient.ftpGetFile("BladeBoot", myFtpclient.getLocalPath() + "BladeBoot");
		myFtpclient.ftpGetFile("BladeOS", myFtpclient.getLocalPath() + "BladeOS");
	}
	
	@Test
	public void UpImage() throws Exception {
		report.report("ftpUp" + myFtpclient.getTftpServer());
		myFtpclient.ftpChangeToBinary();
		myFtpclient.ftpEnterLocalActiveMode();
		myFtpclient.ftpPutFile(myFtpclient.getLocalPath() + "BladeBoot", "BladeBoot");
		myFtpclient.ftpPutFile(myFtpclient.getLocalPath() + "BladeOS", "BladeOS");
	}
}
