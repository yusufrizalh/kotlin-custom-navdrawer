package id.inixindo.secondproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MaterialAdapter(var materials: ArrayList<Material>, var context: Context) :
    RecyclerView.Adapter<MaterialAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // inisialisasi komponen per item
        var tvMat = view.findViewById<TextView>(R.id.tvMat)
        var tvDept = view.findViewById<TextView>(R.id.tvDept)
        var ivImage = view.findViewById<ImageView>(R.id.ivImage)
        var cardView = view.findViewById<CardView>(R.id.cardView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MaterialAdapter.MyViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_material, parent, false)
        var viewHolder = MyViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: MaterialAdapter.MyViewHolder, position: Int) {
        var material = materials.get(position)

        holder.tvMat.setText(material.name)
        holder.tvDept.setText(material.departement)
        var imageUrl = "http://192.168.10.96/myphp/kotlin-backend/images/${material.image}"

        Picasso.get().load(imageUrl).resize(100, 100).centerCrop().into(holder.ivImage)

        holder.cardView.setOnClickListener(View.OnClickListener {
            // salah satu cardview dipilih
            var intent = Intent(context, MaterialDetailActivity::class.java)
            intent.putExtra("KEY_NAME", material.name)
            intent.putExtra("KEY_DEPARTEMENT", material.departement)
            intent.putExtra("KEY_ID", material.id)
            intent.putExtra("KEY_IMAGE", imageUrl)
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return materials.size
    }

}
