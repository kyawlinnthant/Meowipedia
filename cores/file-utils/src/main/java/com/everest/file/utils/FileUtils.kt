package com.everest.file.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.core.database.getStringOrNull
import com.everest.extensions.log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

object FileUtils {
    private val bufferSize = 4028
    private fun writeFile(inputStream: InputStream, file: File) {
        val outputStream = FileOutputStream(file)
        val buffer = ByteArray(bufferSize) // Adjust the buffer size as per your requirements
        var bytesRead: Int
        try {
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }
        } catch (e: IOException) {
            e.log("${e.printStackTrace()}")
        } finally {
            inputStream.close()
            outputStream.close()
        }
    }

    private fun getFilename(context: Context, uri: Uri): String? {
        val cursor = context.contentResolver.query(
            uri,
            null,
            null,
            null,
            null
        )
        cursor?.moveToFirst()
        val name = cursor?.getStringOrNull(
            cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME)
        )
        cursor?.close()
        return name
    }

    fun getFileFromUri(context: Context, uri: Uri): File? {
        val filename = getFilename(context, uri)
        return filename?.let { name ->
            val file = File(context.filesDir, name)
            val inputStream = context.contentResolver.openInputStream(uri)
            inputStream?.let {
                writeFile(it, file)
            }
            file
        } ?: run {
            null
        }
    }
}
