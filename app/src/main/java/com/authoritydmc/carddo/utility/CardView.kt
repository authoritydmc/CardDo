package com.authoritydmc.carddo.utility

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.authoritydmc.carddo.R
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
          Toast.makeText(context,"Card Number Copied to ClipBoard!!",Toast.LENGTH_LONG).show()
          var clipboard=context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
         var clip=ClipData.newPlainText("cardnumber"+getText().trim().toString(),getText())
          clipboard.setPrimaryClip(clip)

      };
        btnView.setOnClickListener{
            val randomNum:StringBuilder= StringBuilder();
          for (i in 1..16 )
          {
              randomNum.append((0..9).random())
              if (i%4==0)
                  randomNum.append("\t")
          }


            setText(randomNum.toString());
        }
    }

    fun getText():String
{
    return txtView.text.trim().toString()
}

    fun setText(txt:String)
    {
        txtView.setText(txt);
    }
}