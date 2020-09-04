package com.teamokcodex.ttodo.Adapter.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.teamokcodex.ttodo.CardActivity
import com.teamokcodex.ttodo.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_wish.*

class WishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wish)


        Name?.text=intent.getStringExtra("Name")
        Phone?.text=intent.getStringExtra("Phone")
        Age?.text=intent.getStringExtra("Age")
        gender?.text=intent.getStringExtra("Gender")
        image?.setImageURI(intent.getStringExtra("logo"))


        var tool: Toolbar =findViewById(R.id.profiletoolbar)
        setSupportActionBar(tool)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)




        datasavebutton_id.setOnClickListener {


            Toast.makeText(this@WishActivity, "IS CALLED......", Toast.LENGTH_SHORT).show()
        }

        Cardsavebutton_id.setOnClickListener {
            startActivity(Intent(this, CardActivity::class.java))
        }




    }
}

private fun CircleImageView.setImageURI(stringExtra: String?) {

}
