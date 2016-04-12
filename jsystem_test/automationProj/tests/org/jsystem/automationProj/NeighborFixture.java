package org.jsystem.automationProj;

import java.io.File;

import jsystem.extensions.analyzers.text.FindText;
import jsystem.framework.fixture.Fixture;

public class NeighborFixture extends Fixture {
    private MySwitchOpen sut1;
	public NeighborFixture(){
	}
	public void setUp() throws Exception {
		sut1 = (MySwitchOpen)system.getSystemObject("my_station");
		sut1.deployPatch(new File("Path to patch file"));
		
		sut1.enterIscli();
		sut1.analyze(new FindText(">"));
		sut1.enable();
		sut1.analyze(new FindText("#"));
		sut1.configureTerminal();
		sut1.analyze(new FindText("(config)#"));
	}
	public void tearDown() throws Exception {
	}
	public void failTearDown() throws Exception {
	}
}
