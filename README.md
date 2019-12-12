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
	        implementation 'com.github.TanveerTaj:AwesomeImageDialog:0.1.0'
	}

### Demo Code
      
        button.setOnClickListener {
            PicDialog.Builder(this)
                .setOutSideDismiss(true)
        // for setting uri - setUri("URI")
                .setUri("URI")
        // for setting url - setUrl("URL")               
                .setUrl("URL")
	// for setting url - setFile(file)              
                .setFile(file)
                .build()
                .show()
        }
        
