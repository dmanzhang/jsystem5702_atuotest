package com.jsystem.cliexample;

import com.jsystem.cliexample.MyStation;

import jsystem.extensions.analyzers.text.FindText;
import junit.framework.SystemTestCase;

public class HostSanityTest extends SystemTestCase {

	private String pingDestination = "127.0.0.1";
	private int    packetSize = 1024;
	private MyStation station;
	
	public HostSanityTest(){
	}
	
	public void setUp() throws Exception {
		station = (MyStation)system.getSystemObject("my_station");
	}
	
	/**
	 */
	public void testPingDUT() throws Exception {
		station.ping(getPingDestination(), getPacketSize());
		station.analyze(new FindText("0% packet loss"));
	}

	public String getPingDestination() {
		return pingDestination;
	}

	public void setPingDestination(String pingDestination) {
		this.pingDestination = pingDestination;
	}

	public int getPacketSize() {
		return packetSize;
	}

	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}
	
}
