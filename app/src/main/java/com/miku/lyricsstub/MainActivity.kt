package com.miku.lyricsstub

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.miku.lyricsstub.util.SystemCheckUtil

class MainActivity : Activity() {
    private val sharedPreferences: SharedPreferences? by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }
    private val editor: SharedPreferences.Editor? by lazy { sharedPreferences?.edit() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = LinearLayout(this)
        view.orientation = LinearLayout.VERTICAL
        view.gravity = android.view.Gravity.CENTER

        val tv = TextView(this)
        tv.text = getString(R.string.tip)
        tv.gravity = android.view.Gravity.CENTER

        val btn = Button(this)
        btn.text = getString(R.string.download)
        btn.setOnClickListener {
            editor?.putBoolean(getString(R.string.key_update_from_dummy), true)?.commit()
            Toast.makeText(this, getString(R.string.tip_auto_start), Toast.LENGTH_SHORT).show()
            val url =
                if (SystemCheckUtil.isUnofficial(
                        this,
                    )
                ) {
                    "https://sourceforge.net/projects/divarelease/files/Miku%20UI%20Lyrics%20Stub/For%20Unofficial/"
                } else {
                    "https://sourceforge.net/projects/divarelease/files/Miku%20UI%20Lyrics%20Stub"
                }
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        view.addView(tv)
        view.addView(btn)
        setContentView(view)
    }
}
