(ns app.test)

;test data
(def links ["https://itunes.apple.com/en/app/skype-for-iphone/id304878510?mt=8"
                 "https://itunes.apple.com/en/app/skype-for-ipad/id442012681?mt=8"
                 "https://play.google.com/store/apps/details?id=com.skype.raider&hl=en"
                 "http://www.windowsphone.com/en-us/store/app/skype/c3f8e570-68b3-4d6a-bdbb-c0a3f4360a51"
                 "https://play.google.com/store/apps/details?id=com.skype.android.access&hl=en"
                 "https://itunes.apple.com/en/app/skype-wifi/id444529922?mt=8"
                 "https://play.google.com/store/apps/details?id=com.skype.android.qik&hl=en"
                 "https://itunes.apple.com/us/app/skype-qik-group-video-messaging/id893994044?mt=8"
                 "https://play.google.com/store/apps/details?id=com.viber.voip&hl=en"
                 "https://itunes.apple.com/en/app/viber/id382617920?mt=8"
                 "https://play.google.com/store/apps/details?id=com.viber.voip&hl=en"
                 "https://play.google.com/store/apps/details?id=com.ketchapp.skyward&hl=en"
                 "https://itunes.apple.com/us/app/skyward/id943273841?mt=8"
                 "https://play.google.com/store/apps/details?id=cz.george.mecheche&hl=en"])

(def apps [[{:host "itunes.apple.com", :link "https://itunes.apple.com/en/app/skype-for-iphone/id304878510?mt=8", :name "Skype for iPhone", :author "Skype Communications S.a.r.l", :desc "Say “hello” to the new Skype for iPhone."}] [{:host "itunes.apple.com", :link "https://itunes.apple.com/en/app/skype-for-ipad/id442012681?mt=8", :name "Skype for iPad", :author "Skype Communications S.a.r.l", :desc "Say “hello” to friends and family with an instant message, voice or video call on Skype for free. There’s so much you can do, right from the palm of your hand."}] [{:host "play.google.com", :link "https://play.google.com/store/apps/details?id=com.skype.raider&hl=en", :name "Skype - free IM & video calls", :author "Skype", :desc "Say “hello” to friends and family with an instant message, voice or video call on Skype for free. Join the millions of people using Skype today to stay in touch with the people who matter most. There’s so much you can do, right from the palm of your hand. "}] [{:host "windowsphone.com", :link "http://www.windowsphone.com/en-us/store/app/skype/c3f8e570-68b3-4d6a-bdbb-c0a3f4360a51", :name "Skype", :author "Skype", :desc "Make free Skype to Skype video and voice calls as well as send instant messages to friends and family around the world.What's new?- A new, compact design.- General improvements and fixesFeatures:- Call friends and family with free Skype to Skype voice calls over 3G* or WiFi and enjoy low cost calls to mobiles and landlines around the world. - Make free video calls to anyone else on Skype with high definition video on supported devices.- Send instant messages to friends on Skype, as well as friends on Messenger if you sign in with your Microsoft account.- Stay in touch even when the app is closed, with call and message notifications.- See all your Skype contacts in the People Hub.System requirements:- Skype for Windows Phone 8 requires a Windows Phone 8 device.- To ensure the best quality experience, Skype for Windows Phone requires a minimum of 512MB of memory.For the best Skype experience, we recommend using an unlimited data plan or a WiFi connection.Want to know more? Visit Skype for Windows Phone: http://skype.com/go/windowsphoneGot a question? Check Skype’s FAQ page: http://skype.com/go/help.faq.windowsphone* Operator data charges may apply.What's new?- A new, compact design.- General improvements and fixes."}] [{:host "play.google.com", :link "https://play.google.com/store/apps/details?id=com.skype.android.access&hl=en", :name "Skype WiFi", :author "Skype", :desc "With Skype WiFi you can get online at over 2 million public WiFi hotspots worldwide at the touch of a button."}] [{:host "itunes.apple.com", :link "https://itunes.apple.com/en/app/skype-wifi/id444529922?mt=8", :name "Skype WiFi", :author "Skype Communications S.a.r.l", :desc "With Skype WiFi you can get online at over 2 million public WiFi hotspots worldwide at the touch of a button."}] [{:host "play.google.com", :link "https://play.google.com/store/apps/details?id=com.skype.android.qik&hl=en", :name "Skype Qik: Group Video Chat", :author "Skype", :desc "Introducing Skype Qik (say “quick”), a free* video messenger made for capturing moments and sharing laughs throughout your day. Set up a group and immediately start shooting and swapping  videos. Send something to crack your friends up, or show off a new dance move—whatever you want. New messages are added to a chat that you can watch like a movie with a single tap.  Qik even lets you erase your video right out of the chat.**"}] [{:host "itunes.apple.com", :link "https://itunes.apple.com/us/app/skype-qik-group-video-messaging/id893994044?mt=8", :name "Skype Qik: Group Video Messaging", :author "Skype Communications S.a.r.l", :desc "Introducing Skype Qik (say “quick”), a free* video messenger made for capturing moments and sharing laughs throughout your day. Set up a group and immediately start shooting and swapping  videos. Send something to crack your friends up, or show off a new dance move—whatever you want. New messages are added to a chat that you can watch like a movie with a single tap.  Qik even lets you erase your video right out of the chat.**"}] [{:host "play.google.com", :link "https://play.google.com/store/apps/details?id=com.viber.voip&hl=en", :name "Viber", :author "Viber Media S.à r.l.", :desc "With Viber, everyone in the world can connect. Freely. More than 516b million Viber users text, make HD-quality phone and video calls, and send photo and video messages worldwide over Wifi or 3G - for free.* Viber Out can be used to make calls to non-Viber mobile and landline numbers at low rates. Viber is available for many smartphones and platforms.   "}] [{:host "itunes.apple.com", :link "https://itunes.apple.com/en/app/viber/id382617920?mt=8", :name "Viber", :author "Viber Media SARL.", :desc "With Viber, everyone in the world can connect. Freely. More than 516 million Viber users text, make HD-quality phone and video calls, and send photo and video messages worldwide over Wifi or 3G - for free.* Viber Out can be used to make calls to non-Viber mobile and landline numbers at low rates. Viber is available for many smartphones and platforms.   "}] [{:host "play.google.com", :link "https://play.google.com/store/apps/details?id=com.viber.voip&hl=en", :name "Viber", :author "Viber Media S.à r.l.", :desc "With Viber, everyone in the world can connect. Freely. More than 516b million Viber users text, make HD-quality phone and video calls, and send photo and video messages worldwide over Wifi or 3G - for free.* Viber Out can be used to make calls to non-Viber mobile and landline numbers at low rates. Viber is available for many smartphones and platforms.   "}] [{:host "play.google.com", :link "https://play.google.com/store/apps/details?id=com.ketchapp.skyward&hl=en", :name "Skyward", :author "Ketchapp", :desc "A great journey to the sky starts with a single step."}] [{:host "itunes.apple.com", :link "https://itunes.apple.com/us/app/skyward/id943273841?mt=8", :name "Skyward", :author "Ketchapp", :desc "A great journey to the sky starts with a single step."}] [{:host "play.google.com", :link "https://play.google.com/store/apps/details?id=cz.george.mecheche&hl=en", :name "Skyward", :author "Jiří Hlinka", :desc "Simple but challenging real-time strategy game for two players at one Android device."}]])