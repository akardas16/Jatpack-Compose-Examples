val list = listOf<Person>(
        Person("Abdullah Kardas","lindsay.ferguson@reqres"),
        Person("Michael Lawson","michael.lawson@reqres.in"),
        Person("Lindsay Ferguson","lindsay.ferguson@reqres.in"),
        Person("Tobias Funke","tobias.funke@reqres.in"),
        Person("Byron Fields","byron.fields@reqres.in"),
        Person("Abdullah Kardas","lindsay.ferguson@reqres"),
        Person("Michael Lawson","michael.lawson@reqres.in"),
        Person("Lindsay Ferguson","lindsay.ferguson@reqres.in"),
        Person("Tobias Funke","tobias.funke@reqres.in"),
        Person("Byron Fields","byron.fields@reqres.in"),
        Person("Abdullah Kardas","lindsay.ferguson@reqres"),
        Person("Michael Lawson","michael.lawson@reqres.in"),
        Person("Lindsay Ferguson","lindsay.ferguson@reqres.in"),
        Person("Tobias Funke","tobias.funke@reqres.in"),
        Person("Byron Fields","byron.fields@reqres.in")
    )

    var selectedIndex by remember {
        mutableStateOf(0)
    }

   Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly,
       horizontalAlignment = Alignment.CenterHorizontally) {


       LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)){
           itemsIndexed(list){index,person ->
               Row(modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 16.dp)
                   .clip(RoundedCornerShape(12.dp))
                   .background(Color.Gray.copy(0.5f))
                   .clickable {  selectedIndex = index}
                   ,
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.SpaceBetween){
                   Column(horizontalAlignment = Alignment.CenterHorizontally) {

                       Text(text = person.name, fontSize = 22.sp,
                           fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))

                       Text(text = person.mail, fontSize = 13.sp, modifier = Modifier.padding(8.dp))

                   }

                   Checkbox(checked = selectedIndex == index, onCheckedChange = {selectedIndex = index
                       Log.i(
                           "sdfsdsfsdfsd",
                           "Greeting2:$it "
                       )})
               }

           }
       }
