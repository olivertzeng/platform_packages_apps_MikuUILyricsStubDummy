package com.miku.lyricsstub

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
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
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sourceforge.net/projects/divarelease/files/Miku%20UI%20Lyrics%20Stub"))
            startActivity(intent)
        }

        view.addView(tv)
        view.addView(btn)
        setContentView(view)
    }
}