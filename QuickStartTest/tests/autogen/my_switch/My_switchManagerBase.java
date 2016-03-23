package autogen.my_switch;
import junit.framework.SystemTestCase;
import org.jsystem.quickstart.MySwitchOpen;
/**
 * Auto generate management object.
 * Managed object class: org.jsystem.quickstart.MySwitchOpen
 * This file <b>shouldn't</b> be changed, to overwrite methods behavier
 * change: My_switchManager.java
 */
public abstract class My_switchManagerBase extends SystemTestCase{
	protected MySwitchOpen my_switch = null;
	public void setUp() throws Exception {
		my_switch = (MySwitchOpen)system.getSystemObject("my_switch");
	}
}
