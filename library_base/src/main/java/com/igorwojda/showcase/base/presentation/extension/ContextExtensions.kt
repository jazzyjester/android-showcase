package com.igorwojda.showcase.base.presentation.extension

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import java.io.IOException
import java.nio.charset.Charset
import kotlin.reflect.KProperty1

fun Context.toast(@StringRes resId: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, getString(resId), length).show()
}

inline fun <reified T : AppCompatActivity> Context.startActivity(
    vararg params: Pair<KProperty1<out Any?, Any?>, Any?>
) {
    val extras = params.map { it.first.name to it.second }.toTypedArray()
    val intent = Intent(this, T::class.java)
    intent.putExtras(bundleOf(*extras))
    startActivity(intent)
}

@Throws(IOException::class)
fun Context.loadJSONFromAsset(jsonFileName: String): String {
    assets.open(jsonFileName).let {
        val buffer = ByteArray(it.available())
        it.read(buffer)
        it.close()
        return String(buffer, Charset.forName("UTF-8"))
    }


}
