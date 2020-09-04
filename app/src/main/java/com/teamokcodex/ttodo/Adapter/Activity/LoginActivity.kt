package com.teamokcodex.ttodo.Adapter.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.teamokcodex.ttodo.R
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {

    private var callbacks: OnVerificationStateChangedCallbacks? = null
    private var mResendToken: ForceResendingToken? = null
    private var mAuth: FirebaseAuth? = null
    private var mVerificationId: String? = null
    private var progressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth=FirebaseAuth.getInstance();

        progressDialog=ProgressDialog(this);

        sendcodeid.setOnClickListener {

            val phone:String=phnnmbertextid.text.toString().trim();

            if(TextUtils.isEmpty(phone))
            {
                Toast.makeText(this,"ENTER A NUMBER", Toast.LENGTH_SHORT).show();
            }
            else
            {
                progressDialog!!.setTitle("PHONE VARIFICATION")
                progressDialog!!.setMessage("plaes wait......")
                progressDialog!!.setCanceledOnTouchOutside(false)
                progressDialog!!.show()

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phone,  // Phone number to verify
                    60,  // Timeout duration
                    TimeUnit.SECONDS,  // Unit of timeout
                    this@LoginActivity,  // com.teamokcodex.ttodo.Adapter.Activity (for callback binding)
                    callbacks
                )


            }
        }

        varifycodeid.setOnClickListener {


            startActivity(Intent(this, MainActivity::class.java))

            sendcodeid.setVisibility(View.INVISIBLE)
            phnnmbertextid.setVisibility(View.INVISIBLE)

            val Varificationcode = phnnvarificationtextid.getText().toString().trim { it <= ' ' }

            if (TextUtils.isEmpty(Varificationcode)) {
                Toast.makeText(this@LoginActivity, "Enter code again ", Toast.LENGTH_SHORT).show()
            } else {
                progressDialog!!.setTitle("Code VARIFICATION")
                progressDialog!!.setMessage("plaes wait......")
                progressDialog!!.setCanceledOnTouchOutside(false)
                progressDialog!!.show()
                val credential =
                    PhoneAuthProvider.getCredential(mVerificationId!!, Varificationcode)
                signInWithPhoneAuthCredential(credential)
            }


        }


        callbacks = object : OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@LoginActivity, "please in a valid", Toast.LENGTH_SHORT).show()
                sendcodeid.setVisibility(View.VISIBLE)
                phnnmbertextid.setVisibility(View.VISIBLE)
                phnnvarificationtextid.setVisibility(View.INVISIBLE)
                varifycodeid.setVisibility(View.INVISIBLE)
                progressDialog!!.dismiss()

            }

            override fun onCodeSent(
                verificationId: String,
                token: ForceResendingToken
            ) {
                mVerificationId = verificationId
                mResendToken = token
                Toast.makeText(this@LoginActivity, "Code hase been send ", Toast.LENGTH_SHORT)
                    .show()
                sendcodeid.setVisibility(View.INVISIBLE)
                phnnmbertextid.setVisibility(View.INVISIBLE)
                phnnvarificationtextid.setVisibility(View.VISIBLE)
                varifycodeid.setVisibility(View.VISIBLE)
                progressDialog!!.dismiss()
            }
        }

    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    progressDialog!!.dismiss()
                    Toast.makeText(this@LoginActivity, "LOgged in ", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                }
            }
    }



}

private fun PhoneAuthProvider.verifyPhoneNumber(phone: String, i: Int, seconds: TimeUnit, loginActivity: LoginActivity, callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks?)
{


}
