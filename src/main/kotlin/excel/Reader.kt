package excel

import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File

object Reader {
    fun readToPrint(filePath: String) {
        val map = Reader.readToMap(filePath)
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

        val result: MutableMap<Long, MutableMap<String, Any>> = mutableMapOf()

        val xlWb = WorkbookFactory.create(File(filePath).inputStream())
        val xlWs = xlWb.getSheetAt(0)

        val xlRows = xlWs.rowIterator()
        for (row in xlRows) {
            if (row.rowNum == 0) continue // the first row contains table' headers
            val rowMap = mutableMapOf(
                "code" to row.getStringOrEmpty(0, "<?>"),
                "product" to row.getStringOrEmpty(1, "<?>"),
                "quantity" to row.getNumberOrEmpty(2, 0) { it.toInt() },
                "cost" to row.getNumberOrEmpty(3, 0.00) { it.toDouble() }
            )
            result[row.rowNum.toLong()] = rowMap
        }

        return result
    }
}