1. webView加载html乱码，需要用baseUrl
2. 一定要webView.setWebChromeClient()，js：alert才有效
3. webView.loadData()方法不能加载js代码，将会当作html字符串
4. 如果loadUrl中的url以javascript:开头就会执行js代码，而不会执行到onPageFinish
     否则每load一次就会回掉一次onPageFinish