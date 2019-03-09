package csv

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.File

object Writer {
    fun writeFromMap(filePath: String, map: MutableMap<Long, MutableMap<String, Any>>) {

        val writer = File(filePath).bufferedWriter()
        val fmt = CSVFormat.EXCEL.withDelimiter(';')
            .withHeader("code", "product", "quantity", "cost")
            .withIgnoreHeaderCase()
            .withTrim()

        val csvPrinter = CSVPrinter(writer, fmt)

        for (entity in map) {
            csvPrinter.printRecord(
                entity.value["code"]?.valueOrEmpty("<?>"),
                entity.value["product"]?.valueOrEmpty("<?>"),
                entity.value["quantity"]?.valueOrEmpty("0"),
                entity.value["cost"]?.valueOrEmpty("0.00")
            )
        }
        csvPrinter.flush()
        csvPrinter.close()
    }
}