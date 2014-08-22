import java.io.*;
import org.json.*;

public class ExcelReaderTester {
  
  public static void main(String[] args) {
    File inFile = new File(args[0]);
    File outputFile = new File(args[1]);
    
    /* Parses the Excel into JSON. */
    JSONObject parsedData = ExcelReader.parseExcel(inFile);
    
    jPrinter(parsedData, outputFile);
  }
  
  /**
   * Prints a JSONObject to a file.
   * @param json The JSONObject to be printed.
   * @param output The output file.
   */
  private static void jPrinter(JSONObject json, File output) {
    try {
      FileWriter writer = new FileWriter(output);     
      
      /* Print without whitespace: */
      // writer.write(json.toString());
      
      /* Print with an indent factor of 2: */
      writer.write(json.toString(2));      
      
      writer.close();
      } catch (IOException e) {
          System.err.println("IO Exception");
      }    
  }
}
