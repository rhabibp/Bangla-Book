package com.timepass.bookreader.components

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.timepass.bookreader.R
import com.timepass.bookreader.components.downloader.NewBookList
import com.timepass.bookreader.model.MyBooks
import com.timepass.bookreader.navigation.BookReaderScreens
import com.timepass.bookreader.ui.theme.myFont3
import com.timepass.bookreader.ui.theme.myFont5
import java.util.Date


@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeiAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,



    ) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = Modifier
            .padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeiAction),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xffff7f28),
            focusedLabelColor = Color(0xffff7f28),
            unfocusedBorderColor = Color(0xffff7f28)
        )

    )


}

@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId: String = "Email",
    enabled: Boolean = true,
    imeiAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,


    ) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeiAction = imeiAction,
        onAction = onAction,

    )
}


@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    onAction: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Done
) {
    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None
    else PasswordVisualTransformation()
    OutlinedTextField(value = passwordState.value,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xffff7f28),
            focusedLabelColor = Color(0xffff7f28),
            unfocusedBorderColor = Color(0xffff7f28)
        ) ,onValueChange ={
        passwordState.value = it

    },
        label = {Text(text = labelId)},
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = Modifier
            .padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction),
        visualTransformation = visualTransformation,
        trailingIcon = {
            PasswordVisibility(passwordVisibility = passwordVisibility)
        },
        keyboardActions = onAction


    )

}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = {passwordVisibility.value = !passwordVisibility.value }) {
        Icons.Default.Close

    }

}
@Composable
fun HomeContent(navController: NavController,context: Context) {
    val listofBooks = listOf(MyBooks(timeStamp = Date())
    )

    Column(Modifier.padding(2.dp)) {
        Box(
            modifier = Modifier
                .width(width = 400.dp)
                .padding(5.dp)
)
        {val currentUserName = if (!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0)
        } else {
            "N/A"
        }
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically ) {
                TitleSection(
                    modifier = Modifier.padding(top = 5.dp),
                    label = "Your Activity \n" + "  Right now..."
                )
                Spacer(modifier = Modifier.padding(start = 190.dp))
                Column(modifier = Modifier) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle, tint = Color(0xffff7f28),
                        modifier = Modifier
                            .size(50.dp)
                            .clickable { navController.navigate(BookReaderScreens.StatsScreen.name) },
                        contentDescription = "User Icon"
                    )
                    Text(text =currentUserName!!, maxLines = 1)

                }


            }

        }
//       ReadingRightNowArea(books = listOf() , navController = navController)

        Spacer(modifier = Modifier.height(10.dp))
        TitleSection(modifier = Modifier.padding(top = 30.dp), label = "Latest Book List" )
        Spacer(modifier = Modifier
            .height(2.dp)
            .padding(start = 5.dp)
            .fillMaxWidth()
            .background(Color(0xffff7f28)))
        NewBookList(navController)


//        BookListArea(listofBooks = listofBooks,navController)


    }

}

@Composable
fun BookListArea(listofBooks: List<MyBooks>, navController: NavController,) {
    HorizontalScrollableComponent(listofBooks){
        //On Card Click

    }

}

@Composable
fun HorizontalScrollableComponent(listofBooks: List<MyBooks>,onCardPress: (String) -> Unit) {
    val scrollState = rememberScrollState()
    
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(280.dp)
        .horizontalScroll(scrollState)) {
        for (book in listofBooks){
            ListCardArea(MyBooks(timeStamp = Date())){
                onCardPress(it)
            }
        }

        
    }

}



@Composable
fun BookReaderAppBar(
    title : String,
    showProfile : Boolean = true,
    navController: NavController,
    onNavigationClick: () -> Unit

) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 27.sp,
                    fontFamily = myFont5,
                    modifier = Modifier
                )
            }
        },
        actions = {
            IconButton(onClick = { FirebaseAuth.getInstance().signOut().run {
                navController.navigate(BookReaderScreens.LoginScreen2.name)
                {
                    popUpTo(BookReaderScreens.HomeScreen.name) {
                        inclusive = true
                    }
                }
            }
            }) {
                Icon(
                    painterResource(id = R.drawable.close), tint = Color.White,
                    contentDescription = "logout",
                    modifier = Modifier
                        .scale(0.5f)
                        .padding(end = 18.dp, top = 2.dp)
                )

            }
        }, backgroundColor = Color(0xffff7f28),
        navigationIcon = {
            IconButton(onClick =  onNavigationClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Toggle Drawer" )

            }
        }
    )
}
@Composable
fun TitleSection(modifier: Modifier,label: String) {
    Surface(modifier = Modifier.padding(start = 5.dp)) {
        Column { Text(text = label , fontStyle = FontStyle.Normal, fontSize = 20.sp) }

    }}

@Composable
fun FABContent(onTap: () -> Unit) {
    FloatingActionButton(
        onClick = {onTap()},
        shape = CircleShape,
        backgroundColor = Color(0xffff7f28),

        ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add a Book",
            tint = Color.White
        )

    }

}

@Composable
fun BookRating(score: Double) {
    Surface(
        Modifier
            .size(70.dp)
            .padding(4.dp)
            .clickable { }
        ,shape = RoundedCornerShape(30.dp)
        , elevation = 2.dp,
        color = Color.White) {
        Column(Modifier.padding(2.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(imageVector = Icons.Filled.Star, contentDescription = "Rating"
                ,Modifier.padding(3.dp))
            Text(text = score.toString(), style = MaterialTheme.typography.subtitle1)


        }

    }

}

@Composable
fun RoundedButton(
    label: String = "Reading",
    radios: Int = 40,
    onPress: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
        Image(painterResource(id = R.drawable.arrow),modifier = Modifier.size(50.dp), contentDescription ="Arrow" )
        Text(text = label,modifier = Modifier.padding(end = 15.dp), fontSize = 12.sp, color = Color.White, fontFamily = myFont3)
    }


}


@SuppressLint("SuspiciousIndentation")

@Composable
fun ListCardArea(
    book: MyBooks,
    onPressDetail: (String) -> Unit = {},
) {


    val context = LocalContext.current

    val resources = context.resources

    val displayMetrics = resources.displayMetrics

    val displayWidth = displayMetrics.widthPixels / displayMetrics.density
    val displayHeight = displayMetrics.heightPixels/displayMetrics.density

    val spacing = 50.dp

    Card(modifier = Modifier
        .height(220.dp)
        .width(200.dp)
        .padding(5.dp)
        .clickable { onPressDetail.invoke(book.title.toString()) },
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White) {

        Column(
            modifier = Modifier



        ) {
            Row(modifier = Modifier
                .padding(top = 5.dp)
                .clip(RoundedCornerShape(10.dp)), horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = rememberAsyncImagePainter(model = "https://images2.imgbox.com/6e/38/nUU8ctyh_o.jpg"),
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.CenterStart,
                    alpha = 0.9f,
                    contentDescription = "book image",
                    modifier = Modifier
                        .width(130.dp)
                        .height(100.dp)
                        .padding(5.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))

                Column(modifier = Modifier
                    .padding(top = 0.dp)
                    , verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription ="Favorite",
                        modifier = Modifier.padding(2.dp))

                    BookRating(3.5)

                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(Modifier.padding(start = 5.dp)) {

            Text(text = book.title.toString(),Modifier
                , fontWeight = FontWeight.Bold
                , maxLines = 2
                , overflow = TextOverflow.Ellipsis)

            Text(text = book.author.toString(), style = MaterialTheme.typography.caption)
            }


            Row {
                RoundedButton()
            }
        }
    }
}

@Composable
fun BookDetailsData(bookData: String) {

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) { Box() {

        Text(text = bookData, fontSize = 40.sp)

    }


    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController, textLabel: String) {
    Scaffold(topBar = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, end = 60.dp)
                    .clickable { navController.navigate(BookReaderScreens.HomeScreen.name) },
                contentDescription = "Back Arrow", tint = Color(0xffff7f28)
            )
            Text(
                textLabel,
                color = Color(0xffff7f28),
                fontSize = 25.sp,
                fontFamily = myFont5,
                modifier = Modifier.padding(top = 10.dp)
            )

        }

    })
    {

    }

}






























