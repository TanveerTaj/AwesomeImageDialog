package `in`.loansimple.picdialog

import android.app.Activity
import android.content.Context
import android.net.Uri
import java.io.File

class PicDialog private constructor(
    private val context: Context,
    private val uri: Uri?,
    private val file: File?,
    private val url: String?,
    private val outSideDismiss: Boolean) {

    fun show() {
        if (url == null && uri == null && file == null) {
            throw PicDialogException()
        }
        else if (url != null){
            context.startActivity(
                ImageActivity.getUrl(context = context, url = url))

            val activity: Activity = context as Activity
            activity.overridePendingTransition(
                R.anim.slide_in_up,
                R.anim.slide_out_up)
        }
        else if (uri != null){
            context.startActivity(
                ImageActivity.getUri(context = context, uri = uri))

            val activity: Activity = context as Activity
            activity.overridePendingTransition(
                R.anim.slide_in_up,
                R.anim.slide_out_up)
        }
        else if (file != null){
            context.startActivity(
                ImageActivity.getFile(context = context, file = file))

            val activity: Activity = context as Activity
            activity.overridePendingTransition(
                R.anim.slide_in_up,
                R.anim.slide_out_up)
        }
    }

    open class Builder(private val context: Context) {

        var outSideDismiss: Boolean = false

        fun setOutSideDismiss(isDismiss: Boolean): Builder {
            outSideDismiss = isDismiss
            return this
        }

        fun setUri(uri: Uri): Helper {
            return Helper(context, outSideDismiss = outSideDismiss, uri = uri)
        }

        fun setFile(file: File): Helper {
            return Helper(context, outSideDismiss = outSideDismiss, file = file)
        }

        fun setUrl(url: String): Helper {
            return Helper(context, outSideDismiss = outSideDismiss, url = url)
        }

    }

    class Helper(
        private val context: Context,
        var outSideDismiss: Boolean = false,
        var uri: Uri? = null,
        var url: String? = null,
        var file: File? = null
    ) {

        fun build() = PicDialog(
            context = context,
            uri = uri,
            file = file,
            url = url,
            outSideDismiss = outSideDismiss
        )


    }
}

