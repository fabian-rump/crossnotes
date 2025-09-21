package de.fabianrump.crossnotes.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.datetime.LocalDate
import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarUnitDay
import platform.Foundation.NSCalendarUnitMonth
import platform.Foundation.NSCalendarUnitYear
import platform.Foundation.NSDateComponents
import platform.UIKit.UIAlertAction
import platform.UIKit.UIAlertActionStyleCancel
import platform.UIKit.UIAlertActionStyleDefault
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication
import platform.UIKit.UIDatePicker
import platform.UIKit.UIDatePickerMode

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun DatePickerDialog(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    initialDate: LocalDate?
) {
    if (show) {
        LaunchedEffect(Unit) {
            try {

                val alertController = UIAlertController.alertControllerWithTitle(
                    title = "Select Date",
                    message = "\n\n\n\n\n\n\n\n\n",
                    preferredStyle = UIAlertControllerStyleAlert
                )

                val datePicker = UIDatePicker().apply {
                    datePickerMode = UIDatePickerMode.UIDatePickerModeDate
                    // toDouble makes problems here
//                    preferredDatePickerStyle = if (platform.UIKit.UIDevice.currentDevice.systemVersion.toDouble() >= 14.0) {
//                        platform.UIKit.UIDatePickerStyle.UIDatePickerStyleInline
//                    } else {
                        platform.UIKit.UIDatePickerStyle.UIDatePickerStyleWheels
//                    }
                    initialDate?.let {
                        val components = NSDateComponents()
                        components.year = it.year.toLong()
                        components.month = it.monthNumber.toLong()
                        components.day = it.dayOfMonth.toLong()
                        NSCalendar.currentCalendar.dateFromComponents(components)?.let { nsDate ->
                            setDate(nsDate, animated = false)
                        }
                    }
                }
                alertController.view.addSubview(datePicker)

                datePicker.translatesAutoresizingMaskIntoConstraints = false
                datePicker.centerXAnchor.constraintEqualToAnchor(alertController.view.centerXAnchor).active = true
                datePicker.topAnchor.constraintEqualToAnchor(alertController.view.topAnchor, constant = 50.0).active = true

                val selectAction = UIAlertAction.actionWithTitle(
                    title = "Select",
                    style = UIAlertActionStyleDefault
                ) { _ ->
                    val selectedNSDate = datePicker.date
                    val calendar = NSCalendar.currentCalendar
                    val components = calendar.components(
                        NSCalendarUnitYear or NSCalendarUnitMonth or NSCalendarUnitDay,
                        fromDate = selectedNSDate
                    )
                    onDateSelected(
                        LocalDate(
                            year = components.year.toInt(),
                            monthNumber = components.month.toInt(),
                            dayOfMonth = components.day.toInt()
                        )
                    )
                    onDismissRequest()
                }

                val cancelAction = UIAlertAction.actionWithTitle(
                    title = "Cancel",
                    style = UIAlertActionStyleCancel
                ) { _ ->
                    onDismissRequest()
                }

                alertController.addAction(selectAction)
                alertController.addAction(cancelAction)

                val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
                rootViewController?.presentViewController(alertController, animated = true, completion = null)
            } catch (e: Exception) {
                println("DatePicker iOS selectAction Exception: ${e.message}")
                e.printStackTrace()
            } finally {
                onDismissRequest()
            }
        }
    }
}
