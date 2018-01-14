# Feedback
<h4> App FeedBack , User can easily gives us feedback through google form. This libray collect data from google form.Developer can
download data or can give feedback answer through mail. Also can download .cvs file</h4>


<p align="center">
  <img src="https://raw.githubusercontent.com/paveltech/Feedback/master/device-2018-01-15-002425.png" width="350"/>
</p>

Add this dependency in your `build.gradle`: 

```groovy
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
		}
	}
```

```xml
dependencies {
    compile 'com.github.paveltech:Feedback:1.0.1'
}
```

### Configuration : Now have to configure java code.

# You can use it as a normal activity or a Fragment 
 ### For Activity 
 
 ```java
 Intent intent = new Intent(YourActivity.this , Feedback.class);
       
        startActivity(intent);
```

### For Fragment 

```java

   FeedBackFragment feedbackFragment = new FeedBackFragment();
        
```	

#Inspired By : https://github.com/sadiqrazasyed/SpreadsheetInput_Feedback_Tut

