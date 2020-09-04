package Adapter

import DataClasees.Cards
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamokcodex.ttodo.R
import de.hdodenhof.circleimageview.CircleImageView

class CardAdapter(val context: Context, val cardlist:ArrayList<Cards>): RecyclerView.Adapter<CardAdapter.dataViewHolder>()
{

    inner class dataViewHolder(view: View): RecyclerView.ViewHolder(view!!)
    {

        val Image: CircleImageView =view.findViewById(R.id.carditemid)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dataViewHolder
    {
        val view = LayoutInflater.from(context).inflate(R.layout.carditem, parent, false)
        return dataViewHolder(view)
    }


    override fun getItemCount(): Int
    {
        return cardlist.size

    }

    override fun onBindViewHolder(holder: dataViewHolder, position: Int)
    {

        val user: Cards =cardlist[position]

        Picasso.get().load(user.getImageurl()).into(holder.Image)

    }

}

