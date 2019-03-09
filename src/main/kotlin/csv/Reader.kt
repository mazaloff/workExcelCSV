package csv

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.File

object Reader {
    fun readToPrint(filePath: String) {
        val map = readToMap(filePath)
        for (entity in map) {
            // print the value to console
            println("Record No - " + entity.key)
            println("---------------")
            println("code : ${entity.value["code"]}")
            println("product : ${entity.value["product"]}")
            println("quantity : ${entity.value["quantity"]}")
            println("cost : ${entity.value["cost"]}")
            println("---------------\n")
        }
    }

    fun readToMap(filePath: String): MutableMap<Long, MutableMap<String, String>> {
        val fmt = CSVFormat.EXCEL.withDelimiter(';')
        val csvParser = CSVParser(
            File(filePath).bufferedReader(), fmt
                .withHeader("code", "product", "quantity", "cost")
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim()
        )
        val result: MutableMap<Long, MutableMap<String, String>> = mutableMapOf()
        for (csvRecord in csvParser) {
            val row = mutableMapOf(
                "code" to csvRecord.getOrEmpty("code", "<?>"),
                "product" to csvRecord.getOrEmpty("product", "<?>"),
                "quantity" to csvRecord.getOrEmpty("quantity", "0"),
                "cost" to csvRecord.getOrEmpty("cost", "0.00")
            )
            result[csvRecord.recordNumber] = row
        }
        return result
    }
}
