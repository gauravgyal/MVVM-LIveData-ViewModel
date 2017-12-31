package com.gauravgoyal.mvvm_with_testing.utility

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by gauravgoyal on 16/12/17.
 */
public class DateUtils {
    companion object {
        public fun convertToDateString(date: String): String {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'").parse(date))
        }
    }
}