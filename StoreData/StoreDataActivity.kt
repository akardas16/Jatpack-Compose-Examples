import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.testproject.ui.theme.TestProjectTheme
import kotlinx.coroutines.launch
import java.util.UUID

class StoreDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestProjectTheme {
                // A surface container using the 'background' color from the theme
                UserEmail()
            }
        }
    }
}

@Composable
fun UserEmail(){
    val scope = rememberCoroutineScope()
    val storeData = StoreData(LocalContext.current)

    val getSavedMail by storeData.getMail.collectAsStateWithLifecycle(initialValue = "")
    val isEligible by storeData.isEligible.collectAsStateWithLifecycle(initialValue = false)
    var email by remember {
        mutableStateOf("")
    }

    val getListFriends by storeData.getFriendList.collectAsStateWithLifecycle(initialValue = listOf())


    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        OutlinedTextField(value = email, onValueChange = {email = it},
            label = { Text(text = "Your email is here", color = Color.Gray)})
        Button(onClick = {
                         scope.launch {
                             storeData.saveEmail(email)
                         }
                },
            modifier = Modifier.padding(vertical = 24.dp)) {
            Text(text = "Save Email")
        }

        Text(text = getSavedMail)
        Switch(checked = isEligible,
            onCheckedChange = {
                scope.launch {
                    storeData.setEligible(it)
                }
            })
        Text(text = "is Eligible: $isEligible")
        Button(onClick = {
            scope.launch {
                storeData.saveFriendList(listOf("Abdullah","Ali","Ahmet"))
            }
        }) {
            Text(text = "Add Friend")
        }
        getListFriends.forEach {
            Text(text = it)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    TestProjectTheme {
      UserEmail()
    }
}

