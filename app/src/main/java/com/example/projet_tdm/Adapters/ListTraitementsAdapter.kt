import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.example.projet_tdm.R
import com.example.projet_tdm.ViewModels.TraitementViewModel
import java.lang.String.format
import java.text.SimpleDateFormat

class ListTraitementsAdapter(
    val context: Context,
    var data: List<TraitementViewModel>?,
    val vm: TraitementViewModel
) :

    RecyclerView.Adapter<ListTraitementsAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date_debut = view.findViewById<TextView>(R.id.debut) as TextView
        val nomMed = view.findViewById<TextView>(R.id.med) as TextView
        val date_fin = view.findViewById<TextView>(R.id.fin) as TextView
        val specialite = view.findViewById<TextView>(R.id.spe) as TextView
        val treatView = view.findViewById<ConstraintLayout>(R.id.traitement) as ConstraintLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.traitement_layout, parent, false)
        )
    }

    override fun getItemCount() = data!!.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val form = SimpleDateFormat("yyyy-mm-dd")
        val date1 :String=form.format(data!!.get(position).date_debut)
        val date2 :String = form.format(data!!.get(position).date_fin)
        holder.date_debut.text = date1
        holder.date_fin.text = date2
        holder.specialite.text = data!!.get(position).nomSpecialite
        holder.nomMed.text=data!!.get(position).nom
        holder.treatView.setOnClickListener(){
            MaterialDialog(this.context).show {
                title(text = "Traitement Details")
                message(text =data!!.get(position).description)
            }
        }

    }
}



