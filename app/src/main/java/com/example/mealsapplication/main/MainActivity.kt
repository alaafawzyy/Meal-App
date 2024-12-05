package com.example.mealsapplication.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.mealsapplication.R
import com.example.domain.model.Category
import com.example.domain.repo.MealsRepository
import com.example.mealsapplication.detailsActivity.MealDetailActivity
import com.example.mealsapplication.splash.SplashScreen
import com.example.mealsapplication.ui.theme.MealsApplicationTheme
import com.example.mealsapplication.ui.theme.gray
import com.example.mealsapplication.ui.theme.gray2
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.wait

@AndroidEntryPoint
class MainActivity (): ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealsApplicationTheme {
                    MainContent()
                }
            }
        }
    }



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(viewModel: MainViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 40.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_listt),
                contentDescription = "ic list",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 16.dp, bottom = 20.dp)

            )
            Text(
                text = "Recipes",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = Color.Black)
            )

            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "ic list",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(35.dp)
                    .width(55.dp)
                    .padding(start = 16.dp)
                    .clip(RoundedCornerShape(100))
            )
        }
        var searchQuery by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = {
                    Text(
                        text = "Search Recipes",
                        color = Color.Gray,
                        style = MaterialTheme.typography.body1
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    containerColor = Color(0xFFF5F5F5),
                ),
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFC107))
                            .align(Alignment.End)

                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            modifier = Modifier.align(Alignment.Center),
                            tint = Color.White // لون الأيقونة
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp)) // شكل دائري بالكامل
                    .background(Color(0xFFF5F5F5)) // لون الخلفية
                    .padding(horizontal = 16.dp)
            )


        LaunchedEffect(Unit) {
            viewModel.fetchCategoryList()
        }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp)

            ) {
                items(viewModel.categryList.size) { position ->
                    MealCardWithIntent(categoryItem = viewModel.categryList[position])
                }
            }
    }
}}


@Composable
fun MealCardWithIntent(categoryItem: Category) {
    val context = LocalContext.current
    MealCard(categoryItem = categoryItem) { selectedCategory ->
        val intent = Intent(context, MealDetailActivity::class.java).apply {
            putExtra("CATEGORY_KEY", selectedCategory)
            Log.w("k","LLLL ${selectedCategory.strCategory}")
        }
        context.startActivity(intent)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MealCard(categoryItem: Category,onClick:  (Category)->Unit) {
    Card(
        modifier = Modifier
            .padding(15.dp)
            .width(150.dp)
            .height(250.dp)
            .clickable {
                onClick(categoryItem)
            }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)

        ) {
            val (image, title, description) = createRefs()
            Card(
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(title.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }

                    .clip(RoundedCornerShape(25.dp))
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                GlideImage(
                    model = categoryItem.strCategoryThumb,
                    contentDescription = stringResource(R.string.category_image_meal),
       contentScale = ContentScale.Fit,
                  modifier = Modifier
                        .fillMaxSize()

                      .height(200.dp)
                     // .padding(top = 22.dp, bottom = 22.dp)


                )
            }
            Text(
                text = categoryItem.strCategory ?: "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(image.bottom)
                    bottom.linkTo(description.top)
                    start.linkTo(image.start)
                },
                style = TextStyle(color = Color.DarkGray)
            )

            Text(
                text = categoryItem.strCategoryDescription ?: "",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .width(100.dp)
                    .constrainAs(description) {
                        bottom.linkTo(parent.bottom)
                        top.linkTo(title.bottom)
                        start.linkTo(image.start)

                    },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(color = Color.Gray)
            )
        }
    }}


    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun MainActivityPreview() {
        val viewModel: MainViewModel = viewModel()
        MealsApplicationTheme {
            MainContent(viewModel = viewModel)
        }
    }

