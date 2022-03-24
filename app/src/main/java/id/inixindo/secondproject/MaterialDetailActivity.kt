package id.inixindo.secondproject

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
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
    }
}