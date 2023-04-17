package com.example.a_level.keyword

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import com.example.a_level.R

class UserPreferenceDialog(context: Context) {
    lateinit var listener: SummitDialogClickedListener
    lateinit var btnSummit: Button

    interface SummitDialogClickedListener {
        fun onSummitClicked()
    }

    private val dialog = Dialog(context)

    fun start() {
        /*타이틀바 제거*/
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        /*커스텀 다이얼로그 radius 적용*/
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.setContentView(R.layout.dialog_user_preference)

        btnSummit = dialog.findViewById(R.id.button_dialog_summit)
        btnSummit.setOnClickListener {
            listener.onSummitClicked()
            dialog.dismiss()
        }

        dialog.show()
    }
}