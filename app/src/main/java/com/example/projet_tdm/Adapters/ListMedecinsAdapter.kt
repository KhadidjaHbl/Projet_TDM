import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projet_tdm.R
import com.example.projet_tdm.url
import com.example.tp3.MedecinViewModel


class ListMedecinsAdapter(
    val context: Context,
    var data: List<MedecinViewModel>?,
    val vm: MedecinViewModel
) :

    RecyclerView.Adapter<ListMedecinsAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nom = view.findViewById<TextView>(R.id.nom) as TextView
        val icone = view.findViewById<ImageView>(R.id.icone) as ImageView
        val prenom = view.findViewById<TextView>(R.id.prenom) as TextView
        val num = view.findViewById<TextView>(R.id.num) as TextView
        val specialite = view.findViewById<TextView>(R.id.specialite) as TextView
        val photo = view.findViewById<ImageView>(R.id.photo) as ImageView
        val medView = view.findViewById<ConstraintLayout>(R.id.medecin) as ConstraintLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.medecin_layout, parent, false)
        )
    }

    override fun getItemCount() = data!!.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nom.text = data!!.get(position).nom
        holder.prenom.text = data!!.get(position).prenom
        holder.num.text = data!!.get(position).num_tel
        holder.specialite.text = data!!.get(position).nomSpecialite
        Glide.with(context).load(url + "/" + data!!.get(position).photo).into(holder.photo)
        holder.num.setOnClickListener {
            val numTel = data!!.get(position).num_tel
            val numero = Uri.parse("tel:$numTel")
            val intent = Intent(Intent.ACTION_DIAL, numero)
            context.startActivity(intent)
        }

        holder.icone.setOnClickListener {
            val latitude = data!!.get(position).latitude
            val longitude = data!!.get(position).longitude
            val geoLocation = Uri.parse("http://maps.google.com/maps?daddr=" + latitude + "," + longitude)
            val intent = Intent(Intent.ACTION_VIEW, geoLocation)
            context.startActivity(intent)
        }

        holder.medView.setOnClickListener {
            vm.idMedecin = data!!.get(position).idMedecin
            vm.nom = data!!.get(position).nom
            vm.prenom = data!!.get(position).prenom
            vm.num_tel = data!!.get(position).num_tel
            vm.nomSpecialite = data!!.get(position).nomSpecialite
            vm.anneesExp = data!!.get(position).anneesExp
            vm.photo = data!!.get(position).photo
            holder.medView.findNavController().navigate(R.id.action_listFragment_to_detailFragment)
        }
    }
}



