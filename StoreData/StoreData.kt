import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//implementation 'androidx.datastore:datastore-preferences:1.1.0-alpha04' is required
class StoreData(private val context: Context) {
    private val gson: Gson = Gson()

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("DataStorage")

        //Your Keys
        private val USER_EMAIL = stringPreferencesKey("email_value")
        private val USER_AGE = intPreferencesKey("age_value")
        private val USER_ELIGIBLE = booleanPreferencesKey("eligible_value")

        private val USER_FRIENDS = stringPreferencesKey("friends_value")
    }

    //Default value will be akardas16@ku.edu.tr
    val getMail:Flow<String> = context.dataStore.data.map {it[USER_EMAIL] ?: "akardas16@ku.edu.tr" }

    suspend fun saveEmail(email:String) = context.dataStore.edit { it[USER_EMAIL] = email}


    val userAge:Flow<Int> = context.dataStore.data.map { (it[USER_AGE]  ?: 25) }
    suspend fun saveAge(age:Int) = context.dataStore.edit { it[USER_AGE] = age }

    val isEligible:Flow<Boolean> = context.dataStore.data.map { it[USER_ELIGIBLE] ?: false }
    suspend fun setEligible(isEligible:Boolean) = context.dataStore.edit { it[USER_ELIGIBLE] = isEligible }



    /* Save Any object data
     you will need to Gson library
     implementation 'com.google.code.gson:gson:2.10.1'*/
    suspend fun saveFriendList(name: List<String>) = context.dataStore.edit { it[USER_FRIENDS] = gson.toJson(name) }

    val getFriendList: Flow<List<String>> = context.dataStore.data
        .map {
            val jsonString = it[USER_FRIENDS] ?: "[]" //default friend list will be empty
            gson.fromJson(jsonString, Array<String>::class.java).toList()
        }
}
