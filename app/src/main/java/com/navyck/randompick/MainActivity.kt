package com.navyck.randompick

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random



class MainActivity : AppCompatActivity() {
    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var layoutNumber = 0
        val layouts : Array<LinearLayout> = arrayOf(layout_input3, layout_input4, layout_input5)

        btn_add.setOnClickListener() {
            if (layoutNumber == layouts.size) {
                Snackbar.make(linearLayout, "더이상 추가할 수 없습니다.", Snackbar.LENGTH_SHORT).show()
            } else {
                layouts[layoutNumber].visibility = View.VISIBLE
                layoutNumber += 1
            }
        }

        btn_delete.setOnClickListener() {
            val candidate3 = edit_input3.text
            val candidate4 = edit_input4.text
            val candidate5 = edit_input5.text
            val candidates = arrayOf(candidate3, candidate4, candidate5)

            if (layoutNumber == 0) {
                Snackbar.make(linearLayout, "더이상 삭제할 수 없습니다.", Snackbar.LENGTH_SHORT).show()
            } else {
                layouts[layoutNumber - 1].visibility = View.GONE
                layoutNumber -= 1
                candidates[layoutNumber].clear()
            }
        }



        btn_pick.setOnClickListener() {
            val candidates: Array<String> = editCandidate()

            val random = Random()
            var candidateNumber = 0

            if (candidates[0] == "" || candidates[1] == "") {
                text_result.text = "후보를 입력해주세요!"
            } else {
                for (i in 0..layoutNumber + 1) {
                    Log.d("${i+1}후보 : ", candidates[i])
                    if (candidates[i] != "") {
                        candidateNumber += 1
                    }
                }
                val num = random.nextInt(candidateNumber)

                text_result.text = candidates[num]
            }

            val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            text_result.startAnimation(animationFadeIn)
        }





    }

    private fun editCandidate(): Array<String> {
        val candidate1 = edit_input1.text.toString()
        val candidate2 = edit_input2.text.toString()
        val candidate3 = edit_input3.text.toString()
        val candidate4 = edit_input4.text.toString()
        val candidate5 = edit_input5.text.toString()
        return arrayOf(candidate1, candidate2, candidate3, candidate4, candidate5)
    }
}

