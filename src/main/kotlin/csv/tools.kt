package csv

import org.apache.commons.csv.CSVRecord

internal fun CSVRecord.getOrEmpty(index: Int, empty: String): String = when {
    this.size() - 1 < index -> empty
    else -> if (this.get(index).isNullOrEmpty()) empty else this.get(index)
}

internal fun CSVRecord.getOrEmpty(name: String, empty: String): String {
    val value = try {
        this.get(name) ?: empty
    } catch (e: IllegalArgumentException) {
        empty
    }
    return when {
        value.isEmpty() -> empty
        else -> value
    }
}

internal fun String.valueOrEmpty(empty: String): String {
    return when (empty) {
        this -> ""
        else -> this
    }
}