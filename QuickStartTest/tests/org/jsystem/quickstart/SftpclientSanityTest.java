package org.jsystem.quickstart;

import org.junit.Before;
import org.junit.Test;

import junit.framework.SystemTestCase4;

public class SftpclientSanityTest extends SystemTestCase4 {
    private MySftpclient mySftpclient;
	
	@Before
	public void before() throws Exception{
		mySftpclient =  
	              (MySftpclient)system.getSystemObject("my_sftpclient");  

	}
	
	@Test
	public void connectBntccServer() throws Exception {
		report.report("connectBntccServer " + mySftpclient.getHost());
		mySftpclient.connect(mySftpclient.getHost(), 22, mySftpclient.getUserName(), mySftpclient.getPassword());
	}
	
	@Test
	public void connectTftpServer() throws Exception {
		report.report("connectTftpServer " + mySftpclient.getTftpServer());
		mySftpclient.connect(mySftpclient.getTftpServer(), 22, mySftpclient.getUserName(), mySftpclient.getPassword());
	}
	
	@Test
	public void disconnectServer() throws Exception {
		report.report("connectServer " + mySftpclient.getHost());
		mySftpclient.disconnect();
	}
	
	@Test
	public void DownImage() throws Exception {
		report.report("ftpDown " + mySftpclient.getHost());
		mySftpclient.download(mySftpclient.getTftpRoot(), "BladeBoot", mySftpclient.getLocalPath() + "BladeBoot");
		mySftpclient.download(mySftpclient.getTftpRoot(), "BladeOS", mySftpclient.getLocalPath() + "BladeOS");
	}
	
	@Test
	public void UpImage() throws Exception {
		report.report("ftpUp" + mySftpclient.getTftpServer());
		mySftpclient.upload(mySftpclient.getTftpRoot(), mySftpclient.getLocalPath() + "BladeBoot");
		mySftpclient.upload(mySftpclient.getTftpRoot(), mySftpclient.getLocalPath() + "BladeOS");
	}
	
	@Test
	public void DownReleaseImage() throws Exception {
		report.report("ftpDown " + mySftpclient.getHost());
		mySftpclient.download(mySftpclient.getTftpRoot(), mySftpclient.getFile1Name(), mySftpclient.getLocalPath() + "BladeBoot");
		mySftpclient.download(mySftpclient.getTftpRoot(), mySftpclient.getFile2Name(), mySftpclient.getLocalPath() + "BladeOS");
	}
	
	@Test
	public void DownFile() throws Exception {
		report.report("ftpDown " + mySftpclient.getHost());
		mySftpclient.download(mySftpclient.getTftpRoot(), mySftpclient.getFile1Name(), mySftpclient.getCodeSrc() + mySftpclient.getFile1Name());
	}
	
	@Test
	public void UpFile() throws Exception {
		report.report("ftpUp" + mySftpclient.getTftpServer());
		mySftpclient.upload(mySftpclient.getTftpRoot(), mySftpclient.getCodeSrc() + mySftpclient.getFile1Name());
	}
	
	@Test
	public void DownFile1() throws Exception {
		report.report("ftpDown " + mySftpclient.getHost());
		mySftpclient.download(mySftpclient.getTftpRoot(), mySftpclient.getFile2Name(), mySftpclient.getCodeSrc() + mySftpclient.getFile2Name());
	}
	
	@Test
	public void UpFile1() throws Exception {
		report.report("ftpUp" + mySftpclient.getTftpServer());
		mySftpclient.upload(mySftpclient.getTftpRoot(), mySftpclient.getCodeSrc() + mySftpclient.getFile2Name());
	}
}
