import csv.Reader as csvReader
import csv.Writer as csvWriter

import excel.Reader as excelReader
import excel.Writer as excelWriter

fun main() {

    val mapCSV = csvReader.readToMap("src\\main\\resources\\fileForRead.csv")
    csvWriter.writeFromMap("src\\main\\resources\\fileForWrite.csv", mapCSV)

    val mapExcel = excelReader.readToMap("src\\main\\resources\\fileForRead.xlsx")
    excelWriter.writeFromMap("src\\main\\resources\\fileForWrite.xlsx", mapExcel)

}

