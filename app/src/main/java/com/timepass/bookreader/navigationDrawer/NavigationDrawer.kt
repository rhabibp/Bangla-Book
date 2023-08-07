package com.timepass.bookreader.navigationDrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerBody(
    items: List<ManuItem>,
    modifier: Modifier,
    onClick: (ManuItem) -> Unit
) {

    LazyColumn(Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color(0xffffd180).copy(0.9f), shape = RoundedCornerShape(10.dp))){
        items(items){item ->
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp)
            .background(color = Color(0xffffd180), shape = RoundedCornerShape(10.dp))
            .clickable { onClick(item) },
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = item.icon, tint = Color(0xFFFF7421), contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Text(text = item.title, fontSize = 22.sp)
            Spacer(modifier = Modifier.padding(20.dp))

            
        }
            
        }

        }
    }


@Composable
fun DrawerHeader() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(color = Color(0xffffd180)
            , shape = RoundedCornerShape(8.dp))
        ,contentAlignment = Alignment.CenterStart
    
    ){
        Text(text = "Header")
    }
    
}











