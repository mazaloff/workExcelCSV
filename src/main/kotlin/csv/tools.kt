package csv

import org.apache.commons.csv.CSVRecord

internal inline fun <reified T : Number> CSVRecord.getNumberOrEmpty(name: String, empty: T, convert: (String) -> T): T {
    val value: Number = try {
        convert.invoke(this.get(name))
    } catch (e: IllegalArgumentException) {
        empty
    } catch (e: NumberFormatException) {
        empty
    }
    return when {
        value.toDouble() == 0.0 -> empty
        else -> value as T
    }
}

internal fun CSVRecord.getStringOrEmpty(name: String, empty: String): String {
    val value =
        try {
            this.get(name) ?: empty
        } catch (e: IllegalArgumentException) {
            empty
        }
    return when {
        value.isEmpty() -> empty
        else -> value
    }
}

internal fun Any.valueOrEmpty(empty: String): String {
    return when (empty) {
        this -> ""
        else -> this.toString()
    }
}