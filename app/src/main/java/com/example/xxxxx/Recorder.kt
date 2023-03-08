package com.example.xxxxx

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.io.IOException


class Recorder( var context:Context ) {

    val     STATUS_NOT_ACTIVE:Int       =   0
    val     STATUS_ACTIVE:Int           =   1
    val     STATUS_PAUSED:Int           =   2
    val     STATUS_ERROR:Int            =   4   //  details of error in function getLastError():String



    private var mediaRecorder: MediaRecorder?   =   null
    var         output:String?                  =   null
    var         lastErrorMessage:String         =   "brak dzialan"
    var         status:Int                      =   STATUS_NOT_ACTIVE



    fun initialize() {
        output  =   Environment.getExternalStorageDirectory().absolutePath + "/recording.mp3"


        mediaRecorder?.setAudioSource( MediaRecorder.AudioSource.MIC )
        mediaRecorder?.setOutputFormat( MediaRecorder.OutputFormat.MPEG_4 )
        mediaRecorder?.setAudioEncoder( MediaRecorder.AudioEncoder.AAC )
        mediaRecorder?.setOutputFile( output )


        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(
                android.Manifest.permission.RECORD_AUDIO,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            ActivityCompat.requestPermissions(context as Activity, permissions, 0)
        }
    }



    fun start() {
            try {
                mediaRecorder?.prepare()
                mediaRecorder?.start()
                status = STATUS_ACTIVE
                lastErrorMessage = "recording is active now"
            } catch (e: java.lang.IllegalStateException) {
                status = STATUS_ERROR
                lastErrorMessage = e.message.toString()
            } catch (e: IOException) {
                status = STATUS_ERROR
                lastErrorMessage = e.message.toString()
            }
    }



    fun stop() {
        if( status == STATUS_ACTIVE ) {
            mediaRecorder?.stop()
            mediaRecorder?.release()
            lastErrorMessage = "recording stopped now"
        } else {
            lastErrorMessage = "you not recording now"
        }
    }
}