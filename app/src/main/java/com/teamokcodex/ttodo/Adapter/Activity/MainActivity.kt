package com.teamokcodex.ttodo.Adapter.Activity

import Fragments.AnniverseryFragment
import Fragments.BirthdayFragment
import Fragments.OtherFragment
import Fragments.ProfileFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.teamokcodex.ttodo.R

class
MainActivity : AppCompatActivity() {



    private val OnNavigationItemReselectedListener = BottomNavigationView.OnNavigationItemReselectedListener{ item ->
        when (item.itemId) {
            R.id.navigation_home ->
            {
                MoveToFragment(BirthdayFragment())

                return@OnNavigationItemReselectedListener


            }
            R.id.navigation_favourite ->
            {
                MoveToFragment(AnniverseryFragment())


                return@OnNavigationItemReselectedListener
            }
            R.id.navigation_other ->
            {
                MoveToFragment(OtherFragment())


                return@OnNavigationItemReselectedListener
            }


            R.id.navigation_Profile ->
            {
                MoveToFragment(ProfileFragment())



                return@OnNavigationItemReselectedListener
            }

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navview:BottomNavigationView=findViewById(R.id.nav_view)

        navview.setOnNavigationItemReselectedListener(OnNavigationItemReselectedListener)

        MoveToFragment(BirthdayFragment())

//
//        save_note_main.setOnClickListener {
//            var pop=PopupMenu(this,save_note_main)
//            pop.inflate(R.menu.popup)
//
//            save_note_main.visibility(View.VISIBLE)
//            pop.setOnMenuItemClickListener {
//
//
//                if(it.title.equals("ADD BIRTHDAY"))
//                {
//                    startActivity(Intent(this,BirthdayEntryActivity::class.java))
//                }
//                else if(it.title.equals("ADD ANNIVERSERY"))
//                {
//                    startActivity(Intent(this,AnniverseryEntryActivity::class.java))
//
//                }
//                else if(it.title.equals("ADD NOTES"))
//                {
//                    startActivity(Intent(this,AddnoteActivity::class.java))
//
//                }
//
//                Toast.makeText(this@MainActivity, "Item"+it.title, Toast.LENGTH_SHORT).show()
//                true
//            }
//            pop.show()
//        }


    }



    private fun MoveToFragment(fragment: Fragment)
    {
        val fragmentTrans=supportFragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.fragment_container,fragment)
        fragmentTrans.commit()

    }



}

private operator fun Int.invoke(visible: Int) {

}





