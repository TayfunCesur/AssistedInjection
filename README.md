# Assisted Injection
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Have your ever need pass to some parameter into your Viewmodel? But wait, your viewmodel have already some injections? So how can you do this ? 

***Behold Assisted Injection!***

<img height="500" align="right" src="https://firebasestorage.googleapis.com/v0/b/events-c4167.appspot.com/o/Assisted.gif?alt=media&token=fcfbbaeb-6e00-42c7-9d91-23c62aaa17b4"></img>

### Main use case of Assisted Injection
Here is the main usage. As you can see on the right preview, we have two view model and second one needs some id of previous selected data. But the second viewmodel has already constructor with some injections like below.

```
class MainDetailViewModel @Inject constructor(  
    private val service: Service  
) : ViewModel() {
	//Some magic works here
} 
``` 

So how can we do this? Because if we pass the parameter in constructor of viewmodel, the constructor injection magic will be pointless and force us to make much boilerplate code.

## Assisted Inject Constructor
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; AssistedInject annotations helps us to pass some additional parameters with already constructor injections we did before. Here is the usage,

```
class MainDetailViewModel @AssistedInject constructor(  
    private val service: Service,  
    @Assisted val civilizationId: Int  //Magic here :)
) : ViewModel() {  
  
    @AssistedInject.Factory  
    interface Factory {  
        fun create(civilizationId: Int): MainDetailViewModel  
    }
    // Some magic works here
}
``` 
 
## Providing the factory
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; If we will use this parameter that we passed via @Assisted, we must provide this as usual. Here is the sample usage,
``` 
//Our id comes from intent here

private val mainDetailViewModel by viewModel(this) {  
  injector.mainDetailViewModelFactory.create(
	  intent.getIntExtra(getString(R.string.selected_civilization), -1))  
}
``` 
# Keynotes

### Usage in Multi-module project
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; As far as I tried, in multi-module project, for example you have some presentation layer that contains your Viewmodels, because of your ***@AssistedInject.Factory***  is generated in your Presentation module and you want to use in UI module which is your main layer, Dagger2 can not access that factory class which is generated in other module. I still search the solution. If you know the problem or you have any questions, hit me on [Twitter](https://twitter.com/CesurTayfun35)

 # Core Libraries
 - [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/)
 - [Dagger2](https://google.github.io/dagger/)
 - [Dagger Assisted Inject](https://github.com/square/AssistedInject)
 - [Rx2](https://github.com/ReactiveX/RxJava)
 - [Retrofit2](http://square.github.io/retrofit/)
 

# Summary
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; If you have Viewmodels that have already use constructor injection and you need to pass additional parameter through them, ***[Assisted Injection](https://github.com/square/AssistedInject)*** is all you ever need. Please have a look! If you have any question why we need to pass the parameter via constructor, please try to rotate your phone and you will see you make network request again. ***Because you should make your request in your ViewModel's init function.***

### Greetings
If you have any questions, hit me on [Twitter](https://twitter.com/CesurTayfun35)

## Licence
```
Copyright 2019 Tayfun CESUR

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
