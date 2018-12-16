package io.plainapp.core.data.database

import androidx.room.TypeConverter

/**
 * Type converters to allow Room to reference complex data types.
 */
class Converters {

    @TypeConverter fun csvToLongArray(csvString: String): List<Long> {
        return if (csvString.isEmpty()) {
            emptyList()
        } else {
            csvString.split(CSV_DELIMITER).map { it.toLong() }
        }
    }

    @TypeConverter fun longListToCsv(longList: List<Long>): String {
        return longList.joinToString(CSV_DELIMITER)
    }

    companion object {
        private const val CSV_DELIMITER = ","
    }
}