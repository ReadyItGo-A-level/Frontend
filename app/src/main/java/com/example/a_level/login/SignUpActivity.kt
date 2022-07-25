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
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarSignup)
        val tb = supportActionBar!!
        tb.setDisplayShowTitleEnabled(false)
        tb.setDisplayHomeAsUpEnabled(true)

        var content = SpannableString(binding.textViewSignupTerms.text.toString())
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.textViewSignupTerms.text = content

        var possibleId = binding.textViewSignupPossibleId
        var sendCertificationNum = binding.textViewSignupSendCertification
        var edPassword = binding.editTextSignupPassword.text.toString()
        var samePwd = binding.textViewSignupSamePwd
        var pwd=binding.textViewSignupPasswordLength

        binding.buttonSignupIdRepeated.setOnClickListener {
            if (binding.editTextSignupName.text.toString().length < 2) {
                possibleId.text = "2글자 이상이어야 합니다."
                possibleId.visibility = View.VISIBLE
            } else if (binding.editTextSignupName.text.toString() == "채오니") {
                print("중복된 아이디")
                possibleId.text = "중복된 아이디입니다."
                possibleId.visibility = View.VISIBLE
            } else if (binding.editTextSignupName.text.toString() != "채오니") {
                possibleId.text = "사용 가능한 아이디입니다."
                print("사용 가능")
                possibleId.visibility = View.VISIBLE
            }
        }

        binding.buttonSignupEmailCertification.setOnClickListener {
            if (!Patterns.EMAIL_ADDRESS.matcher(binding.editTextSignupEmail.text.toString())
                    .matches()
            ) {
                sendCertificationNum.text = "올바른 이메일 형식이 아닙니다."
                sendCertificationNum.visibility = View.VISIBLE
            } else {
                sendCertificationNum.text = "이메일로 인증번호를 전송했습니다."
                sendCertificationNum.visibility = View.VISIBLE
            }
        }

        binding.editTextSignupPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.editTextSignupPassword.text.toString().length==0){
                    samePwd.visibility = View.GONE
                    pwd.visibility = View.GONE
                }
//                else if (binding.editTextSignupPasswordConfirm.text.toString() == binding.editTextSignupPassword.text.toString()) {
//                    samePwd.text = "비밀번호가 일치합니다"
//                    samePwd.visibility = View.VISIBLE
//                } else {
//                    samePwd.text = "비밀번호가 일치하지 않습니다."
//                    samePwd.visibility = View.VISIBLE
//                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if(binding.editTextSignupPassword.text.toString().length == 0){
                    pwd.visibility = View.GONE
                    samePwd.visibility = View.GONE
                }
                else if (binding.editTextSignupPassword.text.toString().length < 8) {
                    pwd.visibility = View.VISIBLE
                }
                else if(binding.editTextSignupPasswordConfirm.text.toString().length==0){
                    pwd.visibility = View.GONE
                    samePwd.visibility=View.GONE
                }
                else if (binding.editTextSignupPasswordConfirm.text.toString() == binding.editTextSignupPassword.text.toString()) {
                    pwd.visibility = View.GONE
                    samePwd.text="비밀번호가 일치합니다"
                    samePwd.visibility = View.VISIBLE
                } else {
                    pwd.visibility = View.GONE
                    samePwd.text="비밀번호가 일치하지 않습니다."
                    samePwd.visibility = View.VISIBLE
                }
            }
        })

        binding.editTextSignupPasswordConfirm.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.editTextSignupPasswordConfirm.text.toString() == binding.editTextSignupPassword.text.toString()) {
                    samePwd.text = "비밀번호가 일치합니다"
                    samePwd.visibility = View.VISIBLE
                } else {
                    samePwd.text = "비밀번호가 일치하지 않습니다."
                    samePwd.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.editTextSignupPasswordConfirm.text.toString().length == 0) {
                    samePwd.visibility = View.GONE
                } else if (binding.editTextSignupPasswordConfirm.text.toString() == binding.editTextSignupPassword.text.toString()) {
                    samePwd.setText("비밀번호가 일치합니다")
                    samePwd.visibility = View.VISIBLE
                } else {
                    samePwd.setText("비밀번호가 일치하지 않습니다.")
                    samePwd.visibility = View.VISIBLE
                }
            }
        })
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