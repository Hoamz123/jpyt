package com.hoamz.jpyt.auth

import androidx.lifecycle.ViewModel
import com.hoamz.jpyt.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.collections.emptyList

class ItemViewModel : ViewModel() {
    private val _username = MutableStateFlow("")
    val username : StateFlow<String> = _username

    fun setUsername(username : String) {
        _username.value = username
    }

    private val _listCategoryBooks  = MutableStateFlow<List<ParentDataClass>>(emptyList())
    val listCategoryBooks : StateFlow<List<ParentDataClass>> = _listCategoryBooks

    init {
        freshData()
    }

    fun freshData(){
        val books = listOf(
            ChildDataClass(R.drawable.book1),
            ChildDataClass(R.drawable.book2),
            ChildDataClass(R.drawable.book3),
            ChildDataClass(R.drawable.book4),
            ChildDataClass(R.drawable.book6),
            ChildDataClass(R.drawable.book10),
            ChildDataClass(R.drawable.book11),
            ChildDataClass(R.drawable.book12),
            ChildDataClass(R.drawable.book13),
            ChildDataClass(R.drawable.book8),
            ChildDataClass(R.drawable.book14)
        )
        val lists : MutableList<ParentDataClass> = mutableListOf()
        lists.add(ParentDataClass("AAA",books.shuffled()))
        lists.add(ParentDataClass("BBB",books.shuffled()))
        lists.add(ParentDataClass("CCC",books.shuffled()))
        lists.add(ParentDataClass("DDD",books.shuffled()))
        lists.add(ParentDataClass("EEE",books.shuffled()))
        lists.add(ParentDataClass("FFF",books.shuffled()))
        lists.add(ParentDataClass("GGG",books.shuffled()))
        _listCategoryBooks.value = lists
    }

}