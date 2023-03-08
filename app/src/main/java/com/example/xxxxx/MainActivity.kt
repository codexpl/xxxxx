package com.example.xxxxx

import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var messenger:Messenger
    lateinit var xbutton:Button
    lateinit var ybutton:Button
    lateinit var zbutton:Button

    lateinit var recorder:Recorder
    val FNAME    =   "DATABASE"
    lateinit var DATABASE_FILE_ABSOLUTE_PATH:String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        DATABASE_FILE_ABSOLUTE_PATH = Environment.getExternalStorageDirectory().absolutePath + "/DATABASE_TEMP.TXT"

          // START MESSENGERA:
          messenger = Messenger( this@MainActivity, findViewById(R.id.textViewID) )
          messenger.reset()

          // START RECORDERA
          recorder  = Recorder(this@MainActivity )
          recorder.initialize()


          // WYSWIETLANIE STANU URUCHOMIENIA
          messenger.add( "------------------\n" + "outputFilePath: \n" + recorder.output + "\n------------------" )
          messenger.add( recorder.lastErrorMessage )




            xbutton = findViewById<Button>(R.id.XbuttonID)
            xbutton.setOnClickListener() {

                messenger.add ( "DIRECTORY_ALARMS = \n" + Environment.DIRECTORY_ALARMS + "\n ---------------------- ")
                messenger.add( "DIRECTORY_DCIM = \n" +  Environment.DIRECTORY_DCIM + "\n ---------------------- ")
                saveData( messenger.get(), DATABASE_FILE_ABSOLUTE_PATH )
          }


         ybutton = findViewById<Button>(R.id.YbuttonID)
         ybutton.setOnClickListener(){

             messenger.reset()
             messenger.add("DANE ODCZYTANE Z PLIKU TEXTOWEGO:\n")
             var raeded:List<String> = readData( DATABASE_FILE_ABSOLUTE_PATH )

         }


        zbutton = findViewById<Button>(R.id.ZbuttonID)
        zbutton.setOnClickListener(){
            messenger.reset()
        }

    }

    fun saveData( data:String, fileAbsolutePath:String ) {
        var file = File( fileAbsolutePath )
        if( file.exists() ) { file.delete() }
        file.createNewFile()
        file.writeText(data)
    }

    fun readData( filename: String ) : List<String> {
        var emptyReturn:List<String> = listOf("stringx", "stringy", "stringz", filename )
        return emptyReturn
    }

}