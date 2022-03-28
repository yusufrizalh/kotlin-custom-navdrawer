package id.inixindo.secondproject

import android.content.DialogInterface
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import kotlinx.android.synthetic.main.activity_material_detail.*

class MaterialDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var extras = intent.extras
        var image = findViewById<ImageView>(R.id.imgDetail)

        edNameDetail.setText(extras!!.getString("KEY_NAME"))
        edDescDetail.setText(extras.getString("KEY_DEPARTEMENT"))
        Picasso.get().load(extras.getString("KEY_IMAGE"))
            .resize(100, 100).centerCrop().into(image)

        btnModify.setOnClickListener {
            // validasi
            var edNameValidate = edNameDetail.validator().nonEmpty("Jangan kosong!")
                .minLength(8, "Minim 8 karakter!")
                .maxLength(24, "Maks 24 karakter!")
                .noNumbers("Hanya huruf!")
                .addErrorCallback { edNameDetail.error = it }
                .check()

            if (edNameValidate) {
                var url = "http://192.168.10.96/myphp/kotlin-backend/update.php"
                var request = object : StringRequest(
                    Method.POST, url,
                    Response.Listener { response ->
                        Toast.makeText(
                            applicationContext,
                            response,
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }, Response.ErrorListener { error ->
                        Toast.makeText(
                            applicationContext,
                            error.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                    override fun getParams(): MutableMap<String, String>? {
                        var params = HashMap<String, String>()
                        params.put("name", edNameDetail.text.toString())
                        params.put("departement", edDescDetail.text.toString())
                        params.put("id", "" + extras.getInt("KEY_ID"))
                        return params
                    }
                }
                Volley.newRequestQueue(applicationContext).add(request)
            }
        }

        btnRemove.setOnClickListener {
            var alertDialog = AlertDialog.Builder(this@MaterialDetailActivity)
            alertDialog.setTitle("Delete Material")
            alertDialog.setMessage("Are you sure want to delete?")
            alertDialog.setIcon(R.drawable.icon_remove_black)
            alertDialog.setCancelable(false)
            alertDialog.setPositiveButton("YES", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    deleteMaterial(extras.getInt("KEY_ID"))
                }

            })
            alertDialog.setNegativeButton("CANCEL", null)
            alertDialog.create()
            alertDialog.show()
        }
    }

    private fun deleteMaterial(id: Int) {
        var url = "http://192.168.10.96/myphp/kotlin-backend/delete.php?id=$id"
        var request = StringRequest(Request.Method.GET, url,
            { response ->
                Toast.makeText(
                    applicationContext,
                    response,
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }, { error ->
                Toast.makeText(
                    applicationContext,
                    error.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            })
        Volley.newRequestQueue(applicationContext).add(request)
    }
}