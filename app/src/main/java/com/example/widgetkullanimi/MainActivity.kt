package com.example.widgetkullanimi

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.widgetkullanimi.ui.theme.WidgetKullanimiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WidgetKullanimiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    SayfaButtonTextTextfield()
//                    SayfaFab()
//                    SayfaSwitch()
//                    SayfaCheckBox()
//                    SayfaTiklanma()
//                    SayfaRadioButton()
//                    SayfaProgressVeSlider()
//                    SayfaWebView()
//                    SayfaImage()
                    SayfaDropDownMenu()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WidgetKullanimiTheme {
        SayfaButtonTextTextfield()
    }
}

@Composable
fun SayfaButtonTextTextfield() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val girilenVeri = remember { mutableStateOf("") }
        val girilenVeriOutlined = remember { mutableStateOf("") }
        val gösterilenVeri = remember { mutableStateOf("") }
        Text(
            text = "Alınan Veri = ${gösterilenVeri.value}",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(background = Color.Blue)
        )
        TextField(
            value = girilenVeri.value,
            onValueChange = { girilenVeri.value = it },
            label = { Text(text = "Veri Giriniz") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Gray,
                textColor = Color.Red,
                focusedIndicatorColor = Color.Green,
                focusedLabelColor = Color.Yellow,
            ),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = { gösterilenVeri.value = girilenVeri.value },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
                contentColor = Color.White
            ),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = "Veriyi Al")
        }
        OutlinedTextField(
            value = girilenVeriOutlined.value,
            onValueChange = { girilenVeriOutlined.value = it },
            label = { Text(text = "Veri Giriniz") }
        )

        OutlinedButton(onClick = { gösterilenVeri.value = girilenVeriOutlined.value }) {
            Text(text = "Veriyi Al Outlined")
        }

    }

}

@Composable
fun SayfaFab() {
    Scaffold(content = { it -> it },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { Log.e("ActionButton", "Tıklandı") },
                text = { Text(text = "EKLE", color = Color.White) },
                backgroundColor = Color.Red,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ekle_resim),
                        contentDescription = "",
                        tint = Color.White
                    )
                }

            )
        })
}

@Composable
fun SayfaSwitch() {
    val switchDurum = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Switch(
            checked = switchDurum.value, onCheckedChange = {
                switchDurum.value = it
                Log.e("Switch", it.toString())
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Blue,
                checkedTrackColor = Color.Green,
                uncheckedThumbColor = Color.Blue,
                uncheckedTrackColor = Color.DarkGray
            )
        )
        Button(onClick = { Log.e("Switch son durum", switchDurum.value.toString()) }) {
            Text(text = "Göster")
        }
    }
}

@Composable
fun SayfaCheckBox() {
    val checkboxDurum = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checkboxDurum.value,
                onCheckedChange = {
                    checkboxDurum.value = it
                    Log.e("checkbox", it.toString())
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Red
                )
            )
            Text(text = "Jetpack Compose", modifier = Modifier.padding(start = 10.dp))
        }
        Button(onClick = { Log.e("Checkbox son durum", checkboxDurum.value.toString()) }) {
            Text(text = "Göster")
        }
    }
}


@Composable
fun SayfaTiklanma() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Red)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { Log.e("Box", "Tıklandı") },
                        onDoubleTap = { Log.e("Box", "Çift Tıklandı") },
                        onLongPress = { Log.e("Box", "Uzun Basıldı") }
                    )
                }
        )
    }
}

@Composable
fun SayfaRadioButton() {
    val secilenIndex = remember {
        mutableStateOf(0)
    }
    val takimListesi = listOf("Real Madrid", "Barcelona")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            takimListesi.forEachIndexed() { index, takim ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
                    secilenIndex.value = index
                    Log.e("Radio", takim)
                }) {
                    RadioButton(
                        selected = (takim == takimListesi[secilenIndex.value]),
                        onClick = {
                            secilenIndex.value = index
                            Log.e("Radio", takim)
                        })
                    Text(text = takim)
                }
            }
        }
        Button(onClick = { Log.e("Radio", takimListesi[secilenIndex.value]) }) {
            Text(text = "Göster")
        }

    }
}

@Composable
fun SayfaProgressVeSlider() {
    val progressDurum = remember {
        mutableStateOf(false)
    }
    val sliderDurum = remember {
        mutableStateOf(0f)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (progressDurum.value) {
            CircularProgressIndicator(color = Color.Red)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { progressDurum.value = true }) {
                Text(text = "Başla")
            }
            Button(onClick = { progressDurum.value = false }) {
                Text(text = "Dur")
            }

        }
        Text(text = "Sonuç : ${sliderDurum.value.toInt()}")
        Slider(
            value = sliderDurum.value,
            onValueChange = {
                sliderDurum.value = it
            },
            valueRange = 0f..100f,
            modifier = Modifier.padding(all = 20.dp),
            colors = SliderDefaults.colors(
                thumbColor = Color.Red,
                activeTrackColor = Color.Blue,
                inactiveTrackColor = Color.Blue
            )
        )
        Button(onClick = { Log.e("Slider", sliderDurum.value.toString()) }) {
            Text(text = "Göster")
        }

    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun SayfaWebView() {

    val url = "https://gelecegiyazanlar.turkcell.com.tr"
//    val url = "https://baklavar.fatihozhan.xyz"
    AndroidView(
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        },
        update = {
            it.loadUrl(url)

        })
}

@Composable
fun SayfaImage() {
    Column {
        val activity = LocalContext.current as Activity
        Image(
            bitmap = ImageBitmap.imageResource(
                id = activity.resources.getIdentifier(
                    "yemek", "drawable", activity.packageName
                )
            ), contentDescription = ""
        )
        Image(painter = painterResource(id = R.drawable.yemek), contentDescription = "")
        Image(painter = painterResource(id = R.drawable.ekle_resim), contentDescription = "")
    }
}

@Composable
fun SayfaDropDownMenu() {
    val ulkeList = listOf("Türkiye", "Almanya", "Japonya", "Rusya", "Çin")
    val menuAcilisKontrol = remember { mutableStateOf(false) }
    val secilenIndex = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .size(100.dp, 200.dp)
                    .clickable { menuAcilisKontrol.value = true }
            ) {
                Text(text = ulkeList[secilenIndex.value])
                Image(
                    painter = painterResource(id = R.drawable.dropdownmenu_resim),
                    contentDescription = ""
                )

            }
            DropdownMenu(expanded = menuAcilisKontrol.value,
                onDismissRequest = { menuAcilisKontrol.value = false }) {
                ulkeList.forEachIndexed { index, ulke ->
                    DropdownMenuItem(onClick = {
                        secilenIndex.value = index; menuAcilisKontrol.value = false
                    }) {
                        Text(text = ulke)
                    }
                }
            }
            Row {

                Button(onClick = {
                    Log.e(
                        "Menu",
                        "En son seçilen ülke : ${ulkeList[secilenIndex.value]}"
                    )
                }) {
                    Text(text = "Göster")
                }
            }

        }

    }
}