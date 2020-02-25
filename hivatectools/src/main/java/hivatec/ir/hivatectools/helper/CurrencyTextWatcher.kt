package trapay.ir.trapay.helper

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class CurrencyTextWatcher(private val ed: EditText) : TextWatcher {

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun afterTextChanged(s: Editable) {

        ed.removeTextChangedListener(this)

        try {
            var givenstring = s.toString()
            val longval: Long?
            if (givenstring.contains(",")) {
                givenstring = givenstring.replace(",".toRegex(), "")
            }
            longval = java.lang.Long.parseLong(givenstring)
            val formatter = DecimalFormat("#,###,###")
            val formattedString = formatter.format(longval)
            ed.setText(formattedString)
            ed.setSelection(ed.text.length)
            // to place the cursor at the end of text
        } catch (nfe: NumberFormatException) {
            nfe.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        ed.addTextChangedListener(this)


    }

    private fun getFormattedString(text: String): String {
        var res = ""
        try {
            val temp = text.replace(",", "")
            val part1: Long
            var part2 = ""
            val dotIndex = temp.indexOf(".")
            if (dotIndex >= 0) {
                part1 = java.lang.Long.parseLong(temp.substring(0, dotIndex))
                if (dotIndex + 1 <= temp.length) {
                    part2 = temp.substring(dotIndex + 1).trim { it <= ' ' }.replace(".", "").replace(",", "")
                }
            } else
                part1 = java.lang.Long.parseLong(temp)

            res = getStringWithSeparator(part1)
            if (part2.isNotEmpty())
                res += ".$part2"
            else if (dotIndex >= 0)
                res += "."
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return res
    }

    companion object {

        fun getStringWithSeparator(value: Long): String {
            val formatter = NumberFormat.getNumberInstance(Locale.US) as DecimalFormat
            return formatter.format(value)
        }
    }
}

