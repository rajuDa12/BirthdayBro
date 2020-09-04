package Adapter

import DataClasees.Note
import Fragments.BirthdayFragment
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.teamokcodex.ttodo.Adapter.Activity.WishActivity
import com.teamokcodex.ttodo.R

class NoteAdapter(val context: Context, val list:ArrayList<Note>): RecyclerView.Adapter<NoteAdapter.dataViewHolder>()

{
    inner class dataViewHolder(view: View):RecyclerView.ViewHolder(view!!)
    {

        val title: TextView =view.findViewById(R.id.titileid)
        val description: TextView =view.findViewById(R.id.desid)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dataViewHolder
    {
        val view = LayoutInflater.from(context).inflate(R.layout.note, parent, false)
        return dataViewHolder(view)

    }

    override fun getItemCount(): Int {

       return list.size
    }

    override fun onBindViewHolder(holder: dataViewHolder, position: Int)
    {
        val user: Note =list[position]

        holder.title?.text = user.getTitle()
        holder.description?.text = user.getDESCRIPTION()

        holder.itemView.setOnClickListener {



            Toast.makeText(context,"clicked"+position,Toast.LENGTH_SHORT).show()

//            val intent= Intent(context, WishActivity::class.java)
//            intent.putExtra("Name",user.getName())
//            intent.putExtra("Phone", user.getPhone())
//
//            intent.putExtra("Age",user.getAge())
//            intent.putExtra("Gender", user.getGender())
//
//            intent.putExtra("logo",user.getImageurl())
//
//            ContextCompat.startActivity(context, intent, intent.extras)
//
//            (context as FragmentActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, BirthdayFragment()).commit()

        }
    }
}