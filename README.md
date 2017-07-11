# Bework-Sample
> Make you easy to create android application.
> Clean code & Structure on your project.

# Feature
- Sample Activity
- Redirect
- RecyclerView
- Secure SharedPreference
- Log Utils 
- Inject FontStyle
- Inject View ID
- Inject Parcelable
- Default Activity animation transition
- Sample Fragment
- Sample HttpClient (Comming soon)


### Dependencies used by This Sample
| Name | Type |
| ------ | ------ |
|compile com.github.opannapo:Bework:Beta-1.0 | Dependencies 






# How to use

### 1. Gradle
Compile Bework on your project
>Gradle (Top-level).

```gradle
...
buildscript {
    repositories {
        jcenter() 
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.1.2'
    }
}
allprojects {
    repositories {
        jcenter()
        maven { url 'https://jitpack.io'}
    }
}
...
```


>Gradle (On your Module:App)

```gradle
...
dependencies {
    compile 'com.github.opannapo:Bework:Beta-1.0'
} 
...
```

### 2. Application Class
Create Application Class,
then extend Bework BaseApp class.
```java
public class APP extends BaseApp {
...
}
```

Setting your SharedPreference Name 
```Java
@Override
protected String configPreferenceName() {
    return "PreferenceName";
}
```

Key to Encrypt your SharedPreference (16 Char)
```Java
@Override
protected String configPreferenceEncryptKey() {
    return "z1x2c3v4Q1W2E3R4";
}
```


Log Settings
```Java
@Override
protected boolean configLogEnable() {
    return true;
}

@Override
protected boolean configLogDetailLine() {
    return true;
}

@Override
protected boolean configLogCaller() {
    return true;
}

@Override
protected String configLogTag() {
    return "BeworkSample-LOG";
}
```

Full Application Class.
```java
public class APP extends BaseApp {
    public static boolean isDevelopment = true;

    public APP() {
        super("APP");
    }

    @Override
    protected String configPreferenceName() {
        return "Bework-Sample";
    }

    @Override
    protected String configPreferenceEncryptKey() {
        return "z1x2c3v4Q1W2E3R4";
    }

    @Override
    protected boolean configLogEnable() {
        return true;
    }

    @Override
    protected boolean configLogDetailLine() {
        return true;
    }

    @Override
    protected boolean configLogCaller() {
        return true;
    }

    @Override
    protected String configLogTag() {
        return "BeworkSample-LOG";
    }
}
```

### 3. Manifest
```xml
...
<application
        android:name=".APP" 
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name" 
        android:theme="@style/AppTheme">
        ...
```

### 4. Activity
Create Activity Class,
then extend Bework BaseActivity class.

Sample -> HomeActivity.java
```Java
public class HomeActivity extends BaseActivity implements HomeView.ViewImpl, View.OnClickListener {
    HomeWorker worker;
    HomeView view;

    @Override
    public BaseViewModel getViewModel() {
        return view;
    }

    @Override
    public BaseActivityControl getWorker() {
        return worker;
    }

    @Override
    public void onActivityCreate(Bundle bundle) {
        view = new HomeView(this, this, worker);
        worker = new HomeWorker(view);
        worker.requestUpdateData();

        view.btnSampleLog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSampleLog:
                redirect(LogActivity.class, ANIM_TYPE.RIGHT_TO_LEFT);
                break;
        }
    }

    @Override
    public void viewUpdateData(String title, String msg, String note) {
        view.tTitle.setText(title);
        view.tMessage.setText(msg);
        view.tNote.setText(note);
    }

    @Override
    protected void onBack() {
        finishWithAnim(ANIM_TYPE.TOP_TO_BOTTOM);
    }
}
```
 
 
### 5. View
Create Class,
then extend Bework BaseViewModel class.

Sample -> HomeView.java
```Java
@Root(R.layout.activity_main)
@FontDefault("Raleway-Regular.ttf")
@FontItalic("Infinity.ttf")
@FontBold("Raleway-Bold.ttf")
public class HomeView extends BaseViewModel {
    @Child(R.id.tTitle)
    public TextView tTitle;
    @Child(R.id.tMessage)
    public TextView tMessage;
    @Child(R.id.tNote)
    public TextView tNote;
    @Child(R.id.btnSampleLog)
    public Button btnSampleLog;


    public interface ViewImpl extends BaseViewImpl {
        void viewUpdateData(String title, String msg, String note);
    }

    public interface WorkerImpl extends BaseWorkerImpl {
        void requestUpdateData();
    }

    public HomeView(BaseActivity activity, BaseViewImpl viewImpl, WorkerImpl workerImpl) {
        super(activity, viewImpl, workerImpl);
    }
}
```
 
### 6. ActivityControl / Worker
Create Class,
then extend Bework BaseActivityControl class.

Sample -> HomeWorker.java
```Java
public class HomeWorker extends BaseActivityControl implements HomeView.WorkerImpl {
    HomeView.ViewImpl view;

    public HomeWorker(BaseViewModel viewModel) {
        super(viewModel);
        this.view = (HomeView.ViewImpl) getViewModel().getViewImpl();
    }

    @Override
    public void requestUpdateData() {
        view.viewUpdateData("This is Title", "This is Message", "This is Note");
    }
}
```
 