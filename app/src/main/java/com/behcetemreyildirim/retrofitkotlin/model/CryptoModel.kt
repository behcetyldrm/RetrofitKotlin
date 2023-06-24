package com.behcetemreyildirim.retrofitkotlin.model

data class CryptoModel(val currency: String, val price: String)

//data sınıfları içerinde fonksiyon gibi şeyler barındırmaya gerek duymayan sadece constructor isteyen sınıflardır.
//data sınıfını oluşturarak internetten alacağımız bilgileri bu sınıfa koyacağız
/*
kotlin data sınıfı ile alınan constructor'ın adı json verisinde gelecek değerin adı ile aynı olduğunda gelecek veri adını
belirtmemiz gerekmez. Javada @SerializedName ile gelecek verinin adını belirtmemiz gerekir
*/