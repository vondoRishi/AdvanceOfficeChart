import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XDesktop;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.sheet.XSpreadsheetDocument;
import com.sun.star.table.XCellRange;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.uno.XInterface;


public class util {
	public static XDesktop getDesktop() {
        XDesktop xDesktop = null;
        XMultiComponentFactory xMCF = null;

        try {
            XComponentContext xContext = null;

            // get the remote office component context
            xContext = com.sun.star.comp.helper.Bootstrap.bootstrap();

            // get the remote office service manager
            xMCF = xContext.getServiceManager();
            if( xMCF != null ) {
                System.out.println("Connected to a running office ...");

                Object oDesktop = xMCF.createInstanceWithContext(
                    "com.sun.star.frame.Desktop", xContext);
                xDesktop = (XDesktop) UnoRuntime.queryInterface(
                    XDesktop.class, oDesktop);
            }
            else
                System.out.println( "Can't create a desktop. No connection, no remote servicemanager available!" );
        }
        catch( Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }


        return xDesktop;
    }


    public static XSpreadsheetDocument createSheetdocument( XDesktop xDesktop ) {
        XSpreadsheetDocument aSheetDocument = null;

        try {
            XComponent xComponent = null;
            xComponent = CreateNewDocument( xDesktop, "scalc" );

            aSheetDocument = (XSpreadsheetDocument) UnoRuntime.queryInterface(
                XSpreadsheetDocument.class, xComponent);
        }
        catch( Exception e) {
            e.printStackTrace(System.err);
        }

        return aSheetDocument;
    }

    protected static XComponent CreateNewDocument( XDesktop xDesktop,
                                                   String sDocumentType ) {
        String sURL = "private:factory/" + sDocumentType;
        sURL = "file:///home/rishi/test.ods";
        
        XComponent xComponent = null;
        XComponentLoader xComponentLoader = null;
        PropertyValue xValues[] = new PropertyValue[1];
        PropertyValue xEmptyArgs[] = new PropertyValue[0];
        
        PropertyValue[] loadProps = new PropertyValue[1];
        loadProps[0] = new PropertyValue();
        loadProps[0].Name = "Hidden";
        loadProps[0].Value = new Boolean(false);

        try {
            xComponentLoader = (XComponentLoader) UnoRuntime.queryInterface(
                XComponentLoader.class, xDesktop );

            xComponent  = xComponentLoader.loadComponentFromURL(
                sURL, "_blank", 0, loadProps);
        }
        catch( Exception e) {
            e.printStackTrace(System.err);
        }

        return xComponent ;
    }
    
    public static XCellRange getSelRng()
    
	  {
	    try
	    {
	      //XModel localXModel = new DocHelp(this.context).getDesktop().getCurrentFrame().getController().getModel();
	      XInterface localXInterface = null;
	      //localXInterface = (XInterface)UnoRuntime.queryInterface(XInterface.class, localXModel.getCurrentSelection());
	      XCellRange localXCellRange = (XCellRange)UnoRuntime.queryInterface(XCellRange.class, localXInterface);
	      return localXCellRange;
	    }
	    catch (NullPointerException localNullPointerException)
	    {
	      //throw new OOoHelpExcept.RangeHelpExcept(localNullPointerException.getMessage());
	    }
		return null;
	  }
}
