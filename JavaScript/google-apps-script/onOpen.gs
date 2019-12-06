/**
 * Open the Google Sheet with my running logs to a specific row when loaded.
 * Author: Andrew Jarombek
 * Date: 12/4/2019
 */

function onOpen() {
  // Location of Season 3 (Beginning Dec. 2nd) in the spreadsheet
  var row = 70;
  var column = 1;

  var ss = SpreadsheetApp.getActiveSpreadsheet();
  var sheet = ss.getActiveSheet();
  var season3Row = sheet.getRange(row + 7, 1).activate();
}