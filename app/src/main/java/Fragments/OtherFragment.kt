package Fragments

import Adapter.InformationAdapter
import DataClasees.Note
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import Adapter.NoteAdapter
import DataClasees.Information
import com.teamokcodex.ttodo.R
import kotlinx.android.synthetic.main.fragment_birthday.view.*

class OtherFragment : Fragment() {


    var firedatabase : FirebaseDatabase? = null
    var list : ArrayList<Note> ? = null
    var NoteRef : DatabaseReference? = null
    var noteRecyclerView : RecyclerView? =null
    var noteAdapter: NoteAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val view:View=  inflater.inflate(R.layout.fragment_other, container, false)



        view.hometoolbar_id.inflateMenu(R.menu.menuitem)


        NoteRef=FirebaseDatabase.getInstance().getReference("Notes")
        noteRecyclerView = view?.findViewById(R.id.notesrecycler)
        noteRecyclerView?.setHasFixedSize(true)
        noteRecyclerView?.layoutManager = LinearLayoutManager(context)

        list = ArrayList()

        noteRecyclerView?.adapter = noteAdapter


        NoteRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val upload = dataSnapshot.getValue(Note::class.java)!!
                    (list as java.util.ArrayList<Note>).add(upload)
                }
                noteAdapter = context?.let {
                    NoteAdapter(
                        it,
                        list as ArrayList<Note>
                    )

                }
                noteRecyclerView?.adapter = noteAdapter
                noteAdapter!!.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {}
        })

//        NoteRef?.addValueEventListener(object : ValueEventListener {
//            override fun onCancelled(p0: DatabaseError)
//            {
//
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                if (p0.exists()) {
//
//                    for (h in p0.children) {
//                        val bal = h.getValue(Note::class.java)
//                        list?.add(bal!!)
//
//                    }
//                    noteAdapter?.notifyDataSetChanged()
//                }
//            }
//        })

        return view

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        MenuInflater(context).inflate(R.menu.menuitem,menu)

        super.onCreateOptionsMenu(menu, inflater)

    }
}
