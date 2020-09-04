package Adapter

import DataClasees.Merriage
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamokcodex.ttodo.R
import de.hdodenhof.circleimageview.CircleImageView

class AnniverserAdapter(val context: Context, val marriagelist:ArrayList<Merriage>): RecyclerView.Adapter<AnniverserAdapter.ViewHolder>()
{

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view!!)
    {

        val Name: TextView =view.findViewById(R.id.annitxt1id)
        val Date: TextView =view.findViewById(R.id.annitext2id)
        val text1: TextView =view.findViewById(R.id.textt1)
        val text2: TextView =view.findViewById(R.id.text2)

        val Image: CircleImageView =view.findViewById(R.id.anniimageid)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(context).inflate(R.layout.anniverseryitem, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int
    {
        return marriagelist.size

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val value: Merriage =marriagelist[position]

        holder.Name?.setText(value.getAge())
        holder.Date?.setText(value.getBoy())
        holder.text1?.setText(value.getDATE())
        holder.text2?.setText(value.getGirl())

        Picasso.get().load(value.getImageurl()).into(holder.Image)

    }

}
