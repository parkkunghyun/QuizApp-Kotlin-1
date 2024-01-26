package org.techtown.quizeapp_1

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.techtown.quizeapp_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    private var currentPosition: Int = 1 // 현재 몇번째 문제
    private var selectedOption: Int = 0; // 현재 몇번째 답변
    private var score: Int = 0; // 몇문제 맞췄는지

    private lateinit var questionList: ArrayList<Question>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questionList = QuestionData.getQuestion()

        getQuestionData()

        binding.option1Text.setOnClickListener(this)
        binding.option2Text.setOnClickListener(this)
        binding.option3Text.setOnClickListener(this)
        binding.option4Text.setOnClickListener(this)


    }
    private fun getQuestionData() {
        //질문 변수에 담기
        val question = questionList[currentPosition - 1]
        //
        binding.progressBar.progress = currentPosition
        binding.progressBar.max = questionList.size

        binding.progressText.text = getString(R.string.count_label, currentPosition, questionList.size)

        //질문 표시
        binding.questionText.text = question.question

        //답변표시
        binding.option1Text.text = question.option_one
        binding.option2Text.text = question.option_two
        binding.option3Text.text = question.option_three
        binding.option4Text.text = question.option_four

        setSubmitBtn("제출")
    }

    private fun setSubmitBtn(name: String) {
        binding.submitBtn.text = getString(R.string.submit, name)
    }

    private fun setOptionStyle() {
        var optionList: ArrayList<TextView> = arrayListOf()
        optionList.add(0, binding.option1Text)
        optionList.add(0, binding.option2Text)
        optionList.add(0, binding.option3Text)
        optionList.add(0, binding.option4Text)

        // 답변 텍스트 뷰 설정하기
        for(op in optionList) {
            op.setTextColor(Color.parseColor("#555151"))
            op.background = ContextCompat.getDrawable(this, R.drawable.option_background)
            op.typeface = Typeface.DEFAULT
        }
    }
    private  fun selectedOptionStyle(view: TextView, opt: Int) {
        //옵션 초기화
        setOptionStyle()

        //위치달기
        selectedOption = opt
        view.setTextColor(Color.parseColor("#5F00FF"))
        view.background = ContextCompat.getDrawable(this, R.drawable.selected_option_background)
        view.typeface = Typeface.DEFAULT_BOLD

    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.option1_text -> selectedOptionStyle(binding.option1Text, 1)
            R.id.option2_text -> selectedOptionStyle(binding.option2Text, 2)
            R.id.option3_text -> selectedOptionStyle(binding.option3Text, 3)
            R.id.option4_text -> selectedOptionStyle(binding.option4Text, 4)
        }
    }
}