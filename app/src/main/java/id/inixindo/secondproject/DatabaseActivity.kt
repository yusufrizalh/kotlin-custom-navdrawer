package id.inixindo.secondproject

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_database.*
import org.json.JSONArray

class DatabaseActivity : AppCompatActivity() {

    var materials: ArrayList<Material>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        recyclerDb.layoutManager = LinearLayoutManager(applicationContext)
        materials = ArrayList()
    }

    override fun onResume() {
        super.onResume()
        if (materials?.size == 0) {
            getDataFromServer()
        } else {
            materials?.clear()
            getDataFromServer()
        }
    }

    private fun getDataFromServer() {
        // memanggil data dari server
        val url = "http://192.168.10.96/myphp/kotlin-backend/read.php"
        var request = StringRequest(Request.Method.GET, url,
            { response ->
                convertJsonToRecycler(response)
            },
            { error ->
                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.VISIBLE
                recyclerDb.visibility = View.GONE
            }
        )
        Volley.newRequestQueue(applicationContext).add(request)
    }

    fun convertJsonToRecycler(response: String?) {
        progressBar.visibility = View.GONE
        recyclerDb.visibility = View.VISIBLE

        var arrayJson = JSONArray(response)

        for (i in 0..arrayJson.length() - 1) {
            var jsonObject = arrayJson.getJSONObject(i)
            var material = Material(
                jsonObject.getInt("id"),
                jsonObject.getString("name"),
                jsonObject.getString("departement"),
                jsonObject.getString("image")
            )
            materials?.add(material)
        }
        var adapter = MaterialAdapter(materials!!, applicationContext)
        recyclerDb.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.option_add) {
            startActivity(Intent(applicationContext, MaterialAddActivity::class.java))
        }
        return true
    }
}