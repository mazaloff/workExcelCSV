import csv.Reader
import csv.Writer

fun main() {
    val map = Reader.readToMap("src\\main\\resources\\fileForRead.csv")
    Writer.writeFromMap("src\\main\\resources\\fileForWrite.csv", map)
}
