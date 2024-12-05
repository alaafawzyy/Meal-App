package com.example.mealsapplication.detailsActivity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.domain.model.Category
import com.example.domain.model.Meal
import com.example.mealsapplication.R
import com.example.mealsapplication.detailsActivity.ui.theme.MealsApplicationTheme
import com.example.mealsapplication.detailsActivity.ui.theme.Yellow3
import com.example.mealsapplication.detailsActivity.ui.theme.Yelow2
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailActivity : ComponentActivity() {
    var categoryies: Category? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealsApplicationTheme {
                categoryies = intent.getParcelableExtra("CATEGORY_KEY")
                MealDetail(category = categoryies)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MealDetail(viewModel: MealsDetailsActivityViewModel = hiltViewModel(), category: Category?) {

    if (category != null) {
        viewModel.getSpecificMeals(category.strCategory ?: "")
    }
    val meal = viewModel.categryLiveData.observeAsState().value


    Column(modifier = Modifier.fillMaxSize().background(Yellow3)) {
//        GlideImage(
//            model = meal?.strMealThumb,
//            contentDescription = "Meal Image",
//            modifier = Modifier.height(300.dp).fillMaxWidth()
//                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)),
//
//            contentScale = ContentScale.Crop,
//        )
        GlideImage(
            model = meal?.strMealThumb,
            contentDescription = "Meal Image",
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 100.dp, bottomEnd = 100.dp,
                    topStart = 100.dp, topEnd = 100.dp)).align(Alignment.CenterHorizontally).padding(top = 22.dp),



        )
        Text(
            text = meal?.strMeal.toString(),
            style = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 18.dp, start = 13.dp)
        )
        Text(
            text = "How to prepare",
            style = TextStyle(
                color = Color.DarkGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 40.dp, start = 30.dp)
        )
        val scrollState = rememberScrollState()

        Box(
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(start = 30.dp, end = 30.dp, bottom = 16.dp)
                .background(Yellow3)

        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding( top = 4.dp)
                    .background(Yellow3)
                    .height(300.dp)

            ) {
                Text(
                    text = meal?.strInstructions.orEmpty(),
                    style = TextStyle(
                        color = Color.DarkGray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = TextUnit.Unspecified

                    ),
                    modifier = Modifier
                        .padding(10.dp) // Padding داخل النص نفسه
                        .clip(RoundedCornerShape(17.dp))
                        .background(Yelow2)
                        .fillMaxWidth()


                )
    }

        }
        Row(
            modifier = Modifier.padding(top = 30.dp, start = 30.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.youtube),
                contentDescription = "YouTube Icon",
                tint = Color.Red, // لون الأيقونة لو حبيتي تغيري
                modifier = Modifier.size(24.dp) // حجم الأيقونة
            )
            Text(
                text = "Watch Video",
                style = TextStyle(
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(start = 8.dp) // مسافة بين النص والأيقونة
            )
        }

        Text(
            text = meal?.strYoutube.orEmpty(),
            style = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp,
            ),
            modifier = Modifier.padding(start = 60.dp, end = 20.dp)
        )


    }
}



