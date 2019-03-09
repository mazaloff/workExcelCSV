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

    fun readToMap(filePath: String): MutableMap<Long, MutableMap<String, Any>> {

        val fmt = CSVFormat.EXCEL.withDelimiter(';')
            .withFirstRecordAsHeader()
            .withHeader("code", "product", "quantity", "cost")
            .withIgnoreHeaderCase()
            .withTrim()

        val csvParser = CSVParser(File(filePath).bufferedReader(), fmt)
        val result: MutableMap<Long, MutableMap<String, Any>> = mutableMapOf()

        for (csvRecord in csvParser) {
            val rowMap = mutableMapOf(
                "code" to csvRecord.getStringOrEmpty("code", "<?>"),
                "product" to csvRecord.getStringOrEmpty("product", "<?>"),
                "quantity" to csvRecord.getNumberOrEmpty("quantity", 0) { it.toInt() },
                "cost" to csvRecord.getNumberOrEmpty("cost", 0.00) { it.toDouble() }
            )
            result[csvRecord.recordNumber] = rowMap
        }
        return result
    }
}
