@Composable
fun CheckBoxes(){

    var selected by remember {
        mutableStateOf(true)
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 8.dp)
        , horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Surface(modifier = Modifier
            .size(24.dp)
            .border(
                2.dp, Color(0xFF1098FF),
                shape = CircleShape
            ).clip(CircleShape).clickable { selected = !selected },
            color = Color.Transparent, shape = CircleShape){

            if (selected){
                Icon(imageVector = Icons.Rounded.Circle, contentDescription = null,
                    modifier = Modifier.padding(5.dp), tint = Color(0xFF1098FF)
                )
            }
        }

        Surface(modifier = Modifier
            .size(25.dp)
            .border(
                2.dp, Color(0xFF1098FF),
                shape = CircleShape
            ).clip(CircleShape).clickable { selected = !selected },
            color = Color.Transparent, shape = CircleShape){

            if (selected){
                Icon(imageVector = Icons.Rounded.Check, contentDescription = null,
                    modifier = Modifier.padding(2.dp), tint = Color(0xFF1098FF)
                )
            }
        }
    }
}

