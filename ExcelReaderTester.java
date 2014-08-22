import java.io.*;
import org.json.*;
import java.util.Set;

public class ExcelReaderTester {
  
  public static void main(String[] args) {
    File inFile = new File(args[0]);
    File outputFile = new File("output.txt");
    JSONObject parsedData = ExcelReader.parseExcel(inFile);
    
    try {
    FileWriter writer = new FileWriter(outputFile);
    writer.write(parsedData.toString(2));
    writer.close();
    } catch (IOException e) {
        System.err.println("IO Exception");
    }
        
  }
}
