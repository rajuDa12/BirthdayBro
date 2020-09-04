package Adapter

import DataClasees.Information
import Fragments.BirthdayFragment
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamokcodex.ttodo.R
import com.teamokcodex.ttodo.Adapter.Activity.WishActivity
import de.hdodenhof.circleimageview.CircleImageView

class InformationAdapter(val context: Context, val list:ArrayList<Information>):RecyclerView.Adapter<InformationAdapter.dataViewHolder>()
{

    inner class dataViewHolder(view:View):RecyclerView.ViewHolder(view!!)
    {

        val name:TextView=view.findViewById(R.id.getnameid)
        val phone:TextView=view.findViewById(R.id.getnumberid)
        val Age:TextView=view.findViewById(R.id.getAgeid)
        val Gender:TextView=view.findViewById(R.id.getgenderid)
        val Image:CircleImageView=view.findViewById(R.id.imageid)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dataViewHolder
    {
        val view = LayoutInflater.from(context).inflate(R.layout.birthdayitem, parent, false)
        return dataViewHolder(view)
    }


    override fun getItemCount(): Int
    {
        return list.size

    }

    override fun onBindViewHolder(holder: dataViewHolder, position: Int)
    {
        val user: Information =list[position]

        holder.name?.setText(user.getName())
        holder.phone?.setText(user.getPhone())
        holder.Age?.setText(user.getAge())
        holder.Gender?.setText(user.getGender())

        Picasso.get().load(user.getImageurl()).into(holder.Image)

        holder.itemView.setOnClickListener {



            val intent= Intent(context, WishActivity::class.java)
            intent.putExtra("Name",user.getName())
            intent.putExtra("Phone", user.getPhone())

            intent.putExtra("Age",user.getAge())
            intent.putExtra("Gender", user.getGender())

            intent.putExtra("logo",user.getImageurl())

            startActivity(context,intent,intent.extras)

            (context as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BirthdayFragment()).commit()

        }





    }
}
