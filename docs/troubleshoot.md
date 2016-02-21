# Common Troubleshooting with Android

In order to load apps onto your phone and connect to it from Android Studio,
you'll need to enable developer mode.

## Android devices older than 5.x don't show up in ADB:

Enable developer mode on Android devices older than 5.x

Go to Settings -> General -> About phone -> Software.
Scroll down to the Build Number, tap 7 times. Then developer options will be visible.

Go back up to
Settings -> General -> Developer options. Enable USB debugging.

Voila!

From here

http://www.askvg.com/tip-enable-hidden-secret-developer-options-menu-in-google-android-mobiles-phones-and-tablets/

In particular for LG phones, you will need to enable an additional option.

Settings -> Networks -> Tethering -> USB tethering.
When you switch it on, it will ask you if the RSA key from your connected computer is okay.
