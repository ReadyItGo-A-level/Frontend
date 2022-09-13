package com.example.a_level.allalcohol

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import com.example.a_level.databinding.ItemAllalcoholsubcategorySpinnerBinding

class AllAlcoholSubCategorySpinnerAdapter(
    context: Context,
    @LayoutRes private val resId: Int,
    private val list: MutableList<AllAlcoholSubCategorySpinnerData>
) :
    ArrayAdapter<AllAlcoholSubCategorySpinnerData>(context, resId, list) {

    override fun getCount() = list.size

    override fun getItem(position: Int) = list[position]

    //스피너 뷰 관련
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding =
            ItemAllalcoholsubcategorySpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val model = list[position]
        try {
            binding.imageviewAllalcoholSpinner.visibility = View.GONE
            binding.textviewAllalcoholSpinner.text = model.name
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return binding.root
    }

    //스피너 드롭다운 뷰 관련
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemAllalcoholsubcategorySpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val model = list[position]
        try {
            if (model.image == null){
                binding.imageviewAllalcoholSpinner.visibility = View.GONE
            }
            else{
                binding.imageviewAllalcoholSpinner.setImageResource(model.image)
            }
            binding.textviewAllalcoholSpinner.text = model.name

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return binding.root
    }

}