package com.example.a_level.feed

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.a_level.R
import com.example.a_level.databinding.ActivityWritefeedBinding
import java.text.DecimalFormat

class WriteFeedActivity : AppCompatActivity() {
    lateinit var binding: ActivityWritefeedBinding
    lateinit var launcher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWritefeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val intent = checkNotNull(result.data)
                    val imageUri = intent.data
                    binding.imageviewWritefeedGallery.apply {
                        visibility = View.VISIBLE
                        setImageURI(imageUri)
                    }
                }

            }

        initActionBar()
        initView()
        initListener()
        initSpinnerAdapter()
        initSpinnerListener()
    }

    private fun initSpinnerListener() {
        val itemSelectedListener = fun(spinner: Spinner): AdapterView.OnItemSelectedListener {
            return object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    when (position) {
                        0 -> {
                            spinner.setBackgroundResource(R.drawable.writefeed_spinner_background)
                            (parent!!.getChildAt(0) as TextView).setTextColor(
                                ContextCompat.getColor(
                                    applicationContext,
                                    R.color.black
                                )
                            )
                            true
                        }
                        else -> {
                            spinner.setBackgroundResource(R.drawable.writefeed_spinner_fill)
                            (parent!!.getChildAt(0) as TextView).setTextColor(
                                ContextCompat.getColor(
                                    applicationContext,
                                    R.color.white
                                )
                            )
                            true
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
        binding.spinnerWritefeedType.onItemSelectedListener =
            itemSelectedListener(binding.spinnerWritefeedType)

        binding.spinnerWritefeedVolumn.onItemSelectedListener =
            itemSelectedListener(binding.spinnerWritefeedVolumn)

        binding.spinnerWritefeedTaste.onItemSelectedListener =
            itemSelectedListener(binding.spinnerWritefeedTaste)

        binding.spinnerWritefeedSugar.onItemSelectedListener =
            itemSelectedListener(binding.spinnerWritefeedSugar)

    }


    private fun initSpinnerAdapter() {
        val spinnerAdapter = fun(spinnerList: List<String>): ArrayAdapter<String> {
            val adapter = object :
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerList) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent)
                    (view.findViewById(android.R.id.text1) as TextView).textSize = 14F
                    (view.findViewById(android.R.id.text1) as TextView).gravity = Gravity.CENTER
                    return view
                }
            }
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            return adapter
        }

        val typeList = listOf("주종*", "맥주", "와인", "전통주", "양주")
        binding.spinnerWritefeedType.adapter = spinnerAdapter(typeList)

        val volumeList = listOf("도수", "1(낮음)", "2(중간)", "3(중간)", "4(높음)", "5(높음)")
        binding.spinnerWritefeedVolumn.adapter = spinnerAdapter(volumeList)

        val sugarList = listOf("당도", "1(씁쓸한)", "2(중간)", "3(중간)", "4(매우 달달한)", "5(매우 달달한)")
        binding.spinnerWritefeedSugar.adapter = spinnerAdapter(sugarList)

        val tasteList = listOf("맛", "탄산", "과일향", "새콤달콤", "꽃향", "청량감", "부드러움", "씁쓸함", "로제")
        binding.spinnerWritefeedTaste.adapter = spinnerAdapter(tasteList)
    }

    private fun initView() {
        setLinearLayoutVisible(binding.linearlayoutWritefeedDefault)
    }

    private fun initListener() {
        binding.textviewWritefeedMoreinfo.setOnClickListener {
            setLinearLayoutVisible(binding.linearlayoutWritefeedMoreinfo)
        }
        binding.imageviewWritefeedCancel.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("정보입력을 취소하시면 작성한 내용은 초기화 됩니다. 취소하시겠습니까?")
                .setPositiveButton("네") { _, _ ->
                    clearMoreInfo()
                    setLinearLayoutVisible(binding.linearlayoutWritefeedDefault)
                }.setNegativeButton("아니요") { _, _ ->

                }.show()
        }

        binding.edittextWritefeedPrice.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.edittextWritefeedPrice.clearFocus()
            }
            false
        }
        binding.edittextWritefeedPrice.setOnFocusChangeListener { view, hasFocus ->
            val decimalFormat = DecimalFormat("#,###")
            var price = binding.edittextWritefeedPrice.text.toString()
            if (hasFocus) {
                binding.edittextWritefeedPrice.filters = arrayOf(InputFilter.LengthFilter(13))
                if (price.isNotEmpty()) {
                    binding.edittextWritefeedPrice.setBackgroundResource(R.drawable.writefeed_textview_border)
                    binding.edittextWritefeedPrice.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.black
                        )
                    )
                    binding.edittextWritefeedPrice.text = null
                }
            } else {
                binding.edittextWritefeedPrice.filters = arrayOf(InputFilter.LengthFilter(18))
                if (price.isNotEmpty()) {
                    binding.edittextWritefeedPrice.setBackgroundResource(R.drawable.writefeed_textview_fill)
                    binding.edittextWritefeedPrice.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.white
                        )
                    )
                    binding.edittextWritefeedPrice.setText(decimalFormat.format(price.toLong()) + "원")
                }
            }
        }
        binding.edittextWritefeedName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.edittextWritefeedName.clearFocus()
            }
            false
        }
        binding.edittextWritefeedName.setOnFocusChangeListener { view, hasFocus ->
            var name = binding.edittextWritefeedName.text.toString()
            if (hasFocus) {
                if (name.isNotEmpty()) {
                    binding.edittextWritefeedName.setBackgroundResource(R.drawable.writefeed_textview_border)
                    binding.edittextWritefeedName.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.black
                        )
                    )
                    binding.edittextWritefeedName.text = null
                }
            } else {
                if (name.isNotEmpty()) {
                    binding.edittextWritefeedName.setBackgroundResource(R.drawable.writefeed_textview_fill)
                    binding.edittextWritefeedName.setTextColor(
                        ContextCompat.getColor(
                            this,
                            R.color.white
                        )
                    )
                }
            }
        }

        binding.floatingbuttonWritefeedGallery.setOnClickListener {
            val intent = Intent().apply {
                type = "image/*"
                action = Intent.ACTION_GET_CONTENT

            }
            launcher.launch(intent)
        }
    }

    private fun clearMoreInfo() {
        binding.spinnerWritefeedType.setSelection(0)
        binding.spinnerWritefeedTaste.setSelection(0)
        binding.spinnerWritefeedSugar.setSelection(0)
        binding.spinnerWritefeedVolumn.setSelection(0)
        binding.spinnerWritefeedType.setBackgroundResource(R.drawable.writefeed_spinner_background)
        binding.spinnerWritefeedTaste.setBackgroundResource(R.drawable.writefeed_spinner_background)
        binding.spinnerWritefeedSugar.setBackgroundResource(R.drawable.writefeed_spinner_background)
        binding.spinnerWritefeedVolumn.setBackgroundResource(R.drawable.writefeed_spinner_background)
        binding.edittextWritefeedName.setText("")
        binding.edittextWritefeedPrice.setText("")
        binding.edittextWritefeedName.setBackgroundResource(R.drawable.writefeed_textview_border)
        binding.edittextWritefeedName.setTextColor(
            ContextCompat.getColor(
                this,
                R.color.black
            )
        )
        binding.edittextWritefeedPrice.setBackgroundResource(R.drawable.writefeed_textview_border)
        binding.edittextWritefeedPrice.setTextColor(
            ContextCompat.getColor(
                this,
                R.color.black
            )
        )
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbarWritefeed)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "글쓰기"
    }

    private fun setLinearLayoutVisible(view: View) {
        when (view) {
            binding.linearlayoutWritefeedDefault -> {
                binding.linearlayoutWritefeedDefault.visibility = View.VISIBLE
                binding.linearlayoutWritefeedMoreinfo.visibility = View.GONE
                true
            }
            binding.linearlayoutWritefeedMoreinfo -> {
                binding.linearlayoutWritefeedDefault.visibility = View.GONE
                binding.linearlayoutWritefeedMoreinfo.visibility = View.VISIBLE
                true
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_write, menu)       // main_menu 메뉴를 toolbar 메뉴 버튼으로 설정
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.menu_write_submit -> {
                Toast.makeText(this, "글을 작성했습니다.", Toast.LENGTH_SHORT).show()
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}