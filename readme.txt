README

Input:
JExcel works with .xls files—not .xlsx files.

Running:
JExcel needs some VM args to work with large files, or you'll get a heap space error. Run it like so:

java -Xms16m -Xmx1024m ExcelReaderTester [inputfile] [outputfile]
