package com.teamokcodex.ttodo.Adapter.Activity

import DataClasees.Information
import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import com.teamokcodex.ttodo.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_birthday_entry.*
import java.util.*


class BirthdayEntryActivity : AppCompatActivity() {



    lateinit var Name: EditText
    lateinit var Phone: EditText
    lateinit var Age: EditText
    lateinit var Birthday: EditText
    lateinit var Image: CircleImageView
    lateinit var SaveButton: Button
    private val IMAGE_REQUEST = 1
    private var ImageUri: Uri? = null

    var setListener: OnDateSetListener? = null


    private var mStorageRef: StorageReference? = null
    private var Ref: DatabaseReference? = null


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday_entry)

        mStorageRef = FirebaseStorage.getInstance().reference.child("Members");
        Ref = FirebaseDatabase.getInstance().getReference("Members")

        var tool: Toolbar = findViewById(R.id.profiletoolbar)
        setSupportActionBar(tool)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Name = findViewById(R.id.nameid)
        Phone = findViewById(R.id.numberid)
        Age = findViewById(R.id.age_id)
        Birthday = findViewById(R.id.birthdate)
        Image = findViewById(R.id.profileimage)
        SaveButton = findViewById(R.id.datasavebutton_id)


        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]


        birthdate.setOnClickListener {

            val datePickerDialog = DatePickerDialog(
                this@BirthdayEntryActivity,
                android.R.style.ThemeOverlay,
                setListener,
                year,
                month,
                day
            )
            datePickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            datePickerDialog.show()

        }
        val finalTextView: TextView = birthdate
        setListener = OnDateSetListener { view, year, month, dayOfMonth ->
            var month = month
            month = month + 1
            val date = "$day/$month/$year"
            finalTextView.text = date
        }







        datasavebutton_id.setOnClickListener {

            SaveData()

//            upladimage()
        }

        addimageButton_id.setOnClickListener {
            openFile()

        }
    }


    private fun SaveData() {
        val name = Name.text.toString().trim()
        val phone = Phone.text.toString().trim()
        val age: String = Age.text.toString().trim()
        val birthday: String = Birthday.text.toString().trim()

        val ref = mStorageRef!!.child(
            System.currentTimeMillis().toString() + "." + getfileExtension(ImageUri)
        )

        if (name.isEmpty()) {
            Name.error = "Please enter a name"
        }

        if (phone.isEmpty()) {
            Phone.error = "Please enter Number"
        }

        if (age.isEmpty()) {
            Age.error = "Please enter Your Age"
        }

        if (birthday.isEmpty()) {
            Birthday.error = "Please your birtday "
        }


        ref.putFile(ImageUri!!)
            .addOnSuccessListener { taskSnapshot ->
                val uriTask =
                    taskSnapshot.storage.downloadUrl
                while (!uriTask.isSuccessful);
                val downloadurl = uriTask.result
                Toast.makeText(this@BirthdayEntryActivity, "Success", Toast.LENGTH_SHORT).show()
                val upload = Information(name, phone, age, birthday, downloadurl.toString())
                val UploadId: String? = Ref?.push()?.getKey()
                Birthday.setText(" ")
                Age.setText(" ")
                Phone.setText(" ")
                Name.setText(" ")

                if (UploadId != null) {
                    Ref?.child(UploadId)?.setValue(upload)
                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    this@BirthdayEntryActivity,
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