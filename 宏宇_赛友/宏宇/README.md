# CompetitionCommunity
An Android application of competition community for college student.

### project structure
``` bash
NEUQerCC
├── NEUQerCCApplication.java
├── account
│   ├── activities
│   │   └── LoginActivity.java
│   └── models
│       └── LoginRequest.java
├── base
│   ├── activities
│   │   ├── BaseActivity.java
│   │   ├── NoBarActivity.java
│   │   └── ToolBarActivity.java
│   ├── adapters
│   │   └── BaseRecyclerViewAdapter.java
│   ├── fragments
│   │   └── BaseFragment.java
│   └── models
│       └── BaseModel.java
├── common
│   ├── Config.java
│   ├── constants
│   │   └── CacheKey.java
│   └── exceptions
│       ├── LogicErrorException.java
│       └── ResultException.java
├── home
│   └── HomeFragment.java
├── main
│   └── MainActivity.java
├── message
│   └── MessageFragment.java
├── mine
│   └── MineFragment.java
├── network
│   ├── APICode.java
│   ├── APIResponse.java
│   ├── APIService.java
│   ├── DataCallback.java
│   ├── GlobalAPIErrorHandler.java
│   ├── RestClient.java
│   └── converter
│       ├── GsonRequestBodyConverter.java
│       ├── GsonResponseBodyConverter.java
│       └── ResponseConverterFactory.java
├── team
│   └── TeamFragment.java
├── utils
│   ├── CacheUtil.java
│   ├── TimeUtil.java
│   ├── ToastUtil.java
│   └── Utility.java
└── widgets
```
