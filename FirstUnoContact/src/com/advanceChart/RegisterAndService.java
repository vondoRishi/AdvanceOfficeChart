package com.advanceChart;

import javax.swing.SwingUtilities;

import com.sun.star.beans.PropertyValue;
import com.sun.star.comp.loader.FactoryHelper;
import com.sun.star.frame.DispatchDescriptor;
import com.sun.star.frame.XDispatch;
import com.sun.star.frame.XDispatchProvider;
import com.sun.star.frame.XFrame;
import com.sun.star.frame.XStatusListener;
import com.sun.star.lang.XInitialization;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XSingleComponentFactory;
import com.sun.star.lang.XSingleServiceFactory;
import com.sun.star.lib.uno.helper.Factory;
import com.sun.star.lib.uno.helper.WeakBase;
import com.sun.star.registry.XRegistryKey;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.util.URL;

public class RegisterAndService extends WeakBase implements XInitialization, XDispatch, XServiceInfo, XDispatchProvider {
	
	private static String m_implementationName = RegisterAndService.class.getName();
	com.sun.star.uno.XComponentContext mXComponentContext = null;
	private XFrame m_xFrame;
	private static final String[] __serviceName = { "com.sun.star.frame.ProtocolHandler" };
	
	public RegisterAndService(com.sun.star.uno.XComponentContext xComponentContext){
		this.mXComponentContext = xComponentContext;
	}

	public String getImplementationName() {
		return getClass().getName();
	}

	public boolean supportsService(String serviceName) {
		int len = __serviceName.length;

	    for (int i = 0; i < len; i++) {
	      if (serviceName.equals(__serviceName[i])) {
	        return true;
	      }
	    }
	    return false;
	}
	 
	public String[] getSupportedServiceNames( ) {
		  return  __serviceName ;
	}
	
	 // static __getServiceFactory() implementation
    // static member __serviceName was introduced above for XServiceInfo implementation 
   /* public static XSingleServiceFactory __getServiceFactory(String implName,
            XMultiServiceFactory multiFactory,
            com.sun.star.registry.XRegistryKey regKey) {

    	System.out.println("__getServiceFactory");
        com.sun.star.lang.XSingleServiceFactory xSingleServiceFactory = null;
        if (implName.equals( RegisterAndService.class.getName()) )
            xSingleServiceFactory = FactoryHelper.getServiceFactory(RegisterAndService.class, 
            		RegisterAndService.__serviceName, multiFactory, regKey);

        System.out.println("bye __getServiceFactory");
        return xSingleServiceFactory;
        
    }*/
    
    // static __writeRegistryServiceInfo implementation
    public static boolean __writeRegistryServiceInfo(XRegistryKey regKey) {
   
        return FactoryHelper.writeRegistryServiceInfo( RegisterAndService.class.getName(), 
        __serviceName, regKey); 
    }
    
    public static XSingleComponentFactory __getComponentFactory(String sImplementationName) {
        XSingleComponentFactory xFactory = null;

        if (sImplementationName.equals(m_implementationName )) {
          xFactory = Factory.createComponentFactory(RegisterAndService.class,  __serviceName );
        }
        return xFactory;
   }

	public XDispatch queryDispatch(URL pURL, String arg1, int arg2) {
		if ((pURL.Protocol.compareTo("com.advanceChart.registerandservice:") == 0)
				&& (pURL.Path.compareTo("RegisterAndService") == 0)) {
			return this;
		}

		return null;
	}

	public XDispatch[] queryDispatches(DispatchDescriptor[] pSeqDescriptors) {
		 int nCount = pSeqDescriptors.length;
		    XDispatch[] seqDispatcher = new XDispatch[pSeqDescriptors.length];

		    for (int i = 0; i < nCount; i++) {
		      seqDispatcher[i] = queryDispatch(pSeqDescriptors[i].FeatureURL, 
		    		  pSeqDescriptors[i].FrameName, pSeqDescriptors[i].SearchFlags);
		    }

		    return seqDispatcher;
	}

	public void addStatusListener(XStatusListener arg0, URL arg1) {
		// TODO Auto-generated method stub
		
	}

	public void dispatch(URL pURL, PropertyValue[] arg1) {
		
		if ((pURL.Protocol.compareTo("com.advanceChart.registerandservice:") == 0)
				&& (pURL.Path.compareTo("RegisterAndService") == 0)) {
	      SwingUtilities.invokeLater(new Runnable()
	      {
	        public void run() {
	          

	          AOCgui aocgui = new AOCgui(RegisterAndService.this.mXComponentContext);
	        }
	      });
	      return;
	    }
		
	}

	public void removeStatusListener(XStatusListener arg0, URL arg1) {
		// TODO Auto-generated method stub
		
	}

	public void initialize(Object[] pObject) throws Exception {
		if (pObject.length > 0)
		      this.m_xFrame = ((XFrame)UnoRuntime.queryInterface(XFrame.class, pObject[0]));
		
	}

}
