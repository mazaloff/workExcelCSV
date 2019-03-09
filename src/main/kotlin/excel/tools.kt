package excel

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row

internal inline fun <T : Number> Row.getNumberOrEmpty(columnNumber: Int, empty: T, convert: (Number) -> T): T {
    val cell = this.getCell(columnNumber)
    return when (cell.cellType) {
        CellType.NUMERIC -> try {
            convert.invoke(cell.numericCellValue)
        } catch (e: IllegalArgumentException) {
            empty
        } catch (e: NumberFormatException) {
            empty
        }
        else -> empty
    }
}

internal fun Row.getStringOrEmpty(columnNumber: Int, empty: String): String {
    val cell = this.getCell(columnNumber)
    return when (cell.cellType) {
        CellType.STRING -> try {
            cell.stringCellValue
        } catch (e: IllegalArgumentException) {
            empty
        } catch (e: NumberFormatException) {
            empty
        }
        else -> empty
    }
}

internal fun Any.stringOrEmpty(empty: String): String {
    return when (empty) {
        this -> ""
        else -> this.toString()
    }
}

internal fun Any.doubleOrEmpty(empty: Number): Double {
    return when {
        this == empty -> empty.toDouble()
        this is Number -> this.toDouble()
        else -> empty.toDouble()
    }
}