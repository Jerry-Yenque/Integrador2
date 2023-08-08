package com.grof.integrator.presentation.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.grof.integrator.R
import java.io.File

class ComposeFileProvider: FileProvider(R.xml.file_paths) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val directory = File(context.cacheDir, "images")
            directory.mkdirs()
            val file = File.createTempFile(
                "selected_image_",
                ".jpg",
                directory
            )
            val authory = context.packageName + ".fileprovider"
            return getUriForFile(
                context,
                authory,
                file
            )
        }
    }
}