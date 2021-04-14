package com.navyck.randompick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_pick.setOnClickListener() {
            val candidate1 = edit_input1.text.toString()
            val candidate2 = edit_input2.text.toString()
            val candidate3 = edit_input3.text.toString()
            val candidate4 = edit_input4.text.toString()
            val candidate5 = edit_input5.text.toString()
            val candidates : Array<String> = arrayOf(candidate1, candidate2, candidate3, candidate4, candidate5)

            val random = Random()
            val num = random.nextInt(5)

            text_result.text = candidates[num]
            if (candidates[num] == "") {
                text_result.text = "후보를 입력해주세요!"
            }
            val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            text_result.startAnimation(animationFadeIn)
        }

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )



    }
}