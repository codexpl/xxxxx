package com.example.xxxxx

import android.content.Context
import android.content.DialogInterface
import android.icu.text.CaseMap.Title
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog




class Messenger (var baseContext: Context, var textfield:TextView ) {



        fun toast( s:String ) {
                Toast.makeText( baseContext, s, Toast.LENGTH_LONG ).show()
        }

        fun add( s:String ) {
                var text:String = textfield.text as String
                text += "\n"
                text += s
                textfield.text = text
        }

        fun get() : String {
                return textfield.text.toString()
        }

        fun reset() {
                textfield.text = ""
        }


}