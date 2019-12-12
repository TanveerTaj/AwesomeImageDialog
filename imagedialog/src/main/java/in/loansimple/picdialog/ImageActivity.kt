package `in`.loansimple.picdialog

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import `in`.loansimple.picdialog.zoomage.ZoomCallback
import kotlinx.android.synthetic.main.activity_dialog.*
import android.os.Build
import android.provider.Settings
import java.io.File
import java.lang.Exception

class ImageActivity : AppCompatActivity(), ZoomCallback {
    private var urlString: String = ""
    private var uri: Uri? = null
    private var file: File? = null
    private var listOfUrl: ArrayList<String>? = null
    private var MY_PERMISSIONS_REQUEST_READ_STORAGE: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawableResource(android.R.color.transparent)
        try {
            urlString = intent.getStringExtra(ARG_URL)
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        uri = intent.getParcelableExtra(ARG_URI)
        file = intent.getSerializableExtra(ARG_FILE) as File?
        listOfUrl = intent.getStringArrayListExtra(ARG_LIST_URL)
        setContentView(R.layout.activity_dialog)
        close_btn.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        permission()
        image.setZoomCallback(this)
    }

    fun loadImage() {
        val cir = CircularProgressDrawable(this)
        cir.start()
        cir.setStyle(CircularProgressDrawable.LARGE)

        if (imageValue.equals("url")){
            Glide.with(this)
                .asBitmap().load(urlString)
                .placeholder(cir)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        image_error.setAnimation("error.json")
                        image_error.playAnimation()
                        image_error.loop(true)
                        image_text.visibility = View.VISIBLE
                        return true
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        image.setImageBitmap(resource)
                        return true
                    }

                }).submit()
        }
        else if (imageValue.equals("uri")){
            Glide.with(this)
                .asBitmap().load(uri)
                .placeholder(cir)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        image_error.setAnimation("error.json")
                        image_error.playAnimation()
                        image_error.loop(true)
                        image_text.visibility = View.VISIBLE
                        return true
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        image.setImageBitmap(resource)
                        return true
                    }

                }).submit()
        }
        else{
            Glide.with(this)
                .asBitmap().load(file)
                .placeholder(cir)
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        image_error.setAnimation("error.json")
                        image_error.playAnimation()
                        image_error.loop(true)
                        image_text.visibility = View.VISIBLE
                        return true
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        image.setImageBitmap(resource)
                        return true
                    }

                }).submit()
        }

    }

    companion object {

        private const val ARG_URL = "arg_url"
        private const val ARG_URI = "arg_uri"
        private const val ARG_FILE = "arg_file"
        private const val ARG_LIST_URL = "arg_list_url"
        private var imageValue: String? = null

        fun getUrl(context: Context, url: String): Intent {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putExtra(ARG_URL, url)
            imageValue = "url"
            return intent
        }

        fun getUri(context: Context, uri: Uri): Intent {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putExtra(ARG_URI, uri)
            imageValue = "uri"
            return intent
        }

        fun getFile(context: Context, file: File): Intent {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putExtra(ARG_FILE, file)
            imageValue = "file"
            return intent
        }

        fun getInstance(context: Context, listOfUrl: ArrayList<String>): Intent {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putStringArrayListExtra(ARG_LIST_URL, listOfUrl)
            return intent
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onZoom(status: Boolean) {
        if (status) {
            center_bg.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            close_btn?.hide()
        } else {
            center_bg.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent))
            close_btn?.show()
        }
    }

    override fun close(status: Boolean) {
        if (status) {
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }

    private fun permission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                forcedPermission("To access the functionality, this permission is required.", this).show()

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST_READ_STORAGE
                )

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            loadImage()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    loadImage()
                }
                else if (Build.VERSION.SDK_INT >= 23 && !shouldShowRequestPermissionRationale(permissions[0])) {
                    finish()
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }
                else {
                 finish()
                }
                return
            }
        }
    }

    private fun forcedPermission(message: String, context: Context): AlertDialog.Builder {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Permission Required").setMessage(message)
        alertDialog.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_READ_STORAGE
            )
        })
        return alertDialog
    }
}
