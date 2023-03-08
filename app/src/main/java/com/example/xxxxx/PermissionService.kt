package com.example.xxxxx

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class PermissionService(var context: Context, var permissions:Array<Manifest.permission>) {

    private val  missingPermissions:Array<android.Manifest.permission>

        get() {
            TODO()
        }

    fun check() {

        }
}