package com.teamokcodex.ttodo.Adapter.Activity

import DataClasees.Information
import DataClasees.Merriage
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import com.teamokcodex.ttodo.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_anniversery_entry.*
import kotlinx.android.synthetic.main.activity_birthday_entry.*

class AnniverseryEntryActivity : AppCompatActivity() {


    lateinit var Girl: EditText
    lateinit var Date: EditText
    lateinit var Age: EditText
    lateinit var Boy: EditText
    lateinit var Image: CircleImageView
    lateinit var SaveButton: Button
    private val IMAGE_REQUEST = 1
    private var ImageUri: Uri? = null

    var setListener: DatePickerDialog.OnDateSetListener? = null


    private var mStorageRef: StorageReference? = null
    private var AnniRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anniversery_entry)


        var tool: Toolbar = findViewById(R.id.profiletoolbar)
        setSupportActionBar(tool)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mStorageRef = FirebaseStorage.getInstance().reference.child("Anniversery");
        AnniRef = FirebaseDatabase.getInstance().getReference("Anniversery")

        Age = findViewById(R.id.textt1)
        Boy = findViewById(R.id.textt2)
        Date = findViewById(R.id.textt3)
        Girl = findViewById(R.id.textt4)

        Image = findViewById(R.id.anniprofileimageid)
        SaveButton = findViewById(R.id.datasave)




        datasave.setOnClickListener {

            SaveData()

        }

        anniprofileimageid.setOnClickListener {
            openFile()

        }

    }


    private fun SaveData() {
        val boy = Boy.text.toString().trim()
        val girl = Girl.text.toString().trim()
        val age: String = Age.text.toString().trim()
        val date: String = Date.text.toString().trim()

        val ref = mStorageRef!!.child(
            System.currentTimeMillis().toString() + "." + getfileExtension(ImageUri)
        )

        if (boy.isEmpty()) {
            Boy.error = "Please enter a name"
        }

        if (girl.isEmpty()) {
            Girl.error = "Please enter Number"
        }

        if (age.isEmpty()) {
            Age.error = "Please enter Your Age"
        }

        if (date.isEmpty()) {
            Date.error = "Please your birtday "
        }


        ref.putFile(ImageUri!!)
            .addOnSuccessListener { taskSnapshot ->
                val uriTask =
                    taskSnapshot.storage.downloadUrl
                while (!uriTask.isSuccessful);
                val downloadurl = uriTask.result
                Toast.makeText(this@AnniverseryEntryActivity, "Success", Toast.LENGTH_SHORT).show()
                val upload = Merriage(age,boy, date,girl,downloadurl.toString())
                val UploadId: String? = AnniRef?.push()?.getKey()
                Age.setText(" ")
                Boy.setText(" ")
                Date.setText(" ")
                Girl.setText(" ")

                if (UploadId != null) {
                    AnniRef?.child(UploadId)?.setValue(upload)
                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    this@AnniverseryEntryActivity,
                    "Failedd.....",
                    Toast.LENGTH_SHORT
                ).show()
            }


    }


    private fun openFile() {
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
