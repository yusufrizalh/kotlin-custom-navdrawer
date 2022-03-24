package id.inixindo.secondproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_material_add.*

class MaterialAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_add)

        btnSave.setOnClickListener {
            postDataToServer()
        }
    }

    private fun postDataToServer() {
        var url = "http://192.168.10.96/myphp/kotlin-backend/insert.php"
        var request = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
                finish()
            },
            Response.ErrorListener { error ->
                Toast.makeText(
                    applicationContext,
                    error.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                // convert string to json
                var params = HashMap<String, String>()
                params.put("name", edNameMaterial.text.toString())
                params.put("departement", edDescMaterial.text.toString())
                return params
            }
        }
        Volley.newRequestQueue(applicationContext).add(request)
    }
}