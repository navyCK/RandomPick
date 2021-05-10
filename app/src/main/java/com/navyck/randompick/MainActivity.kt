package com.navyck.randompick

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.annotation.Dimension
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random



class MainActivity : AppCompatActivity() {
    @SuppressLint("ShowToast", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var language = true
        var layoutNumber = 0
        val layouts : Array<LinearLayout> = arrayOf(layout_input3, layout_input4, layout_input5, layout_input6,
                                                    layout_input7, layout_input8, layout_input9, layout_input10)

        language_setting.setOnClickListener {
            if (language) {
                text_result.text = "input -> pick"
                btn_pick.text = "pick"
                btn_one_pick.text = "one pick"
                btn_add.text = "add"
                btn_delete.text = "delete"
                language = false
            } else {
                text_result.text = "후보 입력 후 뽑기!"
                btn_pick.text = "무작위로 뽑기"
                btn_one_pick.text = "중복없이 뽑기"
                btn_add.text = "추가"
                btn_delete.text = "삭제"
                language = true
            }
        }

        btn_add.setOnClickListener {
            if (layoutNumber == layouts.size) {
                var info = ""
                info = if (language) {
                    "최대 10개까지 추가 가능합니다."
                } else {
                    "max : 10"
                }
                Snackbar.make(linearLayout, info, Snackbar.LENGTH_SHORT).show()
            } else {
                layouts[layoutNumber].visibility = View.VISIBLE
                layoutNumber += 1
            }
        }

        btn_delete.setOnClickListener {
            val candidate3 = edit_input3.text
            val candidate4 = edit_input4.text
            val candidate5 = edit_input5.text
            val candidate6 = edit_input6.text
            val candidate7 = edit_input7.text
            val candidate8 = edit_input8.text
            val candidate9 = edit_input9.text
            val candidate10 = edit_input10.text
            val candidates = arrayOf(candidate3, candidate4, candidate5, candidate6,
                                                        candidate7, candidate8,candidate9, candidate10)

            if (layoutNumber == 0) {
                var info = ""
                info = if (language) {
                    "최대 10개까지 추가 가능합니다."
                } else {
                    "min : 2"
                }
                Snackbar.make(linearLayout, info, Snackbar.LENGTH_SHORT).show()
            } else {
                layouts[layoutNumber - 1].visibility = View.GONE
                layoutNumber -= 1
                candidates[layoutNumber].clear()
            }
        }



        btn_pick.setOnClickListener {
            text_result.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35F)
            val candidates: Array<String> = editCandidate()
            val random = Random()
            var candidateNumber = 0

            if (candidates[0] == "" || candidates[1] == "") {
                var info = ""
                info = if (language) {
                    "후보를 입력해주세요!"
                } else {
                    "please input!"
                }
                text_result.text = info
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

        btn_one_pick.setOnClickListener {
            text_result.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20F)
            val candidates: Array<String> = editCandidate()
            var candidateNumber = 0

            if (candidates[0] == "" || candidates[1] == "") {
                var info = ""
                info = if (language) {
                    "후보를 입력해주세요!"
                } else {
                    "please input!"
                }
                text_result.text = info
            } else {
                for (i in 0..layoutNumber + 1) {
                    Log.d("${i+1}후보 : ", candidates[i])
                    if (candidates[i] != "") {
                        candidateNumber += 1
                    }
                }


                var temp: String
                var temp2: String
                var randomNum1: Int
                var randomNum2: Int

                for (i in 0..layoutNumber + 1) {
                    randomNum1 = (Math.random()*candidateNumber).toInt()
                    temp = candidates[randomNum1]
                    randomNum2 = (Math.random()*candidateNumber).toInt()
                    temp2 = candidates[randomNum2]
                    candidates[randomNum1] = temp2
                    candidates[randomNum2] = temp
                }

                text_result.text = "[1등] : ${candidates[0]}"  + "\t\t\t\t[2등] : ${candidates[1]}"
                for (i in 2..layoutNumber + 1) {
                    text_result.text = text_result.text.toString() + "\t\t\t\t[${i+1}등] : ${candidates[i]}"
                }
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
        val candidate6 = edit_input6.text.toString()
        val candidate7 = edit_input7.text.toString()
        val candidate8 = edit_input8.text.toString()
        val candidate9 = edit_input9.text.toString()
        val candidate10 = edit_input10.text.toString()
        return arrayOf(candidate1, candidate2, candidate3, candidate4, candidate5,
                    candidate6, candidate7, candidate8, candidate9, candidate10)
    }
}

