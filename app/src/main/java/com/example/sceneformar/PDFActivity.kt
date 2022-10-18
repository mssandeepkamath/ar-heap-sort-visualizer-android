package com.example.sceneformar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.mindev.mindev_pdfviewer.MindevPDFViewer
import com.mindev.mindev_pdfviewer.PdfScope
import com.mindev.mindev_pdfviewer.PdfViewHolder

class PDFActivity : AppCompatActivity() {
    lateinit var pdf:MindevPDFViewer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfactivity)
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        pdf=findViewById(R.id.pdfViewer)
        val url = "https://firebasestorage.googleapis.com/v0/b/studybear-79c4e.appspot.com/o/DTL%20REPORT%202022.docx%20(3).pdf?alt=media&token=b3ad87e5-2be3-4aff-bc9c-08709427e337"
        pdf.initializePDFDownloader(url, statusListener)
        lifecycle.addObserver(PdfScope())

    }


    override fun onDestroy() {
        pdf.pdfRendererCore?.clear()
        super.onDestroy()
    }

    private val statusListener = object : MindevPDFViewer.MindevViewerStatusListener {
        override fun onStartDownload() {
            Toast.makeText(this@PDFActivity,"Download started, please wait..",Toast.LENGTH_SHORT).show()
        }

        override fun onPageChanged(position: Int, total: Int) {

        }

        override fun onProgressDownload(currentStatus: Int) {

        }

        override fun onSuccessDownLoad(path: String) {
            pdf.fileInit(path)
        }

        override fun onFail(error: Throwable) {
            Toast.makeText(this@PDFActivity,"Sorry, Failed to load report..",Toast.LENGTH_SHORT).show()
        }

        override fun unsupportedDevice() {
            Toast.makeText(this@PDFActivity,"Sorry, This device is unsupported!",Toast.LENGTH_LONG).show()
        }

    }
}