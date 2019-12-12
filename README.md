# AwesomeImageDialog

[![ezgif-com-video-to-gif-1.gif](https://i.postimg.cc/VNqqL7m3/ezgif-com-video-to-gif-1.gif)](https://postimg.cc/3yw4tFyB)

### Gradle
Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.TanveerTaj:AwesomeImageDialog:Tag'
	}

### Demo Code
      
        button.setOnClickListener {
            PicDialog.Builder(this)
                .setOutSideDismiss(true)
        // for setting uri
                .setUri(Uri.parse("URI"))
        // for setting url               
                .setUrl("URL")
                .build()
                .show()
        }
        
