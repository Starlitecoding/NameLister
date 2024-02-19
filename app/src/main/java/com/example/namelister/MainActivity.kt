package com.example.namelister

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.namelister.ui.theme.NameListerTheme

class MainActivity : ComponentActivity() {

    val viewModel by lazy {
        ViewModelProvider(this).get(MyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val state = viewModel.state.value

            NameListerTheme {
                Column(modifier = Modifier.fillMaxSize()) {


                    LazyColumn(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f))
                    {
                        items(state.namesList.size){
                            Text(text = state.namesList[it])
                        }
                    }



                    MyTextField(
                        textValue = state.text,
                        onValueChanged = { viewModel.updateText(it)},
                        onAddClick = {
                            viewModel.updateNamesList()
                            viewModel.updateText("")
                        })
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MyTextField(
    textValue: String, onValueChanged: (String) -> Unit, onAddClick: () -> Unit
) {

    TextField(value = textValue, onValueChange = {
        onValueChanged(it)
    }, modifier = Modifier.fillMaxWidth(), trailingIcon = {
        Icon(imageVector = Icons.Default.Add,
            contentDescription = null,
            modifier = Modifier.clickable { onAddClick() })
    }

    )


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NameListerTheme {

    }
}