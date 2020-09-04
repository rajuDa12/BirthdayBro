package com.teamokcodex.ttodo.Adapter.Activity

import DataClasees.Information
import DataClasees.Note
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import com.teamokcodex.ttodo.R
import kotlinx.android.synthetic.main.activity_addnote.*
import java.util.*

class AddnoteActivity : AppCompatActivity() {


    lateinit var Title: EditText
    lateinit var Des: EditText
    private var NoteRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnote)


        var tool: Toolbar = findViewById(R.id.profiletoolbar)
        setSupportActionBar(tool)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Title = findViewById(R.id.notetitleid)
        Des = findViewById(R.id.noteid)


        notesaveid.setOnClickListener {
            var title: String? = Title?.text.toString().trim()
            var DESCRIPTION: String? = Des?.text.toString().trim()

            if (title?.isEmpty()!!) {
                Title.error = "please provide note title..."
            }
            if (DESCRIPTION?.isEmpty()!!) {
                Des.error = "please provide note Body..."

            }



            val  NoteRef = FirebaseDatabase.getInstance().getReference("Notes")
            val upload = Note(title ,DESCRIPTION)
            val UploadId:String? = NoteRef.push().getKey()

            Title.setText(" ")
            Des.setText(" ")

            if (UploadId != null) {
                NoteRef.child(UploadId).setValue(upload)
            }
        }
    }
}

