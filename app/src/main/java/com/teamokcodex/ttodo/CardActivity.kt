package com.teamokcodex.ttodo

import Adapter.CardAdapter
import Adapter.InformationAdapter
import DataClasees.Cards
import DataClasees.Information
import DataClasees.Merriage
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.MimeTypeMap
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_card.*
import kotlinx.android.synthetic.main.carditem.*

class CardActivity : AppCompatActivity() {
    private var gridView:GridView?=null
    private val IMAGE_REQUEST = 1
    private var ImageUri: Uri? = null
    lateinit var Image: ImageView

    private var mStorageRef: StorageReference? = null
    private var AnniRef: DatabaseReference? = null

    var firedatabase : FirebaseDatabase? = null
    var list : ArrayList<Cards> ? = null
    var Ref : DatabaseReference? = null
    var mRecyclerView : RecyclerView? =null
    var cardAdapter: CardAdapter?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        mStorageRef = FirebaseStorage.getInstance().reference.child("Cards");
        AnniRef = FirebaseDatabase.getInstance().getReference("Cards")

        Image = findViewById(R.id.cardloadid)

        cardsave.setOnClickListener {
            SAVEDATA()
        }


        crdaddid.setOnClickListener {
            openFile()

        }

        Ref=FirebaseDatabase.getInstance().getReference("Cards")
        mRecyclerView = findViewById(R.id.cardrecycler_id)
        mRecyclerView?.setHasFixedSize(true)
        mRecyclerView?.layoutManager = LinearLayoutManager(this)


        list = ArrayList()
        mRecyclerView?.adapter = cardAdapter
        Ref!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val upload = dataSnapshot.getValue(Cards::class.java)!!
                    (list as java.util.ArrayList<Cards>).add(upload)
                }
                cardAdapter = this@CardActivity?.let {
                    CardAdapter(
                        it,
                        list as ArrayList<Cards>
                    )
                }
                mRecyclerView?.adapter = cardAdapter
                cardAdapter!!.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {}
        })


    }

    private fun SAVEDATA()
    {
        val ref = mStorageRef!!.child(
            System.currentTimeMillis().toString() + "." + getfileExtension(ImageUri)
        )

        ref.putFile(ImageUri!!)
            .addOnSuccessListener { taskSnapshot ->
                val uriTask =
                    taskSnapshot.storage.downloadUrl
                while (!uriTask.isSuccessful);
                val downloadurl = uriTask.result

                Toast.makeText(this@CardActivity, " card upload Success", Toast.LENGTH_SHORT).show()
                val upload = Cards(downloadurl.toString())
                val UploadId: String? = AnniRef?.push()?.getKey()

                if (UploadId != null) {
                    AnniRef?.child(UploadId)?.setValue(upload)
                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    this@CardActivity,
                    "Failedd.....",
                    Toast.LENGTH_SHORT
                ).show()
            }

    }

    private fun openFile()
    {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, IMAGE_REQUEST)
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null);
        run {
            ImageUri = data!!.data
            Picasso.get().load(ImageUri).into(Image)
        }
    }

    fun getfileExtension(imageUri: Uri?): String? {
        val contentResolver = contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri!!))
    }












}
