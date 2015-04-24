# App-analyzer

A program designed to find same apps in different stores: Google Play, Apple Store, WindowsPhone Score

Input example:

	https://itunes.apple.com/en/app/skype­for­iphone/id304878510?mt=8
	https://itunes.apple.com/en/app/skype­for­ipad/id442012681?mt=8
	https://play.google.com/store/apps/details?id=com.skype.raider&hl=en
	http://www.windowsphone.com/en­us/store/app/skype/c3f8e570­68b3­4d6a­bdbb­c0a3f4360a51
	https://play.google.com/store/apps/details?id=com.skype.android.access&hl=en
	https://itunes.apple.com/en/app/skype­wifi/id444529922?mt=8
	https://play.google.com/store/apps/details?id=com.skype.android.qik&hl=en
	https://itunes.apple.com/us/app/skype­qik­group­video­messaging/id893994044?mt=8
	https://play.google.com/store/apps/details?id=com.viber.voip&hl=en
	https://itunes.apple.com/en/app/viber/id382617920?mt=8
	https://play.google.com/store/apps/details?id=com.viber.voip&hl=en
	https://play.google.com/store/apps/details?id=com.ketchapp.skyward&hl=en
	https://itunes.apple.com/us/app/skyward/id943273841?mt=8
	https://play.google.com/store/apps/details?id=cz.george.mecheche&hl=en

Output example:

	Skype
	https://itunes.apple.com/en/app/skype­for­iphone/id304878510?mt=8
	https://itunes.apple.com/en/app/skype­for­ipad/id442012681?mt=8
	https://play.google.com/store/apps/details?id=com.skype.raider&hl=en
	http://www.windowsphone.com/en­us/store/app/skype/c3f8e570­68b3­4d6a­bdbb­c0a3f4360a51

	Skype WiFi
	https://play.google.com/store/apps/details?id=com.skype.android.access&hl=en
	https://itunes.apple.com/en/app/skype­wifi/id444529922?mt=8

	Skype Qik: Group Video Chat
	https://play.google.com/store/apps/details?id=com.skype.android.qik&hl=en
	https://itunes.apple.com/us/app/skype­qik­group­video­messaging/id893994044?mt=8

	Viber
	https://play.google.com/store/apps/details?id=com.viber.voip&hl=en
	https://itunes.apple.com/en/app/viber/id382617920?mt=8
	https://play.google.com/store/apps/details?id=com.viber.voip&hl=en

	Skyward
	https://play.google.com/store/apps/details?id=com.ketchapp.skyward&hl=en
	https://itunes.apple.com/us/app/skyward/id943273841?mt=8	

	Skyward
	https://play.google.com/store/apps/details?id=cz.george.mecheche&hl=en


## Usage

You need Leiningen to build project.

For start run jar file from target folder:
java -jar app-0.1.0-SNAPSHOT-standalone.jar filename

Assigned file must consist list of apps links.

## Description

Two apps are the same, if two of three conditions are satisfied
	apps name equality
	apps authors equality (with using Levenshtein distance)
	apps descriptions equality (with using Levenshtein distance)

To adding app to the group of same apps, program comapare a field with all fields with same name in apps group.

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
