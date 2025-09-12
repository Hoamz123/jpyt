package com.hoamz.jpyt.auth

import android.R
import android.inputmethodservice.Keyboard
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


//item ben trong cua 1 item khac
data class ChildDataClass(
    val image : Int
)

//item goc(cha)
data class ParentDataClass(
    val title : String,
    val childList: List<ChildDataClass>
)

@Composable
fun NestedLazy(
    modifier: Modifier = Modifier,
    parentDataClass: List<ParentDataClass>
) {
    Column(
        modifier = modifier.fillMaxSize()
            .background(color = Color.White)
    ){
        LazyColumn(
            modifier = modifier.fillMaxSize()
                .padding(start = 5.dp, end = 10.dp),
            contentPadding = PaddingValues(vertical = 10.dp, horizontal = 10.dp)
        ) {
            val items = parentDataClass.filter { it.title.contains("BB",ignoreCase = true) }

            items(items, key = {it.title}){obj ->
                ColumnItem(parentDataClass = obj)
            }
        }
    }
}

@Composable
fun ColumnItem(
    modifier: Modifier = Modifier,
    parentDataClass: ParentDataClass
) {
    Card (
        modifier = modifier.fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ){
        Text(text = parentDataClass.title,
            modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow (
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp)
        ){
            items(parentDataClass.childList.size) { index ->
                RowItem(
                    childDataClass = parentDataClass.childList.get(index)
                )
            }
        }
    }
}

@Composable
fun RowItem(
    modifier: Modifier = Modifier,
    childDataClass: ChildDataClass
) {
    Box(
        modifier = modifier.padding(5.dp)
            .clip(RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = modifier.size(100.dp,160.dp),
            painter = painterResource(childDataClass.image),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
    }
}

