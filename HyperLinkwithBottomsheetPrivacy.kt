class HyperLinkwithBottomsheetPrivacy : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestProjectTheme {
                Greeting4()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting4() {

    val modalBottomSheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        HyperlinkText(fullText = "By using our app, you are agreeing to our Terms of Service and Privacy Policy",
            linkText = listOf("Terms of Service","Privacy Policy"),
            hyperlinks = listOf("https://policies.google.com/terms?gl=US&hl=en","https://policies.google.com/privacy?gl=US&hl=en"),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally), onClickUri = {showSheet = true})


        if (showSheet){
            ModalBottomSheet(onDismissRequest = { showSheet = false },
                sheetState = modalBottomSheetState,
                dragHandle = { BottomSheetDefaults.DragHandle() }, containerColor = Color.White
            ) {

                WebViewCompose(url = "https://akardas16.github.io/.well-known/", modifier = Modifier
                    .fillMaxHeight(0.64f)
                    .fillMaxWidth(),
                    onCreated = {it.settings.defaultFontSize = 14})


            }
        }

       


    }
}

private class MyWebView(context: Context) : WebView(context) {
    val verticalScrollRange: Int get() = computeVerticalScrollRange() - height
}

@Composable
fun WebViewCompose(url: String, modifier: Modifier = Modifier, onCreated: (WebView) -> Unit = {}) {
    val context = LocalContext.current
    val client = object : WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            Log.i("sdfsdfsdfsd", "onPageStarted: ")
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            view?.visibility = View.VISIBLE
            Log.i("sdfsdfsdfsd", "onPageFinished: ")
        }
    }
    val webView: MyWebView = remember(context) {
        MyWebView(context).also {
            it.webViewClient = client
            it.visibility = View.INVISIBLE
        }.also(onCreated)
    }
    DisposableEffect(webView) {
        onDispose {
            webView.stopLoading()
            webView.destroy()
        }
    }
    val scrollabeState = rememberScrollableState { delta ->
        val scrollY = webView.scrollY
        val consume = (scrollY - delta).coerceIn(0f, webView.verticalScrollRange.toFloat())
        webView.scrollTo(0, consume.roundToInt())
        (scrollY - webView.scrollY).toFloat()
    }
    AndroidView(
        factory = { webView },
        modifier = modifier
            .scrollable(scrollabeState, Orientation.Vertical)
    ) { webView2 ->
        webView2.loadUrl(url)

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview7() {
    TestProjectTheme {
        Greeting4()
    }
}



@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkText: List<String>,
    linkTextColor: Color = Color.Blue,
    linkTextFontWeight: FontWeight = FontWeight.Medium,
    linkTextDecoration: TextDecoration = TextDecoration.Underline,
    hyperlinks: List<String> = listOf("https://stevdza-san.com"),
    fontSize: TextUnit = TextUnit.Unspecified,
    onClickUri:(uri:String) -> Unit
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)
        linkText.forEachIndexed { index, link ->
            val startIndex = fullText.indexOf(link)
            val endIndex = startIndex + link.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontWeight = linkTextFontWeight,
                    textDecoration = linkTextDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = hyperlinks[index],
                start = startIndex,
                end = endIndex
            )
        }
        addStyle(
            style = SpanStyle(
                fontSize = fontSize
            ),
            start = 0,
            end = fullText.length
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    //uriHandler.openUri(stringAnnotation.item)
                    onClickUri(stringAnnotation.item)
                }
        }, style = TextStyle(textAlign = TextAlign.Center)
    )
}

