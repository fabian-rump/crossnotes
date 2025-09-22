package de.fabianrump.crossnotes.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import kotlinx.datetime.LocalDate
import kotlinx.datetime.number
import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarUnitDay
import platform.Foundation.NSCalendarUnitMonth
import platform.Foundation.NSCalendarUnitYear
import platform.Foundation.NSDateComponents
import platform.Foundation.NSSelectorFromString
import platform.UIKit.NSLayoutConstraint
import platform.UIKit.UIApplication
import platform.UIKit.UIButton
import platform.UIKit.UIButtonTypeSystem
import platform.UIKit.UIColor
import platform.UIKit.UIControlEventTouchUpInside
import platform.UIKit.UIControlStateNormal
import platform.UIKit.UIDatePicker
import platform.UIKit.UIDatePickerMode
import platform.UIKit.UIDatePickerStyle
import platform.UIKit.UIDevice
import platform.UIKit.UIModalPresentationFormSheet
import platform.UIKit.UIViewController
import platform.UIKit.systemBackgroundColor

typealias DateSelectedCallback = (LocalDate) -> Unit
typealias DismissCallback = () -> Unit

@OptIn(ExperimentalForeignApi::class)
class DatePickerViewController(
    private val initialDate: LocalDate?,
    private val onDateSelected: DateSelectedCallback,
    private val onDismiss: DismissCallback
) : UIViewController(nibName = null, bundle = null) {

    private val datePicker = UIDatePicker()
    private val selectButton = UIButton.buttonWithType(buttonType = UIButtonTypeSystem)
    private val cancelButton = UIButton.buttonWithType(buttonType = UIButtonTypeSystem)

    override fun viewDidLoad() {
        super.viewDidLoad()

        view.backgroundColor = UIColor.systemBackgroundColor

        datePicker.datePickerMode = UIDatePickerMode.UIDatePickerModeDate
        if ((UIDevice.currentDevice.systemVersion.toDoubleOrNull() ?: 0.0) >= 14.0) {
            datePicker.preferredDatePickerStyle = UIDatePickerStyle.UIDatePickerStyleInline
        } else {
            datePicker.preferredDatePickerStyle = UIDatePickerStyle.UIDatePickerStyleWheels
        }
        initialDate?.let {
            val components = NSDateComponents()
            components.year = it.year.toLong()
            components.month = it.month.number.toLong()
            components.day = it.day.toLong()
            NSCalendar.currentCalendar.dateFromComponents(components)?.let { nsDate ->
                datePicker.setDate(nsDate, animated = false)
            }
        }

        selectButton.setTitle(title = "Select", forState = UIControlStateNormal)
        selectButton.addTarget(
            target = this,
            action = NSSelectorFromString(aSelectorName = "selectDate"),
            forControlEvents = UIControlEventTouchUpInside
        )

        cancelButton.setTitle(title = "Cancel", forState = UIControlStateNormal)
        cancelButton.addTarget(target = this, action = NSSelectorFromString(aSelectorName = "cancel"), forControlEvents = UIControlEventTouchUpInside)

        view.addSubview(view = datePicker)
        view.addSubview(view = selectButton)
        view.addSubview(view = cancelButton)

        datePicker.translatesAutoresizingMaskIntoConstraints = false
        selectButton.translatesAutoresizingMaskIntoConstraints = false
        cancelButton.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activateConstraints(
            listOf(
                datePicker.centerXAnchor.constraintEqualToAnchor(anchor = view.safeAreaLayoutGuide.centerXAnchor),
                datePicker.topAnchor.constraintEqualToAnchor(anchor = view.safeAreaLayoutGuide.topAnchor, constant = 20.0),
                datePicker.leadingAnchor.constraintGreaterThanOrEqualToAnchor(anchor = view.safeAreaLayoutGuide.leadingAnchor, constant = 20.0),
                datePicker.trailingAnchor.constraintLessThanOrEqualToAnchor(anchor = view.safeAreaLayoutGuide.trailingAnchor, constant = -20.0),

                selectButton.topAnchor.constraintEqualToAnchor(anchor = datePicker.bottomAnchor, constant = 20.0),
                selectButton.trailingAnchor.constraintEqualToAnchor(anchor = view.safeAreaLayoutGuide.trailingAnchor, constant = -20.0),
                selectButton.bottomAnchor.constraintEqualToAnchor(anchor = view.safeAreaLayoutGuide.bottomAnchor, constant = -20.0),

                cancelButton.topAnchor.constraintEqualToAnchor(anchor = datePicker.bottomAnchor, constant = 20.0),
                cancelButton.leadingAnchor.constraintEqualToAnchor(anchor = view.safeAreaLayoutGuide.leadingAnchor, constant = 20.0),
                cancelButton.widthAnchor.constraintEqualToAnchor(anchor = selectButton.widthAnchor)
            )
        )
    }

    @ObjCAction
    fun selectDate() {
        val selectedNSDate = datePicker.date
        val calendar = NSCalendar.currentCalendar
        val components = calendar.components(
            NSCalendarUnitYear or NSCalendarUnitMonth or NSCalendarUnitDay,
            fromDate = selectedNSDate
        )
        onDateSelected(
            LocalDate(
                year = components.year.toInt(),
                month = components.month.toInt(),
                day = components.day.toInt()
            )
        )
        dismissViewControllerAnimated(flag = true, completion = null)
        onDismiss()
    }

    @ObjCAction
    fun cancel() {
        dismissViewControllerAnimated(flag = true, completion = null)
        onDismiss()
    }
}

private fun String.toDoubleOrNull(): Double? {
    val majorVersionString = this.takeWhile { it.isDigit() || it == '.' }

    return try {
        val parts = majorVersionString.split('.')
        if (parts.size >= 2) {
            "${parts[0]}.${parts[1]}".toDouble()
        } else {
            parts[0].toDouble()
        }
    } catch (_: NumberFormatException) {
        null
    }
}

@Composable
actual fun DatePickerDialog(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    initialDate: LocalDate?
) {
    if (show) {
        LaunchedEffect(key1 = Unit) {
            try {
                val datePickerVC = DatePickerViewController(
                    initialDate = initialDate,
                    onDateSelected = { date ->
                        onDateSelected(date)
                    },
                    onDismiss = {
                        onDismissRequest()
                    }
                )

                datePickerVC.modalPresentationStyle = UIModalPresentationFormSheet

                val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
                if (rootViewController == null) {
                    println("DatePicker iOS Error: rootViewController is null. Dialog not shown.")
                    onDismissRequest()
                } else {
                    var presentingVC = rootViewController
                    while (presentingVC?.presentedViewController != null) {
                        presentingVC = presentingVC.presentedViewController
                    }
                    presentingVC?.presentViewController(viewControllerToPresent = datePickerVC, animated = true, completion = null)
                }

            } catch (e: Throwable) {
                println("DatePicker iOS LaunchedEffect Exception: ${e.message}")
                e.printStackTrace()
                onDismissRequest()
            }
        }
    }
}