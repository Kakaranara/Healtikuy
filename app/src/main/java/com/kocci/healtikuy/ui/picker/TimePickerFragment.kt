package com.kocci.healtikuy.ui.picker

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    /**
     * ! Careful! you need to implement TimePickerListener,
     * ! In case you want to show a time.
     * ? cause this listener expecting a parent fragment.
     */

    // * should be called within fragment, and pass a childFragmentManager inside show()
    var listener: TimePickerListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = parentFragment as TimePickerListener?
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val cal = Calendar.getInstance()
        val hours = cal.get(Calendar.HOUR_OF_DAY)
        val minute = cal.get(Calendar.MINUTE)

        return TimePickerDialog(
            activity,
            this,
            hours,
            minute,
            DateFormat.is24HourFormat(activity)
        )
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        listener?.onTimeSet(tag, hourOfDay, minute)
    }


    interface TimePickerListener {
        fun onTimeSet(tag: String?, hour: Int, minute: Int)
    }

}

