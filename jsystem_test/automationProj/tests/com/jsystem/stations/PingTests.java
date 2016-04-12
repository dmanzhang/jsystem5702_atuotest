package com.jsystem.stations;


import junit.framework.SystemTestCase;

import com.aqua.stations.Ping;
import com.aqua.stations.PingAnalyzer;
/**
 * Ping examples.
 * 
 * @author Yoram Shamir
 *
 */
public class PingTests extends SystemTestCase {

	Ping pinger;
	
	public void setUp() throws Exception {
		super.setUp();
		pinger = (Ping)system.getSystemObject("ping");
	}
	
	/**
	 * Straightforward Ping with analyzer.
	 * @throws Exception
	 */
	public void testSimplePing() throws Exception {
		pinger.ping(getIp(), getRetries(), getBufferLen());
		pinger.analyze(new PingAnalyzer(0));
	}

	/**
	 * Ping while taking the return value and analyzing it without analyzer.
	 * @throws Exception
	 */
	public void testSimplePingWithReturnVal() throws Exception {
		int succRetries = pinger.ping(getIp(), getRetries(), getBufferLen());
		
		// The following 
		if (succRetries == getRetries()) {
			report.report("Test PASS - Successfu ping retries = " + succRetries);			
		} else {
			report.report("Test Failed Successfu ping retries = " + succRetries + " While expected = " + getRetries(), false);			
		}
	}
	
	/**
	 * Ping and save to file.
	 * @throws Exception
	 */
	public void testPingAndSave2File() throws Exception {
		pinger.ping(getIp(), getRetries(), getBufferLen(), getFile2save());
		pinger.analyze(new PingAnalyzer(0));
	}

	/*
	 * Getters and Setters.
	 */
	
	/**
	 * Destination IP address.
	 * @param ip
	 * @section Ping params
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp() {
		return ip;
	}
	private String ip = "127.0.0.1";
	
	/**
	 * Number of ping retries.
	 * @return
	 * @section Ping params
	 */
	public void setRetries(int retries) {
		this.retries = retries;
	}
	public int getRetries() {
		return retries;
	}
	private int retries = 4;

	/**
	 * Buffer length
	 * @param bufferLen
	 * @section Ping params
	 */
	public void setBufferLen(int bufferLen) {
		this.bufferLen = bufferLen;
	}
	public int getBufferLen() {
		return bufferLen;
	}	
	private int bufferLen = 32;
	
	/**
	 * Path to file that will hold the ping output string.
	 * @param file2save
	 * @section Ping params
	 */
	public void setFile2save(String file2save) {
		this.file2save = file2save;
	}
	public String getFile2save() {
		return file2save;
	}	
	private String file2save = null;
	
}
