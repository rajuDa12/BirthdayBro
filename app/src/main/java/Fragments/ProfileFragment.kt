package Fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.teamokcodex.ttodo.R
import kotlinx.android.synthetic.main.fragment_birthday.view.*


class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
          val view= inflater.inflate(R.layout.fragment_profle, container, false)

        return  view

    }



}
