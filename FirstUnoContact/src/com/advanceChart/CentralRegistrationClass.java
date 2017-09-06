package com.advanceChart;

import com.sun.star.lang.XSingleComponentFactory;
import com.sun.star.registry.XRegistryKey;

public class CentralRegistrationClass {
	 public static XSingleComponentFactory __getComponentFactory(String sImplementationName)
	  {
	    XSingleComponentFactory xFactory = null;

	   /* if (sImplementationName.equals(WorkbenchPanelFactory.class.getName()))
	      xFactory = WorkbenchPanelFactory.__getComponentFactory(sImplementationName);
	    else if (sImplementationName.equals(ConnectionsOptionsHandler.getImplName()))
	      xFactory = ConnectionsOptionsHandler.__getComponentFactory(sImplementationName);
	    else if (sImplementationName.equals(IBMConnections.getImplName())) {
	      xFactory = IBMConnections.__getComponentFactory(sImplementationName);
	    }*/
	    xFactory = RegisterAndService.__getComponentFactory(sImplementationName);
	    return xFactory;
	  }

	  public static boolean __writeRegistryServiceInfo(XRegistryKey xRegistryKey)
	  {
	    /*boolean bResult = WorkbenchPanelFactory.__writeRegistryServiceInfo(xRegistryKey);
	    bResult |= ConnectionsOptionsHandler.__writeRegistryServiceInfo(xRegistryKey);
	    bResult |= IBMConnections.__writeRegistryServiceInfo(xRegistryKey);*/
		  System.out.println("here");
		
		boolean bResult = RegisterAndService.__writeRegistryServiceInfo(xRegistryKey);

	    return bResult;
	  }
}
