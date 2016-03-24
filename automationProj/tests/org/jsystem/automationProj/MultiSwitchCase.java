package org.jsystem.automationProj;

import org.junit.Before;
import org.junit.Test;

import org.jsystem.automationProj.MyTestEnvironment;
import org.jsystem.automationProj.MySwitchOpen;

import jsystem.extensions.analyzers.text.FindText;
import junit.framework.SystemTestCase4;

public class MultiSwitchCase extends SystemTestCase4 {
	public static int MaxSwitchs=8;
	MyTestEnvironment myEnvironment;
	public static MySwitchOpen [] mySwitchOpen = new MySwitchOpen[MaxSwitchs];
	
	@Before
	public void before() throws Exception{
		int i = 0;
		int j = 0;
		int number = 0;
		int numberLast = 0;
		String list;
		int bottom = 0;
		int top = 0;
		int counter = 0;
		
		myEnvironment =  
	              (MyTestEnvironment)system.getSystemObject("my_environment"); 
		
	    char[] cs = myEnvironment.getSwitchList().toCharArray();
	    int length = cs.length;
	    
		while (i < length)
		{
			if (cs[i] > '0' && cs[i] < '9')
			{
				i++;
				top++;
			}
			else if (cs[i] == ',')
			{
				if (number == 0)
				{
				    list = myEnvironment.getSwitchList().substring(bottom, top);
				    number = Integer.parseInt(list);
				    if (number > MaxSwitchs || counter > MaxSwitchs)
				    {
				    	return;
				    }
				    mySwitchOpen[number] = (MySwitchOpen)system.getSystemObject("my_switch" + number);
				    counter++;
				    i++;
				    number = 0;
				    bottom=top=i;
				}
				else
				{
					list = myEnvironment.getSwitchList().substring(bottom, top);
					numberLast = Integer.parseInt(list);
					if (number > MaxSwitchs || numberLast > MaxSwitchs)
				    {
				    	return;
				    }
					for (j=number; j<=numberLast; j++)
					{
						if (counter > MaxSwitchs)
					    {
					    	return;
					    }
					    mySwitchOpen[j] = (MySwitchOpen)system.getSystemObject("my_switch" + j);
					    counter++;
					}
				    i++;
				    number = 0;
				    numberLast = 0;
				    bottom=top=i;
				}
			}
			else if (cs[i] == '-')
			{
				list = myEnvironment.getSwitchList().substring(bottom, top);
				number = Integer.parseInt(list);
				i++;
				bottom=top=i;
			}
			else if (cs[i] < '0' || cs[i] > '9')
		    {
		    	return;
		    }
		}
		
		if (bottom != top)
		{
			if (number == 0)
			{
			    list = myEnvironment.getSwitchList().substring(bottom, top);
			    number = Integer.parseInt(list);
			    if (number > MaxSwitchs || counter > MaxSwitchs)
			    {
			    	return;
			    }
			    mySwitchOpen[number] = (MySwitchOpen)system.getSystemObject("my_switch" + number);
			    counter++;
			}
			else
			{
				list = myEnvironment.getSwitchList().substring(bottom, top);
				numberLast = Integer.parseInt(list);
				if (number > MaxSwitchs || numberLast > MaxSwitchs)
			    {
			    	return;
			    }
				for (j=number; j<=numberLast; j++)
				{
					if (counter > MaxSwitchs)
				    {
				    	return;
				    }
				    mySwitchOpen[j] = (MySwitchOpen)system.getSystemObject("my_switch" + j);
				    counter++;
				}
			}
		}
	}
	
	@Test
	public void OpenDUT1Command() throws Exception {
		report.report("Calling MySwitchOpen operation");
		mySwitchOpen[1].enterIscli();
		mySwitchOpen[1].analyze(new FindText(">"));
		mySwitchOpen[1].enable();
		mySwitchOpen[1].analyze(new FindText("#"));
		mySwitchOpen[1].configureTerminal();
		mySwitchOpen[1].analyze(new FindText("(config)#"));
	}
	
	@Test
	public void OpenDUT1Command2() throws Exception {
		report.report("Calling MySwitchOpen operation");
		mySwitchOpen[1].enable();
		mySwitchOpen[1].analyze(new FindText("#"));
		mySwitchOpen[1].configureTerminal();
		mySwitchOpen[1].analyze(new FindText("(config)#"));
	}
	
	@Test
	public void OpenDUT2Command() throws Exception {
		report.report("Calling MySwitchOpen operation");
		mySwitchOpen[2].enterIscli();
		mySwitchOpen[2].analyze(new FindText(">"));
		mySwitchOpen[2].enable();
		mySwitchOpen[2].analyze(new FindText("#"));
		mySwitchOpen[2].configureTerminal();
		mySwitchOpen[2].analyze(new FindText("(config)#"));
	}
	
	@Test
	public void OpenDUT2Command2() throws Exception {
		report.report("Calling MySwitchOpen operation");
		mySwitchOpen[2].enable();
		mySwitchOpen[2].analyze(new FindText("#"));
		mySwitchOpen[2].configureTerminal();
		mySwitchOpen[2].analyze(new FindText("(config)#"));
	}
	
	@Test
	public void OpenDUT3Command() throws Exception {
		report.report("Calling MySwitchOpen operation");
		mySwitchOpen[3].enterIscli();
		mySwitchOpen[3].analyze(new FindText(">"));
		mySwitchOpen[3].enable();
		mySwitchOpen[3].analyze(new FindText("#"));
		mySwitchOpen[3].configureTerminal();
		mySwitchOpen[3].analyze(new FindText("(config)#"));
	}
	
	@Test
	public void OpenDUT3Command2() throws Exception {
		report.report("Calling MySwitchOpen operation");
		mySwitchOpen[3].enable();
		mySwitchOpen[3].analyze(new FindText("#"));
		mySwitchOpen[3].configureTerminal();
		mySwitchOpen[3].analyze(new FindText("(config)#"));
	}
	
	@Test
	public void UpdateBuildBootDUT1() throws Exception {
		report.report("UpdateBuildBootDUT1");
		
		mySwitchOpen[1].UpdateBuildBoot(myEnvironment.getTftpServer(), myEnvironment.getImagePash());
		
	}
	
	@Test
	public void UpdateBuildImage1DUT1() throws Exception {
		report.report("UpdateBuildImage1DUT1");
		
		mySwitchOpen[1].UpdateBuildImage1(myEnvironment.getTftpServer(), myEnvironment.getImagePash());
		
	}
	
	@Test
	public void UpdateBuildImage2DUT1() throws Exception {
		report.report("UpdateBuildImage2DUT1");
		
		mySwitchOpen[1].UpdateBuildImage2(myEnvironment.getTftpServer(), myEnvironment.getImagePash());
		
	}
	
	@Test
	public void UpdateBuildBootDUT2() throws Exception {
		report.report("UpdateBuildBootDUT2");
		
		mySwitchOpen[2].UpdateBuildBoot(myEnvironment.getTftpServer(), myEnvironment.getImagePash());
		
	}
	
	@Test
	public void UpdateBuildImage1DUT2() throws Exception {
		report.report("UpdateBuildImage1DUT2");
		
		mySwitchOpen[2].UpdateBuildImage1(myEnvironment.getTftpServer(), myEnvironment.getImagePash());
		
	}
	
	@Test
	public void UpdateBuildImage2DUT2() throws Exception {
		report.report("UpdateBuildImage2DUT2");
		
		mySwitchOpen[2].UpdateBuildImage2(myEnvironment.getTftpServer(), myEnvironment.getImagePash());
		
	}
	
	@Test
	public void UpdateBuildBootDUT3() throws Exception {
		report.report("UpdateBuildBootDUT3");
		
		mySwitchOpen[3].UpdateBuildBoot(myEnvironment.getTftpServer(), myEnvironment.getImagePash());
		
	}
	
	@Test
	public void UpdateBuildImage1DUT3() throws Exception {
		report.report("UpdateBuildImage1DUT3");
		
		mySwitchOpen[3].UpdateBuildImage1(myEnvironment.getTftpServer(), myEnvironment.getImagePash());
		
	}
	
	@Test
	public void UpdateBuildImage2DUT3() throws Exception {
		report.report("UpdateBuildImage2DUT3");
		
		mySwitchOpen[3].UpdateBuildImage2(myEnvironment.getTftpServer(), myEnvironment.getImagePash());
		
	}
	
	@Test
	public void reloadDUT1() throws Exception {
		report.report("reloadDUT1");
		
		mySwitchOpen[1].Reload();
		
	}
	
	@Test
	public void reloadDUT2() throws Exception {
		report.report("reloadDUT2");
		
		mySwitchOpen[2].Reload();
		
	}
	
	@Test
	public void reloadDUT3() throws Exception {
		report.report("reloadDUT3");
		
		mySwitchOpen[3].Reload();
		
	}
}
