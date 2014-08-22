import java.io.*;
import jxl.*;
import org.json.*;
import jxl.read.biff.*;

/*
 * JSON object structure should be Master>Year>Country>Project.
 */
public class ExcelReader {

  ExcelReader() {

  }

  public static JSONObject parseExcel(java.io.File inFile) {
    try {
      Workbook workbook = Workbook.getWorkbook(inFile); 
      Sheet sheet = workbook.getSheet(1);
      return parseSheet(sheet);
    } catch (IOException e) {
        System.err.println("IOException: " + e.getMessage());
    } catch (BiffException e) {
        System.err.println("BiffException: " + e.getMessage());
    }
    return new JSONObject();
  }

  private static JSONObject parseSheet(Sheet sheet) {
    JSONObject parsedData = new JSONObject();
      
    for(int row = 1; row < sheet.getRows(); row++) {
      Cell projectCell = sheet.getCell(2, row);
      String projectID = projectCell.getContents();
      parsedData.append(projectID, parseProject(sheet, row));
    } 

    return parsedData;
  }
    
  private static JSONObject parseProject(Sheet sheet, int projRow) {
    JSONObject project = new JSONObject();
    final int COLUMNS = 29;

    for(int column = 0; column <= COLUMNS; column++) {
      Cell key = sheet.getCell(column, 0);
      Cell val = sheet.getCell(column, projRow);
      
      String keyString = key.getContents();
      String valString = val.getContents();
      
      project.append(keyString, valString);
    }

    return project; 
  }
}
