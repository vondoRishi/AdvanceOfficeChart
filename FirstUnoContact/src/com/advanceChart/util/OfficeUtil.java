package com.advanceChart.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.EndpointContext;

import com.advanceChart.util.charts.DataSeries;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XPropertySet;
import com.sun.star.frame.XDesktop;
import com.sun.star.frame.XModel;
import com.sun.star.lang.DisposedException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.sheet.XCellRangeAddressable;
import com.sun.star.sheet.XCellRangeData;
import com.sun.star.table.CellRangeAddress;
import com.sun.star.table.XCellRange;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.uno.XInterface;

public class OfficeUtil {

	private static XComponentContext sXComponentContext;

	public static void initialize(XComponentContext pXComponentContext) {
		sXComponentContext =pXComponentContext;
		
	}

	public static String getSelRangeText() {
		XModel localXModel = getDesktop().getCurrentFrame().getController().getModel();
	      XInterface localXInterface = null;
	      localXInterface = (XInterface)UnoRuntime.queryInterface(XInterface.class, localXModel.getCurrentSelection());
	      XCellRange localXCellRange = (XCellRange)UnoRuntime.queryInterface(XCellRange.class, localXInterface);
	      
	      XPropertySet localXPropertySet = (XPropertySet)UnoRuntime.queryInterface(XPropertySet.class, localXCellRange);
	      String str = "not found";
		try
	      {
	        str  = localXPropertySet.getPropertyValue("AbsoluteName").toString();
	      }
	      catch (UnknownPropertyException localUnknownPropertyException)
	      {
	    	  System.err.println("error UnknownPropertyException");
	        System.exit(0);
	      }
	      catch (WrappedTargetException localWrappedTargetException)
	      {
	    	  System.err.println("error WrappedTargetException ");
		        System.exit(0);
	      }
	      System.out.println("text "+str);
	   // Gets the selected range's address/location.
          /*XCellRangeAddressable xAdd = (XCellRangeAddressable)UnoRuntime.queryInterface(XCellRangeAddressable.class, localXCellRange);
          
          CellRangeAddress address = xAdd.getRangeAddress();*/
	      
	      return str;
	}

	public static XDesktop getDesktop()	{
		try {
			XMultiComponentFactory localXMultiComponentFactory = sXComponentContext
					.getServiceManager();
			Object localObject = localXMultiComponentFactory
					.createInstanceWithContext("com.sun.star.frame.Desktop",
							sXComponentContext);
			XDesktop localXDesktop = (XDesktop) UnoRuntime.queryInterface(
					XDesktop.class, localObject);
			return localXDesktop;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return null;

	}

	public static List<DataSeries> getNumericSeries(boolean firstRowLabel) {
		XModel localXModel = getDesktop().getCurrentFrame().getController().getModel();
	    XInterface localXInterface = null;
	    localXInterface = (XInterface)UnoRuntime.queryInterface(XInterface.class, localXModel.getCurrentSelection());
	    XCellRange localXCellRange = (XCellRange)UnoRuntime.queryInterface(XCellRange.class, localXInterface);
	    
	    XCellRangeAddressable xAdd = (XCellRangeAddressable)UnoRuntime.queryInterface(XCellRangeAddressable.class, localXCellRange);
	    CellRangeAddress  address = xAdd.getRangeAddress();

        int sRow = address.StartRow;
        int sCol = address.StartColumn;

        int eRow = address.EndRow;
        int eCol = address.EndColumn;
        
        List<DataSeries> lNumericSeries = new ArrayList<DataSeries>((eCol-sCol)+1);
        
        XCellRangeData xData = UnoRuntime.queryInterface( com.sun.star.sheet.XCellRangeData.class, localXCellRange );
        
        Object[][] rawData = xData.getDataArray();
        
        for (int colIndex = 0; colIndex < (eCol-sCol)+1;colIndex++) {
        	DataSeries local = new DataSeries();
        	local.setDataSeriesName("Column_"+colIndex);
        	int rowIndex = 0;
			if( firstRowLabel){
    			local.setDataSeriesName(rawData[0][colIndex].toString());
    			rowIndex=1;
    		}
        	double[] values = new double[(eRow-sRow+1-rowIndex)];
        	for (int i = 0; i < values.length; i++) {
        		values[i] = (Double) rawData[i+rowIndex][colIndex];
			}	
			local.setValues(values);
			lNumericSeries.add(local);
		}
		return lNumericSeries;
	}
	
	public static List<DataSeries> getTextSeries(boolean firstRowLabel) {
		XModel localXModel = getDesktop().getCurrentFrame().getController().getModel();
	    XInterface localXInterface = null;
	    localXInterface = (XInterface)UnoRuntime.queryInterface(XInterface.class, localXModel.getCurrentSelection());
	    XCellRange localXCellRange = (XCellRange)UnoRuntime.queryInterface(XCellRange.class, localXInterface);
	    
	    XCellRangeAddressable xAdd = (XCellRangeAddressable)UnoRuntime.queryInterface(XCellRangeAddressable.class, localXCellRange);
	    CellRangeAddress  address = xAdd.getRangeAddress();

        int sRow = address.StartRow;
        int sCol = address.StartColumn;

        int eRow = address.EndRow;
        int eCol = address.EndColumn;
        
        List<DataSeries> lNumericSeries = new ArrayList<DataSeries>((eCol-sCol)+1);
        
        XCellRangeData xData = UnoRuntime.queryInterface( com.sun.star.sheet.XCellRangeData.class, localXCellRange );
        
        Object[][] rawData = xData.getDataArray();
        
        for (int colIndex = 0; colIndex < (eCol-sCol)+1;colIndex++) {
        	DataSeries local = new DataSeries();
        	local.setDataSeriesName("Column_"+colIndex);
        	int rowIndex = 0;
			if( firstRowLabel){
    			local.setDataSeriesName(rawData[0][colIndex].toString());
    			rowIndex=1;
    		}
        	String[] values = new String[(eRow-sRow+1-rowIndex)];
        	for (int i = 0; i < values.length; i++) {
        		values[i] = rawData[i+rowIndex][colIndex].toString();
			}	
			local.setTextValues(values);
			lNumericSeries.add(local);
		}
		return lNumericSeries;
	}

}
