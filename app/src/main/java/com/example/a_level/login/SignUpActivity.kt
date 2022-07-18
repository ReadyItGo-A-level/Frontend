package com.example.a_level.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.Patterns
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.a_level.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val tb=supportActionBar!!
        tb.setDisplayShowTitleEnabled(false)
        tb.setDisplayHomeAsUpEnabled(true)

        var content= SpannableString(binding.terms.text.toString())
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.terms.text=content

        val edNickname=binding.edNickname.text.toString()
        val possibleId=binding.possibleId
        val edEmail=binding.edEmail.text.toString()
        val sendCertificationNum=binding.sendCertificationNum
        val edPassword=binding.edPassword.text.toString()
        val edPasswordConfirm=binding.edPasswordConfirm.text.toString()
        val samePwd=binding.samePwd

        binding.btnIdRepeated.setOnClickListener {
            print("why")
            print(binding.edNickname.text.toString())
            if (edNickname.length < 3) {
                possibleId.text = "2글자 이상이어야 합니다."
                possibleId.visibility= View.VISIBLE
            }
            if (edNickname == "채오니") {
                print("중복된 아이디")
                possibleId.text = "중복된 아이디입니다."
                possibleId.visibility= View.VISIBLE
            }
            else {
                possibleId.text = "사용 가능한 아이디입니다."
                print("사용 가능")
                possibleId.visibility= View.VISIBLE
            }
        }

        binding.btnEmailCertification.setOnClickListener {
            if(!Patterns.EMAIL_ADDRESS.matcher(edEmail).matches()) {
                sendCertificationNum.setText("올바른 이메일 형식이 아닙니다.")
                sendCertificationNum.visibility= View.VISIBLE
            }
            else{
                sendCertificationNum.setText("이메일로 인증번호를 전송했습니다.")
                sendCertificationNum.visibility= View.VISIBLE
            }
        }

//        binding.edPasswordConfirm.addTextChangedListener(object: TextWatcher{
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                if(edPasswordConfirm==edPassword){
//                    samePwd.setText("비밀번호가 일치합니다")
//                    samePwd.visibility=View.VISIBLE
//                }
//                else{
//                    samePwd.setText("비밀번호가 일치하지 않습니다.")
//                    samePwd.visibility=View.VISIBLE
//                }
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//                if(edPasswordConfirm==edPassword){
//                    samePwd.setText("비밀번호가 일치합니다")
//                    samePwd.visibility=View.VISIBLE
//                }
//                else{
//                    samePwd.setText("비밀번호가 일치하지 않습니다.")
//                    samePwd.visibility=View.VISIBLE
//                }
//            }
//        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}