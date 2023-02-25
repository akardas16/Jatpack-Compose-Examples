AsyncImage(model = ImageRequest.Builder(LocalContext.current)
        .data("https://assets.thehansindia.com/h-upload/2019/11/24/238826-beautiful-scene.jpg")
        .crossfade(true)
        .build(),
        contentDescription = "scene", contentScale = ContentScale.FillBounds,
        placeholder = painterResource(id = R.drawable.google_icon),
        modifier = Modifier.clip(CircleShape).width(120.dp).height(120.dp))
