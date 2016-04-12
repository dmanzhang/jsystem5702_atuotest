package org.jsystem.automationProj;

import jsystem.framework.fixture.Fixture;

public class ConfigureHostsTable extends Fixture {
	  private MyStation station;
	  public ConfigureHostsTable(){
	    setParentFixture(InstallPatch.class);
	  }
	  public void setUp() throws Exception {
	    station = (MyStation)system.getSystemObject("my_station");
	    station.updateHostsTable("anotherLocalHost","127.0.0.1");
	  }
	  public void tearDown() throws Exception {  
	  }
}
