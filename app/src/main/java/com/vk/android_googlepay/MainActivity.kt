package com.vk.android_googlepay

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    val TEZ_REQUEST_CODE = 123
    private val GOOGLE_TEZ_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val payButton = findViewById(R.id.pay_button) as ImageButton
        payButton.setOnClickListener{
            val uri = Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", "googtravel@axisbank") //your-merchant-vpa@xxx
                .appendQueryParameter("pn", "vk Android")  //your-merchant-name
                .appendQueryParameter("mc", "1234")  //your-merchant-code
                .appendQueryParameter("tr", "123456789")  //your-transaction-ref-id
                .appendQueryParameter("tn", "Happy Coding!!")  //your-transaction-note
                .appendQueryParameter("am", "1")  //your-order-amount [am.toString()] for values from edit text
                .appendQueryParameter("cu", "INR")
                .appendQueryParameter("url", "https://test.merchant.website") //your-transaction-url
                .build();
            val intent =  Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(GOOGLE_TEZ_PACKAGE_NAME);
            startActivityForResult(intent, TEZ_REQUEST_CODE);
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == TEZ_REQUEST_CODE) {
            Log.d("result", data!!.getStringExtra("Status"));
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                // Do something with the contact here (bigger example below)
            }
        }
    }

}
