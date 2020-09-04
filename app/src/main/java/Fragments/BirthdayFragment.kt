package Fragments

import com.teamokcodex.ttodo.Adapter.Activity.AddnoteActivity
import com.teamokcodex.ttodo.Adapter.Activity.AnniverseryEntryActivity
import com.teamokcodex.ttodo.Adapter.Activity.BirthdayEntryActivity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import DataClasees.Information
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import Adapter.InformationAdapter
import com.teamokcodex.ttodo.R
import kotlinx.android.synthetic.main.fragment_birthday.*


class BirthdayFragment : Fragment() {


    var firedatabase : FirebaseDatabase? = null
    var list : ArrayList<Information> ? = null
    var Ref : DatabaseReference? = null
    var mRecyclerView : RecyclerView? =null
    var informationAdapter: InformationAdapter?=null
    var image:ImageView?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_birthday, container, false)
        image=view?.findViewById(R.id.options_view)

        image?.setOnClickListener {

            var pop= context?.let { it1 -> PopupMenu(it1,options_view) }
            pop?.inflate(R.menu.popup)
            pop?.setOnMenuItemClickListener {

                when (it.title) {
                    "ADD BIRTHDAY" -> {
                        startActivity(Intent(context,
                            BirthdayEntryActivity::class.java))
                    }
                    "ADD ANNIVERSERY" -> {
                        startActivity(Intent(context, AnniverseryEntryActivity::class.java))

                    }
                    "ADD NOTES" -> {
                        startActivity(Intent(context, AddnoteActivity::class.java))

                    }
                }
                true
            }
            pop?.show()
        }

        Ref=FirebaseDatabase.getInstance().getReference("Members")
        mRecyclerView = view?.findViewById(R.id.homerecycler_id)
        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView?.layoutManager = LinearLayoutManager(context)

        list = ArrayList()
        mRecyclerView?.adapter = informationAdapter
        Ref!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val upload = dataSnapshot.getValue(Information::class.java)!!
                    (list as java.util.ArrayList<Information>).add(upload)
                }
                informationAdapter = context?.let {
                    InformationAdapter(
                        it,
                        list as ArrayList<Information>
                    )
                }
                mRecyclerView?.adapter = informationAdapter
                informationAdapter!!.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        return  view

    }


}





