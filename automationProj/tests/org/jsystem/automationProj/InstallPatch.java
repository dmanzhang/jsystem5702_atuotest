package org.jsystem.automationProj;

import java.io.File;
import jsystem.framework.fixture.Fixture;

public class InstallPatch extends Fixture {
    private MyStation station;
	public InstallPatch(){
	}
	public void setUp() throws Exception {
	  station = (MyStation)system.getSystemObject("my_station");
	  station.deployPatch(new File("Path to patch file"));
	}
	public void tearDown() throws Exception {
	}
	public void failTearDown() throws Exception {
	}
}
