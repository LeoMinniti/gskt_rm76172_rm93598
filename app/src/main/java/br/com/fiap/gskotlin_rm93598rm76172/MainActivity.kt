package br.com.fiap.gskotlin_rm93598rm76172

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.gskotlin_rm93598rm76172.model.EcoDicas
import br.com.fiap.gskotlin_rm93598rm76172.repository.getAllEcoDicas
import br.com.fiap.gskotlin_rm93598rm76172.repository.getEcoDicasListsByDescription
import br.com.fiap.gskotlin_rm93598rm76172.ui.theme.Gskotlin_rm93598rm76172Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Gskotlin_rm93598rm76172Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    EcoDicasScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EcoDicasScreen() {

    var searchTextState by remember {
        mutableStateOf("")
    }
    var ecoDicasListState by remember {
        mutableStateOf(getAllEcoDicas())
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Minhas EcoDicas favoritas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = searchTextState,
            onValueChange = {
                searchTextState = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Título da Ecodica")
            },
            trailingIcon = {
                IconButton(onClick = { ecoDicasListState = getEcoDicasListsByDescription(searchTextState) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = ""
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(){
            items(ecoDicasListState){
                DescriptionCard(ecoDicas = it)
            }
        }

        LazyColumn(){
            items(ecoDicasListState){
                EcoDicasCard(ecoDicas = it)
            }
        }
    }
}

@Composable
fun DescriptionCard(ecoDicas: EcoDicas) {
    Card(modifier = Modifier
        .size(100.dp)
        .padding(end = 4.dp)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = ecoDicas.description)
        }
    }
}


@Composable
fun EcoDicasCard(ecoDicas: EcoDicas) {
    Card(modifier = Modifier.padding(bottom = 8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(3f)
            ) {
                Text(
                    text = ecoDicas.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
                Text(
                    text = ecoDicas.description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

/*
@Preview(showBackground = true, name = "Eco Dicas Screen Preview")
@Composable
fun PreviewEcoDicasScreen() {
    ListasLazyTheme {
        EcoDicasScreen()
    }
}

@Preview(showBackground = true, name = "Description Card Preview")
@Composable
fun PreviewStudioCard() {
    ListasLazyTheme {
        DescriptionCard(ecoDicas = EcoDicas(1, "Titulo Sustentável 01", "Exemplo de descrição sustentável"))
    }
}

@Preview(showBackground = true, name = "Game Card Preview")
@Composable
fun PreviewGameCard() {
    ListasLazyTheme {
        GameCard(game = Game(1, "Example Game", "Example Studio", 2023))
    }
}
*/