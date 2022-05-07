package com.authoritydmc.cardKart.utility

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toDrawable
import com.authoritydmc.cardKart.R
import com.authoritydmc.cardKart.utility.UTILS.Companion.TAG
import java.lang.StringBuilder


class  CardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private  var txtView:TextView

    private var frontLogo:ImageView
    private var cardBG:ConstraintLayout

    private var str:String="Empty right now";

    init {
     inflate(context, R.layout.cardviewnew, this)
        txtView=rootView.findViewById(R.id.cardNumber)

        frontLogo=rootView.findViewById(R.id.cardFrontLogo)
        cardBG=rootView.findViewById(R.id.cardFront)

       if (attrs!=null)
       {
           val a=context.obtainStyledAttributes(attrs,R.styleable.CardView)
           val txtfrm=a.getString(R.styleable.CardView_TEXT)

           txtView.setText(txtfrm)
           val logo_d:Int=a.getResourceId(R.styleable.CardView_LOGO,-1)
           Log.d(TAG, "CARD LOGO $logo_d")
           if (logo_d!=-1)
           frontLogo.setImageResource(logo_d)

           val card_bg_d:Int=a.getResourceId(R.styleable.CardView_card_bg,-1)
           Log.d(TAG, "CARD LOGO $card_bg_d")
           if (card_bg_d!=-1)
              cardBG.background=card_bg_d.toDrawable()



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
        frontLogo.setOnClickListener{
            val randomNum:StringBuilder= StringBuilder();
          for (i in 1..16 )
          {
              randomNum.append((0..9).random())
              if (i%4==0&& i!=16)
                  randomNum.append("\t")
          }


            setText(randomNum.toString());
//            checkSample()
        // checkforupdate()
        }
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