package com.example.a_level.login

import android.content.ContentValues
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.Log
import android.util.Patterns
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.a_level.R
import com.example.a_level.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*
import kotlin.concurrent.timer

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private var checkUsername=false
    private var checkEmail=false
    private var checkConfirm=false
    private var checkPassword=false
    private var termAgree=false
    private lateinit var certificateNum: String
    var timer=false
    var timerTask : Timer?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarSignup)
        val toolbar = supportActionBar!!
        toolbar.setDisplayShowTitleEnabled(false)
        toolbar.setDisplayHomeAsUpEnabled(true)

        var content = SpannableString(binding.textviewSignupTerms.text.toString())
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.textviewSignupTerms.text = content

        var possibleId = binding.textviewSignupPossibleid
        var sendCertificationNum = binding.textviewSignupSendcertification
        var editTextPassword = binding.edittextSignupPassword.text.toString()

        binding.buttonSignupIdRepeated.setOnClickListener {
            if (binding.edittextSignupName.text.toString().length < 2) {
                possibleId.text = "2글자 이상이어야 합니다."
                possibleId.visibility = View.VISIBLE
            } else {
                SignUpService.getRetrofitUsername(binding.edittextSignupName.text.toString()).enqueue(object: Callback<CheckUserResponse>{
                    override fun onResponse(call: Call<CheckUserResponse>, response: Response<CheckUserResponse>){
                        if (response.isSuccessful) {
                            Log.e("log", response.toString())
                            Log.e("log", response.body().toString())
                            possibleId.text = "힙한 별명이네요 :)"
                            possibleId.setTextColor(ContextCompat.getColor(applicationContext, R.color.correct))
                            print("사용 가능")
                            possibleId.visibility = View.VISIBLE
                            checkUsername=true
                        }else {
                            try {
                                val body = response.errorBody()!!.string()
                                possibleId.text = "중복된 아이디입니다."
                                possibleId.setTextColor(ContextCompat.getColor(applicationContext, R.color.error))
                                possibleId.visibility = View.VISIBLE
                                checkUsername=false
//                                    val gson= GsonBuilder().create()
//                                    val error=gson.fromJson(response.errorBody()!!.string())
                                //에러 Toast
                                Log.e(ContentValues.TAG, "body : $body")
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }

                    }
                    override fun onFailure(call: Call<CheckUserResponse>, error: Throwable){
                        Log.e("TAG", "실패원인: {$error}")
                    }
                })
            }
        }

        binding.buttonSignupEmailCertification.setOnClickListener {
            if (!Patterns.EMAIL_ADDRESS.matcher(binding.edittextSignupEmail.text.toString())
                    .matches()
            ) {
                sendCertificationNum.text = "올바른 이메일 형식이 아닙니다."
                sendCertificationNum.setTextColor(ContextCompat.getColor(applicationContext, R.color.error))
                sendCertificationNum.visibility = View.VISIBLE
                checkEmail=false
            } else {
                SignUpService.getRetrofitPostEmail(binding.edittextSignupEmail.text.toString()).enqueue(object: Callback<CheckEmailResponse>{
                    override fun onResponse(call: Call<CheckEmailResponse>, response: Response<CheckEmailResponse>){
                        if(response.isSuccessful) {
                            Log.e("log", response.toString())
                            Log.e("log", response.body().toString())
                            certificateNum = response.body()?.data.toString()
                            Log.e("certificateNum", "$certificateNum")
                            checkEmail=true

                            sendCertificationNum.text = "이메일로 인증번호를 전송하였습니다."
                            sendCertificationNum.setTextColor(
                                ContextCompat.getColor(
                                    applicationContext,
                                    R.color.correct
                                )
                            )
                            sendCertificationNum.visibility = View.VISIBLE

                            binding.linearlayoutSignupTimer.visibility=View.VISIBLE
                            var time = 240000
                            val context = this

                            timerTask = timer(period = 1000) {	// timer() 호출
                                time = time - 1000	// period=10, 0.01초마다 time를 1씩 감소Rp
                                val min = time / 60000	// time/100, 나눗셈의 몫 (초 부분)
                                val sec = (time % 60000) / 1000	// time%100, 나눗셈의 나머지 (밀리초 부분)

                                // UI조작을 위한 메서드
                                runOnUiThread {
                                    binding.textviewSignupMinute.text = "$min"	// TextView 세팅
                                    binding.textviewSignupSecond.text = ":$sec"// Textview 세팅
                                }
                                if(time == 0){
                                    timerTask?.cancel()
                                    binding.linearlayoutSignupTimer.visibility=View.GONE
                                }
                            }
                        }else {
                            try {
                                val body = response.errorBody()!!.string()
                                sendCertificationNum.text = "인증번호 전송에 실패하였습니다."
                                sendCertificationNum.setTextColor(ContextCompat.getColor(applicationContext, R.color.error))
                                sendCertificationNum.visibility = View.VISIBLE
                                checkEmail=false

//                                    val gson=GsonBuilder().create()
//                                    val error=gson.fromJson(response.errorBody().string())
                                //에러 Toast
//                                    val error=JSONObject(body)
                                Log.e(ContentValues.TAG, "body : $body")
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }
                    }
                    override fun onFailure(call: Call<CheckEmailResponse>, error: Throwable){
                        Log.e("TAG", "실패원인: {$error}")
                    }
                })
            }
        }

        binding.buttonSignupCompleteCertification.setOnClickListener {
            if(binding.linearlayoutSignupTimer.visibility==View.VISIBLE) {  //타이머가 살아있으면
                SignUpService.getRetrofitEmailCheck(
                    binding.edittextSignupEmail.text.toString(),
                    binding.edittextSignupCertificationNum.text.toString()
                ).enqueue(object: Callback<CheckEmailResponse> {
                    override fun onResponse(
                        call: Call<CheckEmailResponse>,
                        response: Response<CheckEmailResponse>
                    ) {
                        if (response.isSuccessful) {
                            Log.e("log", response.toString())
                            Log.e("log", response.body().toString())

                            sendCertificationNum.text = "인증이 완료되었습니다."
                            binding.textviewSignupConfirmCertificate.visibility=View.VISIBLE
                            checkConfirm=true

                            timerTask?.cancel()
                            binding.linearlayoutSignupTimer.visibility=View.GONE
                        } else {
                            try {
                                val body = response.errorBody()!!.string()
                                Log.e(ContentValues.TAG, "body : $body")

                                sendCertificationNum.text = "인증번호가 올바르지 않습니다."
                                binding.textviewSignupConfirmCertificate.visibility=View.VISIBLE
                                checkConfirm=false
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }
                    }

                    override fun onFailure(call: Call<CheckEmailResponse>, error: Throwable) {
                        Log.e("TAG", "실패원인: {$error}")
                    }
                })
            } else{
                sendCertificationNum.text = "인증 시간이 초과되었습니다."
                binding.textviewSignupConfirmCertificate.visibility=View.VISIBLE
                checkConfirm=false
            }
        }

        var samePassword = binding.textviewSignupSamepassword
        var password=binding.textviewSignupPasswordlength

        binding.edittextSignupPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.edittextSignupPassword.text.toString().length < 8) {
                    password.visibility = View.VISIBLE
                }
                else{
                    password.visibility = View.GONE
                }
            }
        })

        binding.edittextSignupPasswordconfirm.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.buttonSignupCompleteSignup.setOnClickListener {
            if (binding.edittextSignupPasswordconfirm.text.toString() == binding.edittextSignupPassword.text.toString()) {
                    samePassword.setText("비밀번호가 일치합니다")
                    samePassword.visibility = View.VISIBLE
                    checkPassword=true
                } else {
                    samePassword.setText("비밀번호가 일치하지 않습니다.")
                    samePassword.visibility = View.VISIBLE
                checkPassword=false
                }
            if(binding.radiobuttonSignup.isChecked)
                termAgree=true
            else
                termAgree=false

            Log.e("tag","$checkUsername, $checkEmail, $checkConfirm, $checkPassword, $termAgree");
            if(true && true && true && checkPassword && termAgree) {
                SignUpService.getRetrofitSignUp(SignupRequest(binding.edittextSignupEmail.text.toString(), binding.edittextSignupPassword.text.toString(), binding.edittextSignupName.text.toString()))
                    .enqueue(object : Callback<SignUpResponse> {
                        override fun onResponse(
                            call: Call<SignUpResponse>,
                            response: Response<SignUpResponse>
                        ) {
                            if (response.isSuccessful) {
                                Log.e("log", response.toString())
                                Log.e("log", response.body().toString())

                                Log.d("log", response.toString())
                                Log.d("log", response.body().toString())

                                Toast.makeText(this@SignUpActivity,"회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                                finish()
                            } else {
                                try {
                                    val body = response.errorBody()!!.string()
                                    Log.e(ContentValues.TAG, "body : $body")

                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }
                            }
                        }
                        override fun onFailure(call: Call<SignUpResponse>, error: Throwable) {
                            Log.d("TAG", "실패원인: {$error}")
                        }
                    })
            }else{
                Toast.makeText(this@SignUpActivity,"정보를 다 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val focusView = currentFocus
        if (focusView != null && ev != null) {
            val rect = Rect()
            focusView.getGlobalVisibleRect(rect)
            val x = ev.x.toInt()
            val y = ev.y.toInt()

            if (!rect.contains(x, y)) {
                val imm = getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(focusView.windowToken, 0)
                focusView.clearFocus()
            }
        }
        return super.dispatchTouchEvent(ev)
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