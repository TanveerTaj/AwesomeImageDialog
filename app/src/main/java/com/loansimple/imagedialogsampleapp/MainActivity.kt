package com.loansimple.imagedialogsampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import `in`.loansimple.picdialog.PicDialog
import android.net.Uri
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        click.setOnClickListener {
            PicDialog.Builder(this)
                .setOutSideDismiss(true)
                .setUri(Uri.parse("https://images.pexels.com/photos/1236701/pexels-photo-1236701.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"))
//                .setUrl("https://img.purch.com/w/660/aHR0cDovL3d3dy5saXZlc2NpZW5jZS5jb20vaW1hZ2VzL2kvMDAwLzEwMS81MzMvb3JpZ2luYWwvc2h1dHRlcnN0b2NrXzY3OTQ0NTcwNC5qcGc=")
                .build()
                .show()
        }

    }
}
