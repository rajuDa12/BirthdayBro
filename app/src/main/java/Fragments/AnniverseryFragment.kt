package Fragments

import Adapter.AnniverserAdapter
import DataClasees.Information
import DataClasees.Merriage
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.teamokcodex.ttodo.R
import kotlinx.android.synthetic.main.fragment_birthday.view.*

class AnniverseryFragment : Fragment() {


    var firedatabase : FirebaseDatabase? = null
    var list : ArrayList<Merriage> ? = null
    var AnniRef : DatabaseReference? = null
    var mRecyclerView : RecyclerView? =null
    var anniverserAdapter: AnniverserAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view:View= inflater.inflate(R.layout.fragment_anniversery, container, false)

        view.hometoolbar_id.inflateMenu(R.menu.menuitem)




        AnniRef=FirebaseDatabase.getInstance().getReference("Anniversery")
        mRecyclerView = view?.findViewById(R.id.Anniverseryrecycle)
        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView?.layoutManager = LinearLayoutManager(context)

        list = ArrayList()


        mRecyclerView?.adapter = anniverserAdapter

        AnniRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val upload = dataSnapshot.getValue(Information::class.java)!!
                    (list as java.util.ArrayList<Information>).add(upload)
                }
                anniverserAdapter = context?.let {
                    AnniverserAdapter(
                        it,
                        list as ArrayList<Merriage>
                    )
                }
                mRecyclerView?.adapter = anniverserAdapter
                anniverserAdapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        AnniRef?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError)
            {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {

                    for (h in p0.children) {
                        val bal = h.getValue(Merriage::class.java)


                        list?.add(bal!!)



                    }
//                        val adapter = InformationAdapter(context!!, list = ArrayList<Information>())
//                        mRecyclerView?.adapter = informationAdapter
                    anniverserAdapter?.notifyDataSetChanged()
                }
            }
        })


        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        MenuInflater(context).inflate(R.menu.menuitem,menu)

        super.onCreateOptionsMenu(menu, inflater)

    }
}
