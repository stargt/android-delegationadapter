# DelegationAdapter

Super easy way to create and maintain the painful Android RecyclerView Adapter with various ItemView types. This library is backed by the great [https://github.com/sockeqwe/AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates).

## Java Sample

```groovy
class AnimalAdapter extends ListItemDelegationAdapter<DisplayableItem> {

AnimalAdapter() {

  addDelegate(createDelegateBuilder(Advertisement.class)
    .layout(R.layout.item_advertisement)
    .build());

  addDelegate(createDelegateBuilder(Cat.class)
    .layout(R.layout.item_cat)
    .binder((vh, cat) -> {
      vh.getView(R.id.name, TextView.class).setText(cat.getName());
    })
    .build());

  addDelegate(createDelegateBuilder(Dog.class)
    .layout(R.layout.item_dog)
    .binder((vh, dog) -> {
      vh.getView(R.id.name, TextView.class).setText(dog.getName());
    })
    .build());

  setFallbackDelegate(createDelegateBuilder(DisplayableItem.class)
    .layout(android.R.layout.simple_list_item_1)
    .binder((vh, item) -> {
      vh.getView(android.R.id.text1, TextView.class).setText(item.toString());
    })
    .build());
  }
}

RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
AnimalAdapter adapter = new AnimalAdapter();
adapter.setItems(getAnimals());
recyclerView.setAdapter(adapter);

```

## Kotlin Sample
No Kotlin specific build provided currently but it can be used liked this:
```groovy
val animalAdapter = ListItemDelegationAdapter<DisplayableItem>().apply {

  addDelegate(createDelegateBuilder(Advertisement::class.java)
    .layout(R.layout.item_advertisement)
    .build())

  addDelegate(createDelegateBuilder(Cat::class.java)
    .layout(R.layout.item_cat)
    .binder { vh, cat, payloads -> vh.getView(R.id.name, TextView::class.java).text = cat.name }
    .build())

  addDelegate(createDelegateBuilder(Dog::class.java)
    .layout(R.layout.item_dog)
    .binder { vh, dog -> vh.getView(R.id.name, TextView::class.java).text = dog.name }
    .build())
    
  setFallbackDelegate(createDelegateBuilder(DisplayableItem::class.java)
    .layout(android.R.layout.simple_list_item_1)
    .binder { vh, item, payloads -> vh.getView(android.R.id.text1, TextView::class.java).text = item.toString() }
    .build())
}

recyclerView.apply {
  layoutManager = LinearLayoutManager(context)
  adapter = animalAdapter
}

```

## Dependencies
[![](https://jitpack.io/v/toss/android-delegationadapter.svg)](https://jitpack.io/#toss/android-delegationadapter)

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

```groovy
dependencies {
  compile 'com.github.toss:android-delegationadapter:1.0.1'
```

## License

```
Copyright 2017 Viva Republica, Inc.

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
