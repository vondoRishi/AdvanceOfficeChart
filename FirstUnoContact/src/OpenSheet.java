import com.sun.star.sheet.XSpreadsheetDocument;


public class OpenSheet {
	public static void main(String[] args) {
		 com.sun.star.frame.XDesktop xDesktop = null;
	        xDesktop = util.getDesktop();

	        //xDesktop.getCurrentFrame().getController().getModel();
	        System.out.println(""+xDesktop.getCurrentFrame().getName());
	        // create a sheet document
//	        XSpreadsheetDocument xSheetdocument = null;
//	        xSheetdocument = ( XSpreadsheetDocument ) util.createSheetdocument( xDesktop );
	}
}
