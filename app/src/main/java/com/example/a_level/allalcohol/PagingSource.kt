package com.example.a_level.allalcohol

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.a_level.allalcohol.model.response.Alcohol
import com.example.a_level.allalcohol.service.AllAlcoholService
import com.example.a_level.databinding.FragmentAllalcoholsubcategoryBinding

class PagingSource(
    private val typeToString: String,
    private val category: String,
    private val binding: FragmentAllalcoholsubcategoryBinding
) :
    PagingSource<Int, Alcohol>() {

    override fun getRefreshKey(state: PagingState<Int, Alcohol>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Alcohol> {
        return try {
            val page = params.key ?: 0
            val response = AllAlcoholService.findAlcohol(typeToString, category, page = page)
            val alcohols = response.body()!!.data.alcohol
            binding.textviewAllalcoholsubcategoryCount.text =
                response.body()!!.data.total.toString()
            LoadResult.Page(
                data = alcohols, prevKey = if (page == 0) null else page - 1,
                nextKey = if (alcohols.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}