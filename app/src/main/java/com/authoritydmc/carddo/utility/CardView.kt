package com.authoritydmc.carddo.utility

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.authoritydmc.carddo.R
import com.authoritydmc.carddo.api.retrofitClient
import com.authoritydmc.carddo.models.SAMPLEPOKO
import com.authoritydmc.carddo.models.UpdatePOKO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder


class  CardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private  var txtView:TextView
    private var btnView:Button
private var str:String="Empty right now";

    init {
     inflate(context, R.layout.cardview, this)
        txtView=rootView.findViewById(R.id.codeView_textView)
        btnView=rootView.findViewById(R.id.cardView_btn)
        btnView.setText("Randomize Card number")
       if (attrs!=null)
       {
           val a=context.obtainStyledAttributes(attrs,R.styleable.CardView)
           val txtfrm=a.getString(R.styleable.CardView_TEXT)

           txtView.setText(txtfrm
           )

       }

        InitMain()

    }

    private fun InitMain() {
      txtView.setOnClickListener {
          Toast.makeText(context,"Card Number ${getText()} Copied to ClipBoard!!",Toast.LENGTH_LONG).show()
          var clipboard=context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
         var clip=ClipData.newPlainText("cardnumber",getText())
          clipboard.setPrimaryClip(clip)

      };
        btnView.setOnClickListener{
            val randomNum:StringBuilder= StringBuilder();
          for (i in 1..16 )
          {
              randomNum.append((0..9).random())
              if (i%4==0&& i!=16)
                  randomNum.append("\t")
          }


            setText(randomNum.toString());
//            checkSample()
         checkforupdate()
        }
    }

    private fun checkSample() {
       retrofitClient.instance.SampleAPI().enqueue(object : Callback<SAMPLEPOKO?> {
           override fun onResponse(call: Call<SAMPLEPOKO?>, response: Response<SAMPLEPOKO?>) {
               Toast.makeText(context,response.body().toString(),Toast.LENGTH_LONG
               ).show()
               Log.d("RAJ", "onResponse: found response ${response.body().toString()}")
           }

           override fun onFailure(call: Call<SAMPLEPOKO?>, t: Throwable) {
               Toast.makeText(context,"Failed to get JSON",Toast.LENGTH_LONG
               ).show()
               Log.d("RAJ", "onResponse: found response ${t.message}")
           }
       })
    }

    private fun checkforupdate() {
        Log.d("RAJ", "onResponse: checking")

        retrofitClient.instance.checkUpdate().enqueue(object : Callback<UpdatePOKO?> {
            override fun onResponse(call: Call<UpdatePOKO?>, response: Response<UpdatePOKO?>) {
                Toast.makeText(context,response.body().toString(),Toast.LENGTH_LONG
                ).show()
                Log.d("RAJ", "onResponse: found response ${response.body().toString()}")
            }

            override fun onFailure(call: Call<UpdatePOKO?>, t: Throwable) {
                Toast.makeText(context,"Failed to get JSON",Toast.LENGTH_LONG
                ).show()
                Log.d("RAJ", "onResponse: found response ${t.message}")

            }
        })
    }

    fun getText():String
{
    return txtView.text.replace("""[\s,.]""".toRegex(),"").trim().toString()
}

    fun setText(txt:String)
    {
        txtView.setText(txt);
    }
}