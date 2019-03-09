package excel

import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File


object Writer {
    fun writeFromMap(filePath: String, map: MutableMap<Long, MutableMap<String, Any>>) {

        val xlWb = XSSFWorkbook()
        val xlWs = xlWb.createSheet()

        val styleHeader = xlWb.createCellStyle()
        styleHeader.setFont(xlWb.createFont().apply {
            bold = true
            color = IndexedColors.RED.index
        })

        // the first row contains table' headers
        val xlRowHeader = xlWs.createRow(0)
        xlRowHeader.createCell(0).apply {
            cellStyle = styleHeader
            setCellValue("code")
        }
        xlRowHeader.createCell(1).apply {
            cellStyle = styleHeader
            setCellValue("product")
        }
        xlRowHeader.createCell(2).apply {
            cellStyle = styleHeader
            setCellValue("quantity")
        }
        xlRowHeader.createCell(3).apply {
            cellStyle = styleHeader
            setCellValue("cost")
        }

        for (entity in map) {
            val xlRow = xlWs.createRow(entity.key.toInt())
            xlRow.createCell(0).setCellValue(
                entity.value.getOrDefault("code", "").stringOrEmpty("<?>")
            )
            xlRow.createCell(1).setCellValue(
                entity.value.getOrDefault("product", "").stringOrEmpty("<?>")
            )
            xlRow.createCell(2).setCellValue(
                entity.value.getOrDefault("quantity", 0).doubleOrEmpty(0)
            )
            xlRow.createCell(3).setCellValue(
                entity.value.getOrDefault("cost", 0).doubleOrEmpty(0.0)
            )
        }

        xlWs.setColumnWidth(0, 256*15)
        xlWs.setColumnWidth(1, 256*65)
        xlWs.setColumnWidth(2, 256*12)
        xlWs.setColumnWidth(3, 256*14)

        xlWb.write(File(filePath).outputStream())
        xlWb.close()

    }
}
