import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_tdm.Entities.CategorieRecyclerV
import com.example.projet_tdm.R

class CategorieAdapter(val context: Context, var items: List<CategorieRecyclerV>?) :
    RecyclerView.Adapter<CategorieAdapter.MyViewHolder>() {
    var row_index: Int = -1

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var img = view.findViewById<ImageView>(R.id.image) as ImageView
        var text = view.findViewById<TextView>(R.id.title) as TextView
        var linearLayout = view.findViewById<LinearLayout>(R.id.categorieElt) as LinearLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.categorie_layout, parent, false)
        )
    }

    override fun getItemCount() = items!!.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text.text = items!!.get(position).text.toString()
        holder.img.setImageResource(items!!.get(position).image)
        holder.linearLayout.setOnClickListener {
            row_index = position
            notifyDataSetChanged()
        }
        if (row_index == position) {
            holder.linearLayout.setBackgroundResource(R.drawable.categorie_selected)
        } else {
            holder.linearLayout.setBackgroundResource(R.drawable.categorie_bg)
        }

    }
}


