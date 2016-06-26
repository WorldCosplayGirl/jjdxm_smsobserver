
# [jjdxm_smsobserver][project] #
## Introduction ##
[apk下载][downapk]

### 这是一个用于拦截android实时短信的库,可以进行短信过滤,得到自己想要的内容,可以用于需要自动填写短信验证码的app项目 ###

## Features ##

### 用于监听当前接收到的短信信息 ###
### 过滤接收到的短信，得到自己想要的内容 ###

## Screenshots ##
 
## Download ##

[demo apk下载][downapk]

[下载最新版本jar][lastjar]

Download or grab via Maven:

	<dependency>
	  <groupId>com.dou361.smsobserver</groupId>
	  <artifactId>jjdxm-smsobserver</artifactId>
	  <version>x.x.x</version>
	</dependency>

or Gradle:

	compile 'com.dou361.smsobserver:jjdxm-smsobserver:x.x.x'


jjdxm-smsobserver requires at minimum Java 15 or Android 4.0.

## Get Started ##

### 所需权限 ###

	<uses-permission android:name="android.permission.RECEIVE_SMS" />
    <uses-permission android:name="android.permission.READ_SMS" />

### 初始化 ###

		/***
         * 构造器
         * @param context
         * @param callback 短信接收器
         * @param smsFilter 短信过滤器
         */
        smsObserver = new SmsObserver(this, this, new VerificationCodeSmsFilter("10690"));
        smsObserver.registerSMSObserver();
        Dexter.checkPermission(new CompositePermissionListener(), Manifest.permission.READ_SMS);

### 监听回调和销毁 ###

	@Override
    public void onCallbackSmsContent(String code) {
        et_code.setText(code);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        smsObserver.unregisterSMSObserver();
    }


## More Actions ##

## ChangeLog ##

## About Author ##

#### 个人网站:[http://www.dou361.com][web] ####
#### GitHub:[jjdxmashl][github] ####
#### QQ:316988670 ####
#### 交流QQ群:548545202 ####


## License ##

    Copyright (C) dou361, The Framework Open Source Project
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
     	http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

## (Frequently Asked Questions)FAQ ##
## Bugs Report and Help ##

If you find any bug when using project, please report [here][issues]. Thanks for helping us building a better one.




[web]:http://www.dou361.com
[github]:https://github.com/jjdxmashl/
[project]:https://github.com/jjdxmashl/jjdxm_smsobserver/
[issues]:https://github.com/jjdxmashl/jjdxm_smsobserver/issues/new
[downapk]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_smsobserver/master/apk/app-debug.apk
[lastaar]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_smsobserver/master/release/jjdxm-smsobserver-1.0.0.aar
[lastjar]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_smsobserver/master/release/jjdxm-smsobserver-1.0.0.jar
[icon01]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_smsobserver/master/screenshots/icon01.png
[icon02]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_smsobserver/master/screenshots/icon02.png