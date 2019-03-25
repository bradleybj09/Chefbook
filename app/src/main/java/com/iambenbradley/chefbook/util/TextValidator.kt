package com.iambenbradley.chefbook.util

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText

abstract class TextValidator(val v: TextInputEditText) : TextWatcher {

    abstract fun validate(v: TextInputEditText, s: String)

    override fun afterTextChanged(s: Editable?) {
        validate(v, s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}