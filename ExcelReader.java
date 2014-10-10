import java.io.*;
import jxl.*;
import org.json.*;
import jxl.read.biff.*;

public class ExcelReader {

  /**
   * The constructor.
   */
  ExcelReader() {

  }

  /**
   * Generates a Workbook and pulls the relevant Sheet from that Workbook.
   * @param inFile The raw Excel file.
   * @return A fully-formed JSONObject of inFile.
   */
  public static JSONObject parseExcel(java.io.File inFile) {
    try {
      /* Gets a Workbook from the raw Excel File. */
      Workbook workbook = Workbook.getWorkbook(inFile); 
      
      /* Pulls a Sheet (zero-indexed) from the Workbook). */
      Sheet sheet = workbook.getSheet(1);
      
      return parseSheet(sheet);
    } catch (IOException e) {
        System.err.println("IOException: " + e.getMessage());
    } catch (BiffException e) {
        System.err.println("BiffException: " + e.getMessage());
    }
    /* Should never happen. */
    return new JSONObject();
    
  }

  /**
   * Parses the projects listed in sheet, adding them as keys and their contents
   * as values to a JSONObject.
   * @param sheet the Sheet with all the projects on it.
   * @return A fully-formed JSONObject of sheet.
   */
  private static JSONObject parseSheet(Sheet sheet) {
    JSONObject parsedData = new JSONObject();
    final int PROJECTCOLUMN = 2;
  
    for(int row = 1; row < sheet.getRows(); row++) {
      Cell projectCell = sheet.getCell(PROJECTCOLUMN, row);
      String projectID = projectCell.getContents();
      parsedData.append(projectID, JSONParseProject(sheet, row));
    } 
    return parsedData;
  }
    
  /**
   * Parses the contents of an individual project into a new JSONObject.
   * @param sheet the Sheet with all the projects on it.
   * @param projRow the row in sheet containing the project in question.
   * @return a JSONObject of the project in projRow.
   */
  private static JSONObject JSONParseProject(Sheet sheet, int projRow) {
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
