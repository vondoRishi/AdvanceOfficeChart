# AdvanceOfficeChart
  The objective of this extension is to easily provide high quality charts which are not available in OpenOffice or LibreOffice. Such a list of charts could be found https://wiki.openoffice.org/wiki/Chart2/ChartTypes.  At present, with this extension, it is possible to plot venn diagrams (upto 4 way, get the elements of different sets), histogram (flexible) and boxplot charts and save them in png or svg format.  
  OpenOffice plugin [page](https://extensions.openoffice.org/en/project/advance-office-chart). -- [installer](https://github.com/vondoRishi/AdvanceOfficeChart/blob/master/AOC_Open_0.2.2.oxt) <br>
  Libreoffice plugin [page](https://extensions.libreoffice.org/extensions/advance-office-chart-1). -- [installer](https://github.com/vondoRishi/AdvanceOfficeChart/blob/master/AOC_Libre_0.2.2.oxt)
  
  After installation, restart OpenOffice and the extension will be available from "Tools -> Add-Ons".  Please comment your experience or report any bug.  
  This extension uses JFreeChart (http://www.jfree.org/jfreechart/) library to plot the charts.  
        
    Tested on : 
Windows 7 (64 bit) + OpenOffice 4.0.1 (Passed)  <br>
Windows 7 (64 bit) + LibreOffice 4.1.4.2 (Passed)  <br>
Windows + OpenOffice 3.3 (Passed)  <br>
CentOS + LibreOffice 4.0 (Passed)  <br>
CentOS + LibreOffice 3.5 (Passed)  <br>

Ubuntu + LibreOffice 3.5 (passed)   <br>
{  if you encounter an error message like "CannotRegisterImplementationException" then try following as suggested [here](http://sourceforge.net/apps/trac/comppad/wiki/FrequentlyAskedQuestions#OninstallationIgetCannotRegisterImplementationExceptionerror) .. 

  update libreoffice with   
  "sudo apt-get install libreoffice"  
  reinstall this extension.  


